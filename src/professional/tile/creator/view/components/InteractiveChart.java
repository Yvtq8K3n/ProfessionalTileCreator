package professional.tile.creator.view.components;

import org.knowm.xchart.BubbleChart;
import org.knowm.xchart.XChartPanel;
import professional.tile.creator.ColorsChart;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;

public class InteractiveChart extends JPanel {

    //View
    protected Representation representation;
    protected Selection selection;

    public InteractiveChart() {
        setBackground(Color.red);
        setLayout( new OverlayLayout(this));

        initComponents();
    }

    private void initComponents() {
        selection = new Selection();
        representation = new Representation();

        add(selection, BorderLayout.CENTER); // add transparent panel first
        add(representation, BorderLayout.CENTER);
    }

    public void setTilesetColors(Color[] colors, ColorsChart.EnumColor refColorA, ColorsChart.EnumColor refColorB) {
        representation.setTilesetColors(colors, refColorA, refColorB);
    }

    protected class Representation extends JPanel{
        //Chart: Red x Green
        ColorsChart colorsChart;
        XChartPanel<BubbleChart> chartPanel;

        public Representation() {
            initComponents();
            setLayout(new BorderLayout());
        }

        private void initComponents() {
            colorsChart = new ColorsChart();
            chartPanel = (XChartPanel<BubbleChart>) colorsChart.getEmptyChart();
            add(chartPanel, BorderLayout.CENTER);
        }

        protected void setTilesetColors(Color[] colors, ColorsChart.EnumColor refColorA, ColorsChart.EnumColor refColorB) {
            remove(chartPanel);
            colorsChart = new ColorsChart(refColorA, refColorB);
            colorsChart.setColors(colors);
            chartPanel = (XChartPanel<BubbleChart>) colorsChart.buildPanel();
            add(chartPanel);
        }
    }

    protected class Selection extends JPanel {

        Area selectedArea;

        public Selection() {
            setOpaque(false);
            selectedArea = new Area();
        }

        @Override
        public void paintChildren(Graphics g) {
            super.paintChildren(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.draw(selectedArea);
        }


    }
}
