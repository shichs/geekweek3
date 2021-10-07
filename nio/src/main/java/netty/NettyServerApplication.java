package netty;

import netty.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyServerApplication {
    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","8888");
        String proxyServers = System.getProperty("proxyServer","http://localhost:8801");
        int port = Integer.parseInt(proxyPort);
        System.out.println(" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
