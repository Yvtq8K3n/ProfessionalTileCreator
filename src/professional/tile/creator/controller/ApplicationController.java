package professional.tile.creator.controller;

import javafx.scene.image.Image;
import professional.tile.creator.model.Tileset;

public class ApplicationController {
    Tileset tileset;

    public void loadTileset(Image tileset){
        this.tileset = new Tileset(tileset);

    }

}
