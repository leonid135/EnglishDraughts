package task3.Gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import task3.Logic.Game;
import task3.Logic.Turns;


public class EnglishDraughts extends Application {
    private Game game;
    private GridPane field;
    private Pane statusPane;
    private Label gameStatus;

    private int selectedDX = -1;
    private int selectedDY = -1;
    private int selectedValue = 0;

    private int turn = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("English Draughts");
        primaryStage.setResizable(false);

        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);

        game = new Game();
        field = new GridPane();
        statusPane = new Pane();
        gameStatus = new Label("Move the red");
        gameStatus.setPadding(new Insets(5));
        statusPane.getChildren().add(gameStatus);

        vBox.getChildren().addAll(field, statusPane);

        Scene scene = new Scene(vBox, 640, 690);

        initMenu(menuBar);
        fieldUpdate();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMenu(MenuBar menuBar) {
        Menu menuGame = new Menu("Game");
        MenuItem menuNewGame = new MenuItem("New Game");
        menuNewGame.setOnAction(event -> onNewGame());
        MenuItem menuExit = new MenuItem("Out");
        menuExit.setOnAction(event -> onExit());

        menuGame.getItems().addAll(menuNewGame, menuExit);
        Menu menuHelp = new Menu("Help");

        menuBar.getMenus().addAll(menuGame, menuHelp);

    }

    private void onExit() {
        Platform.exit();
    }

    private void onNewGame() {
        game = new Game();
        turn = -1;
        gameStatus.setText("red move");
        fieldUpdate();
    }

    private void fieldUpdate() {
        for (int y = 0; y < game.getFieldSize(); y++) {
            for (int x = 0; x < game.getFieldSize(); x++) {
                //доска
                Rectangle cell = new Rectangle(80, 80);
                final int finalX = x;
                final int finalY = y;
                if ((x + y) % 2 == 0) {
                    cell.setFill(Color.BLACK);
                } else {
                    cell.setFill(Color.BURLYWOOD);
                    cell.setOnMouseClicked(event -> moveCheckerTo(finalX, finalY));
                }

                field.add(cell, x, y);

                //шашки

                Circle checker = new Circle(30);
                checker.setStroke(Color.color(0, 0, 0, 0));
                checker.setStrokeWidth(20);
                if (x == selectedDX && y == selectedDY) {
                    checker.setFill(Color.GREEN);
                } else if (game.getChekerAt(x, y) == 1) {
                    checker.setFill(Color.WHITE);
                } else if (game.getChekerAt(x, y) == -1) {
                    checker.setFill(Color.RED);
                } else {
                    checker.setOpacity(0);
                    checker.setMouseTransparent(true);
                }
                checker.setOnMouseClicked(event -> selectChecker(finalX, finalY));
                field.add(checker, x, y);
            }
        }
    }


    private void selectChecker(int x, int y) {
        if (game.getChekerAt(x, y) == turn) {
            selectedDX = x;
            selectedDY = y;
            selectedValue = game.getChekerAt(x, y);
            fieldUpdate();
        }

    }

    private void moveCheckerTo(int x, int y) {
        if (selectedDX != -1 && selectedDY != -1 && game.getChekerAt(x, y) == 0) {
            if (selectedValue > 0) {
                if (x > selectedDX && y < selectedDY) {
                    game.makeTurn(selectedDX, selectedDY, Turns.RIGHT);
                } else if (x < selectedDX && y < selectedDY) {
                    game.makeTurn(selectedDX, selectedDY, Turns.LEFT);
                }
            } else if (selectedValue < 0) {
                if (x < selectedDX && y > selectedDY) {
                    game.makeTurn(selectedDX, selectedDY, Turns.RIGHT);

                } else if (x > selectedDX && y > selectedDY) {
                    game.makeTurn(selectedDX, selectedDY, Turns.LEFT);
                }
            }
            turn *= -1;
            if (turn == 1) {
                gameStatus.setText("white move");

            } else {
                gameStatus.setText("red move");
            }

        }
        selectedDX = -1;
        selectedDY = -1;
        selectedValue = 0;
        fieldUpdate();

    }
}