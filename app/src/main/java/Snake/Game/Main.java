package Snake.Game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int currentDirection;
    boolean isrunning = false;

    private Snake snake;
    private Food food = new Food();
    private Score score = new Score();
    private Painter paint = new Painter();

    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(Grid.getWidth(), Grid.getHeight());
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                } else if (code == KeyCode.SPACE){
                    // isrunning = true;
                    if(isrunning == true){
                        isrunning = false;
                    } else {
                        isrunning = true;
                    }
                }
            }
        });

        snake = new Snake();
        food.generateFood(snake);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/8), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        paint.drawBackground(gc);
        paint.drawFood(gc, food);
        paint.drawSnake(gc, snake);
        paint.drawScore(gc, score);
        if (isrunning == true) {
            if (snake.isGameOver()) {
                gc.setFill(Color.RED);
                gc.setFont(new Font("Digital-7", 70));
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.fillText("Game Over", Math.round(Grid.getWidth()  / 2), Math.round(Grid.getHeight() / 2));
                return;
            }
            paint.drawBackground(gc);
            paint.drawFood(gc, food);
            paint.drawSnake(gc, snake);
            paint.drawScore(gc, score);
    
            snake.snakeMove();
    
            switch (currentDirection) {
                case RIGHT:
                    snake.moveRight();
                    break;
                case LEFT:
                    snake.moveLeft();
                    break;
                case UP:
                    snake.moveUp();
                    break;
                case DOWN:
                    snake.moveDown();
                    break;
            }
    
            snake.gameOver();
            snake.eatFood(food, score);
        } else{
            gc.setFill(Color.DARKSALMON);
            gc.setFont(new Font("Verdana", 36));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText("Press Spacebar to Start..", Math.round(Grid.getWidth()  / 2), Math.round(Grid.getHeight() / 2));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}