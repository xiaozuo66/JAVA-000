package io.github.kimmking.gateway.outbound.netty4;

import io.github.kimmking.gateway.outbound.OutboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;

public class NettyHttpClient implements OutboundHandler {


    private String backendUrl="127.0.0.1";

    private int port=8088;

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {

        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new HttpClientCodec());
                    channel.pipeline().addLast(new HttpObjectAggregator(65536));
                    channel.pipeline().addLast(new HttpContentDecompressor());
//                    channel.pipeline().addLast(new HttpCli)
                }
            });
            ChannelFuture future=bootstrap.connect(backendUrl,port).sync();

//            DefaultFullHttpRequest request=new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.GET,new URI(""), Unpooled.wrappedBuffer());

            future.channel().closeFuture().sync();
        }catch(Exception e){

        }
        finally {
            group.shutdownGracefully();
        }
    }

    /*public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                     //客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientOutboundHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();


            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        NettyHttpClient client = new NettyHttpClient();
        client.connect("127.0.0.1", 8844);
    }*/
}