package professional.tile.creator.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class PanelTileset extends JPanel {
    TilesetMenu tilesetMenu;

    JTabbedPane tabbedPane;
    TileRepresentation tileRepresentation;
    ClusterRepresentation clusterRepresentation;


    PanelTileset() {
        initComponents();
   }

    public void initComponents(){
        //Panel1
        tilesetMenu = new TilesetMenu();
        tilesetMenu.setPreferredSize(new Dimension(384, 40));

        //Panel2
        tileRepresentation = new TileRepresentation();
        JScrollPane scrollTilePanel = new JScrollPane(tileRepresentation);
        scrollTilePanel.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollTilePanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        clusterRepresentation = new ClusterRepresentation();
        JScrollPane scrollClusterPanel = new JScrollPane(clusterRepresentation);
        scrollClusterPanel.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollClusterPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tileset", scrollTilePanel);//Single Operations
        tabbedPane.addTab("Cluster", scrollClusterPanel);//MultiOperations
        tabbedPane.addTab("Misc", new JPanel());

        //MainJPanel
        setLayout(new BorderLayout());
        add(tilesetMenu, BorderLayout.PAGE_START);
        add(tabbedPane, BorderLayout.CENTER);
    }

}
