package professional.tile.creator.controller;


import professional.tile.creator.model.Tileset;
import professional.tile.creator.view.ApplicationView;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum ApplicationController {
    INSTANCE;

    private Tileset tileset;
    private ApplicationView applicationView;

    public void loadTileset(BufferedImage tileset){
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(applicationView);
        applicationView.repaint();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public void setTileset(Tileset tileset) {
        this.tileset = tileset;
    }

    public void setApplicationView(ApplicationView applicationView){
        this.applicationView = applicationView;
    }


}
