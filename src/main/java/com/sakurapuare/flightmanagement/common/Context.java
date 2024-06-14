package com.sakurapuare.flightmanagement.common;

@Deprecated
public class Context {

    public static final ThreadLocal<AuthInfo> threadLocal = new ThreadLocal<>();

    @Deprecated
    public static void setCurrentInfo(AuthInfo baseAuthInfo) {
        threadLocal.set(baseAuthInfo);
    }

    @Deprecated
    public static AuthInfo getCurrentAuthInfo() {
        return threadLocal.get();
    }

    @Deprecated
    public static void removeCurrentInfo() {
        threadLocal.remove();
    }
}
