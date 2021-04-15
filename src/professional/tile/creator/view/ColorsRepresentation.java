package professional.tile.creator.view;


import professional.tile.creator.controller.Operator;
import professional.tile.creator.controller.OperatorColorSelector;
import professional.tile.creator.controller.OperatorTileSelector;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.TilesetColorManager;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.*;

public class ColorsRepresentation extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Operator[] operadores;
    private Operator operatorAtual;

    public ColorsRepresentation() {
        setPreferredSize(new Dimension(200, 40));
        TilesetController.INSTANCE.setColorsRepresentation(this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        //Create operators
        operadores = new Operator[1];
        operadores[0] = new OperatorColorSelector();
        operatorAtual = operadores[0];

    }

    public void drawTilesetColors(){
        removeAll();
        ArrayList<ColorButton> btnRectangles = new ArrayList<>();
        TilesetColorManager colorManager = TilesetController.INSTANCE.getTilesetColorManager();
        Color[] colors = colorManager.getSortedColors();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if (evt.getPropertyName().equals("sortedColors")) drawTilesetColors();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        operatorAtual.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        operatorAtual.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        operatorAtual.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        operatorAtual.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        operatorAtual.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        operatorAtual.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        operatorAtual.mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        operatorAtual.mouseWheelMoved(e);
    }

}
