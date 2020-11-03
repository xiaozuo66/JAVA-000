package io.github.kimmking.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;


/**
 * 定义outbound的统一接口
 */
public interface OutboundHandler {

    /**
     * 定义outbound的处理方法
     * @param fullRequest
     * @param ctx
     */
    void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
