package professional.tile.creator.view;

import professional.tile.creator.controller.operators.Operator;
import professional.tile.creator.controller.operators.OperatorTileSelector;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.model.TilesetManager;
import professional.tile.creator.model.selection.SelectorTileset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TileRepresentation extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private Operator[] operadores;
    private Operator operatorAtual;

    TileRepresentation(){
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(384, 576));
        TilesetController.INSTANCE.setTileRepresentation(this);

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        //Create operators
        operadores = new Operator[2];
        operadores[0] = new OperatorTileSelector();
        operatorAtual = operadores[0];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        TilesetManager tilesetManager = TilesetController.INSTANCE.getTilesetManager();
        if (tilesetManager !=null && tilesetManager.hasImage()){
            g.drawImage(tilesetManager.getScaledImage(), 0, 0, this);

            SelectorTileset selectorTileset = TilesetController.INSTANCE.getSelector();
            if (selectorTileset !=null){
                g.setColor(Color.red);
               g.drawRect(selectorTileset.getLowestPoint().getX(), selectorTileset.getLowestPoint().getY(),
                       selectorTileset.getDimensions().getX(), selectorTileset.getDimensions().getY());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("tilesetScaled")){
            BufferedImage bufferedImage = (BufferedImage) evt.getNewValue();
            setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
        }
        repaint();
        getParent().revalidate();
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
