
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class ThreadDemoV1{
	
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
	public static String part1_lock = null;
	public static String part2_lock = null;
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
		while(true){
			if (Worker.part1_lock == null){
				Worker.part1_lock = name;
				for (int i=1; i<= 5; i++){
					System.out.println(name+":"+i);
				}
				Worker.part1_lock = null;
				try{
					Thread.sleep(ThreadLocalRandom.current().nextLong(100));
				}catch(Exception ex){
					System.out.println(ex);
				}
				progress.add(name);
				break;
			}
			else{
				try{
					Thread.sleep(ThreadLocalRandom.current().nextLong(100));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		}
		
	}

	private void part2(String name){
		while(true){
			if (Worker.part2_lock == null){
				Worker.part2_lock = name;
				for (int i=6; i<= 10; i++){
					System.out.println(name+":"+i);
				}				
				Worker.part2_lock = null;
				break;
			}
			else{
				try{
					Thread.sleep(ThreadLocalRandom.current().nextLong(100));
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		}

	}
}