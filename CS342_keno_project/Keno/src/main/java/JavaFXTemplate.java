import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFXTemplate extends Application {

    private Button btnS1;
    private Button btnS4;
    private Button btnS8;
    private Button btnS10;
    private Button btnR1;
    private Button btnR2;
    private Button btnR3;
    private Button btnR4;
    private Button selectNewNumbers;
    private Button confirmNumbers;
    private Button beginDrawing;
    private Button selectAuto;
    private Button finalSceneBtn;
    private Button playAgain, drawAgain;
    final Text  displaySelectSpots = new Text("SELECT NUMBER OF SPOTS"), displaySelectRounds = new Text("SELECT NUMBER OF DRAWINGS"),
            autoSelectTxt = new Text("YOU CAN MANUALLY SELECT BY PRESSING THE NUMBERS ON THE GRID OR PRESS 'QUICK PICK' TO LET THE COMPUTER CHOOSE FOR YOU"), gameOver = new Text("GAME OVER!"),
            by = new Text("BY- ANNSH AGRAWAAL AND SAHIL GEDAM");


    private HashMap<String, Scene> sceneMap;
    public Text display = new Text(), display1 = new Text(), displayNumberOfMatched = new Text(), roundNumberTxt = new Text(), displaySameNumTxt = new Text();
    public Text displayThisRoundTxt = new Text(), displayPreviousWinnings = new Text(), displayRoundWinnings = new Text(), displayTotalWinnings = new Text();
    public Text displayFirstScene = new Text();

    GridPane gridPane;
    PauseTransition pause = new PauseTransition(Duration.seconds(1));
    PauseTransition gamePause = new PauseTransition(Duration.seconds(1));
    PauseTransition finalScenePause = new PauseTransition(Duration.seconds(1));
    ArrayList<Integer> arr = null;
    ListView<Integer> random20List = new ListView<>(), userChoiceList = new ListView<>();
    Label userNumbersLbl;
    logic Logic;
    int userNumSpots=0;
    int userNumRounds=0;
    int currentSpots = 0;
    int currentRounds = 1;
    int matchedNum = 0;
    int i = 0;
    int count = 0;
    int countThisRound = 0;
    double previousWinnings = 0;
    int buttonCount = 1;
    boolean changeBgBool = false;
    double moneyEarnedRound = 0;
    double totalMoneyEarned = 0;
    String numbersMatched = "";
    ArrayList<Integer> userNumsFromGrid = new ArrayList<>();
    boolean contains;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        primaryStage.setTitle("Welcome to KENO");

        sceneMap = new HashMap<String, Scene>();
        Logic= new logic();


        //Pause and then set scene
        pause.setOnFinished(e->primaryStage.setScene(sceneMap.get("createGameScene")));
        gamePause.setOnFinished(e->primaryStage.setScene(sceneMap.get("drawingScene")));
        finalScenePause.setOnFinished(e->primaryStage.setScene(sceneMap.get("finalScene")));


        sceneMap.put("createScene1", createScene1());

        primaryStage.setScene(sceneMap.get("createScene1"));
        primaryStage.show();

    }

    public Scene createScene1() {
        Menu menu;
        MenuItem displayRules;
        MenuItem odds;
        MenuItem exit;
        MenuItem changeBg = new MenuItem("Change Background");
        MenuBar  menuBar;
        menu = new Menu("Menu");

        displayRules = new MenuItem("Display Rules");
        odds = new MenuItem("Display Odds of Winnings");
        exit = new MenuItem("Exit");

        //Adding menu items to menu
        menu.getItems().add(displayRules);
        menu.getItems().add(odds);
        menu.getItems().add(exit);

        menuBar= new MenuBar();
        menuBar.getMenus().add(menu);

        setDisplayFirstScene();
        displayPrint(displayRules, odds, exit, display);

        Button playButton = new Button("PLAY KENO");

        display.setVisible(false);

        VBox vbox = new VBox(playButton, displayFirstScene,display);


        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vbox);
        borderPane.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
        changeBg.setOnAction(e->borderPane.setStyle("-fx-background-image: url('bgdark.jpg'); -fx-background-size: cover"));

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sceneMap.put("createGameScene", createGameScene());
                pause.play();

            }
        });

        return new Scene(borderPane, 850, 750);

    }


    public Scene createGameScene(){
        Menu menuScene2 ;
        Menu menu2;
        MenuItem displayRules;
        MenuItem odds;
        MenuItem exit;
        MenuItem changeBg = new MenuItem("Change Background");
        MenuBar menuBarScene2 = new MenuBar();

        menuScene2 = new Menu("Menu");

        displayRules = new MenuItem("Display Rules");
        odds = new MenuItem("Display Odds of Winnings");
        exit = new MenuItem("Exit");

        menuScene2.getItems().add(displayRules);
        menuScene2.getItems().add(odds);
        menuScene2.getItems().add(exit);

        menu2 = new Menu("New Look");

        menu2.getItems().add(changeBg);

        menuBarScene2.getMenus().addAll(menuScene2);
        menuBarScene2.getMenus().addAll(menu2);

        display1.setFont(Font.font("Verdana", 16));
        display1.setFill(Color.YELLOW);
        display1.setTextAlignment(TextAlignment.CENTER);
        display1.setWrappingWidth(800);


        displayPrint(displayRules, odds, exit, display1);

        int numRows = 8 ;
        int numColumns = 10 ;

        setDisplayPreviousWinningsFunc();
        displayPreviousWinnings.setFill(Color.CYAN);

        display1.setVisible(false);


        setDisplaySelectSpotsFunc();
        setDisplaySelectRoundsFunc();
        setAutoSelectTxtFunc();
        BorderPane root = new BorderPane();

        selectAuto = new Button("Quick Pick");
        selectAuto.setStyle("-fx-background-color: Cyan");


        btnS1 = new Button("1");
        btnS1.setDisable(false);
        btnS4 = new Button("4");
        btnS4.setDisable(false);
        btnS8 = new Button("8");
        btnS8.setDisable(false);
        btnS10 = new Button("10");
        btnS10.setDisable(false);

        btnS1.setStyle("-fx-background-color: Yellow");
        btnS4.setStyle("-fx-background-color: Yellow");
        btnS8.setStyle("-fx-background-color: Yellow");
        btnS10.setStyle("-fx-background-color: Yellow");

        btnR1 = new Button("1");
        btnR2 = new Button("2");
        btnR3 = new Button("3");
        btnR4 = new Button("4");

        btnR1.setStyle("-fx-background-color: Yellow");
        btnR2.setStyle("-fx-background-color: Yellow");
        btnR3.setStyle("-fx-background-color: Yellow");
        btnR4.setStyle("-fx-background-color: Yellow");


        selectNewNumbers = new Button("Select New Numbers");
        confirmNumbers = new Button("Confirm Numbers");
        beginDrawing = new Button("Begin Drawing");

        btnR1.setDisable(true);
        btnR1.setDisable(true);
        btnR1.setDisable(true);
        btnR1.setDisable(true);
        beginDrawing.setDisable(true);


        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        for (int row = 0 ; row < numRows ; row++ ){
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            gridPane.getRowConstraints().add(rc);
        }
        for (int col = 0 ; col < numColumns; col++ ) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            gridPane.getColumnConstraints().add(cc);

        }

        for (int i = 0 ; i < 80 ; i++) {
            Button button = createButton(Integer.toString(i+1));
            gridPane.add(button, i % 10, i / 10);
        }


        HBox hBoxRounds = new HBox(btnR1,btnR2,btnR3,btnR4);
        hBoxRounds.setAlignment(Pos.CENTER);
        hBoxRounds.setSpacing(15);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnS1,btnS4,btnS8,btnS10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(15);

        VBox autoSelectBox = new VBox();
        autoSelectBox.setAlignment(Pos.CENTER);
        autoSelectBox.getChildren().addAll(hbox, autoSelectTxt ,selectAuto);
        autoSelectBox.setSpacing(15);

        VBox displayWinnings = new VBox();
        displayWinnings.getChildren().addAll(displayPreviousWinnings, displaySelectSpots);
        displayWinnings.setAlignment(Pos.CENTER);
        displayWinnings.setSpacing(15);

        VBox upperVbox = new VBox();
        upperVbox.setSpacing(30);
        upperVbox.getChildren().addAll(menuBarScene2,displayWinnings ,autoSelectBox);
        upperVbox.setAlignment(Pos.CENTER);

        root.setTop(upperVbox);

        VBox nextSceneBox = new VBox();
        nextSceneBox.getChildren().addAll(beginDrawing,display1);
        nextSceneBox.setAlignment(Pos.BOTTOM_CENTER);
        nextSceneBox.setSpacing(30);


        VBox myVbox = new VBox(displaySelectRounds,hBoxRounds,nextSceneBox);
        myVbox.setAlignment(Pos.CENTER);
        myVbox.setSpacing(30);
        myVbox.setPadding(new Insets(10,10,100,10));

        HBox gridHBox = new HBox();
        gridHBox.getChildren().addAll(selectNewNumbers,gridPane,confirmNumbers);
        gridHBox.setSpacing(50);
        gridHBox.setAlignment(Pos.CENTER);

        root.setCenter(gridHBox);
        root.setBottom(myVbox);


        gridPane.setStyle("-fx-grid-lines-visible: true");

        gridPane.setDisable(true);
        btnR1.setDisable(true);
        btnR2.setDisable(true);
        btnR3.setDisable(true);
        btnR4.setDisable(true);
        selectNewNumbers.setDisable(true);
        confirmNumbers.setDisable(true);
        beginDrawing.setDisable(true);
        selectAuto.setDisable(true);

        btnS1.setOnAction(e -> {
            System.out.println("Spots " +1);
            userNumSpots = 1;
            btnS1.setDisable(true);
            btnS4.setDisable(false);
            btnS8.setDisable(false);
            btnS10.setDisable(false);
            gridPane.setDisable(false);
            selectNewNumbers.setDisable(false);
            selectAuto.setDisable(false);
        });
        btnS4.setOnAction(e -> {
            System.out.println("Spots " +4);
            userNumSpots = 4;
            btnS4.setDisable(true);
            btnS1.setDisable(false);
            btnS8.setDisable(false);
            btnS10.setDisable(false);
            gridPane.setDisable(false);
            selectNewNumbers.setDisable(false);
            selectAuto.setDisable(false);
        });
        btnS8.setOnAction(e -> {
            System.out.println("Spots " +8);
            userNumSpots = 8;
            btnS8.setDisable(true);
            btnS1.setDisable(false);
            btnS4.setDisable(false);
            btnS10.setDisable(false);
            gridPane.setDisable(false);
            selectNewNumbers.setDisable(false);
            selectAuto.setDisable(false);
        });
        btnS10.setOnAction(e -> {
            System.out.println("Spots " +10);
            userNumSpots = 10;
            btnS10.setDisable(true);
            btnS1.setDisable(false);
            btnS8.setDisable(false);
            btnS4.setDisable(false);
            gridPane.setDisable(false);
            selectNewNumbers.setDisable(false);
            selectAuto.setDisable(false);
        });

        btnR1.setOnAction(e->{
            System.out.println("Round " +1);
            userNumRounds=1;
            btnR1.setDisable(true);
            btnR2.setDisable(false);
            btnR3.setDisable(false);
            btnR4.setDisable(false);
            beginDrawing.setDisable(false);
        });
        btnR2.setOnAction(e->{
            System.out.println("Round " +2);
            userNumRounds=2;
            btnR1.setDisable(false);
            btnR2.setDisable(true);
            btnR3.setDisable(false);
            btnR4.setDisable(false);
            beginDrawing.setDisable(false);
        });
        btnR3.setOnAction(e->{
            System.out.println("Round " +3);
            userNumRounds=3;
            btnR1.setDisable(false);
            btnR2.setDisable(false);
            btnR3.setDisable(true);
            btnR4.setDisable(false);
            beginDrawing.setDisable(false);
        });
        btnR4.setOnAction(e->{
            System.out.println("Round " +4);
            userNumRounds=4;
            btnR1.setDisable(false);
            btnR2.setDisable(false);
            btnR3.setDisable(false);
            btnR4.setDisable(true);
            beginDrawing.setDisable(false);
        });

        confirmNumbers.setOnAction(e->{
            gridPane.setDisable(true);
            selectNewNumbers.setDisable(true);
            selectAuto.setDisable(true);
            btnS1.setDisable(true);
            btnS4.setDisable(true);
            btnS8.setDisable(true);
            btnS10.setDisable(true);
            btnR1.setDisable(false);
            btnR2.setDisable(false);
            btnR3.setDisable(false);
            btnR4.setDisable(false);
        });

        //If changBgBool is true means the bg is the green one and if changeBgBool is false means that the bg is dark.
        changeBg.setOnAction(e->{
            if(changeBgBool){
                changeBgBool = false;
                root.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
                displaySelectRounds.setFill(Color.YELLOW);
            }
            else{
                changeBgBool = true;
                root.setStyle("-fx-background-image: url('bgdark.jpg'); -fx-background-size: cover");
                displaySelectRounds.setFill(Color.LIME);

            }
        });



        selectAuto.setOnAction(e->{
            btnS1.setDisable(true);
            btnS4.setDisable(true);
            btnS8.setDisable(true);
            btnS10.setDisable(true);

            ArrayList<Integer> randomNumbersGeneratedForUser = new ArrayList<>();
            randomNumbersGeneratedForUser = Logic.getNumbersForUser(userNumSpots);
            System.out.println("random numbers generated for user");
            System.out.println(randomNumbersGeneratedForUser);
            userNumsFromGrid = randomNumbersGeneratedForUser;

            for(Node button: gridPane.getChildren()){
                for(int i = 0; i < userNumsFromGrid.size(); i++){
                    if(userNumsFromGrid.get(i) == buttonCount){
                        button.setDisable(true);
                    }
                }
                buttonCount++;
            }


            confirmNumbers.setDisable(false);
            selectAuto.setDisable(true);
        });

        selectNewNumbers.setOnAction(e->{
            btnS1.setDisable(false);
            btnS4.setDisable(false);
            btnS8.setDisable(false);
            btnS10.setDisable(false);
            confirmNumbers.setDisable(true);
            selectAuto.setDisable(true);

            for(Node button : gridPane.getChildren()){
                button.setDisable(false);
            }
            gridPane.setDisable(true);

            currentSpots = 0;
            userNumsFromGrid.clear();
            buttonCount = 1;

            System.out.println("Select New Numbers");
        });


        arr = Logic.getRandom20(currentRounds);
        EventHandler<ActionEvent> eventEventHandler = (e->{
            random20List.getItems().add(arr.get(i));
            contains = checkEqualNumbers(arr.get(i));
            if(contains){
                count++;
                countThisRound++;
                numbersMatched = numbersMatched + " " + arr.get(i).toString() ;
            }
            System.out.println("TOTAL NUMBERS MATCHED: " +count );
            displayNumberOfMatched.setText("TOTAL NUMBERS MATCHED: " +count);
            displayThisRoundTxt.setText("NUMBERS MATCHED IN THIS ROUND:" +countThisRound);
            displaySameNumTxt.setText("NUMBERS WHICH HAVE MATCHED THIS ROUND:" +numbersMatched);
            System.out.println("User num spots:" +userNumSpots);
            moneyEarnedRound = Logic.getPrizeMoney(userNumSpots, countThisRound);
            displayRoundWinnings.setText("MONEY EARNED THIS ROUND: $"+moneyEarnedRound);
            displayTotalWinnings.setText("TOTAL WINNINGS TILL NOW: $" +totalMoneyEarned);
            i++;
            if(i == 20){

                drawAgain.setDisable(false);
            }
            else{
                drawAgain.setDisable(true);
            }
        });


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), eventEventHandler),
                new KeyFrame(Duration.millis(1000))
        );

        timeline.setCycleCount(20);


        beginDrawing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                moneyEarnedRound = 0;
                sceneMap.put("drawingScene", drawingScene());
                gamePause.play();
                timeline.play();

            }
        });

