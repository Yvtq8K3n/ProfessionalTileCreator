package professional.tile.creator.view;


import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.controller.operators.Operator;
import professional.tile.creator.controller.operators.OperatorColorSelector;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.TilesetColorManager;
import professional.tile.creator.view.components.ColorRepresentation;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.*;

public class ColorsTilesetRepresentation extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Operator[] operadores;
    private Operator operatorAtual;

    public ColorsTilesetRepresentation() {
        setPreferredSize(new Dimension(200, 40));
        TilesetController.INSTANCE.setColorsTilesetRepresentation(this);
        ColorsTileController.INSTANCE.setColorsTilesetRepresentation(this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(true);

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
        ArrayList<ColorRepresentation> btnRectangles = new ArrayList<>();
        TilesetColorManager colorManager = TilesetController.INSTANCE.getTilesetColorManager();
        Color[] colors = colorManager.getSortedColors();
        for (int i = 0; i<colors.length; i++){
            System.out.println("Button created");
            ColorRepresentation btnRectangle = new ColorRepresentation();
            btnRectangle.setBackground(colors[i]);
            btnRectangles.add(btnRectangle);
            add(btnRectangle);
        }

        //Resize Panel to accommodate the new buttons
        int btnRows = (btnRectangles.size() / 8) + 1;
        setPreferredSize(new Dimension(getWidth(), btnRows * ColorRepresentation.DIMENSION));
        repaint();
        getParent().revalidate();
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        SelectorColor selector = ColorsTileController.INSTANCE.getSelector();
        if (selector != null){
            g.setColor(Color.red);
            g.drawRect(selector.getLowestPoint().getX(), selector.getLowestPoint().getY(),
                    selector.getDimensions().getX(), selector.getDimensions().getY());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if (evt.getPropertyName().equals("sortedColors")) drawTilesetColors();
        if (evt.getPropertyName().equals("selectorResized")) {
            System.out.println("was resized");
            revalidate();
            repaint();
        }
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
