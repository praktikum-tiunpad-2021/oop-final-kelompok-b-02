package Snake.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Painter {

    public void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < Grid.getRows(); i++) {
            for (int j = 0; j < Grid.getColumns(); j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.CORNSILK);
                } else {
                    gc.setFill(Color.DARKSEAGREEN);
                }
                gc.fillRect(i * Grid.getSquareSize(), j * Grid.getSquareSize(), Grid.getSquareSize(),
                        Grid.getSquareSize());
            }
        }
    }

    public void drawFood(GraphicsContext gc, Food food) {
        gc.drawImage(food.getFoodImage(), food.getFoodX() * Grid.getSquareSize(),
                food.getFoodY() * Grid.getSquareSize(), Grid.getSquareSize(), Grid.getSquareSize());
    }

    public void drawSnake(GraphicsContext gc, Snake snake) {
        gc.setFill(Color.INDIANRED);
        gc.fillRoundRect(snake.getSnakeHead().getX() * Grid.getSquareSize(),
                snake.getSnakeHead().getY() * Grid.getSquareSize(), Grid.getSquareSize() - 1, Grid.getSquareSize() - 1,
                35, 35);

        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            gc.fillRoundRect(snake.getSnakeBody().get(i).getX() * Grid.getSquareSize(),
                    snake.getSnakeBody().get(i).getY() * Grid.getSquareSize(), Grid.getSquareSize() - 1,
                    Grid.getSquareSize() - 1, 20, 20);
        }
    }

    public void drawScore(GraphicsContext gc, Score score) {
        gc.setFill(Color.MIDNIGHTBLUE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score.getScore(), 80, 35);
    }

}
