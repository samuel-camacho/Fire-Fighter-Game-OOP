package pt.iul.poo.firefight.objects;
import java.awt.Point;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;

/**
 * Abstract Class/ It's the Base Class to all Classes derived from FireFightObject
 * 
 * @author Samuel Camacho
 *
 *
 */
public abstract class FireFightObject implements ImageTile{
	
	/**
	 * position where the FireFightObject Starts
	 */
	private Point position;
	
	/**
	 * 
	 * name of the FireFightObject 
	 */
	private String name;

	
	/**
	 * Constructs and Create a Class FireFightObject and, implements the Interface ImageTile
	 *  @param  position, sets the Position of the FireFightObject
     *  @param  name, sets the Name of the Object, needs to be equal to the image name in the Folder images
	 */
	public FireFightObject(Point position, String name) {
		this.position = position;
		this.name=name;
	}

	/**
	 * 
	 * @return the FireFightObject name
	 * @throws no exception
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the position of the given FireFightObject
	 * 
	 * @throws no exception
	 */
	@Override
	public Point getPosition() {
		return this.position;
	}
	
	/**
	 * 
	 * @return the Layer where the Given FireFightObject stands
	 * 
	 * @throws no exception
	 */
	@Override
	public int getLayer() {
		return 1;
	}
	
	/**
	 * 
	 * @param name - sets the nameGiven FireFightObject
	 * @throws no exception
	 */
	public void setName(String name){
		
		this.name=name;
	}

	/**
	 * 
	 * @param point sets the Position of the Given FireFightObject
	 * 
	 * @throws no exception
	 */
	public void setPosition(Point point) {
		position = point;
	}
	
/**
 * 	
 * @param newDirection Determinate the Direction of the Given FireFghtObject
 * 
 * FireFightObject sets in the newPoint, if newPoint is within the Limits
 * 
 * @throws no exception
 */
	
public void move(Direction newDirection) {

Point newPoint=ActiveNewPoint(newDirection);			
if(newPoint.x < 10 && newPoint.x >=0 && newPoint.y<10 && newPoint.y >=0){
		setPosition(newPoint);
			
	}
ImageMatrixGUI.getInstance().update();
}

/**
 * 
 * @param newDirection  Determinate the Direction of the Given FireFghtObject
 * Point p is equal to newDirection but as a vector
 * @return the newPoint, which is the current Position of the Given FIreFighObject plus the Point p
 * 
 * @throws no exception
 */
public Point ActiveNewPoint(Direction newDirection){

	Point p= newDirection.asVector();
	 
		return new Point (getPosition().x + p.x, getPosition().y +p.y);
}

/**
 * 
 * @return true if the Given FireFightObject to be Analyze is Burnable( like Pine ou Grass)
 * by default it returns true
 * 
 * @throws no exception
 */
public boolean isBurnable(){
		
		return true;
	}

/**
 * 
 * @return the probability of the given FireFightObject to Burn
 * by default it returns 0.0
 * 
 * @throws no exception
 */
public double getProb(){
	
	return 0.0;
}

/**
 * 
 * @return true if the Given FireFightObject is Catchable
 * by default it returns false
 * 
 * @throws no exception
 */
public boolean isCatcable(){
	
	return false;
}

/**
 * 
 * @return the number of Cycles of the Give FireFightObject, that need to Burn until it gets Burnt
 * by defautl it returns -1 
 * 
 * @throws no exception
 */

public int getCycle(){
	
	return -1;

		}

/**
 * if the Given FireFightObject is On Fire, than in decrements one value to Object Cycle until it gets to 0
 * by default its a empty function that do nothing
 * its used on Burnable Objects(Pine, Eucaliptus, Grass)
 * 
 * 
 * @throws no exception
 */
public void startCycle(){
	
		}
}