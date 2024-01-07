package live;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * EchoServer
 */
public class EchoServer extends Thread{
    private InputStream input;
    private OutputStream output;
    private List<EchoServer> echoServerList;
    private boolean running = false;

    BufferedInputStream bufferedInput;
    BufferedOutputStream BufferedOutput;


    public EchoServer(InputStream input,
                    OutputStream output,
                    List<EchoServer> echoServerList){
        this.input=input;
        this.output=output;
        this.echoServerList=echoServerList;
        bufferedInput = new BufferedInputStream(input);
        BufferedOutput = new BufferedOutputStream(output);
    }
    public void stop2(){
        running=false;
    }

    public void write(String message){
        try{
            BufferedOutput.write(message.getBytes());
            BufferedOutput.flush();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void run(){
        byte[] buffer = new byte[256];
        String message ="";
        running=true;
        try{
            while(running){
                int length = bufferedInput.read(buffer,0, buffer.length);
                if(length > 0){
                    message = new String(Arrays.copyOf(buffer,length));
                    System.out.println("Receive : "+message);
                    System.out.println("Echo : "+message);
                    for(EchoServer echoServer : echoServerList){
                        echoServer.write(message);

                    }
                }else if(length<0){
                    running=false;
                }
                System.out.println("====================");
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}