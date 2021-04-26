package professional.tile.creator.controller;

import professional.tile.creator.exceptions.InvalidOperationException;
import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.TilesetManager;
import professional.tile.creator.model.TilesetColorsManager;
import professional.tile.creator.model.comparison.*;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.view.ColorReplacer;
import professional.tile.creator.view.TilesetColorsPanel;

import java.util.ArrayList;
import java.awt.*;

public enum  ColorsTileController {
    INSTANCE;

    //Model
    private TilesetColorsManager tilesetColorsManager;
    private SelectorColor selector;
    private SelectorColor previousSelector;

    //View
    private TilesetColorsPanel tilesetColorsPanel;
    private ColorReplacer colorReplacer;

    public void reloadTilesetColors(TilesetManager tilesetManager){
        //TilesetMenu
        this.tilesetColorsManager = new TilesetColorsManager(tilesetManager);
        this.tilesetColorsManager.addPropertyChangeListener(tilesetColorsPanel);
        this.tilesetColorsPanel.setColorManager(tilesetColorsManager);
        this.tilesetColorsPanel.reloadTilesetColors();

        //PanelPallet
        this.tilesetColorsManager.addPropertyChangeListener(colorReplacer);
    }

    public void createSimpleSelector(int startX, int startY){
        removePreviousSelection(); //Remove all previous selections
        createSelector(startX, startY); //Create selection
        addToSelection(startX, startY); //Add selection to view

    }

    private void removePreviousSelection() {
        if (previousSelector!=null){
            tilesetColorsPanel.removeSelection(previousSelector);

            ArrayList<Color> colors = tilesetColorsPanel.retrieveSelectedColors(previousSelector);
            tilesetColorsManager.removeSelectedColors(colors);
        }
        previousSelector = null;
    }


    public void createSelector(int startX, int startY){
        boolean selectorIsFinished = selector!=null
                && selector.getState() == Selector.State.FINISH;

        //Retrieve the highest value X and Y
        int maxX = tilesetColorsPanel.getWidth();
        int maxY = tilesetColorsPanel.getHeight();

        if (selector == null || selectorIsFinished) {
            try {
                selector = new SelectorColor(startX, startY, maxX, maxY);

                //Force colorsTilesetRepresentation to readjust every time selector is resized
                selector.addPropertyChangeListener(tilesetColorsPanel);

                //Reload View
                tilesetColorsPanel.redraw();

            } catch (OutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    public void resizeSelector(int endX, int endY) {
        boolean selectorIsEditable = selector != null && (selector.getState() == Selector.State.CREATED
                || selector.getState() == Selector.State.RESIZING);

        if (selectorIsEditable) {
            try {
                selector.resizeEndCoordinates(new Point(endX, endY));
            } catch (OutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void addToSelection(int endX, int endY) {
        if (selector != null) {
            resizeSelector(endX, endY);
            selector.setState(Selector.State.FINISH);
            tilesetColorsPanel.addSelection(selector);

            ArrayList<Color> colors = tilesetColorsPanel.retrieveSelectedColors(selector);
            tilesetColorsManager.addSelectedColors(colors);
        }
        previousSelector = selector;
        selector = null;
    }

    public void removeFromSelection(int endX, int endY) {
        if (selector != null) {
            resizeSelector(endX, endY);
            selector.setState(Selector.State.FINISH);
            tilesetColorsPanel.removeSelection(selector);

            ArrayList<Color> colors = tilesetColorsPanel.retrieveSelectedColors(selector);
            tilesetColorsManager.removeSelectedColors(colors);
        }
        selector = null;
    }


    public SelectorColor getSelector() {
        return selector;
    }

    public void setTilesetColorsPanel(TilesetColorsPanel tilesetColorsPanel) {
        this.tilesetColorsPanel = tilesetColorsPanel;
    }

    public void setColorsSort(String colorsSort) {
        try {
            ColorComparator criteria = null;
            switch (colorsSort){
                case "LastSave":
                    throw new InvalidOperationException("Sort Method");
                case "Hue":
                    criteria = CompareColorsByHue.CRITERIA;
                    break;
                case "Luminosity":
                    criteria = CompareColorsByLuminosity.CRITERIA;
                    break;
                case "Step":
                    criteria = CompareColorsByStepSorting.CRITERIA;
                    break;
                case "RGBStep":
                    criteria = CompareColorsByRGBStepSorting.CRITERIA;
                    break;
                case "InverseStep":
                    criteria = CompareColorsByStepInvertSorting.CRITERIA;
                    break;
                default:
                    throw new InvalidOperationException("Sort Method");
            }
            tilesetColorsManager.setSortComparator(criteria);
        } catch (InvalidOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public TilesetColorsManager getTilesetColorsManager() {
        return tilesetColorsManager;
    }

    public void setColorReplacer(ColorReplacer colorReplacer) {
        this.colorReplacer = colorReplacer;
    }

    public Color getSelectedColor(){
        return this.tilesetColorsManager.getSelectedColor();
    }
}
