package professional.tile.creator.view;

import professional.tile.creator.controller.ApplicationController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TilesetMenu extends JPanel {

    JButton btnLoad;
    JButton btnEdit;

    public TilesetMenu() {
        initComponents();
        setBackground(Color.red);
        //Not working as i wanted
        //setLayout(new FlowLayout());
    }

    public void initComponents(){
        btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "BMP, JPG, PNG & GIF Images", "bmp", "jpg", "png", "gif");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(new JFrame());
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    File uploadedFile = chooser.getSelectedFile();
                    BufferedImage image = ImageIO.read(uploadedFile);
                    ApplicationController.INSTANCE.loadTileset(image);

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
            }

        });

        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(e -> {

        });
        add(btnLoad);
        add(btnEdit);
    }
}
