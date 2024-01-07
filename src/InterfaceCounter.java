import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterfaceCounter implements Runnable {
    String name;
    int count;
    Thread thread;
    public InterfaceCounter(String name, int count){
        this.name=name;
        this.count = count;
        this.thread = new Thread(this);
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run(){
        try {
            for(int i=0;i<count;i++){
                System.out.println(name+" : " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(this.name+" end");
    }

    public static void main(String[] args) {
        InterfaceCounter counter = new InterfaceCounter("counter1", 10);
        InterfaceCounter counter2 = new InterfaceCounter("counter2", 10);

        ExecutorService pool = Executors.newFixedThreadPool(1);

        pool.execute(counter);
        pool.execute(counter2);
        pool.shutdown();
    }
    /*
        Thread를 직접 만드는건 resource가 굉장히 많이 사용됨.
        하지만 runnable을 많이 만들고 thread 소량을 재사용하면
        굉장히 자원을 아낄수있음.

     */
}
