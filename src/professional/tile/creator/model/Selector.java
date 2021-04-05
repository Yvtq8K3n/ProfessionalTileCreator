package professional.tile.creator.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Selector {

    private static final int DEFAULT_ROUNDING = 8;
    private static final int BLOCK_SIZE_ROUNDING = 16;

    public enum State{
        CREATED,
        RESIZING,
        FINISH
    }

    private int startX, startY;
    private int endX, endY;
    private int baseBlock;
    public State state;
    private PropertyChangeSupport changes;

    public Selector() {
        changes = new PropertyChangeSupport(this);
        baseBlock = BLOCK_SIZE_ROUNDING;
        state = State.CREATED;
    }

    public Selector(int startX, int startY) {
        this();
        this.startX = rounding(startX);
        this.startY = rounding(startY);
        this.endX = this.startX + baseBlock;
        this.endY = this.startY + baseBlock;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        int newValue;

        //Identifies the Lowest Point coordinate
        if (endX > startX) {
            //Round to the nearest multiple of base block(default: 8)
            newValue = rounding(endX + DEFAULT_ROUNDING / 2);
            if (newValue - startX < baseBlock) return;
        }else{
            //Round to the nearest multiple of base block(default: 8)
            newValue = rounding(endX + (DEFAULT_ROUNDING/2)*3);
            if (startX - newValue  < baseBlock) return;
        }

        changes.firePropertyChange("endX", this.endX, newValue);
        this.endX = newValue;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        int newValue;

        //Identifies the Lowest Point coordinate
        if (endY > startY) {
            newValue = rounding(endY + DEFAULT_ROUNDING/2);
            if (newValue - startY < baseBlock) return;
        }else{
            //Round to the nearest multiple of base block(default: 8)
            newValue = rounding(endY + (DEFAULT_ROUNDING/2)*3);
            if (startY - newValue  < baseBlock) return;
        }
        changes.firePropertyChange("endY", this.endY, newValue);
        this.endY = newValue;
    }

    public Point getLowestPoint(){
        int lowestX = (endX>startX) ? startX : endX;
        int lowestY = (endY>startY) ? startY : endY;
        return new Point(lowestX, lowestY);
    }

    public Point getDimensions(){
        int width = (endX>startX) ? endX-startX : startX - endX;
        int height = (endY>startY) ? endY-startY : startY - endY;
        return new Point(width, height);
    }

    public void getInitialY(){

    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }


    private int rounding(int value){
        return baseBlock * Math.round(value/ baseBlock);
    }
}
