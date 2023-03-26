import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.collections.FXCollections;
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

import java.util.ArrayList;
import java.util.HashMap;


public class JavaFXTemplate extends Application {
	HashMap<String, Scene> sceneMap; //Hashmap that will store different scenes for game
	private ListView<String> listView; //Holding Tasks of Users
	private ArrayList<Double> ratingsList = new ArrayList<>(); //Used to store emotion rating data

	private TextField taskInput;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Welcome!");
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
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: lightblue;");

		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10, 10, 10, 10));
		flowPane.setHgap(10);
		flowPane.setVgap(10);
		flowPane.setAlignment(Pos.TOP_CENTER);

		for (int i = 1; i <= 7; i++) {
			Button button = new Button(String.valueOf(i));
			flowPane.getChildren().add(button);
		}


//		if (ratingsList.get(0) <= 5) {
//			// Create task input field
//			taskInput = new TextField();
//			taskInput.setPromptText("Enter task here");
//
//			// Create add task button
//			Button addButton = new Button("Add");
//			addButton.setOnAction(event -> {
//				String task = taskInput.getText();
//				if (!task.isEmpty()) {
//					listView.getItems().add(task);
//					taskInput.clear();
//				}
//			});
//
//			// Create delete task button
//			Button deleteButton = new Button("Delete");
//			deleteButton.setOnAction(event -> {
//				int index = listView.getSelectionModel().getSelectedIndex();
//				if (index >= 0) {
//					listView.getItems().remove(index);
//				}
//			});
//
//			// Create list view
//			listView = new ListView<>();
//			listView.setItems(FXCollections.observableArrayList());
//
//			// Create horizontal box for input and add button
//			HBox inputBox = new HBox();
//			inputBox.setAlignment(Pos.CENTER);
//			inputBox.setSpacing(10);
//			inputBox.getChildren().addAll(taskInput, addButton);
//
//			// Create vertical box for delete button and list view
//			VBox listBox = new VBox();
//			listBox.setAlignment(Pos.CENTER);
//			listBox.setSpacing(10);
//			listBox.getChildren().addAll(deleteButton, listView);
//
//			// Add input box and list box to root pane
//			root.setTop(inputBox);
//			root.setCenter(listBox);
//
//			// Set padding for root pane
//			root.setPadding(new Insets(10));
//			root.setTop(gridPane);
//		}
			root.setTop(flowPane);
			return new Scene(root, 700, 700);


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
