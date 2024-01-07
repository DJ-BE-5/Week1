public class ThreadCounter extends Thread {
    String name;
    int count;
    public ThreadCounter(String name, int count){
        this.name=name;
        this.count = count;
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
        Thread counter = new ThreadCounter("counter1", 10);
        Thread counter2 = new ThreadCounter("counter2", 10);
        counter.start();
        counter2.start();
    }
}
