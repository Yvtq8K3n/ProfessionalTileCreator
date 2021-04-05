package professional.tile.creator.model;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tileset{
    private BufferedImage image;
    private BufferedImage scaledImage;
    private int scale = 1;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public Tileset(BufferedImage image) {
        changes.firePropertyChange("image", null, image);
        this.image = image;
        this.scaledImage = generateScaledImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        changes.firePropertyChange("image",  this.image, image);
        this.image = image;
    }

    private BufferedImage generateScaledImage() {
        int w = image.getWidth();
        int h = image.getHeight();

        // Create a new image of the proper size
        int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);
        BufferedImage after = new BufferedImage(w2, h2, image.getType());
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        Graphics2D g2 = (Graphics2D) after.getGraphics();
        // Here, you may draw anything you want into the new image, but we're just drawing
        // a scaled version of the original image. This is slower than
        // calling scaleOp.filter().
        g2.drawImage(image, scaleOp, 0, 0);
        g2.dispose();
        return after;
    }

    public void reduceScaleFactor(){
        changes.firePropertyChange("scale", scale, --scale);
        this.scaledImage = generateScaledImage();
    }

    public void increaseScaleFactor(){
        changes.firePropertyChange("scale", scale, ++scale);
        this.scaledImage = generateScaledImage();
    }

    public BufferedImage getScaledImage() {
        return scaledImage;
    }

    public void setScaledImage(BufferedImage scaledImage) {
        this.scaledImage = scaledImage;
    }

    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public int getScaleFactor() {
        return scale;
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
