package professional.tile.creator.model;

import javafx.scene.image.Image;

public class Tileset {
    Image tileset;

    public Tileset(Image tileset) {
        this.tileset = tileset;
    }

    public Image getTileset() {
        return tileset;
    }

    public void setTileset(Image tileset) {
        this.tileset = tileset;
    }
}
