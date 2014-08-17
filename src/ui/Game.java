package ui;

import java.util.Iterator;

import com.sun.javafx.event.EventHandlerManager;

import world.HexMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class Game extends Application {
	
	private MainController controller;
	private BorderPane root;
	
	public static void main(String[] args) {
			launch(args);
	  }

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new MainController();
		try {
//			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
			root = new BorderPane();
			ToolBar toolbar = addToolBar();
			HBox statusbar = new HBox();
			Pane content = addContent();
			root.setTop(toolbar);
			root.setCenter(content);
			root.setBottom(statusbar);
			Scene scene = new Scene(root,1000,750);
//			//scene.getStylesheets().add("/fxml/styles/main.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle("Hex Game");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private Pane addContent() {
		Pane mapPane = new Pane();
		mapPane.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				controller.highlightNeighbours((int)event.getX(), (int)event.getY());
			}
		});
		
		return mapPane;
	}

	private ToolBar addToolBar() {
		
		// Radio Buttons
		
		ToggleGroup buttonGroup = new ToggleGroup();
		RadioButton selectButton = new RadioButton("Select");
		selectButton.setToggleGroup(buttonGroup);
		selectButton.setSelected(true);
		RadioButton drawButton = new RadioButton("Draw");
		drawButton.setToggleGroup(buttonGroup);
		drawButton.setSelected(false);
		
		// Push Buttons
		
		Button generateButton = new Button("Generate New!");
		generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            	controller.doGenerateNewMap((Pane)root.getCenter());
            	
            	Iterator<Node> it = ((Pane) root.getCenter()).getChildren().iterator();
            	while (it.hasNext()){
            		final Node nextNode = it.next();
            		if (nextNode instanceof Polygon){
            			nextNode.setOnMouseClicked(new EventHandler<MouseEvent>(){

            				@Override
            				public void handle(MouseEvent event) {
            					controller.highlight((Polygon)nextNode);
            				}
            			});
            		}
            	}
            	
            }
        });
		
		// Generate toolbar
		
		ToolBar toolbar = new ToolBar(drawButton,selectButton,generateButton);
		return toolbar;
	}

}
