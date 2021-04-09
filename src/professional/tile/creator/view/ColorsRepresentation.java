package professional.tile.creator.view;


import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.TilesetColorManager;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.*;

public class ColorsRepresentation extends JPanel implements PropertyChangeListener {
    JScrollPane scrollPanel;

    ArrayList<ColorButton> btnRectangles;

    public ColorsRepresentation() {
        setPreferredSize(new Dimension(200, 40));
        TilesetController.INSTANCE.setColorsRepresentation(this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

    }

    public void drawTilesetColors(){
        removeAll();
        btnRectangles = new ArrayList<>();
        TilesetColorManager colorManager = TilesetController.INSTANCE.getTilesetColorManager();
        Color[] colors = colorManager.getColorsSortByStepSorting();
        for (int i = 0; i<colors.length; i++){
            System.out.println("Button created");
            ColorButton btnRectangle = new ColorButton();
            btnRectangle.setBackground(colors[i]);
            btnRectangles.add(btnRectangle);
            add(btnRectangle);
        }

        //Resize Panel to accommodate the new buttons
        int btnRows = (btnRectangles.size() / 8) + 1;
        setPreferredSize(new Dimension(getWidth(), btnRows * ColorButton.DIMENSION));
        repaint();
        getParent().revalidate();
    }

    private void updateTilesetColors() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
    }

    public void addScrollPanel(JScrollPane scrollPanel) {
            this.scrollPanel = scrollPanel;
    }
}
