package pt.iul.poo.firefight.objects;

import java.awt.Point;

public class Land extends FireFightObject {


	public Land(Point point){
		
		super(point,"land");
	}
	
	
public int getLayer(){
	
	return 0;

	}


public boolean isBurnable(){
	
	return false;
}

@Override
public boolean isCatcable(){
	
	return false;
}
}

