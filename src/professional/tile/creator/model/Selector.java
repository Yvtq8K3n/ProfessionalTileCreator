package professional.tile.creator.model;

import javafx.scene.layout.TilePane;
import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.controller.ApplicationController;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Selector implements PropertyChangeListener{
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
    private int baseBlockScaled;
    public State state;
    private PropertyChangeSupport changes;

    private Selector() {
        changes = new PropertyChangeSupport(this);
        baseBlock = DEFAULT_ROUNDING;
        state = State.CREATED;
    }

    public Selector(int startX, int startY) throws OutOfBoundsException {
        this();
        Tileset tileset = getTileset();
        int scale = tileset.getScaleFactor();
        this.baseBlockScaled = baseBlock * scale;

        //Making sure start point is created within the bounds of the tileset
        this.startX = rounding(startX);
        if ( this.startX > getTileset().getWidth() * scale)
            throw new OutOfBoundsException(this.getClass().getSimpleName());
        this.startY = rounding(startY);
        if ( this.startY > tileset.getHeight() * scale)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        this.endX = this.startX + baseBlockScaled;
        this.endY = this.startY + baseBlockScaled;
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

    public void setEndX(int endX) throws OutOfBoundsException {
        int scale = getTileset().getScaleFactor();
        BufferedImage tileset = getTileset().getScaledImage();

        int newValue;
        //Identify the Lowest X coordinate in order to use it as point of reference
        if (endX > startX) {
            //Force selection to only have DEFAULT_ROUNDING blocks
            newValue = rounding(endX + (DEFAULT_ROUNDING*scale) / 2);
            if (newValue - startX < baseBlockScaled
                    || newValue > tileset.getWidth())
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            //Force selection to only have DEFAULT_ROUNDING blocks
            newValue = rounding(endX + (DEFAULT_ROUNDING*scale/2)*3);
            if (startX - newValue  < baseBlockScaled || newValue < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        changes.firePropertyChange("endX", this.endX, newValue);
        this.endX = newValue;
    }

    public void setEndY(int endY) throws OutOfBoundsException {
        int scale = getTileset().getScaleFactor();
        BufferedImage tileset = getTileset().getScaledImage();

        int newValue;
        //Identify the Lowest Y coordinate in order to use it as point of reference
        if (endY > startY) {
            ///Force selection to only have DEFAULT_ROUNDING blocks
            newValue = rounding(endY + DEFAULT_ROUNDING*scale/2);
            if (newValue - startY < baseBlockScaled
                    || newValue > tileset.getHeight())
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            //Force selection to only have DEFAULT_ROUNDING blocks
            newValue = rounding(endY + (DEFAULT_ROUNDING*scale/2)*3);
            if (startY - newValue  < baseBlockScaled || newValue < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
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

    /**
     * @param evt
     * When the tileset factor value changes, the selector will automatically resize
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Tileset tileset = getTileset();
        BufferedImage scaledImage = getTileset().getScaledImage();
        int scale = getTileset().getScaleFactor();
        baseBlockScaled = baseBlock * scale;
        startX = startX * tileset.getWidth() / scaledImage.getWidth() * scale;
        startY = startY * tileset.getHeight() / scaledImage.getHeight() * scale;
        endX = endX * tileset.getWidth() / scaledImage.getWidth() * scale;
        endY = endY * tileset.getHeight() / scaledImage.getHeight() * scale;
    }

    private int rounding(int value){
        return baseBlockScaled * Math.round(value/ baseBlockScaled);
    }

    private Tileset getTileset(){
        return ApplicationController.INSTANCE.getTileset();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

}
