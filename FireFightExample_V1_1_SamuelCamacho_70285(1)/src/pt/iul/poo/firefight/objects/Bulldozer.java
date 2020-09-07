package pt.iul.poo.firefight.objects;

import java.awt.Point;
import java.util.ArrayList;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.poo.firefight.main.FireSimulator;


public class Bulldozer extends FireFightObject {

private FireSimulator simulator;
	
public Bulldozer(Point point, FireSimulator simulator) {
		super(point, "bulldozer");
		this.simulator=simulator;
	}
	
	
	
	public int getLayer(){
		
		return 2;
		
	}
	
	
	public boolean isBurnable(){
		
		return false;
	
	}

@Override
public void move(Direction newDirection) {
Point newPoint=super.ActiveNewPoint(newDirection);

if(!simulator.ActiveCheckFire(newPoint)){
	if(newPoint.x < 10 && newPoint.x >=0 && newPoint.y<10 && newPoint.y >=0){
		if((simulator.getObjectOnPoint(newPoint).getLayer()==1)){
			ImageMatrixGUI.getInstance().removeImage(simulator.getObjectOnPoint(newPoint));
			simulator.removeObject(simulator.getObjectOnPoint(newPoint));
			}
		}
super.move(newDirection);
setBullName(newDirection);
	}
ImageMatrixGUI.getInstance().update();
}
	

public void setBullName(Direction d) {

	switch (d) {
		case UP:
			super.setName("bulldozer_up");
			break;
		case DOWN:
			super.setName("bulldozer_down");
			break;
		case RIGHT:
			super.setName("bulldozer_right");
			break;
		case LEFT:
			super.setName("bulldozer_left");
			break;
		}
	}

@Override
public boolean isCatcable(){
	
	return true;
	}
}
