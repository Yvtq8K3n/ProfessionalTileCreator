package professional.tile.creator.model.comparison;

import java.awt.*;
import java.util.Comparator;

public enum CompareColorsByHue implements ColorComparator {
    CRITERIA;

    @Override
    public int compare(Color o1, Color o2) {
        //Convert obj1 to HSV format
        double[] HSVobj1 = convertRGBToHSV(o1);
        double obj1Hue = HSVobj1[0];
        double obj1saturation = HSVobj1[1];
        double obj1Value = HSVobj1[2];

        //Convert obj2 to HSV format
        double[] HSVobj2 = convertRGBToHSV(o2);
        double obj2Hue = HSVobj2[0];
        double obj2saturation = HSVobj2[1];
        double obj2value = HSVobj2[2];


        //Check which color has higher hue value
        if (obj2Hue < obj1Hue) return -1;
        else if (obj2Hue > obj1Hue) return 1;

        //Check which color has more saturation
        if (obj2saturation < obj1saturation) return -1;
        if (obj2saturation > obj1saturation) return 1;

        //Check which color has higher color value
        if (obj2value < obj1Value) return -1;
        if (obj2value > obj1Value) return 1;
        return 0;
    }
}
