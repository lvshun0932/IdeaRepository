package com.lv.example.springboot.exception;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = -3277216519192266977L;
    private int code;
    private Object context;

    public int getCode(){return code;}

    public Object getContext(){return context;}

    public AuthException(){super();}

    public AuthException(int code,String msg){this(code,msg,null);}

    public AuthException(int code,String msg,Throwable t){
        super(msg,t);
        this.code = code;
    }


}
