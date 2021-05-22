package professional.tile.creator.view;


import org.knowm.xchart.BubbleChart;
import org.knowm.xchart.XChartPanel;
import professional.tile.creator.ColorsChart;
import professional.tile.creator.ColorsChart.*;
import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.controller.operators.Operator;
import professional.tile.creator.controller.operators.OperatorMultiColorDeletion;
import professional.tile.creator.controller.operators.OperatorMultiColorSelection;
import professional.tile.creator.controller.operators.OperatorSingleColorSelection;
import professional.tile.creator.model.TilesetColorsManager;
import professional.tile.creator.view.components.ColorRepresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class ClusterRepresentation extends JPanel{
    //Model
    protected TilesetColorsManager colorManager;

    //View
    protected Representation representation;
    protected Selection selection;

    public ClusterRepresentation() {
        setLayout( new OverlayLayout(this));
        setPreferredSize(new Dimension(384, 576));
        ColorsTileController.INSTANCE.setClusterRepresentation(this);

        initComponents();
    }

    private void initComponents() {
        selection = new Selection();
        representation = new Representation();

        add(selection, BorderLayout.CENTER);
        add(representation, BorderLayout.CENTER);
    }

    public void redraw(){
        revalidate();
        repaint();
    }


    public void reloadTilesetColors() {
        representation.reloadTilesetColors();
    }

    public void setColorManager(TilesetColorsManager colorManager) {
        this.colorManager = colorManager;
    }

    protected class Representation extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
        private Operator[] operadores;
        private Operator operatorAtual;

        //Chart: Red x Green
        ColorsChart colorsChartRG;
        XChartPanel<BubbleChart> chartPanelRG;

        //Chart: Red x Blue
        ColorsChart colorsChartRB;
        XChartPanel<BubbleChart> chartPanelRB;

        //Chart: Green x Blue
        ColorsChart colorsChartGB;
        XChartPanel<BubbleChart> chartPanelGB;

        public Representation() {
                setLayout(new GridLayout(3, 0));
                //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
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

        public void reloadTilesetColors() {
            colorsChartRG = new ColorsChart(EnumColor.RED, EnumColor.GREEN);
            colorsChartRG.setColors(colorManager.getColors());
            chartPanelRG = colorsChartRG.buildPanel();
            add(chartPanelRG);

            colorsChartRB = new ColorsChart(EnumColor.RED, EnumColor.BLUE);
            colorsChartRB.setColors(colorManager.getColors());
            chartPanelRB = colorsChartRB.buildPanel();
            add(chartPanelRB);


            colorsChartGB = new ColorsChart(EnumColor.GREEN, EnumColor.BLUE);
            colorsChartGB.setColors(colorManager.getColors());
            chartPanelGB = colorsChartGB.buildPanel();
            add(chartPanelGB);
        }

            @Override
            public void mouseClicked (MouseEvent e){
                operatorAtual.mouseClicked(e);
            }

            @Override
            public void mousePressed (MouseEvent e){
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
            public void mouseReleased (MouseEvent e){
                operatorAtual.mouseReleased(e);
            }

            @Override
            public void mouseEntered (MouseEvent e){
                operatorAtual.mouseEntered(e);
            }

            @Override
            public void mouseExited (MouseEvent e){
                operatorAtual.mouseExited(e);
            }

            @Override
            public void mouseDragged (MouseEvent e){
                operatorAtual.mouseDragged(e);
            }

            @Override
            public void mouseMoved (MouseEvent e){
                operatorAtual.mouseMoved(e);
            }

            @Override
            public void mouseWheelMoved (MouseWheelEvent e){
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
                    //add(new Point(relX, relY), new Point(ColorRepresentation.DIMENSION, ColorRepresentation.DIMENSION));
                }
            }
        }

        public void clear() {
            selectedArea = new Area();
        }
    }
}
