package pt.iul.poo.firefight.objects;

import java.awt.Point;

public class Eucaliptus extends FireFightObject {
	
private double prob=0.1;
private int cycle=6;

	public Eucaliptus(Point point){
		
		super(point,"eucaliptus");
	
	}
	
	
	public int getLayer(){
		
		return 1;
	
	}
	

public boolean isBurnable(){
	
	return true;

}


public void setProb(int n){
	
	this.prob=n;
	
}


public double getProb(){
	
	return prob;
}
public int getCycle(){
	
	return cycle;
}



public void startCycle (){
	
	
	if(cycle!=0){
		cycle--;
	}
}
}






