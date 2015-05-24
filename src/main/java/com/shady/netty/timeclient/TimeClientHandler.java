package com.shady.netty.timeclient;

import com.shady.netty.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by shady on 24/05/15.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private ByteBuf buf;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        buf.release();
        buf = null;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        UnixTime m = (UnixTime) msg;
        System.out.println(m);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
