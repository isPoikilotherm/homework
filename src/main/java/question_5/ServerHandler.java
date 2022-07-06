package question_5;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Spider spider=new Spider(msg);
        String res= spider.job();
        Channel channel = ctx.channel();
        channel.writeAndFlush(res);
    }
}
