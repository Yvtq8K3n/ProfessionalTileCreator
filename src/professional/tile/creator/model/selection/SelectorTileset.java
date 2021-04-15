package professional.tile.creator.model.selection;

import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.Tileset;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectorTileset implements Selector{
    //Selector coordinates
    protected Tileset tileset;
    protected int startX, startY;
    protected int endX, endY;

    //Selector Block Size
    private int baseBlock;
    private int baseBlockScaled;

    //Selector state
    protected State state;
    protected PropertyChangeSupport changes;

    public SelectorTileset(Tileset tileset, int startX, int startY) throws OutOfBoundsException {
        this.tileset = tileset;
        this.baseBlock = DEFAULT_ROUNDING;
        this.baseBlockScaled = baseBlock * tileset.getScaleFactor();
        this.changes = new PropertyChangeSupport(this);
        this.state = State.CREATED;

        generateCoordinates(startX, startY);

    }

    private void generateCoordinates(int startX, int startY) throws OutOfBoundsException {
        int scale = tileset.getScaleFactor();

        //Rounding start coordinates based on baseBlockScaled
        this.startX = rounding(baseBlockScaled, startX);
        this.startY = rounding(baseBlockScaled, startY);

        //Making sure start point is created within the bounds of the tileset
        if ( this.startX > getTileset().getWidth() * scale)
            throw new OutOfBoundsException(this.getClass().getSimpleName());
        if ( this.startY > tileset.getHeight() * scale)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        //Adding baseBlockedScaled to create a square
        this.endX = this.startX + baseBlockScaled;
        this.endY = this.startY + baseBlockScaled;
    }

    public void setEndX(int endX) throws OutOfBoundsException {
        BufferedImage scaledTileset = tileset.getScaledImage();

        int scale = tileset.getScaleFactor();
        int halfScaledBlock = (DEFAULT_ROUNDING * scale) / 2;

        //Round EndX to Block if Selection coordinates exceed HalfScaledBlock
        int newRoundedX = rounding(baseBlockScaled,endX + halfScaledBlock);

        //Verify if Selector X coordinate exceeds the tileset dimensions
        if (endX > startX) {
            if (newRoundedX - startX < baseBlockScaled
                    || newRoundedX > scaledTileset.getWidth())
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            if (startX - newRoundedX  < baseBlockScaled || newRoundedX < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endX = newRoundedX;
        changes.firePropertyChange("selectorResized", null, endX);
    }

    public void setEndY(int endY) throws OutOfBoundsException {
        BufferedImage scaledTileset = tileset.getScaledImage();

        int scale = tileset.getScaleFactor();
        int halfScaledBlock = (DEFAULT_ROUNDING * scale) / 2;

        //Round EndX to Block if Selection coordinates exceed HalfScaledBlock
        int newRoundedY = rounding(baseBlockScaled,endY + halfScaledBlock);

        //Verify if Selector Y coordinate exceeds the tileset dimensions
        if (endY > startY) {
            if (newRoundedY - startY < baseBlockScaled
                    || newRoundedY > scaledTileset.getHeight())
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            if (startY - newRoundedY  < baseBlockScaled || newRoundedY < 0)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endY = newRoundedY;
        changes.firePropertyChange("selectorResized",  null, this.endY);
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
     * When the tileset factor value changes, the selection will automatically resize
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("scaleChanged")){
            Tileset tileset = getTileset();
            BufferedImage scaledImage = getTileset().getScaledImage();
            int scale = getTileset().getScaleFactor();

            //Converts coordinates based OriginalImage e ScaledImage
            baseBlockScaled = baseBlock * scale;
            startX = startX * tileset.getWidth() / scaledImage.getWidth() * scale;
            startY = startY * tileset.getHeight() / scaledImage.getHeight() * scale;
            endX = endX * tileset.getWidth() / scaledImage.getWidth() * scale;
            endY = endY * tileset.getHeight() / scaledImage.getHeight() * scale;
        }
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    protected Tileset getTileset(){
        return TilesetController.INSTANCE.getTileset();
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
