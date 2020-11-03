package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.outbound.OutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.*;

import java.io.IOException;

/**
 * 自定义的handler，通过OkHttp实现
 */
public class OkhttpOutboundHandler implements OutboundHandler {

    private static final String url="https:www.baidu.com";//https:

    public OkhttpOutboundHandler(String backendUrl){

    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);

        //同步调用,返回Response,会抛出IO异常
        try {
            Response response = call.execute();
            System.out.println("----请求得到返回内容---"+response);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
