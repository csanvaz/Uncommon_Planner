import javafx.animation.*;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;


public class JavaFXTemplate extends Application {
	HashMap<String, Scene> sceneMap; //Hashmap that will store different scenes for game
	private ListView<String> listView; //Holding Tasks of Users
	int ratingHolder = -1; // Int used to store emotion rating data
	int counter = 0; // Int used to set off a timer for a breathing exercise

	private TextField taskInput;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Joyful Planner");
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
		MenuBar homeMenu = menuBarFunction (pStage);

		//Creating image for the background
		Image homeBackGround = new Image( "gradient.png");
		BackgroundImage bGround = new BackgroundImage (homeBackGround,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(1.0, 1.0, true, true, false, false));
		Background hbg = new Background(bGround);
		homePane.setBackground(hbg);
		homePane.setTop(homeMenu);

		Text quote = new Text("\"Nothing diminishes anxiety \nfaster than action.\"");
		quote.setStyle("-fx-font-size: 40px;" + "-fx-fill: #002154;");
		quote.setTextAlignment(TextAlignment.CENTER);
		BorderPane.setAlignment(quote, Pos.CENTER);
		homePane.setCenter(quote);
		homePane.setPadding(new Insets(0, 0, 50, 0));

		Text direct = new Text("Joyful Agenda\nAll the info (other pages) you need can \nbe found in the menu bar!");
		direct.setStyle("-fx-font-size: 20px;" + "-fx-fill: #002154;");
		direct.setTextAlignment(TextAlignment.CENTER);
		BorderPane.setAlignment(direct, Pos.CENTER);
		homePane.setBottom(direct);


