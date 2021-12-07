package Snake.Game;

import java.awt.Point;
import javafx.scene.image.Image;

public class Food {
    private static final String[] FOODS_IMAGE = new String[] {
            "/img/palarafi.png" , "/img/palawildan.png" , "/img/palarangga.png" , "/img/palaaffan.png" 
            , "/img/palakentung.png" , "/img/palareja.png" , "/img/palarakha.png"
    };
    private Image foodImage;
    private int foodX;
    private int foodY;

    public Food() {
        this.foodX = 0;
        this.foodY = 0;
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public Image getFoodImage() {
        return foodImage;
    }

    public void generateFood(Snake snake) {
        start: while (true) {
            foodX = (int) (Math.random() * Grid.getRows());
            foodY = (int) (Math.random() * Grid.getColumns());

            for (Point c : snake.getSnakeBody()) {
                if (c.getX() == foodX && c.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }
}
