package com.fh.util;

import javax.servlet.http.HttpServletRequest;

public class WebRequestFilerUitl {
    private static ThreadLocal<HttpServletRequest> requestFiler = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request){
                requestFiler.set(request);
    }

    public static HttpServletRequest getRequest(){
        return requestFiler.get();
    }

    public static void removeRequest(){
        requestFiler.remove();
    }
}
