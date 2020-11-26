package com.zqlc.www.view.login;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.login.LoginBean;
import com.zqlc.www.dialog.VerifyDialog;
import com.zqlc.www.mvp.login.LoginContract;
import com.zqlc.www.mvp.login.LoginPresenter;
import com.zqlc.www.mvp.my.SendCodeContract;
import com.zqlc.www.mvp.my.SendCodePresenter;
import com.zqlc.www.util.AppUtils;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.StatusBarUtil;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.SetLoginMobileEvent;
import com.zqlc.www.view.home.HomeActivity;
import com.zqlc.www.view.web.WebTextActivity;

import java.util.concurrent.TimeUnit;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View, SendCodeContract.View {

    TextView tv_title, tv_register, tv_login, btn_login, btn_forget,tv_agreement;
    LinearLayout tab_login, tab_register;
    View line_register, line_login;
    EditText et_phone, et_password;

    ConstraintLayout cl_register, cl_login;

    EditText et_phone_rgt, et_verify_rgt, et_password_rgt, et_password_rgt2, et_invite;
    TextView tv_verify_code_rgt, btn_register;
    ImageView iv_check;

    boolean isCheck;

    LoginPresenter mPresenter;

    Disposable setMobileDisposable;

    SendCodePresenter mPresenterCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.color_1C81E9));

        hideTitleLayout();

        tv_title = $(R.id.tv_title);
        cl_register = $(R.id.cl_register);
        cl_login = $(R.id.cl_login);

        //注册
        et_phone_rgt = $(R.id.et_phone_rgt);
        et_verify_rgt = $(R.id.et_verify_rgt);
        et_password_rgt = $(R.id.et_password_rgt);
        et_password_rgt2 = $(R.id.et_password_rgt2);
        et_invite = $(R.id.et_invite);
        tv_verify_code_rgt = $(R.id.tv_verify_code_rgt);
        btn_register = $(R.id.btn_register);
        iv_check = $(R.id.iv_check);
        tv_agreement = $(R.id.tv_agreement);

        tv_verify_code_rgt.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_agreement.setOnClickListener(this);

        //登录
        line_login = $(R.id.line_login);
        line_register = $(R.id.line_register);
        tab_login = $(R.id.tab_login);
        tab_register = $(R.id.tab_register);
        tv_register = $(R.id.tv_register);
        tv_login = $(R.id.tv_login);

        et_password = $(R.id.et_password);
        et_phone = $(R.id.et_phone);

        btn_login = $(R.id.btn_login);
        btn_forget = $(R.id.btn_forget);

        tab_login.setOnClickListener(this);
        tab_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_forget.setOnClickListener(this);

        testLogin();

    }

    public void testLogin() {
        et_phone.setText("18565390456");
        et_password.setText("123456");
    }

    @Override
    public void initData() {
        tv_title.setText(String.format("智链云仓(%1$s)", AppUtils.getVersionName()));

        mPresenter = new LoginPresenter(context, this);
        mPresenterCode = new SendCodePresenter(context,this);

        setMobileDisposable = RxBus2.getInstance().toObservable(SetLoginMobileEvent.class, setLoginMobileEvent -> {
            et_phone.setText(setLoginMobileEvent.getMobile());
            et_password.setText("");
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_login:
                setTab(true);
                break;
            case R.id.tab_register:
                setTab(false);
                break;
            case R.id.btn_login:
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;

                mPresenter.login(et_phone.getText().toString(), et_password.getText().toString());

                break;
            case R.id.btn_forget:
                intent = new Intent(context,ForgetActivity.class);
                intent.putExtra("mobile",et_phone.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv_verify_code_rgt:
                if (!LoginUtil.verifyPhone(et_phone_rgt.getText().toString()))
                    return;
                new VerifyDialog(context).setSubmitListener(() -> {
                    verifyEvent();
                    mPresenterCode.sendCode(et_phone_rgt.getText().toString());
                }).show();
                break;
            case R.id.btn_register:
                if (!LoginUtil.verifyPhone(et_phone_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyVerify(et_verify_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password_rgt2.getText().toString()))
                    return;
                if (!LoginUtil.verifyPasswordDouble(et_password_rgt.getText().toString(), et_password_rgt2.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(et_invite.getText().toString(), "请输入邀请码"))
                    return;
                if(!isCheck){
                    showShortToast("请勾选隐私协议");
                    return;
                }

                mPresenter.register(et_phone_rgt.getText().toString()
                        , et_verify_rgt.getText().toString()
                        , et_password_rgt.getText().toString()
                        , et_invite.getText().toString());
                break;
            case R.id.iv_check:
                isCheck = !isCheck;
                iv_check.setImageResource(isCheck ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
                break;
            case R.id.tv_agreement:
                intent = new Intent(context, WebTextActivity.class);
                intent.putExtra("web_text", getAgreement());
                startActivity(intent);
                break;

        }
    }

    public void setTab(boolean isLogin) {
        tv_login.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_login.setVisibility(View.INVISIBLE);
        tv_register.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_register.setVisibility(View.INVISIBLE);
        cl_login.setVisibility(View.GONE);
        cl_register.setVisibility(View.GONE);

        if (isLogin) {
            tv_login.setTextColor(ContextCompat.getColor(context, R.color.color_text));
            tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_login.setVisibility(View.VISIBLE);
            cl_login.setVisibility(View.VISIBLE);

        } else {
            tv_register.setTextColor(ContextCompat.getColor(context, R.color.color_text));
            tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_register.setVisibility(View.VISIBLE);
            cl_register.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loginSuccess(LoginBean data) {
        MySelfInfo.getInstance().setLoginData(data, et_phone.getText().toString());
        finish();
        startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public void loginFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void registerSuccess(EmptyModel data) {
        setTab(true);
        et_phone.setText(et_phone_rgt.getText().toString());

        isCheck = false;
        iv_check.setImageResource(isCheck ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
        et_phone_rgt.setText("");
        et_verify_rgt.setText("");
        et_password_rgt.setText("");
        et_password_rgt2.setText("");
        et_invite.setText("");
    }

    @Override
    public void registerFailed(String msg) {
        showShortToast(msg);
    }


    Disposable disposable;

    private void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                    tv_verify_code_rgt.setEnabled(false);//在发送数据的时候设置为不能点击
                    tv_verify_code_rgt.setTextColor(ContextCompat.getColor(context, R.color.color_66));
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tv_verify_code_rgt, String.format(getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tv_verify_code_rgt.setEnabled(true);
                        tv_verify_code_rgt.setText("重新发送");
                        tv_verify_code_rgt.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        if (setMobileDisposable != null && !setMobileDisposable.isDisposed())
            setMobileDisposable.dispose();
    }

    private String getAgreement() {
        return "" +
                "</br>用户协议" +
                "</br>  欢迎您使用智趣链仓的服务！" +
                "</br>  为确保您正常使用智趣链仓的服务，您应当阅读并遵守《智趣链仓协议》（以下简称“本协议”）和《智趣链仓隐私政策》。请您务必审慎阅读、充分理解各条款内容，特别是免除或者限制智趣链仓责任的条款、对用户权利进行限制的条款、约定争议解决方式和司法管辖的条款（如第十八条相关约定）等，以及开通或使用其他涉及您重大权益的条款可能以加粗、加下划线等形式提示您重点注意。" +
                "</br>  除非您已充分阅读、完全理解并接受本协议所有条款，否则您无权使用智趣链仓服务。您点击“同意”或“下一步”，或您使用智趣链仓服务，或者以其他任何明示或者默示方式表示接受本协议，均视为您已阅读并同意签署本协议。本协议即在您与智趣链仓之间产生法律效力，成为对双方均具有约束力的法律文件。" +
                "</br>  如果您因年龄、智力等因素而不具有完全民事行为能力，请在法定监护人（以下简称“监护人”）的陪同下阅读和判断是否同意本协议、并特别注意未成年人使用条款。" +
                "</br>  如果您是中国大陆地区以外用户，您订立或履行本协议还需要同时遵守您所属和/或所处国家或地区的法律。" +
                "</br>  第一条【协议的范围】" +
                "</br>  1.1本协议时用户与智趣链仓之间关于用户享受智趣链仓的服务所订立的协议。“智趣链仓”是指智趣链仓和/或其相关服务可能存在的运营关联单位。“用户”是指智趣链仓的服务的使用人，在本协议中更多地称为“您”。" +
                "</br>  1.2智趣链仓的服务是指智趣链仓向用户提供的，包括但不限于即时通讯、网络媒体、互联网增值、互动娱乐、电子商务、广告等产品及服务，具体服务以智趣链仓实际提供的为准（以下简称“本服务”）。" +
                "</br>  1.3本协议内容同时包括《智趣链仓隐私政策》，且您在使用智趣链仓某一特定服务时，该服务可能会另有单独的协议、相关业务规则等（以下统称为“单独协议”）。上述内容一经正式发布，即为本协议不可分割的组成部分，您同样应当遵守。您对前述任何单独协议的接受，即视为您对本协议全部的接受。您对本协议的接受，即视为您对《智趣链仓隐私政策》的接受。" +
                "</br>  第二条【定义】" +
                "</br>  2.1智趣链仓APP：是指我们开发并按照本协议之约定授权用户下载、安装、使用的软件。" +
                "</br>  2.2智趣链仓账户（以下简称“账户”）：指在您取得用户标识并通过智趣链仓身份验证后，我们为您开立的账户。" +
                "</br>  2.3智趣链仓服务：指我们基于计算机及互联网技术为您提供相关市场商品、内容运营等相关服务。" +
                "</br>  2.4金豆：是指用户在使用智趣链仓APP浏览商品时所产生的品台积分奖励，可用于智趣链仓商城的优惠券兑换、商品资讯的打赏等，其本身不具备流通的属性。金豆通过浏览商品产生。用户每日所获取的金豆数=基本计量单位*浏览商品数*活跃度。" +
                "</br>  2.5贡献值：是指用户通过推荐他人使用，分享商品链接等方式获得的贡献值，贡献值不具备流通性，不能转让。" +
                "</br>  第三条【账号与密码安全】" +
                "</br>  3.1您在使用本服务时可能需要注册一个账号。关于您使用账号的具体规则，请仔细阅读并遵守相关单独协议。" +
                "</br>  3.2智趣链仓特别提醒您应妥善保管您的账户和密码。当您使用完毕后，应安全退出。因您保管不善可能导致遭受被盗号或密码失窃，责任由您自行承担。" +
                "</br>  3.3为增强用户体验和/或技术便利，本服务的账号可能包括数字、字母或者组合，以及手机号码、电子信箱等多种形式。在您注册某一形式的账号是，智趣链仓可能附赠该账号的另一表现形式。具体的账号形式、账号体系及账号之间的关联关系等，以智趣链仓实际提供的为准。" +
                "</br>  3.4您在使用本服务过程中，智趣链仓可能使用您的账号设置昵称、头像、签名、留言等信息，也可能为您建立或者管理、参与的俱乐部、组队等设置名称、图片、简介等信息。您应当保证这些信息的内容和形式符合法律法规（本协议中的“法律法规”指用户所属/所处地区、国家现行有效的法律、行政法规、司法解释、地方法规、地方规章、部门规章及其他规范性文件以及对于该等法律法规的不时修改和补充，以及相关政策规定等，下同。）、公序良俗、社会公德以及智趣链仓平台规则，且不会侵害任何主体的合法权益。" +
                "</br>  3.5部分第三方网站或者服务可能可以用智趣链仓账号等作为其登陆途径之一。您知晓，除非智趣链仓特别说明外，这些网站或者服务并非智趣链仓运营，您应自行判断此类第三方网站或者服务的安全性和可用性，并自行承担相关风险和责任。" +
                "</br>  第四条【用户个人信息保护】" +
                "</br>  4.1保护用户个人信息是智趣链仓的一项基本原则。智趣链仓是按照本协议及《智趣链仓隐私政策》的规定收集、使用、储存盒分享您的个人信息。本协议对个人信息保护相关内容未作明确规定的，均应以《智趣链仓隐私政策》的内容为准。" +
                "</br>  4.2您在注册账号或使用本服务的过程中，可能需要填写一些必要的信息。若国家法律法规有特殊规定的，您需要填写真实的身份信息。若您填写的信息不完整，则可能无法使用本服务或在使用过程中受到限制。" +
                "</br>  4.3一般情况下，您可根据相关产品规则浏览、修改自己提交的信息，但出于安全性和身份识别（如号码申诉服务等）的考虑下，您可能无法修改注册时提供的初始注册信息及其他验证信息。" +
                "</br>  4.4智趣链仓将尽可能运用各种安全技术鹅程序建立完善的管理制度来保护您的个人信息，以免遭受未经授权的访问、使用或披露。" +
                "</br>  4.5智趣链仓不会将您的个人信息转移或披露给任何第三方，除非" +
                "</br>  相关法律法规或司法机关、行政机关要求。" +
                "</br>  为完成合并、分立、收购或资产转让而转移。" +
                "</br>  为提供您要求的服务所必需。" +
                "</br>  依据《智趣链仓隐私政策》或其他相关协议规则可以转移或披露给任何第三方的情形。" +
                "</br>  第五条【使用本服务的方式】" +
                "</br>  5.1本服务仅为您个人非商业性质的使用，除非您与智趣链仓另有约定。" +
                "</br>  5.2您依本协议条款所取得的权益不可转让。" +
                "</br>  5.3您不得使用任何方式（包括但不限于第三方软件、插件、外挂、系统、设备等）对本服务进行干扰、破坏、修改或施加其他影响。" +
                "</br>  5.4您应当通过智趣链仓提供或认可的方式使用本服务，不得通过任何第三方软件、插件、外挂、系统、设备等登陆或使用本服务。" +
                "</br>  5.5任何人未经智趣链仓授权不得使用任何第三方软件、插件、外挂、系统等查看、获取本服务中所包含的智趣链仓、智趣链仓合作伙伴或用户的任何相关信息、数据等内容，同时，应当严格遵守智趣链仓发布的相关协议规则。" +
                "</br>  第六条【按现状提供服务】" +
                "</br>  您理解并同意：" +
                "</br>  6.1本服务是按照现有技术和条件所能达到的现状提供的。智趣链仓会尽最大努力保障服务的连贯性和安全性，但智趣链仓不能随时预见和防范法律、技术以及其他风险，智趣链仓对此类风险在法律允许的范围内免责，包括但不限于不可抗力、病毒、木马、黑客攻击、系统不稳定、第三方服务瑕疵、政府行为等原因可能导致的服务中断、数据丢失以及其他的损失和风险。" +
                "</br>  6.2因经营策略安排或调整等原因，不同地区的用户可使用的具体智趣链仓服务的内容可能会存在差异，具体以智趣链仓实际提供的为准。" +
                "</br>  第七条【自备设备】" +
                "</br>  7.1您应当理解，您使用本服务需自行准备与相关服务有关的终端设备（如电脑、移动终端和必要的网络接入设备等装置），并承担所需的费用（如电话费、上网费等费用）。" +
                "</br>  7.2您理解并同意，您使用本服务室会耗用您的终端设备和带宽等资源。" +
                "</br>  第八条【广告】" +
                "</br>  8.1您同意智趣链仓可以自行或由第三方通过短信、电子邮件或电子信息等多种方式向您发送、战士广告或其他信息（包括商业与非商业信息），广告或其他信息的具体发送及展示形式、频次及内容等以智趣链仓实际提供为准。" +
                "</br>  8.2智趣链仓将依照相关法律法规要求开展广告业务。您同意，对本服务中出现的广告，您应审慎判断其真实性和可靠性，除法律明确规定外，您应对因该广告而实施的行为责任。" +
                "</br>  第九条【服务费用】" +
                "</br>  9.1智趣链仓的部分服务是以收费方式提供的，如您使用收费服务，请遵守相关的协议。" +
                "</br>  9.2智趣链仓可能根据实际需要对收费服务的收费标准、方式进行修改和变更，智趣链仓也可能会对部分免费服务开始收费。前述修改、变更或开始收费前，智趣链仓将在相应服务页面进行通知或公告。如果您不同意上述修改、变更或付费内容，则应停止使用该服务。" +
                "</br>  9.3在智趣链仓降低收费服务的收费标准或者将收费服务改为免费服务提供时，智趣链仓不对原付费用户提供退费或者费用调整之权利。" +
                "</br>  第十条【第三方提供的产品或服务】" +
                "</br>  10.1您在智趣链仓平台上使用第三方提供的产品或服务室，除遵守本协议约定外，还应遵守第三方的用户协议。智趣链仓和第三方对可能出现的纠纷在法律规定和约定的范围内各自承担风险。" +
                "</br>  10.2因用户使用本软件或要求我们提供特定服务时，本软件可能会调用第三方系统或者通过第三方支持用户的使用或访问，使用或访问的结果由该第三方提供（包括但不限于您通过本服务跳转到的第三方提供的服务及内容，第三方通过本软件接入的服务及内容等）。" +
                "</br>  第十一条【基于软件提供服务】" +
                "</br>  若智趣链仓依托“软件”向您提供服务，您还应遵守以下约定：" +
                "</br>  11.1您在使用本服务的过程中可能还需要下载软件，对于这些软件，智趣链仓给予您一项个人的、不可转让及非排他性的许可。您仅可为使用本服务的目的而使用这些软件。" +
                "</br>  11.2为了改善用户体验、保障服务的安全性及产品功能的一致性等目的，智趣链仓可能会对软件进行更新。您应该将相关软件更新到最新版本，否则智趣链仓并不保证软件或服务能正常使用。" +
                "</br>  11.3智趣链仓可能为不同的终端设备开发不同的软件版本，您应当根据实际情况选择下载合适的版本进行安装。您可以直接从智趣链仓平台上获取软件，也可以从得到智趣链仓授权的第三方获取。如果您从未经智趣链仓授权的第三方获取软件或与软件名称相同的安装程序，智趣链仓无法保证该软件或服务能够正常使用，并对因此给您造成的损失不承担任何责任。" +
                "</br>  11.4除非智趣链仓书面许可，您不得从事下列任何一行为：" +
                "</br>  删除软件及其副本上关注著作权的信息" +
                "</br>  对软件进行反向工程、反向汇编、反向编译、或者以其他方式尝试发现软件的源代码。" +
                "</br>  对智趣链仓拥有知识产权的内容进行使用、出租、出借、复制、修改、链接、转载、汇编、发表、出版、建立镜像站点等。" +
                "</br>  对软件或者软件运行过程中释放到任何终端内存中的数据、软件运行过程中客户端与服务器端的交互数据，以及软件所必需的系统数据，进行复制、修改、增加、删除、挂接运行或创作任何衍生作品，形式包括但不限于使用插件、外挂或非经智趣链仓授权的第三方工具/服务接入软件和相关系统。" +
                "</br>  通过修改或伪造软件运行的指令、数据等方式，增加、删除、变动软件的功能或运行效果，或者将用于上述用途的软件、方式进行运营或向公众传播，无论这些行为是否为商业目的的。" +
                "</br>  通过非智趣链仓开发、授权的第三方软件、插件、外挂、系统、设备等任何方式、登陆或使用智趣链仓软件及服务，或制作软件、发布、传播非智趣链仓开发、授权的用于登陆或使用智趣链仓软件及服务的第三方软件、插件、外挂、系统、设备等。" +
                "</br>  第十二条【知识产权声明】" +
                "</br>  12.1智趣链仓在本服务中提供的内容（包括但不限于网页、文字、图片、音频、视频、图表、计算机软件等）的知识产权对智趣链仓所有，用户在使用本服务总产生内容的知识产权归用户或相关权利人所有，除非您与智趣链仓另有约定。" +
                "</br>  12.2除另有特别声明外、智趣链仓提供本服务时所依托的著作权、专利权及其他知识产权均归智趣链仓所有。" +
                "</br>  12.3智趣链仓在本服务中所使用的“智趣链仓”“金豆”等商业标识，其著作权或商标权归智趣链仓所有。" +
                "</br>  12.4上述及其他任何本服务包含的内容的知识产权均受到法律法规保护，未经智趣链仓、用户或相关权利人书面许可，任何人不得以任何形式进行使用或创造相关衍生作品。" +
                "</br>  第十三条【用户违法违规行为】" +
                "</br>  13.1您在使用本服务时须遵守法律法规，不得制作、复制、发布、传播还有下列内容的信息或从事相关行为，也不得为制作、复制、发布、传播含有下列内容的信息或从事相关行为提供便利：" +
                "</br>  反对宪法所确定的基本原则。" +
                "</br>  危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一。" +
                "</br>  损害国家荣誉和利益的。" +
                "</br>  煽动民族仇恨、民族歧视，破坏民族团结的。" +
                "</br>  破坏国家宗教政策，宣扬邪教和封建迷信的。" +
                "</br>  散播谣言，扰乱社会秩序，破坏社会稳定的。" +
                "</br>  散播淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的。" +
                "</br>  侮辱或者诽谤他人，侵害他人合法权益的。" +
                "</br>  违反法律法规底线、社会主义制度底线、国家利益底线、公民合法权益底线、社会公共秩序底线、道德风尚底线和信息真实性底线的“七条底线”要求的。" +
                "</br>  相关法律法规或本协议、相关协议、规则等禁止的其他行为。" +
                "</br>  13.2如果您在使用本服务过程中违反了相关法律法规或本协议约定，相关国家机关或机构可能会对您提起诉讼、罚款或采取其他制裁措施，并要求智趣链仓给予协助。因此给您或者他人造成损害的，您应自行承担全部责任，智趣链仓不承担任何责任。" +
                "</br>  13.3如果您违反本协议约定，智趣链仓有权进行独立判断并采取相应措施，包括但不限于通过技术手段删除、屏蔽相关内容或断开链接等。同时，智趣链仓有权视用户的行为性质，采取包括但不限于暂停或终止向您提供服务，限制、中止、冻结或终止您对智趣链仓账号的使用，追究法律责任等措施。" +
                "</br>  13.4您违反本协议约定，导致任何主题损失的，您应当独立承担责任；智趣链仓因此遭受损失的，您也应当一并赔偿。" +
                "</br>  第十四条【遵守当地法律监管】" +
                "</br>  14.1您在使用本服务过程中应当遵守淡定相关的法律法规，并尊重当地的道德和风俗习惯。如果您的行为违反了当地法律法规货到的风俗，您应当为此独立承担责任。" +
                "</br>  14.2您应避免因使用本服务而使智趣链仓违反法律法规或卷入政治和公共事件，否则智趣链仓有权暂停或终止对您的服务。" +
                "</br>  第十五条【用户发送、传播的内容和投诉处理】" +
                "</br>  15.1你通过本服务发送或传播的内容（包括但不限于网页、文字、图片、音频、视频、图表等）均由您自行承担责任。" +
                "</br>  15.2您发送或传播的内容应有合法来源，相关内容为您所有或您已获得必要的授权。" +
                "</br>  15.3如果您发送或传播的内容我违法违规或侵犯他人的权利的，智趣链仓有权进行独立判断并采取删除、屏蔽或断开链接等措施。" +
                "</br>  15.4如果您被他人他投诉或您投诉他人，智趣链仓有权将争议中相关方的主体信息、联系方式、投诉相关内容等必要信息提供给相关事方或相关部门，以便及时解决投诉纠纷，保护各方合法权益。" +
                "</br>  15.5您保证对您在投诉处理程序中提供的信息、材料、证据等的真实性、合法性、有效性负责。" +
                "</br>  第十六条【不可抗力及其他免责事由】" +
                "</br>  16.1您理解并同意，在使用本服务的过程中，可能会遇到不可抗力等风险因素，是本服务受到影响。不可抗力是指不能预见、不能克服并不能避免且对一方或双方造成重大影响的客观事件，包括但不限于自然灾害如洪水、地震、瘟疫流行和风暴等以及社会实践如战争、动乱、政府行为等。出现上述情况，智趣链仓将努力在第一时间与相关单位配合，争取即使经行处理，但是由此给您造成的损失智趣链仓在法律允许的范围内免责。" +
                "</br>  16.2在法律允许的范围内，智趣链仓对以下情形导致的服务中断或受阻不曾担责任：" +
                "</br>  受到计算机病毒、木马或者其他恶意程序、黑客攻击的破坏。" +
                "</br>  用户或智趣链仓的电脑软件、系统、硬件和通信线路出现故障。" +
                "</br>  用户操作不当或用户通过非智趣链仓授权的方式使用本服务。" +
                "</br>  程序版本过时、设备的老化和/或其兼容性问题。" +
                "</br>  其他智趣链仓无法控制或合理预见的情形。" +
                "</br>  16.3您理解并同意，在使用本服务的过程中，可能会遇到网络信息或其他用户行为带来的风险，智趣链仓不对任何信息的真实性、适用性、合法性承担责任，也不对因侵权行为给您造成的损害负责。这些风险包括但不限于：" +
                "</br>  来自他人匿名或冒名的含有威胁、诽谤、令人反感或非法内容的信息。" +
                "</br>  遭受他人误导、欺骗或其他导致可能导致的任何心理、生理上的伤害以及经济上的损失。" +
                "</br>  其他因网络信息或用户行为引起的风险。" +
                "</br>  16.4智趣链仓依据本协议约定获得处理违法违规内容的权利，该权利不构成智趣链仓的义务或承诺，智趣链仓不能保证及时发现违法行为或进行相应处理。" +
                "</br>  16.5在任何情况下，您不应轻信借款、索要、提供密码或邪路其他涉及财产的信息。涉及财产操作的，请一定先核实对方身份，并请经常留意智趣链仓有关防范诈骗犯罪的提示。" +
                "</br>  第十七条【协议的生效与变更】" +
                "</br>  17.1您使用本服务即视为您已阅读本协议并接受本协议的约束。" +
                "</br>  17.2智趣链仓有权在必要时修改本协议条款。您可以在相关服务页面查阅最新版本的协议条。" +
                "</br>  17.3本协议条款变更后，如果您继续使用智趣链仓提供的软件或服务，即视为您已接受变更后的协议。" +
                "</br>  第十八条【服务的变更、中断、终止】" +
                "</br>  18.1您理解并同意，智趣链仓基于经营策略的调整，可能会对服务内容进行变更，也可能会中断、中止或终止服务。" +
                "</br>  18.2在智趣链仓发生合并、分立、收购、资产转让时，智趣链仓可向第三方转让本服务下相关资产；智趣链仓也可在单方通知您后，将本协议下部分或全部服务及相应的权利义务转交第三方运营或履行。具体受让主体以智趣链仓通知的为准。" +
                "</br>  18.3如发生以下任何一种情形，智趣链仓有权不经通知而中断或终止向您提供服务：" +
                "</br>  根据法律法规规定您应提交真实信息，而您提供的个人资料不真实、或注册时信息不一致又未能提供合理证明。" +
                "</br>  您违反相关法律法规的规定或违反本协议的约定。" +
                "</br>  按照法律法规规定，司法机关或主管部门的要求。" +
                "</br>  出于安全的原因或其他必要的情形。" +
                "</br>  18.4智趣链仓有权按本协议8.2条的约定进行收费。若您未按时足额付费，智趣链仓有权中断、中止或终止提供服务。" +
                "</br>  18.5您有责任自行备份存储在本服务中的数据。如果您的服务被终止，智趣链仓有权从服务器上永久地删除您的数据；法律法规另有规定的除外，服务中止或终止后，智趣链仓没有义务向您提供或返还数据。" +
                "</br>  第十九条【管辖与法律适用】" +
                "</br>  19.1本协议的成立、生效、履行、解释及纠纷解决等相关事宜，均使用中华人民共和国大陆地区法律（不包括冲突法）。" +
                "</br>  19.2本协议签订的为中华人民共和国浙江省义乌市。" +
                "</br>  19.3若您和智趣链仓之间发生任何纠纷或争议首先应友好协商解决；协商不成的，您同意将纠纷或正提交本协议签订地（即中华人民共和国浙江省义乌市）有股管辖权的人民法院管辖。" +
                "</br>  19.4本协议所有条款的标题因为阅读方便，本身并无实际涵义，不能作为本协议解释的依据。" +
                "</br>  19.5本协议条款无论因何种原因部分无效或不可执行，其余条款仍有效，对双方具有约束力。" +
                "</br>  19.6若本协议中有中文、英文等多个语言版本，相应内容不一致的，均以中文版的内容为准。" +
                "</br>  第二十条【未成年人使用条款】" +
                "</br>  20.1若用户未满18周岁，则为未成年人，应在监护人监护下、指导下阅读本协议和使用本服务。" +
                "</br>  20.2未成年人用户涉世未深，容易为网络虚像迷惑，且好奇心强，遇事缺乏随机应变的处理能力，很容易被别有用心的人利用而又缺乏自我保护能力。因此，未成年人用户在使用本服务时应注意以下事项，提高安全意识，加强自我保护：" +
                "</br>  认清网络世界和现实世界的区别，避免沉迷于网络，影响日常的学习生活。" +
                "</br>  填写个人资料时，加强个人保护意识，以免不良分子对个人生活造成骚扰。" +
                "</br>  在监护人霍老师的指导下，学习正确使用网络。" +
                "</br>  避免陌生网友随意会面或参与联谊活动，以免不法分子有机可乘，危及自身安全。" +
                "</br>  20.3监护人、学校均应对未成年人使用本服务多做引导。特别是家长应关心子女的成长，注意与子女的沟通，指导子女上网应该注意的安全问题，防患于未然。" +
                "</br>  20.4已满18周岁的成年人因任何原因不具备完全民事行为能力的，参照适用本协议之未成年人使用条款之相关约定。" +
                "</br>  第二十一条【其他】" +
                "</br>  21.1如果您对本协议或本服务有意见或建议，可通过APP中“联系客服”功能与智趣链仓客户服务部门取得联系，我们会给予您必要的帮助。" +
                "";
    }

    @Override
    public void sendCodeSuccess(EmptyModel data) {
        showShortToast("手机验证码发送成功");
    }

    @Override
    public void sendCodeFailed(String msg) {
        showShortToast(msg);
    }
}
