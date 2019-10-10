package org.academiadecodigo.bootcamp.gfx.simplegfx;

import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.AbstractGridPosition;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private Rectangle rectangle;
    private SimpleGfxGrid simpleGfxGrid;

    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid){

        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);

        rectangle = new Rectangle(grid.columnToX(super.getCol()), grid.rowToY(super.getRow()), grid.getCellSize(), grid.getCellSize());
        show();
    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid){
        super(col, row, grid);

        rectangle = new Rectangle(grid.columnToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize());
        show();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {

        rectangle.fill();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {

        rectangle.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {

        int oldRow = getRow(), oldCol = getCol();
        int remainder, maxDistance;
        SimpleGfxGrid grid = (getGrid() instanceof SimpleGfxGrid ? (SimpleGfxGrid) getGrid() : null);
        int cellSize = grid.getCellSize();
        int dist = distance, row = getRow(), col = getCol();
        int rows = getGrid().getRows(), cols = grid.getCols();

        super.moveInDirection(direction, distance);

        switch (direction) {
            case UP:
                maxDistance = dist < row ? dist : row;
                //rectangle.translate(0.0, (row - maxDistance) * cellSize);
                rectangle.translate(0.0, (-maxDistance) * cellSize);
                break;
            case DOWN:
                remainder = rows - (row + 1);
                maxDistance = dist > remainder ? remainder : dist;
                rectangle.translate(0.0, (maxDistance) * cellSize);
                break;
            case LEFT:
                maxDistance = dist < col ? dist : col;
                rectangle.translate((-maxDistance) * cellSize,0.0);
                break;
            case RIGHT:
                remainder = cols - (col + 1);
                maxDistance = dist > remainder ? remainder : dist;
                rectangle.translate((maxDistance) * cellSize,0.0);
                break;
        }
    }

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {

        super.setColor(color);
        switch (color) {
            case BLUE:
                rectangle.setColor(Color.BLUE);
                break;
            case RED:
                rectangle.setColor(Color.RED);
                break;
            case GREEN:
                rectangle.setColor(Color.GREEN);
                break;
            case MAGENTA:
                rectangle.setColor(Color.MAGENTA);
                break;
            case NOCOLOR:
                rectangle.setColor(Color.WHITE);
                break;
            default:
                // Should handle this better probably by throwing a suitable exception;
                return;
        }

        show();
    }
}
