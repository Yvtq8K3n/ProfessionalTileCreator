package professional.tile.creator.model.selection;

import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;

import java.util.ArrayList;

public class SelectorChart extends Selector {
    private int maxX, maxY; //Max Bounds
    private ArrayList<Point> points;

    public SelectorChart(int startX, int startY, int maxX, int maxY) throws OutOfBoundsException {
        super();
        this.maxX = maxX;
        this.maxY = maxY;
        points = new ArrayList<>();
        generateCoordinates(startX, startY);
    }


    @Override
    protected void generateCoordinates(int startX, int startY) throws OutOfBoundsException {
        this.startX = startX;
        this.startY = startY;

        boolean isStartXOutOfBounds = this.startX <0 || this.startX >= maxX;
        if (isStartXOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        boolean isStartYOutOfBounds = this.startY <0 || this.startY >= maxY;
        if (isStartYOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());
    }

    public void addCoordinate(Point point){
        points.add(point);
    }

    @Override
    protected void setEndX(int endX) throws OutOfBoundsException {

    }

    @Override
    protected void setEndY(int endY) throws OutOfBoundsException {

    }
}
