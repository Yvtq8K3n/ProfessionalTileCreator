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
import professional.tile.creator.view.components.InteractiveChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class ClusterRepresentation extends JPanel{
    //Model
    protected TilesetColorsManager colorManager;

    //Chart: Red x Green
    InteractiveChart chartPanelRG;

    //Chart: Red x Blue
    InteractiveChart chartPanelRB;

    //Chart: Green x Blue
    InteractiveChart chartPanelGB;

    public ClusterRepresentation() {
        setLayout(new GridLayout(3, 0));
        setPreferredSize(new Dimension(384, 576));
        TilesetController.INSTANCE.setClusterRepresentation(this);
        ColorsTileController.INSTANCE.setClusterRepresentation(this);

        initComponents();
    }

    private void initComponents() {
        chartPanelRG = new InteractiveChart();
        add(chartPanelRG);

        chartPanelRB = new InteractiveChart();
        add(chartPanelRB);

        chartPanelGB = new InteractiveChart();
        add(chartPanelGB);
    }

    public void reloadTilesetColors() {
        chartPanelRG.setTilesetColors(colorManager.getColors(), EnumColor.RED, EnumColor.GREEN);
        chartPanelRB.setTilesetColors(colorManager.getColors(), EnumColor.RED, EnumColor.BLUE);
        chartPanelGB.setTilesetColors(colorManager.getColors(), EnumColor.GREEN, EnumColor.BLUE);

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
