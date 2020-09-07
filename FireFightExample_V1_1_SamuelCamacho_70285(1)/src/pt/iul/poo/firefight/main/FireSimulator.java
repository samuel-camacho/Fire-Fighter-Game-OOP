package pt.iul.poo.firefight.main;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.poo.firefight.objects.Bulldozer;
import pt.iul.poo.firefight.objects.Burnt;
import pt.iul.poo.firefight.objects.Eucaliptus;
import pt.iul.poo.firefight.objects.Fire;
import pt.iul.poo.firefight.objects.FireFightObject;
import pt.iul.poo.firefight.objects.Fireman;
import pt.iul.poo.firefight.objects.Grass;
import pt.iul.poo.firefight.objects.Land;
import pt.iul.poo.firefight.objects.Pine;
import pt.iul.poo.firefight.objects.Plane;

public class FireSimulator implements Observer {

	private static final String CONFIG_DIR = "levels";
	private static final String CONFIG_FILE = "landscape.txt";
	private Fireman fireman;
	private Bulldozer bull;
	private Plane plane = new Plane(null, this);
	private ArrayList<FireFightObject> objects = new ArrayList<FireFightObject>();
	private ArrayList<Fire> firePositions = new ArrayList<Fire>();

	private static FireSimulator INSTANCE = null;