		return new Scene(homePane, 700, 700);
	}

	public Scene about_Us(Stage pStage) {
		BorderPane aboutUsPane = new BorderPane();
		aboutUsPane.setStyle("-fx-background-color: lightblue;");
		String title_style = "-fx-font-weight: bold; -fx-font-size: 36px; -fx-text-decoration: underline";
		String subtitle_style = "-fx-font-weight: bold; -fx-font-size: 22px";
		String body_style = "-fx-font-size: 14px;";

		Text about_proj = new Text("\nJoyful Agenda was made for Uncommon Hacks 2023. The goal was to combine a daily planner" +
										" that promotes mental wellbeing and work-life balance.\n");
		about_proj.setStyle(body_style);

		Text cynthia_title = new Text("\nCynthia Sanchez Vasquez:\n");
		Text sam_title = new Text("\nSam Doepker:\n");

		cynthia_title.setStyle(subtitle_style);
		sam_title.setStyle(subtitle_style);

		Text cynthia_bio = new Text("As a mental health therapist I have seen first hand the struggles that can come" +
				"with creating a to-do list. I hope that by adding a few extra features to a planner, the process can be" +
				" more joyful and lead to goal achievement.");
		cynthia_bio.setStyle(body_style);

		Text sam_bio = new Text("One of my passions has always been the power of computer science and technology to" +
				"improve peoples' lives, and Joyful Agenda's core goal is to do just that. My hope is that the agenda" +
				"makes it easier to take mental health breaks and take some pressure of planning the day.");
		sam_bio.setStyle(body_style);


		Text title = new Text("About us");
		title.setStyle(title_style);
		TextFlow textFlow = new TextFlow(title, about_proj, cynthia_title, cynthia_bio, sam_title, sam_bio);

		VBox paneCenter = new VBox (10, title, textFlow);
		paneCenter.setAlignment(Pos.CENTER);
		aboutUsPane.setCenter(paneCenter);
		aboutUsPane.setPadding(new Insets(10, 20, 20, 30));

		return new Scene(aboutUsPane, 700, 700);
	}

	public Scene daily_Tasks(Stage pStage) {
		BorderPane root = new BorderPane();
		root.setStyle("-fx-background-color: lightblue;");


		String dailyTaskIntro = "    Rate Your Distress Level On The Scale Below\n" + "0 Means No Distress and 7 Mean Extreme Distress";

		Label prompt = new Label(dailyTaskIntro);
		prompt.setStyle("-fx-font-family:'Robotom Medium'; " + "-fx-font-size: 1.4em;" + "-fx-fill:-text-color-900;");

		FlowPane flowPane = new FlowPane();
		flowPane.setPadding(new Insets(10, 10, 10, 10));
		flowPane.setHgap(10);
		flowPane.setVgap(10);
		flowPane.setAlignment(Pos.TOP_CENTER);


		for (int i = 1; i <= 7; i++) {
			Button button = new Button(String.valueOf(i));
			button.setStyle("-fx-background-color: white;");
			flowPane.getChildren().add(button);
			button.setDisable(false);
			button.setOnAction(e -> {
				button.setStyle("-fx-background-radius: 5em;" + "-fx-background-color: navy;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;");
				flowPane.setDisable(true);
				ratingHolder = Integer.parseInt(button.getText());
				taskListCheck(ratingHolder, root, pStage);
				System.out.println(ratingHolder + "This is happening when button is cliecked");
			});

		}

		Button clear = new Button("Clear");
		clear.setStyle("-fx-background-color: white;");
		clear.setOnAction(e ->
		{
			ratingHolder = -1;
			sceneMap.put("dailyTasks", daily_Tasks(pStage));
			pStage.setScene(sceneMap.get("dailyTasks"));
		});


		System.out.println(ratingHolder + "This is happening outside of button click");

		if (ratingHolder <= 3 && ratingHolder > -1) {

		VBox taskBox = new VBox();
		taskBox.getChildren().addAll(prompt, flowPane, clear);
		taskBox.setAlignment(Pos.TOP_CENTER);
		root.setTop(taskBox);
		return new Scene(root, 700, 700);


	}

	public void taskListCheck(int check, BorderPane taskPane, Stage pStage){

		if (ratingHolder <= 3 && ratingHolder != -1) {
			System.out.println(ratingHolder + "inside if statement");
			// Create task input field
			taskInput = new TextField();
			taskInput.setPromptText("Enter task here");

			// Create add task button
			Button addButton = new Button("Add");
			addButton.setOnAction(event -> {
				String task = taskInput.getText();
				if (!task.isEmpty()) {
					listView.getItems().add(task);
					taskInput.clear();
				}
			});

			// Create delete task button
			Button deleteButton = new Button("Delete");
			deleteButton.setOnAction(event -> {
				int index = listView.getSelectionModel().getSelectedIndex();
				if (index >= 0) {
					listView.getItems().remove(index);
				}
			});

			// Create list view
			listView = new ListView<>();
			listView.setItems(FXCollections.observableArrayList());

			// Create horizontal box for input and add button
			HBox inputBox = new HBox();
			inputBox.setAlignment(Pos.CENTER);
			inputBox.setSpacing(10);
			inputBox.getChildren().addAll(taskInput, addButton);

			// Create vertical box for delete button and list view
			VBox listBox = new VBox();
			listBox.setAlignment(Pos.CENTER);
			listBox.setSpacing(10);
			listBox.getChildren().addAll(deleteButton, listView);

			// Add input box and list box to root pane
			taskPane.setCenter(inputBox);
			taskPane.setBottom(listBox);

			// Set padding for root pane
			taskPane.setPadding(new Insets(10));
		}

		if(ratingHolder > 3) {
			breathingTimer(check, taskPane, pStage);
		}



	}

	public void breathingTimer(int check, BorderPane timerPane, Stage pStage){
		System.out.println(ratingHolder + "inside buttonpressed function");
		Label label = new Label("Breathe In: " + counter);
		label.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;" +  "-fx-font-size: 30;");

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				counter++;
				label.setText("Breathe In: " + counter);
				if (counter >= 5){
					label.setText("Hold Breath: " + counter);
				}

				if (counter >= 12){
					label.setText("Breath Out: " + counter);
				}

				if (counter == 20){
					ratingHolder = -1;
					counter = 0;
					sceneMap.put("dailyTasks", daily_Tasks(pStage));
					pStage.setScene(sceneMap.get("dailyTasks"));
				}
			}
		}));
		timeline.setCycleCount(20);
		timeline.play();

		VBox listBox = new VBox();
		listBox.setAlignment(Pos.CENTER);
		listBox.setSpacing(10);
		listBox.getChildren().addAll(label);


		timerPane.setCenter(listBox);
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
