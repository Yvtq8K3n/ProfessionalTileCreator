package professional.tile.creator.view;


import org.knowm.xchart.BubbleChart;
import org.knowm.xchart.XChartPanel;
import professional.tile.creator.ColorsChart;
import professional.tile.creator.ColorsChart.*;
import professional.tile.creator.controller.ColorsTileController;
import professional.tile.creator.controller.TilesetController;
import professional.tile.creator.controller.operators.*;
import professional.tile.creator.model.TilesetColorsManager;
import professional.tile.creator.view.components.ColorRepresentation;
import professional.tile.creator.view.components.InterativeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class ClusterRepresentation extends JPanel{
    //Model
    protected TilesetColorsManager colorManager;

    //Chart: Red x Green
    ColorsChart colorsChartRG;
    XChartPanel<BubbleChart> chartPanelRG;

    //Chart: Red x Blue
    ColorsChart colorsChartRB;
    XChartPanel<BubbleChart> chartPanelRB;

    //Chart: Green x Blue
    ColorsChart colorsChartGB;
    XChartPanel<BubbleChart> chartPanelGB;

    public ClusterRepresentation() {
        setLayout(new GridLayout(3, 0));
        setPreferredSize(new Dimension(384, 576));
        TilesetController.INSTANCE.setClusterRepresentation(this);
        ColorsTileController.INSTANCE.setClusterRepresentation(this);

        initComponents();
    }

    private void initComponents() {
        colorsChartRG = new ColorsChart();
        chartPanelRG = (XChartPanel<BubbleChart>) colorsChartRG.getEmptyChart();
        add(chartPanelRG);

        colorsChartRB = new ColorsChart();
        chartPanelRB = (XChartPanel<BubbleChart>) colorsChartRB.getEmptyChart();
        add(chartPanelRB);

        colorsChartGB = new ColorsChart();
        chartPanelGB = (XChartPanel<BubbleChart>) colorsChartGB.getEmptyChart();
        add(chartPanelGB);
    }

    public void reloadTilesetColors() {
        remove(chartPanelRG);
        colorsChartRG = new ColorsChart(EnumColor.RED, EnumColor.GREEN);
        colorsChartRG.setColors(colorManager.getColors());
        chartPanelRG = (XChartPanel<BubbleChart>) colorsChartRG.buildPanel();
        add(chartPanelRG);

        remove(chartPanelRB);
        colorsChartRB = new ColorsChart(EnumColor.RED, EnumColor.BLUE);
        colorsChartRB.setColors(colorManager.getColors());
        chartPanelRB = (XChartPanel<BubbleChart>) colorsChartRB.buildPanel();
        add(chartPanelRB);

        remove(chartPanelGB);
        colorsChartGB = new ColorsChart(EnumColor.GREEN, EnumColor.BLUE);
        colorsChartGB.setColors(colorManager.getColors());
        chartPanelGB = (XChartPanel<BubbleChart>) colorsChartGB.buildPanel();
        add(chartPanelGB);

        redraw();
    }

    public void redraw(){
        revalidate();
        repaint();
    }

    public void setColorManager(TilesetColorsManager colorManager) {
        this.colorManager = colorManager;
    }
}