	public static FireSimulator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FireSimulator();
		}
		return INSTANCE;
	}

	private FireSimulator() {
		try {
			ImageMatrixGUI.getInstance().addObserver(this);
			ArrayList<ImageTile> graphic = readConfig(CONFIG_DIR + "/" + CONFIG_FILE);
			ImageMatrixGUI.getInstance().addImages(graphic);
			ImageMatrixGUI.getInstance().go();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*----------------------READ FILE / BUILD LEVEL & ADD OF OBJECTS------------------*/

	private ArrayList<ImageTile> readConfig(String fName) throws FileNotFoundException {

		ArrayList<ImageTile> levelTiles = new ArrayList<ImageTile>();

		try {
			Scanner input = new Scanner(new FileReader(fName));
			int j = 0;
			while (input.hasNext()) {
				String line = input.nextLine();
				char[] letter = line.toCharArray();

				for (int i = 0; i != letter.length; i++) {

					if (letter[i] == 'p') {
						Pine p = new Pine(new Point(i, j));
						objects.add(p);
						levelTiles.add(p);
					}
					if (letter[i] == 'e') {
						Eucaliptus e = new Eucaliptus(new Point(i, j));
						objects.add(e);
						levelTiles.add(e);
					}
					if (letter[i] == '_') {
						Grass grass = new Grass(new Point(i, j));
						objects.add(grass);
						levelTiles.add(grass);
					}
				}
				if (line.contains("Fireman")) {
					String x = String.valueOf(line.charAt(8));
					String y = String.valueOf(line.charAt(10));

					fireman = new Fireman(new Point(Integer.parseInt(x), Integer.parseInt(y)), this);
					objects.add(fireman);
					levelTiles.add(fireman);
				}
				if (line.contains("Bulldozer")) {
					String x = String.valueOf(line.charAt(10));
					String y = String.valueOf(line.charAt(12));

					bull = new Bulldozer(new Point(Integer.parseInt(x), Integer.parseInt(y)), this);
					objects.add(bull);
					levelTiles.add(bull);
				}
				if (line.contains("Fire ")) {
					String x = String.valueOf(line.charAt(5));
					String y = String.valueOf(line.charAt(7));

					Fire fire = new Fire(new Point(Integer.parseInt(x), Integer.parseInt(y)));
					firePositions.add(fire);
					levelTiles.add(fire);
				}
				j++;
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("O Ficheiro Não Foi Encontrado!");
			e.printStackTrace();
		}
		for (int i = 0; i != 10; i++) {
			for (int j = 0; j != 10; j++) {
				Land floor = new Land(new Point(i, j));
				objects.add(floor);
				levelTiles.add(floor);
			}
		}
		return levelTiles;
	}
	/*--------------FIRE SPREADING FUNCTIONS---------------------*/

	public boolean isOnFire(FireFightObject object) {

		for (Fire fire : firePositions) {
			if (fire.getPosition().equals(object.getPosition())) {
				return true;
			}
		}
		return false;
	}

	public boolean CanBurn(FireFightObject ob) { // See's if the area can Burn!

		Point OBpoint = ob.getPosition();
		for (Fire fire : firePositions) {
			if (fire.nextFire().equals(OBpoint)) {
				if (!(isOnFire(ob))) {
					if (!(OBpoint.equals(fireman.getPosition()) || OBpoint.equals(bull.getPosition()) || OBpoint.equals(plane.getPosition()))) {
						if (ob.isBurnable()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void newFire(Point p) {// AUX FUNCTION

		Fire fire = new Fire(p);
		ImageMatrixGUI.getInstance().addImage(fire);
		ImageMatrixGUI.getInstance().update();
		firePositions.add(fire);
	}

	public void setOnFire() { // This function Spread's the Fire in the Forest

		Random r = new Random();
		double p = r.nextDouble();

		for (FireFightObject ob : objects) {
			if (CanBurn(ob)) {
				if (ob.getProb() <= p) {
					newFire(ob.getPosition());
				}
			}
		}
	}

	public void killFire(Point p) {

		Iterator<Fire> itr = firePositions.iterator();

		while (itr.hasNext()) {
			Fire fire = itr.next();
			if (fire.getPosition().equals(p)) {
				itr.remove();
				ImageMatrixGUI.getInstance().removeImage(fire);
				ImageMatrixGUI.getInstance().update();
			}
		}
	}
	/*------------------ ACTIVE OBJECTS FUNCTIONS-------------------------*/

	public boolean ActiveCheckFire(Point p1) { // Used to Fireman and Bull check if objects are on fire!
		for (FireFightObject ob : objects) {
			if (ob.getPosition().equals(p1)) {
				if (isOnFire(ob)) {
					return true;
				}
			}
		}
		return false;
	}

	public FireFightObject getObjectOnPoint(Point p) { // Used On Bull Class
		FireFightObject object = null;
		for (FireFightObject ob : objects) {
			if (ob.getPosition().equals(p)) {
				object = ob;
				break;
			}
		}
		return object;
	}

	public void removeObject(FireFightObject ob) {
		objects.remove(ob);
	}

	public void controlBull(int key) {

		fireman.setDriveMode(true);
		if (Direction.isDirection(key)) {
			if (insideTransport(fireman.getPosition()) && fireman.getDriveMode() == true) {
				ImageMatrixGUI.getInstance().removeImage(fireman);
				bull.move(Direction.directionFor(key));
				fireman.setPosition(bull.getPosition());
			}
		} else if (key == KeyEvent.VK_ENTER) {
			ImageMatrixGUI.getInstance().addImage(fireman);
			fireman.setDriveMode(false);
		}
	}

	public boolean insideTransport(Point p) { // See's if there's a Catchable object, in this case, a Transport(Bull)
		for (FireFightObject ob : objects) {
			if (ob.getPosition().equals(p)) {
				if (ob.isCatcable()) {
					return true;
				}
			}
		}
		return false;
	}

	public ArrayList<Fire> getFireLocations() { // Used On Plane Class to locate the Column with Max Fire

		return firePositions;
	}

	public void FiremanCallPlane(int key) {

		if (key == KeyEvent.VK_A && plane.getCall() == false) {
			plane.AirForceArrived(firePositions, objects);
		}
		if (Direction.isDirection(key) && plane.getCall() == true) {
			if (plane.IsOutOfArea()) {
				Point p = new Point(plane.getPosition().x, plane.getPosition().y - 1);
				killFire(p);
				plane.callPlane(false);
			}
			plane.move();
		}
	}

	public void controlFireman(int key) {
		if (Direction.isDirection(key)) {
			fireman.move(Direction.directionFor(key));
		}
	}

	/*----------------BURNT FUNCTIONS---------------------*/

	public FireFightObject Burned(FireFightObject ob) { // AUX FUNCTION
		Burnt burnt = new Burnt(ob.getPosition());
		ImageMatrixGUI.getInstance().removeImage(ob);
		ImageMatrixGUI.getInstance().addImage(burnt);
		killFire(ob.getPosition());
		return burnt;
	}

	public void setBurnt() {
		ListIterator<FireFightObject> itr = objects.listIterator();
		while (itr.hasNext()) {

			FireFightObject ob = itr.next();
			if (ob.isBurnable()) {
				if (isOnFire(ob)) {
					if (fireman.getMoves() % 2 != 0) {
						if (ob.getCycle() == 0) {
							itr.remove();
							itr.add(Burned(ob));
						}
						ob.startCycle();
					}
				}
			}
		}
	}

/*----------------------------UPDATE--------------------------------*/

	@Override
	public void update(Observable arg0, Object arg1) {
		int key = (Integer) arg1;
		controlFireman(key);
		controlBull(key);
		FiremanCallPlane(key);
		setOnFire();
		setBurnt();
	}
}
