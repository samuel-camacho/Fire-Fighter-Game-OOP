package pt.iul.poo.firefight.objects;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.poo.firefight.main.FireSimulator;

public class Plane extends FireFightObject {

	
private FireSimulator simulator;
private boolean emergencyCall=false;	
	
public Plane (Point point, FireSimulator simulator) {
		super(point, "plane3");
		this.simulator=simulator;
	}
	
	public int getLayer(){
		
		return 2;
		
	}
	
	
	public boolean isBurnable(){
		
		return false;
	}
	
	

public void move() {
	
Point newPoint = new Point (getPosition().x, getPosition().y-1);
Point newPoint2= new Point(getPosition().x, getPosition().y-2);

if(simulator.ActiveCheckFire(newPoint)||simulator.ActiveCheckFire(newPoint2)){
simulator.killFire(newPoint);
simulator.killFire(newPoint2);
	}
super.setPosition(newPoint2);
ImageMatrixGUI.getInstance().update();
	}


public boolean IsOutOfArea(){

Point newPoint2= new Point(getPosition().x, getPosition().y-2);
	if(newPoint2.y<0){
		return true;
		}
return false;	
}


public Point locateFire(ArrayList<Fire> firePositions){ //Locate the Column With the Max Fire

int currentColumn=0;
int maxFireColumn=0;
int maxfire=0;

while(currentColumn!=10){
int currentFires=0;

	for(Fire fire: firePositions){
		if(fire.getPosition().x==currentColumn){
		currentFires++;
		}
	}

	if(maxfire<currentFires){
		maxfire=currentFires;
		maxFireColumn=currentColumn;
		}
currentColumn++;
	}
return new Point(maxFireColumn, 9);
}


public void AirForceArrived(ArrayList<Fire>firePositions, ArrayList<FireFightObject> objects){ //PLANE STARTS IN MAX FIRE COLUMN IN ( plane.locateFire(firePositions));
	
setPosition(locateFire(firePositions));
simulator.killFire(getPosition());
ImageMatrixGUI.getInstance().addImage(this);
objects.add(this);
callPlane(true);
}


public boolean getCall(){
	
	return emergencyCall;

	}

public void callPlane(boolean b){
	
	emergencyCall=b;
	}
}


