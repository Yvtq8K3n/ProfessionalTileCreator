package professional.tile.creator.controller;

import professional.tile.creator.exceptions.InvalidOperationException;
import professional.tile.creator.exceptions.OutOfBoundsException;
import professional.tile.creator.model.Point;
import professional.tile.creator.model.TilesetManager;
import professional.tile.creator.model.TilesetColorsManager;
import professional.tile.creator.model.comparison.*;
import professional.tile.creator.model.selection.Selector;
import professional.tile.creator.model.selection.SelectorColor;
import professional.tile.creator.view.TilesetColorsPanel;

public enum  ColorsTileController {
    INSTANCE;

    //Model
    private TilesetColorsManager tilesetColorsManager;
    private SelectorColor selector;

    //View
    private TilesetColorsPanel tilesetColorsPanel;

    public void reloadTilesetColors(TilesetManager tilesetManager){
        this.tilesetColorsManager = new TilesetColorsManager(tilesetManager);
        this.tilesetColorsManager.addPropertyChangeListener(tilesetColorsPanel);
        this.tilesetColorsPanel.reloadTilesetColors();
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
        }
        selector = null;
    }

    public void removeFromSelection(int endX, int endY) {
        if (selector != null) {
            resizeSelector(endX, endY);
            selector.setState(Selector.State.FINISH);
            tilesetColorsPanel.removeSelection(selector);
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
            switch (colorsSort){
                case "LastSave":
                    throw new InvalidOperationException("Sort Method");
                case "Hue":
                    tilesetColorsManager.setSortComparator(CompareColorsByHue.CRITERIA);
                    break;
                case "Luminosity":
                    tilesetColorsManager.setSortComparator(CompareColorsByLuminosity.CRITERIA);
                    break;
                case "Step":
                    tilesetColorsManager.setSortComparator(CompareColorsByStepSorting.CRITERIA);
                    break;
                case "RGBStep":
                    tilesetColorsManager.setSortComparator(CompareColorsByRGBStepSorting.CRITERIA);
                    break;
                case "InverseStep":
                    tilesetColorsManager.setSortComparator(CompareColorsByStepInvertSorting.CRITERIA);
                    break;
                default:
                    throw new InvalidOperationException("Sort Method");
            }
        } catch (InvalidOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public TilesetColorsManager getTilesetColorsManager() {
        return tilesetColorsManager;
    }
}
