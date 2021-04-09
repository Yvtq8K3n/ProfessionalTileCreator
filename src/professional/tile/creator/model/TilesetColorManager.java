package professional.tile.creator.model;

import professional.tile.creator.model.comparison.CompareColorsByStepInvertSorting;
import professional.tile.creator.model.comparison.CompareColorsByStepSorting;
import professional.tile.creator.model.comparison.CompareColorsByHue;
import professional.tile.creator.model.comparison.CompareColorsByLuminosity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TilesetColorManager {
    private Tileset tileset;
    private Color[] colors;
    private Color[] colorsSortedByHUE;
    private Color[] colorsSortedByLuminosity;
    private Color[] colorsSortByStepSorting;
    private Color[] colorsSortByStepInvertSorting;

    public TilesetColorManager(Tileset tileset) {
        this.tileset = tileset;
        this.colors = extractTilesetColors();

        List<Color> colorsSortedByHUE = Arrays.asList(colors);
        Collections.sort(colorsSortedByHUE, CompareColorsByHue.CRITERIA);
        this.colorsSortedByHUE = colorsSortedByHUE.toArray(new Color[colorsSortedByHUE.size()]);

        List<Color> colorsSortedByLuminosity = Arrays.asList(colors);
        Collections.sort(colorsSortedByLuminosity, CompareColorsByLuminosity.CRITERIA);
        this.colorsSortedByLuminosity = colorsSortedByLuminosity.toArray(new Color[colorsSortedByLuminosity.size()]);

        List<Color> colorsSortByStepSoring = Arrays.asList(colors);
        Collections.sort(colorsSortByStepSoring, CompareColorsByStepSorting.CRITERIA);
        this.colorsSortByStepSorting = colorsSortByStepSoring.toArray(new Color[colorsSortByStepSoring.size()]);

        List<Color> colorsSortByStepInvertSorting = Arrays.asList(colors);
        Collections.sort(colorsSortByStepInvertSorting, CompareColorsByStepInvertSorting.CRITERIA);
        this.colorsSortByStepInvertSorting = colorsSortByStepInvertSorting.toArray(new Color[colorsSortByStepInvertSorting.size()]);


    }

    //Performance can be increased with intelligent search
    // start at middle
    // < if color lowered goes back
    // > if color is higher goes front
    // Need to be organized in some kind of visualization patterns
    private Color[] extractTilesetColors(){
        BufferedImage image = tileset.getImage();
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

    public Color[] getColorsSortedByHUE() {
        return colorsSortedByHUE;
    }

    public Color[] getColorsSortedByLuminosity() {
        return colorsSortedByLuminosity;
    }

    public Color[] getColorsSortByStepSorting() {
        return colorsSortByStepSorting;
    }

    public Color[] getColorsSortByStepInvertSorting() {
        return colorsSortByStepInvertSorting;
    }

}
