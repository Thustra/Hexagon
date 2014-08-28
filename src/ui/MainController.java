package ui;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import world.Coordinate;
import world.HexMap;
import world.Hexagon;
import world.axialCoordinate;

public class MainController {
	
	private HexMap worldMap;

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	public void doGenerateNewMap(Pane center) {
		
		//Remove all old map elements
		center.getChildren().removeAll(center.getChildren());
		
		this.worldMap = new HexMap(40,30);
		
		Hashtable<axialCoordinate, Hexagon> hexesToDraw = worldMap.getHashOfHexes();


		Iterator<Map.Entry<axialCoordinate, Hexagon>> it = hexesToDraw.entrySet().iterator();

		while (it.hasNext()){
			Map.Entry<axialCoordinate, Hexagon> entry = it.next();

			// Calculate the centerpoint of a hexagon
			double center_x =  (entry.getValue().getSize() * Math.sqrt(3) * (entry.getKey().getQ() + (double)entry.getKey().getR()/2));
			double center_y = entry.getValue().getSize() * 3/2 * (double)entry.getKey().getR();

			Polygon p = new Polygon();
			Double[] polygonPoints = new Double[12];
			for (int j = 0; j < 12; j=j+2){
				polygonPoints[j] = 100+center_x + entry.getValue().getSize() * Math.cos(2 * Math.PI / 6 * (j/2 + 0.5));
				polygonPoints[j+1] = 100+center_y + entry.getValue().getSize() * Math.sin(2 * Math.PI / 6 * (j/2 + 0.5));
			}
			p.getPoints().addAll(polygonPoints);
			p.setFill(Color.GREENYELLOW);
			center.getChildren().add(p);
		}
	}
	
	// TODO Make this method work with a polygon object 
	// Figure out if I should abstract the JavaFX from the rest, aka can things behind the controller know of 'polygon'
	// If not we need to work with x and y and link this to 'polygon'
	
	public void highlightNeighbours(int x, int y) {
		if (worldMap == null){
			throw new NullPointerException();
		}else{
			// Sets the internal reprsentation of the hexagon to have RED as color.
			HexMap nb = worldMap.getHexagonByScreenCoordinates(x, y).getNeighbours();	
			Iterator it = nb.getHashOfHexes().entrySet().iterator();
			while(it.hasNext()){
				Map.Entry pairs = (Map.Entry)it.next();
				worldMap.getByCoordinate((Coordinate) pairs.getKey()).setHexColor(Color.RED);
			
			}
		}
	}

	public void highlight(Polygon nextNode) {
		if (nextNode.getFill() == Color.GREENYELLOW){
			nextNode.setFill(Color.RED);
		} else{
			nextNode.setFill(Color.GREENYELLOW);
		}
	}		
}

