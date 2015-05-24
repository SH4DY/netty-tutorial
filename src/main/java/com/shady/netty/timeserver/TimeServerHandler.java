package com.shady.netty.timeserver;

import com.shady.netty.UnixTime;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * ServerHandler which responds with a com.shady.netty.UnixTime POJO
 * Created by shady on 24/05/15.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        ctx.flush();
        //release of msg is implicit when calling ctx.write()
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final ChannelFuture f = ctx.writeAndFlush(new UnixTime());
        f.addListener(ChannelFutureListener.CLOSE); //Close AFTER operation finished
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
