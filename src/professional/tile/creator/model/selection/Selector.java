package professional.tile.creator.model.selection;
import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Selector implements PropertyChangeListener {
    int DEFAULT_ROUNDING = 8;
    int DEFAULT_BLOCK = 16;

    public enum State{
        CREATED,
        RESIZING,
        FINISH
    }

    //Selector base structure
    protected int baseBlock;
    protected int startX, startY;
    protected int endX, endY;

    //Selector state
    protected State state;
    protected PropertyChangeSupport changes;

    protected Selector(){
        this.baseBlock = DEFAULT_BLOCK;
        this.changes = new PropertyChangeSupport(this);
        this.state = State.CREATED;
    }

    protected abstract void generateCoordinates(int startX, int startY) throws OutOfBoundsException;
    protected abstract void setEndX(int endX) throws OutOfBoundsException;
    protected abstract void setEndY(int endY) throws OutOfBoundsException;

    public void resizeEndCoordinates(Point endCoordinates) throws OutOfBoundsException{
        OutOfBoundsException e = null;
        try{
            setEndX(endCoordinates.getX());
        }catch (OutOfBoundsException ex){
            e = ex;
        }
        try{
            setEndY(endCoordinates.getY());
        }catch (OutOfBoundsException ex){
            e = ex;
        }
        setState(SelectorTileset.State.RESIZING);
        changes.firePropertyChange("selectorResized",  null, endCoordinates);
        if (e!=null) throw e;
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

    /**
     * @param blockSize Selector block unit
     * @param value a number between 0-192
     * @return
     * Round the given value to a multiple of the given blockSize
     */
    protected int rounding(int blockSize, int value){
        return blockSize * Math.floorDiv(value,blockSize);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }


}
