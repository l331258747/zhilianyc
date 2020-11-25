package com.zqlc.www.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by LGQ
 * Time: 2018/9/26
 * Function:
 */

public class DialogUtil {

    private static DialogUtil instance = null;

    public static DialogUtil getInstance() {
        if (instance == null) {
            instance = new DialogUtil();
        }
        return instance;
    }

    private DialogUtil() {
    }


    public AlertDialog getDefaultDialog(Context context, String title, String content, String positiveName, final DialogCallBack callBack,final DialogCallBack callBack2) {
        AlertDialog alterDialog = new AlertDialog.Builder(context)
                .setMessage(content)
                .setPositiveButton(positiveName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBack == null){
                            dialog.cancel();
                        }else{
                            callBack.exectEvent(dialog);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(callBack2 == null){
                            dialog.cancel();
                        }else{
                            callBack2.exectEvent(dialog);
                        }
                    }
                }).create();

        return alterDialog;
    }

    public AlertDialog getDefaultDialog(Context context, String content) {
        return this.getDefaultDialog(context,"提示",content,"确定",null,null);
    }

    public AlertDialog getDefaultDialog(Context context, String content,final DialogCallBack callBack) {
        return this.getDefaultDialog(context,"提示",content,"确定",callBack,null);
    }

    public AlertDialog getDefaultDialog(Context context, String content,String positiveName,final DialogCallBack callBack) {
        return this.getDefaultDialog(context,"提示",content,positiveName,callBack,null);
    }

    public interface DialogCallBack {
        void exectEvent(DialogInterface alterDialog);
    }

    public interface DialogEditCallBack {
        void exectEvent(DialogInterface alterDialog, String str);
    }


}
