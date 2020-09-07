package pt.iul.poo.firefight.objects;

import java.awt.Point;

public class Burnt extends FireFightObject{
	
	
public Burnt(Point point){
		
		super(point, "burnt");
	}

public int getLayer(){
	
	return 1;
	
}

public boolean isBurnable(){
	
	return false;
}
	
}