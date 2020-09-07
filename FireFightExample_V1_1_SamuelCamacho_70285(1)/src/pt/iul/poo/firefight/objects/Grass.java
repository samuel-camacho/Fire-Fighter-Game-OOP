package pt.iul.poo.firefight.objects;

import java.awt.Point;

public class Grass extends FireFightObject {
	
private double prob=0.2;
private int cycle=3;

public Grass(Point point){
		
		super(point,"grass");
		
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
		
		cycle--;
		if(cycle==0){
			return;
		}
	}
}


