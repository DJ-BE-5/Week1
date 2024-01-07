package snc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class Client {
    private String addr;
    private int port;
    private Socket socket;
    private BufferedInputStream bufferedInput;
    private BufferedOutputStream bufferedOutput;
    private final Scanner scanner = new Scanner(System.in);

    public Client(String addr, int port){
        this.addr=addr;
        this.port=port;
    }

    public void run(){
        try{
            socket= new Socket(addr, port);
            bufferedInput = new BufferedInputStream(socket.getInputStream());
            bufferedOutput = new BufferedOutputStream(socket.getOutputStream());
            byte[] buffer = new byte[256];
            while(socket.isConnected()){
                write(scanner.next());
                int length = bufferedInput.read(buffer,0, buffer.length);
                if(length > 0){
                    String message = new String(Arrays.copyOf(buffer,length));
                    System.out.println("Echo Receive : "+message);
                }else if(length<0){
                    socket.close();
                }

            }
            socket.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    private void write(String message){
        try{
            bufferedOutput.write(message.getBytes());
            bufferedOutput.flush();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
