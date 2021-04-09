package professional.tile.creator.model.comparison;

import java.awt.*;
import java.util.Comparator;

public interface ColorComparator extends Comparator<Color> {

    default double calculateColorAverage(Color color){
        double red = (float) (color.getRed() / 255.0);
        double green = (float) (color.getGreen() / 255.0);
        double blue = (float) (color.getBlue() / 255.0);

        return (red + green + blue)/3;
    }

    default double calculateLuminosity(Color color){
        double red = (float) (color.getRed() / 255.0);
        double green = (float) (color.getGreen() / 255.0);
        double blue = (float) (color.getBlue() / 255.0);

        return Math.sqrt( 0.241 * red + 0.691 * green + 0.068 * blue);

        //return Math.pow((0.299 * red + 0.587 * green + 0.114 * blue), 1/2.2);
    }

    default double[] convertRGBToHSV(Color color){
        //1. Divide r, g, b by 255
        // R, G, B values are divided by 255
        // to change the range from 0..255 to 0..1
        float red = (float) (color.getRed() / 255.0);
        float green = (float) (color.getGreen() / 255.0);
        float blue = (float) (color.getBlue() / 255.0);

        //2. Compute cmax, cmin, difference
        // h, s, v = hue, saturation, value
        double cmax = Math.max(red, Math.max(green, blue)); // maximum of r, g, b
        double cmin = Math.min(red, Math.min(green, blue)); // minimum of r, g, b
        double diff = cmax - cmin; // diff of cmax and cmin.
        double h = -1, s = -1;

        //3. Hue calculation (Circle of colors)
        if (cmax == cmin) h = 0;
        else if (cmax == red) h = (60 * ((green - blue) / diff) + 360) % 360 / 360;
        else if (cmax == green) h = (60 * ((blue - red) / diff) + 120) % 360 / 360;
        else if (cmax == blue) h = (60 * ((red - green) / diff) + 240) % 360 / 360;

        //4. Hue calculation
        if (cmax == 0)  s = 0;
        else s = (diff / cmax);

        // compute v
        double v = cmax;

        return new double[]{h,s,v};
    }
}
