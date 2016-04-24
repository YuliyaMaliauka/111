package draw;

public class Artist {
	StringBuilder s;
	boolean valueSet = false;
	synchronized String get() {
		while (!valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("exception interrupted");
			}
		for(int i=0; i<10; i++){
			s.append("*");
		}
			System.out.println("Drawning: "+ s);
			valueSet = false;
			notify();
			return s.toString();
	}
	synchronized void put(StringBuilder s) {
		while (valueSet)
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("exception interrupted");
			}
			this.s = s;
			
			s.delete(0, s.length());
			valueSet = true;
			System.out.println("Cleaning: "+ s);
			notify();
	}
}
