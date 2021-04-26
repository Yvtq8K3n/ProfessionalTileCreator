package professional.tile.creator.model;

import professional.tile.creator.exceptions.InvalidOperationException;
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
        this.colors = extractTilesetColors();

        setSortComparator(CompareColorsByStepInvertSorting.CRITERIA);
        this.selectedColors = new ArrayList<>();
    }


    //Performance can be increased with intelligent search
    // start at middle
    // < if color lowered goes back
    // > if color is higher goes front
    // Need to be organized in some kind of visualization patterns
    private Color[] extractTilesetColors(){
        BufferedImage image = tilesetManager.getImage();
        List<Color> colors =  new ArrayList<>();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color newColor = new Color(image.getRGB(x, y));
                if (!colors.contains(newColor)) colors.add(newColor);
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

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

}
