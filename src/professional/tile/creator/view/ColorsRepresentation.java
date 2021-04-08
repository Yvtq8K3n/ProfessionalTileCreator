package professional.tile.creator.view;

import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Tileset;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ColorsRepresentation extends JPanel implements PropertyChangeListener {
    ArrayList<ColorButton> btnRectangles;

    public ColorsRepresentation() {
        setPreferredSize(new Dimension(200, 40));
        TilesetController.INSTANCE.setColorsRepresentation(this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    }

    public void drawTilesetColors(){
        removeAll();
        btnRectangles = new ArrayList<>();
        Tileset tileset = TilesetController.INSTANCE.getTileset();
        Color colors[] = tileset.getColors();
        for (int i = 0; i<colors.length; i++){
            System.out.println("Button created");
            ColorButton btnRectangle = new ColorButton();
            btnRectangle.setBackground(colors[i]);
            btnRectangles.add(btnRectangle);
            add(btnRectangle);
        }
        revalidate();
        repaint();
    }

    private void updateTilesetColors() {
        System.out.println("NOT YET SUPPORTED");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateTilesetColors();
    }
}
