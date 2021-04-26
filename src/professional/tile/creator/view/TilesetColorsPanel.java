package professional.tile.creator.view;

import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.controller.operators.Operator;
import professional.tile.creator.controller.operators.OperatorMultiColorDeletion;
import professional.tile.creator.controller.operators.OperatorMultiColorSelection;
import professional.tile.creator.controller.operators.OperatorSingleColorSelection;
import professional.tile.creator.exceptions.InvalidOperationException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.TilesetColorsManager;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.view.components.ColorRepresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class TilesetColorsPanel extends JPanel implements PropertyChangeListener{
    public enum Operation{
        SINGLE,
        MULTI
    }

    //Model
    protected TilesetColorsManager colorManager;

    //View
    protected Representation representation;
    protected Selection selection;

    public TilesetColorsPanel() {
        setBackground(Color.red);
        setLayout( new OverlayLayout(this));
        ColorsTileController.INSTANCE.setTilesetColorsPanel(this);

        initComponents();
    }

    private void initComponents() {
        selection = new Selection();
        representation = new Representation();

        add(selection, BorderLayout.CENTER); // add transparent panel first
        add(representation, BorderLayout.CENTER);
    }

    public void redraw(){
        revalidate();
        repaint();
    }

    public void reloadTilesetColors() {
        representation.drawTilesetColors();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if (evt.getPropertyName().equals("sortedColors")) {
            representation.drawTilesetColors();
            selection.redrawSelectorsPosition();
        }
        if (evt.getPropertyName().equals("selectorResized")) {
            redraw();
        }
    }

    public void removeSelection(SelectorColor selector) {
        selection.remove(selector);
    }

    public void addSelection(SelectorColor selector) {
        selection.add(selector);
    }

    public ArrayList<Color> retrieveSelectedColors(SelectorColor selector) {
        Point start = selector.getLowestPoint();
        Point end = selector.getDimensions();
        end = new Point(start.getX() + end.getX(), start.getY() + end.getY());
        return representation.retrieveColors(start, end);
    }

    public void setColorManager(TilesetColorsManager colorManager) {
        this.colorManager = colorManager;
    }

    public void setOperator(Operation operation){
        try {
            representation.setOperation(operation);
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    protected class Representation extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
        private Operator[] operadores;
        private Operator operatorAtual;

        public Representation() {
            setPreferredSize(new Dimension(200, 40));
            setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            setOpaque(true);

            //Add event listeners
            addMouseListener(this);
            addMouseMotionListener(this);
            addMouseWheelListener(this);

            //Create operators
            operadores = new Operator[3];
            operadores[0] = new OperatorSingleColorSelection();
            operadores[1] = new OperatorMultiColorSelection();
            operadores[2] = new OperatorMultiColorDeletion();
            operatorAtual = operadores[0];
        }

        public void drawTilesetColors(){
            removeAll();
            ArrayList<ColorRepresentation> btnRectangles = new ArrayList<>();
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
            redraw();
        }

        public ArrayList<Color> retrieveColors(Point start, Point end){
            ArrayList<Color> colors = new ArrayList<>();
            for (int i=start.getY(); i<end.getY(); i+=ColorRepresentation.DIMENSION){
                for (int j=start.getX(); j<end.getX(); j+=ColorRepresentation.DIMENSION){
                    System.out.println(j+":"+i);
                    ColorRepresentation rep = (ColorRepresentation) getComponentAt(j, i);
                    colors.add(rep.getBackground());
                }
            }
            return colors;
        }

        public void setOperation(Operation operation) throws InvalidOperationException {
            switch (operation){
                case SINGLE:
                    operatorAtual = operadores[0];
                break;
                case MULTI:
                    operatorAtual = operadores[1];
                break;
                default:
                    throw new InvalidOperationException("Operation:");
            }
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
        public void mouseClicked(MouseEvent e) {
            operatorAtual.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            boolean isOperationSingle = operatorAtual == operadores[0];

            if (!isOperationSingle) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    operatorAtual = operadores[1];
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    operatorAtual = operadores[2];
                }
            }
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

    protected class Selection extends JPanel {
        final float dash1[] = {10.0f};
        final BasicStroke dashed =
                new BasicStroke(2.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);

        Area selectedArea;

        public Selection() {
            setOpaque(false);
            setLayout(null);
            selectedArea = new Area();
        }

        @Override
        public void paintChildren(Graphics g) {
            super.paintChildren(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.setStroke(dashed);
            g2.draw(selectedArea);
        }

        public void add(SelectorColor selector) {
            Point position = selector.getLowestPoint();
            Point dimensions = selector.getDimensions();

            add(position, dimensions);
        }

        private void add(Point position, Point dimensions) {
            Rectangle rec = new Rectangle(position.getX(), position.getY(),
                    dimensions.getX(),dimensions.getY());
            selectedArea.add(new Area(rec));
        }

        public void remove(SelectorColor selector) {
            Point position = selector.getLowestPoint();
            Point dimensions = selector.getDimensions();

            Rectangle rec = new Rectangle(position.getX(), position.getY(),
                    dimensions.getX(),dimensions.getY());
            selectedArea.subtract(new Area(rec));
        }

        public void redrawSelectorsPosition() {
            selectedArea = new Area();
            Color[] sortedColors = colorManager.getSortedColors();
            ArrayList<Color> selectedColors = colorManager.getSelectedColors();

            for(int i=0; i<sortedColors.length; i++) {
                int y = i / 8;
                int x = i % 8;
                //Is one of the current color selected?
                if (selectedColors.contains(sortedColors[i])) {
                    System.out.println(selectedColors.size());
                    System.out.println(sortedColors[i]);
                    int relY = y * ColorRepresentation.DIMENSION;
                    int relX = x * ColorRepresentation.DIMENSION;

                    //Add Selection
                    add(new Point(relX, relY), new Point(ColorRepresentation.DIMENSION, ColorRepresentation.DIMENSION));
                }
            }
        }
    }
}
