package professional.tile.creator.view;

import javafx.application.Application;
import professional.tile.creator.controller.ApplicationController;
import professional.tile.creator.model.Tileset;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TileRepresentation extends JPanel {

    public TileRepresentation(){
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(430, 576));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Tileset tileset = ApplicationController.INSTANCE.getTileset();
        if (tileset!=null && tileset.hasImage()){
            g.drawImage(tileset.getImage(), 0, 0, this);
        }
    }

}
