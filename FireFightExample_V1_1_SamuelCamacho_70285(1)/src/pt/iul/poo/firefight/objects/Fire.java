package pt.iul.poo.firefight.objects;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;



public class Fire extends FireFightObject {
	
	
public Fire(Point position) {
		super(position,"fire");

	
	}

	public int getLayer() {
		return 2;
	}
	

public boolean isBurnable(){
		
		return false;
	}


public Point randPoint(){
Random rand= new Random();
	int n= rand.nextInt(4);
		Direction[] directions= Direction.values();
			Direction d1= directions[n];
				Point p2= d1.asVector();	
return p2;
}



public Point nextFire(){

return new Point(getPosition().x+randPoint().x, getPosition().y+randPoint().y);
	
	}

}	
	
	
	
	
	