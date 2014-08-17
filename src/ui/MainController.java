package ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import world.HexMap;

public class MainController {

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	

	public void doGenerateNewMap(){
		HexMap worldMap = new HexMap(40,30);
	}


	public void doGenerateNewMap(Node center) {
		
	}


	public void doGenerateNewMap(Pane center) {
		// TODO Fix this method
		//center.getChildren().addAll(polygons);
		
	}

}
