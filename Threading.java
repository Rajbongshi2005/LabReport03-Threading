class SharedCounter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class FirstThread extends Thread{
    SharedCounter counter;
    public FirstThread(SharedCounter counter){
        this.counter = counter;
    }
    public void run(){
        try{
        for(int i=1; i<=10; i++){
            System.out.println("First Thread: " + i);
            counter.increment();
            System.err.println("SharedCounter count: "+counter.getCount());
            Thread.sleep(500);
        }
    } catch(Exception e){
        System.out.println(e);
    }
}
}
class SecondThread implements Runnable{
    SharedCounter counter;
    public SecondThread(SharedCounter counter){
        this.counter = counter;
    }
   
    public void run(){
         try {
        for(int i=1; i<=10; i++){
            System.out.println("Square Thread: " + (i*i));
            counter.increment();
            Thread.sleep(500);
        }
    } catch(InterruptedException e){
        System.out.println(e); 
}
}
}

public class Threading {
    public static void main(String[] args) {
        SharedCounter counter = new SharedCounter();
        FirstThread t1 = new FirstThread(counter);
        Thread t2 = new Thread(new SecondThread(counter));
        t1.start();
        t2.start();
    }
}