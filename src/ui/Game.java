package ui;

import world.HexMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
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
            	
            }
        });
		
		// Generate toolbar
		
		ToolBar toolbar = new ToolBar(drawButton,selectButton,generateButton);
		return toolbar;
	}

}
