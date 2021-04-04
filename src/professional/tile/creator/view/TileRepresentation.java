package professional.tile.creator.view;

import javafx.application.Application;
import professional.tile.creator.Operador;
import professional.tile.creator.OperadorSelector;
import professional.tile.creator.controller.ApplicationController;
import professional.tile.creator.model.Selector;
import professional.tile.creator.model.Tileset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TileRepresentation extends JPanel implements MouseListener, MouseMotionListener{
    private Operador[] operadores;
    private Operador operadorAtual;

    public TileRepresentation(){
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(430, 576));
        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);

        //Create operators
        operadores = new Operador[2];
        operadores[0] = new OperadorSelector();
        operadorAtual = operadores[0];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Tileset tileset = ApplicationController.INSTANCE.getTileset();
        if (tileset!=null && tileset.hasImage()){
            g.drawImage(tileset.getImage(), 0, 0, this);

            Selector selector = ApplicationController.INSTANCE.getSeletor();
            if (selector!=null){
                g.setColor(Color.red);
                g.drawRect(selector.getStartX(), selector.getStartY(),
                        selector.getEndX()-selector.getStartX(), selector.getEndY()-selector.getStartY());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        operadorAtual.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        operadorAtual.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        operadorAtual.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        operadorAtual.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        operadorAtual.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        operadorAtual.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        operadorAtual.mouseMoved(e);
    }
}
