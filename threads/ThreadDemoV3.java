
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class ThreadDemoV3{
	
	public static void main(String []args){
		PrintWorker pw = new PrintWorker();
		(new Thread(new Worker1(pw))).start();
		(new Thread(new Worker2(pw))).start();
		(new Thread(new Worker3(pw))).start();
	}

}



 class Worker1 implements Runnable{
	private PrintWorker pw;

	 Worker1(PrintWorker pw){
		this.pw = pw;
	}
	
	public void run(){
		pw.work1();
		
	}
}

 class Worker2 implements Runnable{
	private PrintWorker pw;

	 Worker2(PrintWorker pw){
		this.pw = pw;
	}
	
	public void run(){
		pw.work2();
		
	}
}

 class Worker3 implements Runnable{
	private PrintWorker pw;

	 Worker3(PrintWorker pw){
		this.pw = pw;
	}
	
	public void run(){
		pw.work3();
		
	}
}

class PrintWorker{
	boolean part1_done=false;
	boolean part2_done=false;
	boolean part3_done=false;
	boolean part4_done=false;
	boolean part5_done=false;

	synchronized void work1(){
		for (int i=1; i<= 5; i++){
			System.out.println(i);
		}
		part1_done = true;
		notifyAll();
		while(!part3_done){
			try {
                wait();
            } catch (InterruptedException e) {}
		}
		for (int i=6; i<= 10; i++){
			System.out.println(i);
		}
		part4_done=true;
		notifyAll();
	}
	synchronized void work2(){
		while(!part1_done){
			try {
                wait();
            } catch (InterruptedException e) {}
		}
		for (int i=11; i<= 15; i++){
			System.out.println(i);
		}
		part2_done = true;
		notifyAll();
		while(!part4_done){
			try {
                wait();
            } catch (InterruptedException e) {}
		}
		for (int i=16; i<= 20; i++){
			System.out.println(i);
		}
		part5_done=true;
		notifyAll();
	}
	synchronized void work3(){
		while(!part2_done){
			try {
                wait();
            } catch (InterruptedException e) {}
		}
		for (int i=21; i<= 25; i++){
			System.out.println(i);
		}
		part3_done = true;
		notifyAll();
		while(!part5_done){
			try {
                wait();
            } catch (InterruptedException e) {}
		}
		for (int i=26; i<= 30; i++){
			System.out.println(i);
		}
		notifyAll();
	}
}