package professional.tile.creator.view;

import professional.tile.creator.controller.Operator;
import professional.tile.creator.controller.OperatorSelector;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileRepresentation extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Operator[] operadores;
    private Operator operatorAtual;

    public TileRepresentation(){
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(385, 576));
        TilesetController.INSTANCE.setTileRepresentation(this);

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        //Create operators
        operadores = new Operator[2];
        operadores[0] = new OperatorSelector();
        operatorAtual = operadores[0];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Tileset tileset = TilesetController.INSTANCE.getTileset();
        if (tileset!=null && tileset.hasImage()){
            g.drawImage(tileset.getScaledImage(), 0, 0, this);

            Selector selector = TilesetController.INSTANCE.getSelector();
            if (selector!=null){
                g.setColor(Color.red);
               g.drawRect(selector.getLowestPoint().getX(), selector.getLowestPoint().getY(),
                       selector.getDimensions().getX(), selector.getDimensions().getY());
            }
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
