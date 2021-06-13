
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class ThreadDemoV1{
	
	public static void main(String []args){
		Worker w1 = new Worker("W1",1,5,6,10,0);
		Worker w2 = new Worker("W2",11,15,16,20,1);
		Worker w3 = new Worker("W3",21,25,26,30,2);
		w1.start();
		w2.start();
		w3.start();
	}

}



class Worker extends Thread{
	public static String part1_lock = null;
	public static String part2_lock = null;
	public final static List<String> progress1 = new ArrayList();
	public final static List<String> progress2 = new ArrayList();
	String name;
	int part1_start;
	int part1_end;
	int part2_start;
	int part2_end;
	int progress_count;

	Worker(String name,int part1_start,int part1_end,int part2_start,int part2_end,int progress_count){
		super(name);
		this.name = name;
		this.part1_start = part1_start;
		this.part1_end = part1_end;
		this.part2_start = part2_start;
		this.part2_end = part2_end;
		this.progress_count = progress_count;
	}
	
	public void run(){
		this.part1();
		this.checkProgress();
		this.part2();
		
	}

	private void checkProgress(){
		while (!(progress1.size() == 3 && progress2.size() == progress_count)){
			try{
					Thread.sleep(ThreadLocalRandom.current().nextLong(100));
				}catch(Exception ex){
					System.out.println(ex);
				}
		}
	}

	private void part1(){
		while(!(Worker.part1_lock == null && progress1.size() == progress_count)){
			try
			{
				Thread.sleep(ThreadLocalRandom.current().nextLong(100));
			}
			catch(Exception ex){
				System.out.println(ex);
			}
		}
		Worker.part1_lock = name;
		for (int i=part1_start; i<= part1_end; i++){
			System.out.println(i);
		}
		progress1.add(name);
		Worker.part1_lock = null;		
	}

	private void part2(){
		while(Worker.part2_lock != null){
			try
			{
				Thread.sleep(ThreadLocalRandom.current().nextLong(100));
			}
			catch(Exception ex){
				System.out.println(ex);
			}
		}
		Worker.part2_lock = name;
		for (int i=part2_start; i<= part2_end; i++){
			System.out.println(i);
		}
		progress2.add(name);
		Worker.part2_lock = null;		
	}
}