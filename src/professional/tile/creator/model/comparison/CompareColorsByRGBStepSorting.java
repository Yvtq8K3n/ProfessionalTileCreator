package professional.tile.creator.model.comparison;

import java.awt.*;

public enum CompareColorsByRGBStepSorting implements ColorComparator{
    CRITERIA;

    private static final int REPETITIONS = 4;

    @Override
    public int compare(Color o1, Color o2) {
        int obj1Luminosity = (int) (REPETITIONS * calculateLuminosity(o1));
        int obj1Red =  (int) (REPETITIONS * (float) (o1.getRed() / 255.0));
        int obj1Green = (int) (REPETITIONS * (float) (o1.getGreen() / 255.0));
        int obj1Blue = (int) (REPETITIONS * (float) (o1.getBlue() / 255.0));


        int obj2Luminosity = (int) (REPETITIONS * calculateLuminosity(o2));
        int obj2Red =  (int) (REPETITIONS * (float) (o2.getRed() / 255.0));
        int obj2Green = (int) (REPETITIONS * (float) (o2.getGreen() / 255.0));
        int obj12Blue = (int) (REPETITIONS * (float) (o2.getBlue() / 255.0));

        //Check Luminosity
        if (obj2Luminosity < obj1Luminosity) return -1;
        else if (obj2Luminosity > obj1Luminosity) return 1;

        //Check which color has higher hue value
        if (obj2Red < obj1Red) return -1;
        else if (obj2Red > obj1Red) return 1;

        //Check which color has more saturation
        if (obj2Green < obj1Green) return -1;
        if (obj2Green > obj1Green) return 1;

        //Check which color has higher color value
        if (obj12Blue < obj1Blue) return -1;
        if (obj12Blue > obj1Blue) return 1;
        return 0;
    }
}
