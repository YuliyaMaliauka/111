import java.util.Arrays;
import java.util.Random;

public class SortMass {
	
    public static void main(String[] args) throws InterruptedException {
    	Random r = new Random();
    	int n = 100000; 
		int[] arr = new int[n];
		int[] f = new int[n/2];
		int[] s = new int[n/2];
		
		for(int i = 0; i < arr.length; i++){
			arr[i] = r.nextInt();
        }		
		for(int i = 0; i < arr.length/2; i++){
        	f[i] = arr[i];
        }
		for(int i = arr.length/2; i < arr.length; i++){
    	    s[i-arr.length/2] = arr[i];
    	}		
		
		long start = System.nanoTime();
		for (int i = 1; i < 4; i++) {
            switch (i) {
                case 1:                 	
                	new Thread(new Thread1(f)).start();                 	
                case 2:                 	
                	new Thread(new Thread1(s)).start(); 		 
                case 3: 
                	new Thread(new Thread2(f,s)).start();                        
            }
        }		
    	long end = System.nanoTime();
    	long traceTime = end-start;
    	System.out.println("Time 1 - "+traceTime);
    	
		long start1 = System.nanoTime();
    	Arrays.sort(arr);
    	long end1 = System.nanoTime();
     	long traceTime1 = end1-start1;
     	System.out.println("Time 2 - "+traceTime1);
     
    }
	
public static class Thread1 extends Thread {
		
		int[] arr = null;
		Thread1(int[] arr)
		{
			this.arr = arr;			
		}
        public void run() {
        	synchronized (this) {		
        		Arrays.sort(arr); }      		        		
        }                
    }

public static class Thread2 extends Thread {
	
	int[] f = null;
	int[] s = null;
	int[] result;
	Thread2(int[] f, int[] s )
	{
		this.f = f;
		this.s = s;
		
	}
    public void run() {
    	synchronized (this) {	
    		int n = f.length;
    		int m = s.length;
            result = new int[n + m];               
            int i = 0, j = 0;
            int index = 0;
            while (i < n && j < m) {
                
                if (f[i] < s[j]) {
                    result[index] = f[i];
                    i++;
                } else {
                    result[index] = s[j];
                    j++;
                }

                index++;
            }
           while (i < n) {
                result[index] = f[i];
                index++;
                i++;
            }

            while (j < m) {
                result[index] = s[j];
                index++;
                j++;
            }
    }
    
    	/*for(int i = 0; i < result.length; i++){
			System.out.println("**"+result[i]);} */ 	
}
}
}