//        root.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
        if(changeBgBool){
            root.setStyle("-fx-background-image: url('bgdark.jpg'); -fx-background-size: cover");
        }
        else{
            root.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
        }

        return new Scene(root,850,750);
    }


    public Scene drawingScene() {
        Menu menuScene2 ;
        Menu menu2;
        MenuItem displayRules;
        MenuItem odds;
        MenuItem exit;
        MenuItem changeBg = new MenuItem("Change Background");
        MenuBar menuBarScene2 = new MenuBar();

        menuScene2 = new Menu("Menu");

        displayRules = new MenuItem("Display Rules");
        odds = new MenuItem("Display Odds of Winnings");
        exit = new MenuItem("Exit");

        menuScene2.getItems().add(displayRules);
        menuScene2.getItems().add(odds);
        menuScene2.getItems().add(exit);

        menu2 = new Menu("New Look");

        menu2.getItems().add(changeBg);

        menuBarScene2.getMenus().addAll(menuScene2);
        menuBarScene2.getMenus().addAll(menu2);


        displayPrint(displayRules, odds, exit, display);

        i =0;
        countThisRound = 0;

        drawAgain = new Button("DRAW AGAIN ");
        drawingButtonDisplay();

        playAgain = new Button("PLAY AGAIN");

        display.setVisible(false);

        finalSceneBtn = new Button("QUIT NOW");

        playAgain.setDisable(true);
        finalSceneBtn.setDisable(true);

        for (int i = 0; i < userNumsFromGrid.size(); i++) {
            userChoiceList.getItems().add(userNumsFromGrid.get(i));
        }


        random20List.setOrientation(Orientation.HORIZONTAL);
        random20List.setPrefSize(570,100);

        userChoiceList.setOrientation(Orientation.HORIZONTAL);
        userChoiceList.setPrefSize(280,100);

        drawingSceneLabelsAndText();


        finalSceneBtn.setOnAction(e->{
            sceneMap.put("finalScene", finalScene());
            finalScenePause.play();

        });


        EventHandler<ActionEvent> eventEventHandlerDraw = (e->{
            System.out.println("Current Rounds:" + currentRounds);
            random20List.getItems().add(arr.get(i));
            contains = checkEqualNumbers(arr.get(i));
            if(contains){
                count++;
                countThisRound++;
                numbersMatched = numbersMatched + " " + arr.get(i).toString() ;
            }
            System.out.println("Number of Matched Numbers: " +count );
            displayNumberOfMatched.setText("TOTAL NUMBERS MATCHED: " +count);
            displayThisRoundTxt.setText("NUMBERS MATCHED IN THIS ROUND:" +countThisRound);
            displaySameNumTxt.setText("NUMBERS WHICH HAVE MATCHED THIS ROUND:" +numbersMatched);
            moneyEarnedRound = Logic.getPrizeMoney(userNumSpots, countThisRound);
            displayRoundWinnings.setText("MONEY EARNED THIS ROUND: $"+moneyEarnedRound);
            displayTotalWinnings.setText("TOTAL WINNINGS TILL NOW: $" +totalMoneyEarned);
            i++;
            if(i == 20){

                drawAgain.setDisable(false);
            }
            else{
                drawAgain.setDisable(true);
            }

        });

        Timeline timeLineDraw = new Timeline(
                new KeyFrame(Duration.seconds(1), eventEventHandlerDraw),
                new KeyFrame(Duration.millis(1000))

        );

        timeLineDraw.setCycleCount(20);

        drawAgain.setOnAction(e->{
            i = 0;
            countThisRound = 0;
            totalMoneyEarned += moneyEarnedRound;
            moneyEarnedRound = 0;
            numbersMatched = "";
            if(currentRounds != userNumRounds){
                currentRounds++;
                arr = Logic.getRandom20(currentRounds);
                random20List.getItems().clear();
                System.out.println("Number of Rounds :" + currentRounds);
                roundNumberTxt.setText("ROUND: " +currentRounds);
                timeLineDraw.play();
            }
            else{
                drawAgain.setDisable(true);
                playAgain.setDisable(false);
                finalSceneBtn.setDisable(false);
                displayTotalWinnings.setText("TOTAL WINNINGS TILL NOW: $" +totalMoneyEarned);
            }
        });

        playAgain.setOnAction(e->{
            btnS1.setDisable(false);
            btnS4.setDisable(false);
            btnS8.setDisable(false);
            btnS10.setDisable(false);
            confirmNumbers.setDisable(true);

            btnR1.setDisable(true);
            btnR2.setDisable(true);
            btnR3.setDisable(true);
            btnR4.setDisable(true);
            beginDrawing.setDisable(true);


            for(Node button : gridPane.getChildren()){
                button.setDisable(false);
            }

            setDisplayMenuOptionsFunc();

            random20List.getItems().clear();
            userChoiceList.getItems().clear();
            userNumsFromGrid.clear();
            display1.setVisible(false);
            selectAuto.setDisable(true);
            selectNewNumbers.setDisable(true);
            count = 0;
            countThisRound = 0;
            numbersMatched = "";
            moneyEarnedRound = 0;
            currentRounds = 1;
            currentSpots = 0;
            userNumSpots = 0;
            userNumRounds = 0;
            buttonCount = 1;
            previousWinnings = totalMoneyEarned;
            displayPreviousWinnings.setText("YOUR TOTAL WINNINGS FROM PREVIOUS GAME: " +previousWinnings);


            pause.play();

        });

        setDisplayNumberOfMatchedFunc();


        setRoundNumberTxtFunc();


        setDisplayThisRoundTxtFunc();


        setDisplayRoundWinningsFunc();


        setDisplayTotalWinningsFunc();

        setDisplaySameNumTxtFunc();


        HBox displayWinningsBtn = new HBox();
        displayWinningsBtn.setSpacing(15);
        displayWinningsBtn.getChildren().addAll(playAgain, drawAgain,finalSceneBtn);
        displayWinningsBtn.setAlignment(Pos.CENTER);


        VBox displayMatchedNumbers = new VBox();
        displayMatchedNumbers.setSpacing(15);
        displayMatchedNumbers.getChildren().addAll(displayNumberOfMatched ,displaySameNumTxt);


        VBox displayMatchedAndMoney = new VBox();
        displayMatchedAndMoney.setSpacing(15);
        displayMatchedAndMoney.getChildren().addAll(displayThisRoundTxt ,displayMatchedNumbers, displayRoundWinnings);
        displayMatchedAndMoney.setAlignment(Pos.CENTER);


        VBox displayMatchedBox = new VBox();
        displayMatchedBox.setSpacing(15);
        displayMatchedBox.getChildren().addAll(displayWinningsBtn, displayMatchedAndMoney, displayTotalWinnings);
        displayMatchedBox.setAlignment(Pos.CENTER);
        displayMatchedBox.setMaxWidth(700);



        VBox randomList = new VBox();
        randomList.setSpacing(10);
        randomList.getChildren().addAll(roundNumberTxt,random20List, displayMatchedBox);
        randomList.setAlignment(Pos.TOP_CENTER);
        randomList.setMaxWidth(540);
        randomList.setFillWidth(false);

        VBox userList = new VBox();
        userList.setSpacing(10);
        userList.getChildren().addAll(userNumbersLbl, userChoiceList,display);
        userList.setAlignment(Pos.TOP_CENTER);
        userList.setMaxWidth(540);
        userList.setFillWidth(false);

        BorderPane scene = new BorderPane();
        scene.setTop(menuBarScene2);
        scene.setCenter(randomList);
        scene.setBottom(userList);


        changeBg.setOnAction(e->{
            if(changeBgBool){
                changeBgBool = false;
                scene.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
                displayNumberOfMatched.setFill(Color.CYAN);
                roundNumberTxt.setFill(Color.CYAN);
                displayThisRoundTxt.setFill(Color.CYAN);
                displayRoundWinnings.setFill(Color.CYAN);
                displayTotalWinnings.setFill(Color.CYAN);
                displaySameNumTxt.setFill(Color.CYAN);
                userNumbersLbl.setTextFill(Color.CYAN);
            }
            else{
                changeBgBool = true;
                scene.setStyle("-fx-background-image: url('bgdark.jpg'); -fx-background-size: cover");
                displayNumberOfMatched.setFill(Color.WHITE);
                roundNumberTxt.setFill(Color.WHITE);
                displayThisRoundTxt.setFill(Color.WHITE);
                displayRoundWinnings.setFill(Color.WHITE);
                displayTotalWinnings.setFill(Color.WHITE);
                displaySameNumTxt.setFill(Color.WHITE);
                userNumbersLbl.setTextFill(Color.LIGHTGOLDENRODYELLOW);
            }
        });



        if(changeBgBool){
            scene.setStyle("-fx-background-image: url('bgdark.jpg'); -fx-background-size: cover");
        }
        else{
            scene.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");
        }


        return new Scene(scene, 850, 750);
    }


    public Scene finalScene(){

        gameOver.setFont(Font.font("Verdana", 20));
        gameOver.setFill(Color.CYAN);


        by.setFont(Font.font("Verdana", 20));
        by.setFill(Color.LIGHTPINK);

        VBox gameOverBox = new VBox();
        gameOverBox.getChildren().addAll(gameOver, by);
        gameOverBox.setAlignment(Pos.CENTER);
        gameOverBox.setSpacing(50);


        BorderPane pane = new BorderPane();
        pane.setCenter(gameOverBox);
        BorderPane.setAlignment(by,Pos.CENTER);

        pane.setStyle("-fx-background-image: url('bg1.jpg'); -fx-background-size: cover");

        return new Scene(pane, 850, 750);
    }


    private Button createButton(String text) {
        Button button = new Button(text);
        button.setOnAction(e -> {
            if(userNumSpots != currentSpots) { //compare this counter with second counter
                btnS1.setDisable(true);
                btnS4.setDisable(true);
                btnS8.setDisable(true);
                btnS10.setDisable(true);
                selectAuto.setDisable(true);
                userNumsFromGrid.add(Integer.parseInt(text));
                currentSpots++;
                if(userNumSpots == currentSpots)
                {
                    gridPane.setDisable(true);
                    confirmNumbers.setDisable(false);
                }

                System.out.println("Button Pressed :" + text);

            }
            else
            {
                confirmNumbers.setDisable(false);
                gridPane.setDisable(true);
            }
            button.setDisable(true);
        });

        return button;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void displayPrint(MenuItem displayRules, MenuItem odds, MenuItem exit, Text display1) {
        displayRules.setOnAction(actionEvent -> {
            display1.setVisible(true);
            display1.setText("The game of Keno is played first selecting the number of spots(number of numbers) you want to select in that drawing and then picking the same number of numbers from 1 to 80" +
                    ". From there, 20 numbers are drawn randomly." +
                    " If the numbers drawn match the numbers you selected, you win. The amount of numbers you picked and how many of them you got right determine the amount of your payout.");
        });

        odds.setOnAction(actionEvent -> {
            display1.setVisible(true);
            display1.setText("Spots 10 : 1 in 9.1, " +
                    "Spots 8: 1 in 9.8, " +
                    "Spots 4: 1 in 3.9, " +
                    "Spots 1 in 4.0");
        });

        exit.setOnAction(e-> Platform.exit());
        setDisplayMenuOptionsFunc();
    }

    private void drawingButtonDisplay(){
        if(userNumRounds == 1){
            drawAgain.setDisable(true);
        }
        else{
            drawAgain.setDisable(false);
        }
    }


    private boolean checkEqualNumbers(int var){

        if(userNumsFromGrid.contains(var)){
            return true;
        }
        else{
            return false;
        }
    }

    private void setDisplaySelectSpotsFunc(){

        displaySelectSpots.setFont(Font.font("Verdana", 14));
        displaySelectSpots.setFill(Color.YELLOW);

    }

    private void setDisplaySelectRoundsFunc(){

        displaySelectRounds.setFont(Font.font("Verdana", 14));
        if(changeBgBool){
            displaySelectRounds.setFill(Color.CYAN);
        }
        else{
            displaySelectRounds.setFill(Color.YELLOW);
        }

    }

    private  void setAutoSelectTxtFunc(){
        autoSelectTxt.setFont(Font.font("Verdana", 15));
        autoSelectTxt.setTextAlignment(TextAlignment.CENTER);
        autoSelectTxt.setFill(Color.LIMEGREEN);
        autoSelectTxt.setWrappingWidth(800);

    }

    private void setDisplayFirstScene(){
        displayFirstScene = new Text("YOU CAN SEE HOW TO PLAY THE GAME AND ALSO SEE YOUR CHANCES OF WINNING BY CLICKING THE MENU BUTTON AND " +
                "SELECTING THE APPROPRIATE OPTION");
        displayFirstScene.setFont(Font.font("Verdana", 18));
        displayFirstScene.setFill(Color.CYAN);
        displayFirstScene.setTextAlignment(TextAlignment.CENTER);
        displayFirstScene.setWrappingWidth(800);

    }


    private void drawingSceneLabelsAndText(){

        userNumbersLbl = new Label("Your Numbers");
        userNumbersLbl.setFont(new Font("Verdana", 18));
        userNumbersLbl.setPrefSize(800,50);
        userNumbersLbl.setAlignment(Pos.CENTER);
        if(changeBgBool){
            userNumbersLbl.setTextFill(Color.WHITE);
        }
        else{
            userNumbersLbl.setTextFill(Color.CYAN);
        }
    }

    private void setDisplayPreviousWinningsFunc(){
        displayPreviousWinnings = new Text("YOUR TOTAL WINNINGS FROM PREVIOUS GAME: $" +previousWinnings);
        displayPreviousWinnings.setFont(Font.font("Verdana", 16));
        displayPreviousWinnings.setFill(Color.WHITE);
        displayPreviousWinnings.setTextAlignment(TextAlignment.CENTER);
        displayPreviousWinnings.setWrappingWidth(800);
    }

    private void setDisplayNumberOfMatchedFunc(){
        if(changeBgBool){
            displayNumberOfMatched.setFill(Color.WHITE);
        }
        else{
            displayNumberOfMatched.setFill(Color.CYAN);
        }
        displayNumberOfMatched.setText("TOTAL NUMBERS MATCHED: " +matchedNum);
        displayNumberOfMatched.setFont(Font.font("Verdana", 18));
        displayNumberOfMatched.setTextAlignment(TextAlignment.CENTER);
        displayNumberOfMatched.setWrappingWidth(800);
    }

    private void setRoundNumberTxtFunc(){

        roundNumberTxt = new Text("ROUND: " +currentRounds);
        roundNumberTxt.setFont(Font.font("Verdana", 18));
        roundNumberTxt.setTextAlignment(TextAlignment.CENTER);
        roundNumberTxt.setWrappingWidth(800);
        if(changeBgBool){
            roundNumberTxt.setFill(Color.WHITE);
        }
        else{
            roundNumberTxt.setFill(Color.CYAN);
        }

    }

    private void setDisplayThisRoundTxtFunc(){
        displayThisRoundTxt = new Text("NUMBERS MATCHED IN THIS ROUND:" +countThisRound);
        displayThisRoundTxt.setFont(Font.font("Verdana", 18));
        displayThisRoundTxt.setTextAlignment(TextAlignment.CENTER);
        displayThisRoundTxt.setWrappingWidth(800);
        if(changeBgBool){
            displayThisRoundTxt.setFill(Color.WHITE);
        }
        else{
            displayThisRoundTxt.setFill(Color.CYAN);
        }
    }

    private void setDisplayRoundWinningsFunc(){
        displayRoundWinnings = new Text("MONEY EARNED THIS ROUND: $" +moneyEarnedRound);
        displayRoundWinnings.setFont(Font.font("Verdana", 18));
        displayRoundWinnings.setTextAlignment(TextAlignment.CENTER);
        displayRoundWinnings.setWrappingWidth(800);
        if(changeBgBool){
            displayRoundWinnings.setFill(Color.WHITE);
        }
        else{
            displayRoundWinnings.setFill(Color.CYAN);
        }

    }

    private void setDisplayTotalWinningsFunc(){
        displayTotalWinnings = new Text("TOTAL WINNINGS TILL NOW: $" +totalMoneyEarned);
        displayTotalWinnings.setFont(Font.font("Verdana", 18));
        displayTotalWinnings.setTextAlignment(TextAlignment.CENTER);
        displayTotalWinnings.setWrappingWidth(800);
        if(changeBgBool){
            displayTotalWinnings.setFill(Color.WHITE);
        }
        else{
            displayTotalWinnings.setFill(Color.CYAN);
        }

    }

    private void setDisplaySameNumTxtFunc(){
        displaySameNumTxt.setText("NUMBERS WHICH HAVE MATCHED THIS ROUND:" +numbersMatched);
        displaySameNumTxt.setFont(Font.font("Verdana", 18));
        displaySameNumTxt.setTextAlignment(TextAlignment.CENTER);
        displaySameNumTxt.setWrappingWidth(800);

        if(changeBgBool){
            displaySameNumTxt.setFill(Color.WHITE);
        }
        else{
            displaySameNumTxt.setFill(Color.CYAN);
        }

    }

    private void setDisplayMenuOptionsFunc(){
        display.setFont(Font.font("Verdana", 20));
        display.setFill(Color.YELLOW);
        display.setTextAlignment(TextAlignment.CENTER);
        display.setWrappingWidth(800);

    }
}