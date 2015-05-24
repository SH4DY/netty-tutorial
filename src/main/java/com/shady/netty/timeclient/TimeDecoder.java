package com.shady.netty.timeclient;

import com.shady.netty.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * ByteToMessageDecoder is an implementation of ChannelHandler.
 * decode() is called whenever new data is received, internally
 * maintained cumulative buffer.
 *
 * Created by shady on 24/05/15.
 */
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {

        if(byteBuf.readableBytes() < 4) return;

        out.add(new UnixTime(byteBuf.readInt()));
    }
}
