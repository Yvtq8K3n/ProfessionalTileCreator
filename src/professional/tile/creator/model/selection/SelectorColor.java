package professional.tile.creator.model.selection;

import professional.tile.creator.exceptions.OutOfBoundsException;
import java.beans.PropertyChangeEvent;

public class SelectorColor extends Selector {
    private final int COLOR_ROUNDING = 25;
    private int maxX, maxY; //Max Bounds

    /**
     * @param startX initial position
     * @param startY initial position
     * @param maxX maximum value X coordinate can take
     * @param maxY maximum value Y coordinate can take
     * @throws OutOfBoundsException
     */
    public SelectorColor(int startX, int startY, int maxX, int maxY) throws OutOfBoundsException {
        super();
        this.baseBlock = COLOR_ROUNDING;
        this.maxX = maxX;
        this.maxY = maxY;

        generateCoordinates(startX, startY);
    }

    protected void generateCoordinates(int startX, int startY) throws OutOfBoundsException {
        //Rounding start coordinates based on baseBlockScaled
        this.startX = rounding(baseBlock, startX);
        this.startY = rounding(baseBlock, startY);

        boolean isStartXOutOfBounds = this.startX <0 || this.startX >= maxX;
        if (isStartXOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        boolean isStartYOutOfBounds = this.startY <0 || this.startY >= maxY;
        if (isStartYOutOfBounds)
            throw new OutOfBoundsException(this.getClass().getSimpleName());

        //Adding baseBlockedScaled to create a square
        this.endX = this.startX + baseBlock;
        this.endY = this.startY + baseBlock;
    }

    protected void setEndX(int endX) throws OutOfBoundsException {
        int halfBlock = (baseBlock) / 2;
        int newRoundedX = rounding(baseBlock,endX + halfBlock); //Round Block if Selection exceed halfBlock
        boolean isBlockValid, isRoundXOutOfBounds;

        if (endX > startX) {
            isBlockValid = newRoundedX - startX >= baseBlock;
            isRoundXOutOfBounds = newRoundedX > maxX;

            if (!isBlockValid || isRoundXOutOfBounds) throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            isBlockValid = startX - newRoundedX >= baseBlock;
            isRoundXOutOfBounds = newRoundedX < 0;

            if (!isBlockValid || isRoundXOutOfBounds) throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endX = newRoundedX;
    }

    protected void setEndY(int endY) throws OutOfBoundsException {
        int halfBlock = (baseBlock) / 2;
        int newRoundedY = rounding(baseBlock,endY + halfBlock); //Round Block if Selection exceed halfBlock
        boolean isBlockValid, isRoundYOutOfBounds;

        //Verify if Selector Y coordinate exceeds the tilesetManager dimensions
        if (endY > startY) {
            isBlockValid = newRoundedY - startY >= baseBlock;
            isRoundYOutOfBounds =  newRoundedY > maxY;

            if (!isBlockValid || isRoundYOutOfBounds) throw new OutOfBoundsException(this.getClass().getSimpleName());
        }else{
            isBlockValid = startY - newRoundedY >= baseBlock;
            isRoundYOutOfBounds =  newRoundedY < 0;

            if (!isBlockValid || isRoundYOutOfBounds) throw new OutOfBoundsException(this.getClass().getSimpleName());
        }

        this.endY = newRoundedY;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
