package netty.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket accept = serverSocket.accept();
            exec(accept);
        }
    }

    private static void exec(Socket socket) throws IOException {
        PrintWriter printStream = new PrintWriter(socket.getOutputStream(),true);
        printStream.println("HTTP/1.1 200 OK");
        printStream.println("Content-Type:test/html;charset=utf-8");
        String body = "test socket";
        printStream.println("Content-Length:" + body.getBytes().length);
        printStream.println();
        printStream.write(body);
        printStream.close();
        socket.close();
    }
}
