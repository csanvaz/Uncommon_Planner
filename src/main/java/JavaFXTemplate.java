import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;


public class JavaFXTemplate extends Application {
	HashMap<String, Scene> sceneMap; //Hashmap that will store different scenes for game

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Welcome to Keno!");
		// create our HashMap of scenes
		sceneMap = new HashMap<>();
		//Different scenes created and put in hashmap scenemap
		// our first scene is the home scene
		sceneMap.put("home", homeScene(primaryStage));
		primaryStage.setScene(sceneMap.get("home"));
		primaryStage.show();
	}


	// this method returns our home screen scene
	public Scene homeScene(Stage pStage) {

		// borderpane for our initial home screen
		BorderPane homePane = new BorderPane();

		//Creating image for the background
		Image homeBackGround = new Image( "kenoBackground.jpg");
		BackgroundImage bGround = new BackgroundImage (homeBackGround,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background hbg = new Background(bGround);
		homePane.setBackground(hbg);

		return new Scene(homePane, 700, 700);
	}
}
