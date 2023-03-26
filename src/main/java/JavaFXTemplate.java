import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
		primaryStage.setTitle("Welcome to {site name here}!");
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

		// creating a new menu
		MenuBar homeMenu = menuBarFunction(pStage);

		//Creating image for the background
		Image homeBackGround = new Image( "gradient.png");
		BackgroundImage bGround = new BackgroundImage (homeBackGround,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background hbg = new Background(bGround);
		homePane.setBackground(hbg);
		//homePane.setStyle("-fx-background-color: wheat ;");
		homePane.setTop(homeMenu);
		Text quote = new Text(10, 20, "\"Nothing diminishes anxiety \nfaster than action.\"");
		quote.setStyle("-fx-font-size: 40px;" + "-fx-fill: #002154;");
		quote.setTextAlignment(TextAlignment.CENTER);
		homePane.setCenter(quote);

		//Text direct = new Text(10, 60, "All the info you need can \nbe found in the menu bar!");
		//direct.setStyle("-fx-font-size: 20px;" + "-fx-fill: #002154;");
		//direct.setTextAlignment(TextAlignment.CENTER);
		//homePane.setCenter(direct);


		return new Scene(homePane, 700, 700);
	}

	public Scene about_Us(Stage pStage) {
		BorderPane aboutUsPane = new BorderPane();
		aboutUsPane.setStyle("-fx-background-color: lightblue;");
		return new Scene(aboutUsPane, 700, 700);
	}

	public Scene daily_Tasks(Stage pStage) {
		BorderPane dailyTasks = new BorderPane();
		dailyTasks.setStyle("-fx-background-color: lightblue;");
		return new Scene(dailyTasks, 700, 700);
	}

	public Scene dataTracking(Stage pStage) {
		BorderPane dataTrackPane = new BorderPane();
		dataTrackPane.setStyle("-fx-background-color: lightblue;");
		return new Scene(dataTrackPane, 700, 700);
	}

	public MenuBar menuBarFunction(Stage pStage){
		// create our menu tab titled "Menu"
		Menu menu = new Menu("Menu");
		// create the items we want in the menu tab
		MenuItem aboutUs = new MenuItem("About Us");
		MenuItem tasks = new MenuItem("Daily Task");
		MenuItem tracking = new MenuItem("Progress Tracking");
		MenuItem exit = new MenuItem ("Exit");
		// add the items into the menu tab
		menu.getItems().add(aboutUs);
		menu.getItems().add(tasks);
		menu.getItems().add(tracking);
		menu.getItems().add(exit);
		// create the menu bar
		MenuBar mb = new MenuBar();
		// then add the menu with the menu items, into the menu bar
		mb.getMenus().add(menu);
		// event handler when clicking on rules: Leads to new scene display
		aboutUs.setOnAction(e -> {
			sceneMap.put("aboutUs", about_Us(pStage));
			pStage.setScene(sceneMap.get("aboutUs"));
		});
		// event handler when clicking on odds: Leads to new scene display
		tasks.setOnAction(e -> {
			sceneMap.put("dailyTasks", daily_Tasks(pStage));
			pStage.setScene(sceneMap.get("dailyTasks"));
		});
		// newGui will change the theme of the game
		tracking.setOnAction(e -> {
				sceneMap.put("pTracking", dataTracking(pStage));
				pStage.setScene(sceneMap.get("pTracking"));

		});
		// event handler for exit button
		exit.setOnAction(e -> System.exit(0));
		return mb;
	}
}
