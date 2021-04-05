package professional.tile.creator.model;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tileset {
    private BufferedImage image;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public Tileset(BufferedImage image) {
        changes.firePropertyChange("image", null, image);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        changes.firePropertyChange("image",  this.image, image);
        this.image = image;
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public Boolean hasImage(){
        return image != null;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
