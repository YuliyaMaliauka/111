package cars;

import java.util.logging.Level;
import java.util.logging.Logger;



public class Car implements Runnable {
	public static final long MAX_DISTANCE = 10000;        
    public static volatile int countSeconds = 5000;
    
        private final Logger log = Logger.getLogger(Car.class.getName());
        private long friction;
        private long distance;
        public String name;
        public Thread t;
        
        public Car(String name, long friction) {
            this.name = name;
            this.friction = friction;
            t = new Thread(this, name);
            log.info(name+" on start");
            t.start();
        }
        public synchronized long getDistanse(){return distance;}
        @Override
        public void run() {
        	
        	try {
        		 if (friction<countSeconds){
                     while (distance < MAX_DISTANCE) {               	 
                	              		      			
                         Thread.sleep(friction);
                       
                         distance += 100;
                         System.out.println(name + " " + distance);
                         
                     }  }
                     else{
                    	 System.out.println(name + " Disqualify"); 
                     }
                	              	 
                 
                     
        		                  
             } catch (InterruptedException ex ) {
                 log.log(Level.SEVERE, "Exception: ", ex);
             }
                 
             log.fine("some minor, debug message");
         
             if (log.isLoggable(Level.FINE)) {
                 log.fine("Some CPU consuming message: ");
             }            
        }
}
