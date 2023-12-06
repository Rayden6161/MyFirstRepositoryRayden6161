

public class CounterThread extends  Thread  {
    private Counter counter;
    public CounterThread(Counter counter,String name){
        this.counter = counter;
super(name);
    }

    public void run()  {
        while(true){
            counter.increment();
            System.out.println(getName() + "increased counter.Value"+ counter.value());
          try{  Thread.sleep((long)Math.random()*1000);
        }
          catch (InterruptedException ex){
              throw new RuntimeException(ex);
          }
        }
    }


}
