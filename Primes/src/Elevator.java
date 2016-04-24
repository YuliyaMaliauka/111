import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class Elevator {
	private static final Phaser PHASER = new Phaser(1);//Сразу регистрируем главный поток
    //Фазы 0 и 6 - это автобусный парк, 1 - 5 остановки

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Passenger> passengers = new ArrayList<>();
        Passenger pas1 = new Passenger(1, 1, 2);
        Passenger pas2 = new Passenger(2, 2, 5);
        Passenger pas3 = new Passenger(3, 3, 4);
        Passenger pas4 = new Passenger(4, 3, 2);
        Passenger pas5 = new Passenger(5, 5, 1);
        passengers.add(pas1);
        passengers.add(pas2);
        passengers.add(pas3);
        passengers.add(pas4);
        passengers.add(pas5);
      
        for (int i = 0; i < 11; i++) {
            switch (i) {
                case 0:
                    //System.out.println("Elevator moves up.");
                    PHASER.arrive();//В фазе 0 всего 1 участник - автобус
                    break;
                case 10:               	
                    PHASER.arriveAndDeregister();//Снимаем главный поток, ломаем барьер
                    break;  
                default:
                    int floorStop = PHASER.getPhase();                   
                    if(floorStop<=5) System.out.println("Floor № " + floorStop);
                    if(floorStop>5) System.out.println("Floor № " + (int)(10-floorStop));                    
                    for (Passenger p : passengers){ //Проверяем, есть ли пассажиры на остановке
                        if (p.floorStart == floorStop ) {
                            PHASER.register();//Регистрируем поток, который будет участвовать в фазах
                            p.start();        // и запускаем
                        }
                      
                    }
                    PHASER.arriveAndAwaitAdvance();//Сообщаем о своей готовности
                    
                    
            }
        }

    }

    public static class Passenger extends Thread {
        private int floorStart;
        private int floorFinish;
        //private int floorFinish1;
        
        private int numberPassenger;
        
        public Passenger(int pas, int start, int finish) {
        	numberPassenger = pas;
        	floorStart = start;            
            if(start<finish)
            floorFinish = finish;
            if(start>finish)
            floorFinish = 10-finish;
            System.out.println("Persons № "+numberPassenger+" waits on the "+ floorStart +" floor and it is necessary for him on the "+ finish+ " floor");
        }

       
        
        @Override
        public void run() {
        	
            try {
            	System.out.println("Persons № "+numberPassenger + " has entered the elevator.");
            if(floorStart<floorFinish){
                           
                while (PHASER.getPhase()  < floorFinish ) //Пока автобус не приедет на нужную остановку(фазу)
                    PHASER.arriveAndAwaitAdvance();     //заявляем в каждой фазе о готовности и ждем
   
                Thread.sleep(1);
                System.out.println("Persons № "+numberPassenger + " left the elevator.");
                PHASER.arriveAndDeregister(); //Отменяем регистрацию на нужной фазе 
              }if(floorStart>floorFinish) {
            	 // System.out.println("Persons № "+numberPassenger + " has entered the elevator.");
            	  while (PHASER.getPhase()  > floorFinish ) //Пока автобус не приедет на нужную остановку(фазу)
                      PHASER.arriveAndAwaitAdvance();     //заявляем в каждой фазе о готовности и ждем
     
                  Thread.sleep(1);
                  System.out.println("Persons № "+numberPassenger + " left the elevator.");
                  PHASER.arriveAndDeregister(); //Отменяем регистрацию на нужной фазе
              }
            
            } catch (InterruptedException e) {
            }
        	
        	
        }

       
    }
}
