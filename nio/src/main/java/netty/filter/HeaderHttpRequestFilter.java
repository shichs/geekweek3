package netty.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("mao", "soul");
        final String netty = fullRequest.headers().get("netty");
        return !"true".equalsIgnoreCase(netty);
    }
}
