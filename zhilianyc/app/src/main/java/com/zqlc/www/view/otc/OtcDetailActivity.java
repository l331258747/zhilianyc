package com.zqlc.www.view.otc;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.otc.OtcDetailBean;
import com.zqlc.www.mvp.my.FeeContract;
import com.zqlc.www.mvp.my.FeePresenter;
import com.zqlc.www.mvp.otc.OtcDetailContract;
import com.zqlc.www.mvp.otc.OtcDetailPresenter;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.accessory.ImageUtils;
import com.zqlc.www.util.dialog.LoadingDialog;
import com.zqlc.www.util.glide.GlideUtil;
import com.zqlc.www.util.photo.TackPicturesUtil;
import com.zqlc.www.util.popupwindow.PopOtcBuySubmit;
import com.zqlc.www.util.popupwindow.PopOtcSellSubmit;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.UpLoadPhotos;
import com.zqlc.www.util.thread.MyThreadPool;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class OtcDetailActivity extends BaseActivity implements OtcDetailContract.View, FeeContract.View {

    int orderType;
    String beansSendId;
    OtcDetailPresenter mPresenter;
    FeePresenter mPresenterFee;

    TextView tv_status, tv_count_down, tv_No, tv_seller, tv_buyer, tv_unit_price, tv_number, tv_total_price, tv_order_time, tv_collection_code, tv_voucher;
    TextView btn_1, btn_2, btn_text,tv_copy;

    boolean isCheck;

    View view_pop;

    ImageView iv_camera;

    private PopOtcBuySubmit mPopOtcBuySubmit;
    private PopOtcSellSubmit mPopOtcSellSubmit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_detail;
    }

    @Override
    public void initView() {
        orderType = intent.getIntExtra("orderType", 0);
        beansSendId = intent.getStringExtra("beansSendId");
        if (orderType == 0) {
            showLeftAndTitle("求购单详情");
        } else {
            showLeftAndTitle("转让单详情");
        }


        view_pop = $(R.id.view_pop);
        btn_text = $(R.id.btn_text);
        btn_1 = $(R.id.btn_1);
        btn_2 = $(R.id.btn_2);
        iv_camera = $(R.id.iv_camera);
        tv_copy = $(R.id.tv_copy);

        tv_copy.setOnClickListener(v -> {
            ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            copy.setText(MySelfInfo.getInstance().getInviteCode());
            showShortToast("卖方收款号复制成功");
        });

        tv_status = $(R.id.tv_status);
        tv_count_down = $(R.id.tv_count_down);
        tv_No = $(R.id.tv_No);
        tv_seller = $(R.id.tv_seller);
        tv_buyer = $(R.id.tv_buyer);
        tv_unit_price = $(R.id.tv_unit_price);
        tv_number = $(R.id.tv_number);
        tv_total_price = $(R.id.tv_total_price);
        tv_order_time = $(R.id.tv_order_time);
        tv_collection_code = $(R.id.tv_collection_code);
        tv_voucher = $(R.id.tv_voucher);


        iv_camera.setVisibility(View.GONE);
        tv_voucher.setVisibility(View.GONE);

        getPicPermission(context);

    }

    @Override
    public void initData() {
        tackPicUtil = new TackPicturesUtil(activity);
        loadingDialog = new LoadingDialog(context);

        mPresenter = new OtcDetailPresenter(context, this);
        mPresenter.getOtcDetail(MySelfInfo.getInstance().getUserId(), beansSendId);

        mPresenter.getOtcCheck(MySelfInfo.getInstance().getUserId(), beansSendId);

        mPresenterFee = new FeePresenter(context,this);

    }

    OtcDetailBean data;
    @Override
    public void getOtcDetailSuccess(OtcDetailBean data) {
        this.data = data;
        if (disposableDown != null && !disposableDown.isDisposed())
            disposableDown.dispose();

        if ((data.getSendStatus() == 3 || data.getSendStatus() == 4) && data.getCountDownTime() > 0) {
            tv_count_down.setVisibility(View.VISIBLE);
            verifyEvent(data.getCountDownTime(), "自动取消");
        } else {
            tv_count_down.setVisibility(View.GONE);
        }

        tv_status.setText(data.getSendStatusStr());
        tv_No.setText(data.getId());
        tv_seller.setText(data.getSendName());
        tv_buyer.setText(data.getReceiveName());
        tv_unit_price.setText(StringUtils.getStringNum(data.getUnitPrice()));
        tv_number.setText(data.getCount() + "");
        tv_total_price.setText(StringUtils.getStringNum(data.getTotalPrice()));
        tv_order_time.setText(data.getCreateTime());
        tv_collection_code.setText(data.getSendAccount());

        if(!TextUtils.isEmpty(data.getSendAccount())){
            tv_copy.setVisibility(View.VISIBLE);
        }else{
            tv_copy.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(data.getPayImgUrl())) {
            iv_camera.setVisibility(View.GONE);
            tv_voucher.setVisibility(View.VISIBLE);
            tv_voucher.setText("没有上传付款凭证");
        } else {
            iv_camera.setVisibility(View.VISIBLE);
            tv_voucher.setVisibility(View.GONE);
            GlideUtil.loadImage(context, data.getPayImgUrl(), iv_camera);

            iv_camera.setOnClickListener(v -> {
                intent = new Intent(context, BigImgActivity.class);
                intent.putExtra("imgUrl", data.getPayImgUrl());
                startActivity(intent);
            });
        }

        btn_1.setVisibility(View.GONE);
        btn_2.setVisibility(View.GONE);
        btn_text.setVisibility(View.GONE);
        btn_1.setOnClickListener(null);
        btn_2.setOnClickListener(null);

        int sendStatus = data.getSendStatus();
        String receiveId = data.getReceiveId();
        String sendId = data.getSendId();

        //撤回订单 9
        //上传凭证 7
        //确认提交 4
        //确认放豆 5
        if (orderType == 0) {
            //求购单
            //uid 和 receiveId 相同
            if (TextUtils.equals(receiveId, MySelfInfo.getInstance().getUserId())) {//买方
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(9);
                    });
                } else if (sendStatus == 2) {
                    btn_text.setVisibility(View.VISIBLE);
                    btn_text.setText("等待对方放豆");
                } else if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        sendVoucher();
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(4);
                    });
                }
            } else {//卖方
                if (sendStatus == 1) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要转让");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        mPresenterFee.feeRatio(MySelfInfo.getInstance().getUserId());

                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(5);
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        intent  = new Intent(context,OtcFeedbackActivity.class);
                        intent.putExtra("beansSendId",beansSendId);
                        startActivity(intent);
                    });
                }
            }
        } else if (orderType == 1) {
            //转让单
            if (TextUtils.equals(sendId, MySelfInfo.getInstance().getUserId())) {//买方
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("撤回订单");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(9);
                    });
                } else if (sendStatus == 4) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("确认放豆");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(5);
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("我要申诉");
                    btn_2.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        intent  = new Intent(context,OtcFeedbackActivity.class);
                        intent.putExtra("beansSendId",beansSendId);
                        startActivity(intent);
                    });
                }
            } else {//卖方
                if (sendStatus == 3) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("我要求购");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        mPopOtcBuySubmit = new PopOtcBuySubmit(activity, view_pop,data);
                        mPopOtcBuySubmit.setOnItemClickListener((pwd, vCode) -> {
                            getOtcHandleSubmit(pwd,vCode);
                        });
                        mPopOtcBuySubmit.showPopupWindow(view_pop);
                    });
                } else if (sendStatus == 2
                        && TextUtils.equals(data.getLockUid(), MySelfInfo.getInstance().getUserId())
                        && TextUtils.equals(data.getReceiveId(), MySelfInfo.getInstance().getUserId())) {
                    btn_1.setVisibility(View.VISIBLE);
                    btn_1.setText("上传凭证");
                    btn_1.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        sendVoucher();
                    });
                    btn_2.setVisibility(View.VISIBLE);
                    btn_2.setText("确认提交");
                    btn_2.setOnClickListener(v -> {
                        if (!isCheck()) return;
                        getOtcHandle(4);

                    });
                }
            }
        }
    }

    //上传凭证 TODO
    private void sendVoucher() {
        tackPicUtil.showDialog(context);
    }

    //otc sendStatus 0已成交1订单已发起2订单已锁定卖方已放豆4买方已付款5卖方确认7卖方申诉中9用户撤回10系统撤回11系统解除申诉
    public void getOtcHandle(int sendStatus) {
        mPresenter.getOtcHandle(MySelfInfo.getInstance().getUserId(), sendStatus, beansSendId);
    }

    public void getOtcHandleSubmit(String pwd,String vCode) {
        mPresenter.getOtcHandleSubmit(MySelfInfo.getInstance().getUserId(), 2, beansSendId,pwd,vCode);
    }

    public boolean isCheck() {
        if (!isCheck)
            showShortToast("该订单被占用！");
        return isCheck;
    }

    @Override
    public void getOtcDetailFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void getOtcVoucherSuccess(EmptyModel data) {
        loadingDialog.dismiss();
        //支付凭证
        mPresenter.getOtcDetail(MySelfInfo.getInstance().getUserId(), beansSendId);
    }

    @Override
    public void getOtcVoucherFailed(String msg) {
        loadingDialog.dismiss();
        showShortToast(msg);
    }

    @Override
    public void getOtcCheckSuccess(EmptyModel data) {
        //校验是否占用
        isCheck = true;
    }

    @Override
    public void getOtcCheckFailed(String msg) {
        showShortToast(msg);
        isCheck = false;
    }

    @Override
    public void getOtcHandleSuccess(EmptyModel data) {
        //处理
        mPresenter.getOtcDetail(MySelfInfo.getInstance().getUserId(),beansSendId);
    }

    @Override
    public void getOtcHandleFailed(String msg) {
        showShortToast(msg);
    }


    //图片
    private TackPicturesUtil tackPicUtil;
    private String headpath;// 头像地址
    private String headCompressPath;
    private Disposable disposable;
    private LoadingDialog loadingDialog;
    //-----------start 拍照-----------

    //拍照，存储权限
    public void getPicPermission(Context context) {
        tackPicUtil.checkPermission(context);
    }


    /**
     * 获取图片回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TackPicturesUtil.CHOOSE_PIC:
            case TackPicturesUtil.TACK_PIC:
            case TackPicturesUtil.CROP_PIC:
                String path = tackPicUtil.getPicture(requestCode, resultCode, data, false);
                if (path == null)
                    return;
                headpath = path;
                upFile();
                break;
            default:
                break;
        }
    }

    public void upFile() {
        disposable = RxBus2.getInstance().toObservable(UpLoadPhotos.class, upLoadPhotos -> {
            sendHead();
            disposable.dispose();
        });

        loadingDialog.showDialog("上传图片...");
        loadingDialog.setCancelable(false);
        compressImage();
    }

    public void sendHead() {
        //构建要上传的文件
        File file = new File(headCompressPath);
        mPresenter.getOtcVoucher(MySelfInfo.getInstance().getUserId(), beansSendId, file);
    }

    private void compressImage() {
        MyThreadPool.getInstance().submit(() -> {
            File file = new File(headpath);
            String savePath = TackPicturesUtil.IMAGE_CACHE_PATH + File.separator + "crop" + File.separator + file.getName();
            ImageUtils.getImage(headpath, savePath);
            headCompressPath = savePath;
            RxBus2.getInstance().post(new UpLoadPhotos());
        });
    }

    //----------------end 拍照


    Disposable disposableDown;

    public void verifyEvent(int count, String str) {
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposableDown = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        tv_count_down.setText("剩" + StringUtils.getHour(num) + str);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        mPresenter.getOtcDetail(MySelfInfo.getInstance().getUserId(), beansSendId);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposableDown != null && !disposableDown.isDisposed())
            disposableDown.dispose();
    }


    @Override
    public void feeRatioSuccess(Float fee) {
        mPopOtcSellSubmit = new PopOtcSellSubmit(activity, view_pop,data,fee);
        mPopOtcSellSubmit.setOnItemClickListener((pwd, vCode) -> {
            getOtcHandleSubmit(pwd,vCode);
        });
        mPopOtcSellSubmit.showPopupWindow(view_pop);
    }

    @Override
    public void feeRatioFailed(String msg) {

    }
}
