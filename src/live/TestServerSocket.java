package live;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class TestServerSocket{
    public static void main(String[] args) throws InterruptedException{
        List<EchoServer> echoList = new LinkedList<>();
        try(ServerSocket serversocket = new ServerSocket(5944)){
            while(!serversocket.isClosed()){
                Socket socket = serversocket.accept();
                System.out.println("client connected"+
                    socket.getInetAddress() +
                    socket.getPort()
                );
                
                EchoServer ES = new EchoServer(socket.getInputStream(), socket.getOutputStream(),echoList);
                echoList.add(ES);
                ES.start();
                
            }

            for(EchoServer echoServer : echoList ){
                echoServer.stop2();
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}