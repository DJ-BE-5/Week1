public class main {
    public static void main(String[] args) {
        InterfaceCounter t = new InterfaceCounter("counter", 10);
        t.start();
        System.out.println(t.thread.getState());
    }
}

