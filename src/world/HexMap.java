package world;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class HexMap {
	
	private Hashtable<axialCoordinate, Hexagon> hashOfHexes;
	private int SIZE = 10;
	
	/**
	 * Creates a generic rectangles of hexes.
	 * 
	 * @param width
	 * @param heigth
	 */
	
	public HexMap (int width, int heigth){
		hashOfHexes = new Hashtable<axialCoordinate, Hexagon>();
		for (int q = 0; q<width; q++){
			for ( int r = 0; r<heigth; r++){
				axialCoordinate ac = new axialCoordinate((int) (q-Math.floor(r/2)), r);
				Hexagon hex = new Hexagon(SIZE, ac);
				hex.setHexMap(this);
				hashOfHexes.put(ac, hex);
			}
		}
	}

	/**
	 * Create and empty HexMap
	 */
	
	public HexMap(){
		hashOfHexes = new Hashtable<axialCoordinate, Hexagon>();
	}
	
	/**
	 * Add a hexagon to the map
	 * @return 
	 */
	
	public void addHexagon(axialCoordinate ac, Hexagon hex){
		hashOfHexes.put(ac, hex);
	}

	public Hashtable<axialCoordinate, Hexagon> getHashOfHexes() {
		return hashOfHexes;
	}

	public void setHashOfHexes(final Hashtable<axialCoordinate, Hexagon> hashOfHexes) {
		this.hashOfHexes = hashOfHexes;
	}
	
	public Hexagon getByCoordinate(Coordinate position){
		return hashOfHexes.get(position);
	}
	
	public void printMap(){
		Iterator it = this.getHashOfHexes().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println(pairs.getKey() + " hashcode: "+ pairs.getKey().hashCode() + " " + pairs.getValue());
		}
	}
	
	public Hexagon getHexagonByScreenCoordinates(int x, int y){
		// Find out which hexagon was clicked
		x=x-100;
		y=y-100;
		double q = (double)( Math.sqrt(3) * x/3 - y/3)/SIZE;
		double r = (double) 2/3 * (double) y / SIZE;
		
		// create a coordinate with doubles
		axialCoordinate position = new axialCoordinate(q, r);
		// convert to cubeCoordinate
		cubeCoordinate positionCube = position.ConvertDouble();
		// use hex rounding
		positionCube.hex_round();
		// convert back to axial
		position = positionCube.ConvertDouble();
		
		return this.getByCoordinate(position);
	}

	/**
	 * Check if the tiles in this map are part of the worldmap
	 * @param worldmap
	 */
	
	public void validate(HexMap worldmap) {
		
	}

}
