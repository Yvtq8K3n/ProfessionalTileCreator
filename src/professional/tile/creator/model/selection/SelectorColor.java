package professional.tile.creator.model.selection;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectorColor implements Selector {
    private final int COLOR_ROUNDING = 25;

    //Selector coordinates
    protected int startX, startY;
    protected int endX, endY;
    private int maxX, maxY;

    //Selector Block Size
    private int baseBlock;

    //Selector state
    protected State state;
    protected PropertyChangeSupport changes;

    private SelectorColor(int startX, int startY){
        this.baseBlock = COLOR_ROUNDING;
        this.changes = new PropertyChangeSupport(this);
        this.state = State.CREATED;
    }

    /**
     * @param startX initial position
     * @param startY initial position
     * @param maxX maximum value X coordinate can take
     * @param maxY maximum value Y coordinate can take
     * @throws OutOfBoundsException
     */
    public SelectorColor(int startX, int startY, int maxX, int maxY) throws OutOfBoundsException {
        this(startX, startY);
        this.maxX = maxX;
        this.maxY = maxY;

        generateCoordinates(startX, startY);
    }

    private void generateCoordinates(int startX, int startY) throws OutOfBoundsException {
        //Rounding start coordinates based on baseBlockScaled
        this.startX = rounding(baseBlock, startX);
        this.startY = rounding(baseBlock, startY);

        //Making sure start point is created within the bounds of the tileset
        if ( this.startX > maxX)
            throw new OutOfBoundsException(this.getClass().getSimpleName());
        if ( this.startY > maxY)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        //Adding baseBlockedScaled to create a square
        this.endX = this.startX + baseBlock;
        this.endY = this.startY + baseBlock;
    }

    public void setEndX(int endX) throws OutOfBoundsException {
        int halfBlock = (baseBlock) / 2;

        //Round EndX to Block if Selection coordinates exceed halfBlock
        int newRoundedX = rounding(baseBlock,endX + halfBlock);

        //Verify if Selector X coordinate exceeds the tileset dimensions
        if (endX > startX) {
            if (newRoundedX - startX < baseBlock
                    || newRoundedX > maxX)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            if (startX - newRoundedX  < baseBlock || newRoundedX < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endX = newRoundedX;
        changes.firePropertyChange("selectorResized", null, endX);
    }

    public void setEndY(int endY) throws OutOfBoundsException {
        int halfBlock = (baseBlock) / 2;

        //Round EndX to Block if Selection coordinates exceed HalfScaledBlock
        int newRoundedY = rounding(baseBlock,endY + halfBlock);

        //Verify if Selector Y coordinate exceeds the tileset dimensions
        if (endY > startY) {
            if (newRoundedY - startY < baseBlock
                    || newRoundedY > maxY)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            if (startY - newRoundedY  < baseBlock || newRoundedY < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endY = newRoundedY;
        changes.firePropertyChange("selectorResized",  null, this.endY);
    }

    public Point getLowestPoint(){
        int lowestX = (endX>startX) ? startX : endX;
        int lowestY = (endY>startY) ? startY : endY;
        return new professional.tile.creator.model.Point(lowestX, lowestY);
    }

    public Point getDimensions(){
        int width = (endX>startX) ? endX-startX : startX - endX;
        int height = (endY>startY) ? endY-startY : startY - endY;
        return new Point(width, height);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
