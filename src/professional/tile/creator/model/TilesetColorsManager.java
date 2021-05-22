package professional.tile.creator.model;

import professional.tile.creator.model.comparison.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TilesetColorsManager {
    private final int COLOR_BASE = 8;
    private final int MAX_COLOR_VALUE = 248;

    private TilesetManager tilesetManager;
    private Color[] colors;

    //Order
    private Color[] sortedColors;
    private ColorComparator sortComparator;

    //SelectedColors
    private Color selectedColor;
    private ArrayList<Color> selectedColors;

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public TilesetColorsManager(TilesetManager tilesetManager) {
        this.tilesetManager = tilesetManager;
        this.colors = processTileset();

        setSortComparator(CompareColorsByStepInvertSorting.CRITERIA);
        this.selectedColors = new ArrayList<>();
    }


    //Performance can be increased with intelligent search
    // start at middle
    // < if color lowered goes back
    // > if color is higher goes front
    // Need to be organized in some kind of visualization patterns
    private Color[] processTileset(){
        BufferedImage image = tilesetManager.getImage();
        List<Color> colors =  new ArrayList<>();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                Color converted = round(pixelColor, COLOR_BASE);
                image.setRGB(x, y, converted.getRGB());

                if (!colors.contains(converted)) colors.add(converted);
            }
        }

        return colors.toArray(new Color[colors.size()]);
    }

    public Color[] getColors() {
        return colors;
    }


    public Color[] getSortedColors() {
        return sortedColors;
    }

    public void setSortComparator(ColorComparator sortComparator) {
        this.sortComparator = sortComparator;
        List<Color> newSortedColors = Arrays.asList(colors);
        Collections.sort(newSortedColors, sortComparator);
        this.sortedColors = newSortedColors.toArray(new Color[newSortedColors.size()]);
        changes.firePropertyChange("sortedColors",  null, newSortedColors);
    }


    public void addSelectedColors(ArrayList<Color> colors) {
        selectedColor = colors.get(0);
        for (Color color:colors) {
            if (!selectedColors.contains(color)){
                selectedColors.add(color);
            }
        }
        changes.firePropertyChange("selectedColor", null, selectedColor);
    }

    public ArrayList<Color> getSelectedColors() {
        return selectedColors;
    }

    public void removeSelectedColors(ArrayList<Color> colors) {
        for (Color color:colors) {
                selectedColors.remove(color);
        }
    }

    public void removeSelectedColor(Color color) {
        selectedColors.remove(color);
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }


    /** Given a
     * @param color and a
     * @param base
     * @return A color with RGB value rounded by a multiple of the given base
     */
    protected Color round(Color color, int base){
        int r = base * Math.floorDiv(color.getRed() + COLOR_BASE/2,base);
        if (r>MAX_COLOR_VALUE) r = MAX_COLOR_VALUE;

        int g = base * Math.floorDiv(color.getGreen() + COLOR_BASE/2,base);
        if (g>MAX_COLOR_VALUE) g = MAX_COLOR_VALUE;

        int b = base * Math.floorDiv(color.getBlue() + COLOR_BASE/2,base);
        if (b>MAX_COLOR_VALUE) b = MAX_COLOR_VALUE;

        return new Color(r, g, b);
    }

}
