package draw;

public class Eraser implements Runnable {
	Artist a;
	public Eraser(Artist a){
		this.a = a;
		new Thread(this, "Eraser").start();
	}
	public void run(){
		while(true){	
			StringBuilder s = new StringBuilder();
			a.put(s);			
		}
	}
}
