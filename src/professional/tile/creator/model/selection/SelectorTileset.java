package professional.tile.creator.model.selection;

import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Tileset;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

public class SelectorTileset extends Selector {

    protected Tileset tileset;
    private int baseBlockScaled;

    public SelectorTileset(Tileset tileset, int startX, int startY) throws OutOfBoundsException {
        super();
        this.tileset = tileset;
        this.baseBlock = DEFAULT_ROUNDING;
        this.baseBlockScaled = baseBlock * tileset.getScaleFactor();

        generateCoordinates(startX, startY);
    }

    protected void generateCoordinates(int startX, int startY) throws OutOfBoundsException {
        //Rounding start coordinates based on baseBlockScaled
        this.startX = rounding(baseBlockScaled, startX);
        this.startY = rounding(baseBlockScaled, startY);
        int scale = tileset.getScaleFactor();

        boolean isStartXOutOfBounds = this.startX > getTileset().getWidth() * scale;
        if (isStartXOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        boolean isStartYOutOfBounds = this.startY > tileset.getHeight() * scale;
        if (isStartYOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        //Adding baseBlockedScaled to create a square
        this.endX = this.startX + baseBlockScaled;
        this.endY = this.startY + baseBlockScaled;
    }

    protected void setEndX(int endX) throws OutOfBoundsException {
        BufferedImage scaledTileset = tileset.getScaledImage();
        int scale = tileset.getScaleFactor();
        int halfScaledBlock = (DEFAULT_ROUNDING * scale) / 2;
        int newRoundedX = rounding(baseBlockScaled,endX + halfScaledBlock);
        boolean isBlockValid, isRoundXOutOfBounds;

        if (endX > startX) {
            isBlockValid = newRoundedX - startX >= baseBlockScaled;
            isRoundXOutOfBounds = newRoundedX > scaledTileset.getWidth();

            if (!isBlockValid || isRoundXOutOfBounds) throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            isBlockValid = startX - newRoundedX >= baseBlockScaled;
            isRoundXOutOfBounds = newRoundedX < 0;

            if (!isBlockValid || isRoundXOutOfBounds)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endX = newRoundedX;
    }

    protected void setEndY(int endY) throws OutOfBoundsException {
        BufferedImage scaledTileset = tileset.getScaledImage();
        int scale = tileset.getScaleFactor();
        int halfScaledBlock = (DEFAULT_ROUNDING * scale) / 2;
        int newRoundedY = rounding(baseBlockScaled,endY + halfScaledBlock);
        boolean isBlockValid, isRoundYOutOfBounds;

        if (endY > startY) {
            isBlockValid = newRoundedY - startY >= baseBlockScaled;
            isRoundYOutOfBounds = newRoundedY > scaledTileset.getHeight();

            if (!isBlockValid || isRoundYOutOfBounds)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            isBlockValid = startY - newRoundedY >= baseBlockScaled;
            isRoundYOutOfBounds = newRoundedY < 0;

            if (!isBlockValid || isRoundYOutOfBounds)
                throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endY = newRoundedY;
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

            //Converts coordinates based OriginalImage and ScaledImage
            baseBlockScaled = baseBlock * scale;
            startX = startX * tileset.getWidth() / scaledImage.getWidth() * scale;
            startY = startY * tileset.getHeight() / scaledImage.getHeight() * scale;
            endX = endX * tileset.getWidth() / scaledImage.getWidth() * scale;
            endY = endY * tileset.getHeight() / scaledImage.getHeight() * scale;
        }
    }

    protected Tileset getTileset(){
        return TilesetController.INSTANCE.getTileset();
    }
}
