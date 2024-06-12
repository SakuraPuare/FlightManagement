package com.sakurapuare.flightmanagement.common;

public class Context {

    public static final ThreadLocal<AuthInfo> threadLocal = new ThreadLocal<>();

    public static void setCurrentInfo(AuthInfo baseAuthInfo) {
        threadLocal.set(baseAuthInfo);
    }

    public static AuthInfo getCurrentAuthInfo() {
        return threadLocal.get();
    }

    public static void removeCurrentInfo() {
        threadLocal.remove();
    }
}
