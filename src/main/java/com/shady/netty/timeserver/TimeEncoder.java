package com.shady.netty.timeserver;

import com.shady.netty.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * Created by shady on 24/05/15.
 */
public class TimeEncoder extends ChannelHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        UnixTime m = (UnixTime) msg;
        ByteBuf encoded = ctx.alloc().buffer(4);
        encoded.writeLong(m.value());
        ctx.write(encoded, promise); //We pass the original ChannelPromise as-is so that Netty marks
        // it as success or failure when the encoded data is actually written out to the wire.
    }

    //Alternative. Extend MessageToByteEncoder<com.shady.netty.UnixTime>
//    @Override
//    protected void encode(ChannelHandlerContext ctx, com.shady.netty.UnixTime msg, ByteBuf out) {
//        out.writeLong(msg.value());
//    }

}
