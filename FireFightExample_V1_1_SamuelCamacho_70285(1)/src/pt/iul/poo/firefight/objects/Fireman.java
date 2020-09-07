package pt.iul.poo.firefight.objects;

import java.awt.Point;
import java.util.Random;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.poo.firefight.main.FireSimulator;

public class Fireman extends FireFightObject {

private FireSimulator simulator;
private boolean driving=true;
private int moves=0;


public Fireman(Point position, FireSimulator simulator) {
		super(position, "fireman");
		this.simulator=simulator;
	}

public int getLayer() {
		return 3;
	}
	
public boolean isBurnable(){
		
		return false;
	}

@Override
public void move(Direction newDirection) {
		
Point newPoint = super.ActiveNewPoint(newDirection);

if(simulator.insideTransport(getPosition())){
	if(getDriveMode()==false){
		simulator.killFire(newPoint);
			super.move(newDirection);
				moves++;
				}
			}
else if(simulator.ActiveCheckFire(newPoint)){
	simulator.killFire(newPoint);
		}
else{		
	super.move(newDirection);
	moves++;
	}
}

public void setDriveMode(boolean b){
	
	driving=b;
}

public boolean getDriveMode(){
	
	return driving;
	
}

public int getMoves(){
	
	return moves;
}

}
