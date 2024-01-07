package TicTacToe;


import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TT_Game extends Thread{

    private int index;
    private InputStream input;
    private OutputStream output;
    private List<TT_Game> echoServerList;
    private boolean running = false;
    private static String board = "000000000";

    BufferedInputStream bufferedInput;
    BufferedOutputStream BufferedOutput;


    public TT_Game(InputStream input,
                      OutputStream output,
                      List<TT_Game> echoServerList, int index){
        this.input=input;
        this.output=output;
        this.echoServerList=echoServerList;
        bufferedInput = new BufferedInputStream(input);
        BufferedOutput = new BufferedOutputStream(output);
        this.index=index;
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
    private void setBoard(String command, int player){
        String[] split = command.split(",");
        int x = Integer.parseInt(String.valueOf(split[0].charAt(1)));
        int y = Integer.parseInt(String.valueOf(split[1].charAt(0)));
        this.board = this.board.substring(0,3*y+x) + Integer.toString(player+1) + this.board.substring(3*y+x+1);
    }
    @Override
    public void run(){
        byte[] buffer = new byte[256];
        String message ="";
        running=true;
        try{
            if(index==0) {
                echoServerList.get(0).write("\n");
                Thread.sleep(100);
                echoServerList.get(0).write("\r");}
            else if(index==1){
                echoServerList.get(1).write("\n");
                Thread.sleep(100);
                echoServerList.get(1).write("\r");}


            while(running){
                int length = bufferedInput.read(buffer,0, buffer.length);
                if(length > 0){
                    message = new String(Arrays.copyOf(buffer,length));
                    System.out.print("Receive: "+message);
                    setBoard(message, index);

                    for(TT_Game echoServer : echoServerList){
                        echoServer.write(this.board);
                    }

                }else if(length<0){
                    running=false;
                }
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Run END.");
    }
}
