package TicTacToe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class App {
    private final static int port = 5944;
    public static void main(String[] args) {
        List<TT_Game> echoList = new LinkedList<>();
        try(ServerSocket serversocket = new ServerSocket(port)){
            while(!serversocket.isClosed()){
                Socket socket = serversocket.accept();
                System.out.println("client connected"+
                        socket.getInetAddress() +
                        socket.getPort()
                );

                TT_Game ES = new TT_Game(socket.getInputStream(), socket.getOutputStream(),echoList, echoList.size());
                echoList.add(ES);
                ES.start();

            }

            for(TT_Game echoServer : echoList ){
                echoServer.stop2();
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
