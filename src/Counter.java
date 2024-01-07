public class Counter {
    String name;
    int count;
    public Counter(String name, int count){
        this.name=name;
        this.count = count;
    }

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
        Counter counter = new Counter("counter1", 10);
        Counter counter2 = new Counter("counter2", 10);
        counter.run();
        counter2.run();
    }
}
