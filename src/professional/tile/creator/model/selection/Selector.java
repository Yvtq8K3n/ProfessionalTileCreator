package professional.tile.creator.model.selection;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Tileset;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Selector extends PropertyChangeListener {
    enum State{
        CREATED,
        RESIZING,
        FINISH
    }

    int DEFAULT_ROUNDING = 8;
    int BLOCK_SIZE_ROUNDING = 16;

    State getState();
    void setState(State state);

    /**
     * @param blockSize Selector block unit
     * @param value a number between 0-192
     * @return
     * Round the given value to a multiple of the given blockSize
     */
    default int rounding(int blockSize, int value){
        return blockSize * Math.floorDiv(value,blockSize);
    }

}
