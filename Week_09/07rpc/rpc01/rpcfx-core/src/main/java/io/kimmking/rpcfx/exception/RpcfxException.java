package io.kimmking.rpcfx.exception;


/**
 * 自定义业务异常
 */
public class RpcfxException extends RuntimeException{


    public RpcfxException(String message){
        super(message);
    }
}
