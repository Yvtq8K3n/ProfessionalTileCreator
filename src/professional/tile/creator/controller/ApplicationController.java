package professional.tile.creator.controller;


import professional.tile.creator.Exceptions.OutOfBoundsException;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;
import professional.tile.creator.view.ApplicationView;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum ApplicationController {
    INSTANCE;

    private Tileset tileset;
    private ApplicationView applicationView;
    private Selector seletor;

    public void loadTileset(BufferedImage tileset){
        this.tileset = new Tileset(tileset);
        this.tileset.addPropertyChangeListener(applicationView);
        applicationView.repaint();
    }

    public Tileset getTileset() {
        return tileset;
    }

    public int getWidth(){
        return tileset.getImage().getWidth();
    }

    public int getHeight(){
        return tileset.getImage().getHeight();
    }

    public void setTileset(Tileset tileset) {
        this.tileset = tileset;
    }

    public void setApplicationView(ApplicationView applicationView){
        this.applicationView = applicationView;
    }

    public ApplicationView getApplicationView() {
        return applicationView;
    }

    public void createSelector(int x, int y) throws OutOfBoundsException {
        seletor = new Selector(x, y);
        seletor.addPropertyChangeListener(applicationView);
        applicationView.repaint();
    }

    public Selector getSeletor() {
        return seletor;
    }

    public void setSeletor(Selector seletor) {
        this.seletor = seletor;
    }
}
