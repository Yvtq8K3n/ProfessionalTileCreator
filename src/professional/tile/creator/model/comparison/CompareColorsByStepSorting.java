package professional.tile.creator.model.comparison;

import java.awt.*;
import java.util.Comparator;

public enum CompareColorsByStepSorting implements ColorComparator {
    CRITERIA;

    private static final int REPETITIONS = 8;


    @Override
    public int compare(Color o1, Color o2) {
        Color a = new Color(144, 0, 0);
        Color b = new Color(34, 34, 34);

        if (o2.equals(a)){
            if (o1.equals(b)){
                System.out.println("Nice");
            }
        }
        //Convert obj1 to HSV format

        //To dampen the impact that sorting on the first component has, we can reduce the colour space from a float value between 0 to 1, to an integer from 0 to 7.

        double[] HSVobj1 = convertRGBToHSV(o1);
        int obj1Hue = (int) (HSVobj1[0] * REPETITIONS);
        int obj1Luminosity = (int) (calculateLuminosity(o1) * REPETITIONS);
        int obj1Saturation = (int) (HSVobj1[1] * REPETITIONS);
        int obj1Value = (int) (HSVobj1[2] * REPETITIONS);

        //Convert obj2 to HSV format
        double[] HSVobj2 = convertRGBToHSV(o2);
        int obj2Hue = (int) (HSVobj2[0] * REPETITIONS);
        int obj2Luminosity = (int) (calculateLuminosity(o2) * REPETITIONS);
        int obj2Saturation = (int) (HSVobj1[1] * REPETITIONS);
        int obj2Value = (int) (HSVobj2[2] * REPETITIONS);

        //Check which color has higher hue value
        if (obj2Hue < obj1Hue) return -1;
        if (obj2Hue > obj1Hue) return 1;

        //Check which color has more luminosity
        if (obj2Luminosity < obj1Luminosity) return -1;
        if (obj2Luminosity > obj1Luminosity) return 1;

        //Check which color has more saturation
        if (obj2Saturation < obj1Saturation) return -1;
        if (obj2Saturation > obj1Saturation) return 1;

        //Check which color has higher color value
        if (obj2Value < obj1Value) return -1;
        if (obj2Value > obj1Value) return 1;


        return 0;
    }
}
