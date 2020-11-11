package com.zlyc.www.util;

import android.text.TextUtils;

/**
 * Created by LGQ
 * Time: 2018/8/10
 * Function:
 */

public class LoginUtil {

    public static boolean verifyPhone(String phone) {
        if (phone.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入电话号码");
        } else if (!StringUtils.isMobileNO(phone)) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的手机号码");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyPassword(String password) {
        if (password.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入密码");
        } else if (password.length() < 6) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入不少于6位的密码");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyPasswordDouble(String password,String newPassword) {
        if(!TextUtils.equals(password,newPassword)){
            ToastUtil.showShortToast(AppUtils.getContext(), "两次密码不正确");
        }else{
            return true;
        }
        return false;
    }

    public static boolean verifyVerify(String verify) {
        if (verify.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入验证码");
        } else if (verify.length() != 6) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入6位验证码");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyName(String name) {
        if (name.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入名称");
        } else if (name.length() < 2) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的名称");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyID(String name) {
        if (name.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入证件号");
        } else if (name.length() < 15) {
            ToastUtil.showShortToast(AppUtils.getContext(), "请输入正确的证件号");
        } else {
            return true;
        }
        return false;
    }

    public static boolean verifyEmpty(String name,String tip) {
        if (name.equals("")) {
            ToastUtil.showShortToast(AppUtils.getContext(), tip);
        }  else {
            return true;
        }
        return false;
    }
}
