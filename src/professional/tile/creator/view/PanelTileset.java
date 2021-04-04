package professional.tile.creator.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PanelTileset extends JPanel {


    TilesetMenu tilesetMenu;
    TileRepresentation tileRepresentation;


    PanelTileset() {
        initComponents();
   }

    public void initComponents(){
        //Panel1
        tilesetMenu = new TilesetMenu();
        tilesetMenu.setPreferredSize(new Dimension(384, 40));

        //Panel2
        tileRepresentation = new TileRepresentation();
        JScrollPane scrollPanel = new JScrollPane(tileRepresentation);

        //MainJPanel
        setLayout(new BorderLayout());
        add(tilesetMenu, BorderLayout.PAGE_START);
        add(scrollPanel, BorderLayout.CENTER);
    }

}
