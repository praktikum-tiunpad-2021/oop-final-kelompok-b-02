package Snake.Game;

public class Grid {
    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;

    public static final int getWidth() {
        return WIDTH;
    }

    public static final int getHeight() {
        return HEIGHT;
    }

    public static final int getRows() {
        return ROWS;
    }

    public static final int getColumns() {
        return COLUMNS;
    }

    public static final int getSquareSize() {
        return SQUARE_SIZE;
    }
}
