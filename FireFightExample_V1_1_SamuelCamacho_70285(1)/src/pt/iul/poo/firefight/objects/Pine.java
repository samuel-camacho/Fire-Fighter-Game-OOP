
package pt.iul.poo.firefight.objects;
import java.awt.Point;

/**
 * Represents a Pine in Collective of FireFight Objects
 * 
 * @author Samuel Camacho
 */
public class Pine extends FireFightObject {

/**
 * prob is the Probability of the Object to Burn	
 */
private double prob= 0.5;

/**
 * 
 * Cycle is the number of Cycles that the Object is Burning until it becomes Burnt
 * 
 * 
 */
private int cycle=10;

/**
 * 
 * Constructs and Creates a new Pine in given Point
 * 
 * @param point
 * @param "pine"
 * 
 * 	
 */
	public Pine(Point point) {
		super(point,"pine");
	}
	
/**
 * 
 * 
 * @return the Layer of the Object Pine is placed in the game 	
 */
	
	public int getLayer(){
		
		return 1;
		
	}
	
/**
 * @return true, because Pine is a burnable Object	
 */
	
	public boolean isBurnable(){
		
		return true;
	
	}
	
/**
 * 
 * @return the probability of the Object Pine to burn
 * 
 */
	public double getProb(){
		
		return prob;
	}
	
/**
 * 
 * @return False, because Pine isn´t a Catchable object (Like Bulldozer)	
 */
	@Override
	public boolean isCatcable(){
		
		return false;
	}
	
/**
 * 
 * @return the number of Cycles that Object Pine needs to Burn until it gets Burnt	
 */
	public int getCycle(){
		
		return cycle;
	}
	
	
/**
 * 
 * 	
 * @param n - Number of Cycles in Object Pine becomes equals to n
 */
	public void setCycle(int n){
		
		cycle=n;
	}

/**
 * 
 * When this Function is Called in class FireSimulator, in Function setBurnt,
 * and if the current Cycle of the objects isn´t 0
 * decrements one unity to the Number of Cycles in the Object Pine
 * 	
 */

public void startCycle (){
	
	if(cycle!=0){
		cycle--;
		}
	}
}



