import draw.Artist;
import draw.Eraser;
import draw.Pencil;

public class Drawing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Artist a = new Artist();
		new Pencil(a);		
		new Eraser(a);
		System.out.println("take up Ctrl+C");
	}

}
