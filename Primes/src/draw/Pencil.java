package draw;



public class Pencil implements Runnable {
	Artist a;
	public Pencil(Artist a){
		this.a = a;
		new Thread(this, "Pencil").start();
	}
	public void run(){
		
		while(true){			
			a.get();			
		}			
	}
	
	
	
	
	
}
