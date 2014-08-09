package world;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Hexagon implements Tile {
	
	private int[][] neighbours = {{1,-1,0},{1,0,-1},{0,1,-1},{-1,1,0},{-1,0,1},{0,-1,1}};
	
	/**
	 * The worldmap this hexagon belongs to
	 */
	private HexMap world;
	
	/**
	 * Position of this hexagon saved as an axialCoordinate
	 */
	private axialCoordinate position;
	
	/**
	 * Size of the hexagon
	 */
	private int size;
	
	/**
	 * Movement value of the tile. This is the value it costs to enter this tile. Leaving a tile is free.
	 */
	private hexType overlay;

	public Hexagon(int size, axialCoordinate ac){
		this.setPosition(ac);
		this.setSize(size);
		createOverlay();
	}
	
	private void createOverlay() {
		double test = Math.random() * 3;
		if (test < 1){
			this.overlay = new HexTypePlains();
		} else if (test < 2){
			this.overlay = new HexTypeForest();
		} else {
			this.overlay = new HexTypeWater();
		}	
	}
	@Override
	public Coordinate getPosition() {
		return position;
	}
	
	private void setPosition(axialCoordinate ac){
		this.position = ac;
	}
	
	
	/**
	 * 
	 * Get neighbours of this hexagon
	 * 
	 */
	
	public HexMap getNeighbours(){
		// Get cubeCoordinate for this hexagon
		cubeCoordinate cube = position.Convert();
		int i = 0;
		int[] coordinates = {cube.getX(),cube.getY(),cube.getZ()};
		HexMap result = new HexMap();
		while (i<6){
			cubeCoordinate tempCube = new cubeCoordinate(neighbours[i][0] + coordinates[0], neighbours[i][1] + coordinates[1], neighbours[i][2] + coordinates[2]);
			result.addHexagon(tempCube.Convert(), new Hexagon(20, tempCube.Convert()));
			i++;
			
		}
		return filter(result);
}

	/**
	 * Filter the input hexmap to see if they belong to the worldmap.
	 * This basically removes all hexagons from the input that don't belong.
	 * @param input
	 */
	
	private HexMap filter(HexMap input) {
		HexMap inputFiltered = new HexMap();
		Iterator<Entry<axialCoordinate, Hexagon>> it = input.getHashOfHexes().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<axialCoordinate, Hexagon> pairs = it.next();
			if (world.getHashOfHexes().containsKey(pairs.getKey())){
				inputFiltered.getHashOfHexes().put(pairs.getKey(), pairs.getValue());
			}
		}
		return inputFiltered;
	}
	
	public int getSize() {
		return size;
	}

	private void setSize(int size) {
		this.size = size;
	}
	public Color getHexColor() {
		return overlay.getColor();
	}
	public void setHexColor(Color hexColor) {
		this.overlay.setColor(hexColor);
	}
	
//	public void switchHexColor(){
//		if (this.hexColor == Color.BLUE){
//			this.hexColor = Color.WHITE;
//		}else{
//			this.hexColor = Color.BLUE;
//		}
//	}
	public void setHexMap(HexMap hexMap) {
		this.world = hexMap;
	}
}
