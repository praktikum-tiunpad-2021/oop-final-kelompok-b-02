package Snake.Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private boolean gameOver;

    public Snake() {
        for (int i = 0; i < 5; i++) {
            snakeBody.add(new Point(5, Grid.getRows() / 2));
        }
        snakeHead = snakeBody.get(0);
        gameOver = false;
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    public Point getSnakeHead() {
        return snakeBody.get(0);
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void moveRight() {
        snakeHead.x++;
    }

    public void moveLeft() {
        snakeHead.x--;
    }

    public void moveUp() {
        snakeHead.y--;
    }

    public void moveDown() {
        snakeHead.y++;
    }

    public void snakeMove() {
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }
    }

    public void eatFood(Food food, Score score) {
        if (snakeHead.getX() == food.getFoodX() && snakeHead.getY() == food.getFoodY()) {
            snakeBody.add(new Point(-1, -1));
            food.generateFood(this);
            score.setScore();
        }
    }

    public void gameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * Grid.getSquareSize() >= (Grid.getWidth() - 1)
                || snakeHead.y * Grid.getSquareSize() >= (Grid.getHeight() - 1)) {
            gameOver = true;
        }

        // Destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }
}
