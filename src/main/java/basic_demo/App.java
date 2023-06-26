package basic_demo;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Producer producer1 = new Producer("[basic_demo.Producer 1]",10);
        Consumer consumer1 = new Consumer("[basic_demo.Consumer 1]", 100);
        Consumer consumer2 = new Consumer("[basic_demo.Consumer 2]", 300);

        new Thread(producer1).start();

        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}