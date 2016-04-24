import java.util.ArrayList;
import java.util.List;


import cars.Car;



public class goCars  {
	
	public static void main(String[] args) throws InterruptedException {
		List<Car> cars = new ArrayList<Car>();		
		Car car1 = new Car("car1", 200);
		Car car2 = new Car("car2", 5000);
		Car car3 = new Car("car3", 300);
		cars.add(car1);cars.add(car2);cars.add(car3);
		List<Car> carFinish = new ArrayList<Car>();
		try{
			System.out.println("Rice started");
			      
			for(Car car: cars){		
			car.t.join();
			
			if(car.getDistanse()>=Car.MAX_DISTANCE){
				carFinish.add(car);
			}
			}
			
			
		} catch(InterruptedException e) {
			System.out.println("Thread interrupt");
		}
		
		System.out.println("Win "+ carFinish.get(0).name);
			
		}
    }
            
    
  

