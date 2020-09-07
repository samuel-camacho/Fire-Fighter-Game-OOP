package pt.iul.poo.firefight.main;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import pt.iul.poo.firefight.objects.Pine;

public class JUnitPine {
	
private	Point point;
private Pine test= new Pine(point);
private int before;

	@Before
	public void init(){
	point= new Point(9,6);	
	before=test.getCycle();
	
		}
	
	@Test 
	public void testSetPosition(){
		
		Point p= new Point(5,7);
		test.setPosition(p);
		Point p2=test.getPosition();
		assertEquals(p,p2 );
	
		}
	
	@Test
	public void testGetLayer() {
		
		int layer= test.getLayer();
		assertEquals(1, layer);
	}
	
	@Test
	public void testBurnable(){
		
		boolean burnable=test.isBurnable();
		assertTrue(burnable==true);
		
	}
	
	@Test
	
	public void testGetProb(){
		
		double prob= test.getProb();
		double p=0.5;
		assertEquals(p, prob, 0.0);
	}
	
	@Test
	
	public void testIsCatchable(){
		
		boolean catchable=test.isCatcable();
		
		assertTrue(catchable==false);
	}
	
	@Test
	
	public void testSetCycle(){
		
		test.setCycle(10);
		
		assertEquals(10,test.getCycle());
	}
	
	@Test
	
	public void testStartCycle(){
		
		
		
		test.startCycle();
		
		int after=test.getCycle();
		
		assertEquals(before-1, after);
	}
}