
import java.util.concurrent.*;
import java.util.*;
public class ThreadDemoV2{
	
	public static void main(String []args){
		Worker w1 = new Worker("W1");
		Worker w2 = new Worker("W2");
		Worker w3 = new Worker("W3");
		w1.start();
		w2.start();
		w3.start();
	}

}



class Worker extends Thread{
	private final static Semaphore part1_lock = new Semaphore(1, true);
	private final static Semaphore part2_lock = new Semaphore(1, true);
	public final static List<String> progress = new ArrayList();

	Worker(String name){
		super(name);
	}
	
	public void run(){
		String name = Thread.currentThread().getName();
		this.part1(name);
		this.checkProgress();
		this.part2(name);
		
	}

	private void checkProgress(){
		while (progress.size() != 3){
			try{
					Thread.sleep(ThreadLocalRandom.current().nextLong(100));
				}catch(Exception ex){
					System.out.println(ex);
				}
		}
	}

	private void part1(String name){
		try{
			part1_lock.acquire();
			for (int i=1; i<= 5; i++){
				System.out.println(name+":"+i);
			}
			progress.add(name);
			part1_lock.release();	
		}catch(Exception ex){
			System.out.println(ex);
		}	
	}

	private void part2(String name){
		try{
			part2_lock.acquire();
			for (int i=6; i<= 10; i++){
				System.out.println(name+":"+i);
			}
			part2_lock.release();	
		}catch(Exception ex){
			System.out.println(ex);
		}	
	}
}