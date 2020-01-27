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

        simpleGfxGrid = grid;
        int x = grid.columnToX(getCol());
        int y = grid.rowToY(getRow());
        rectangle = new Rectangle(x, y, grid.getCellSize(), grid.getCellSize());
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

        simpleGfxGrid = grid;
        int x = grid.columnToX(col);
        int y = grid.rowToY(row);
        rectangle = new Rectangle(x, y, grid.getCellSize(), grid.getCellSize());
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
    /**@Override
    public void moveInDirection(GridDirection direction, int distance) {

        int remainder, maxDistance;
        int cellSize = simpleGfxGrid.getCellSize();
        int row = getRow();
        int col = getCol();
        int rows = getGrid().getRows(), cols = simpleGfxGrid.getCols();

        super.moveInDirection(direction, distance);

        switch (direction) {
            case UP:
                maxDistance = distance < row ? distance : row;
                //rectangle.translate(0.0, (row - maxDistance) * cellSize);
                rectangle.translate(0.0, (-maxDistance) * cellSize);
                break;
            case DOWN:
                remainder = rows - (row + 1);
                maxDistance = distance > remainder ? remainder : distance;
                rectangle.translate(0.0, (maxDistance) * cellSize);
                break;
            case LEFT:
                maxDistance = distance < col ? distance : col;
                rectangle.translate((-maxDistance) * cellSize,0.0);
                break;
            case RIGHT:
                remainder = cols - (col + 1);
                maxDistance = distance > remainder ? remainder : distance;
                rectangle.translate((maxDistance) * cellSize, 0.0);
                break;
        }
    }*/

    @Override
    public void moveInDirection (GridDirection direction, int distance) {

        int initialCol = getCol();
        int initialRow = getRow();

        super.moveInDirection(direction, distance);

        int dx = simpleGfxGrid.columnToX(getCol()) - simpleGfxGrid.columnToX(initialCol);
        int dy = simpleGfxGrid.rowToY(getRow()) - simpleGfxGrid.rowToY(initialRow);

        rectangle.translate(dx, dy);
    }

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {

        this.rectangle.setColor(SimpleGfxColorMapper.getColor(color));
        super.setColor(color);
    }
}
