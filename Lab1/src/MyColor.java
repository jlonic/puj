@SuppressWarnings("SpellCheckingInspection")

public class MyColor {
    int color;

    public MyColor(int red, int green, int blue)
    {
        color = ((red & 255) << 16) | ((green & 255) << 8) | ((blue & 255));
    }

    public int getRed()
    {
        return (color >> 16) & 255;
    }

    public int getGreen()
    {
        return (color >> 8) & 255;
    }

    public int getBlue()
    {
        return (color) & 255;
    }

    public static MyColor decode(String hexColor)
    {
        int value = Integer.decode(hexColor);

        return new MyColor((value >> 16) & 255, (value >> 8) & 255, value & 255);
    }

    public static void RGBtoHSB(int red, int green, int blue, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null)
            hsbvals = new float[3];

        int cmax = Math.max(red, green);
        if (blue > cmax)
            cmax = blue;

        int cmin = Math.min(red, green);
        if (blue < cmin)
            cmin = blue;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - red)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - green)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - blue)) / ((float) (cmax - cmin));
            if (red == cmax)
                hue = bluec - greenc;
            else if (green == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
    }

    public static float[] RGBtoCMYK(float red, float green, float blue) {

        float white,black;

        if ((red / 255) > (green / 255) && (red / 255) > (blue / 255)) {
            white = red / 255;
        } else white = Math.max((green / 255), (blue / 255));

        float cyan, magenta, yellow;
        cyan = (white - (red / 255)) / white;
        magenta = (white - (green / 255)) / white;
        yellow = (white - (blue / 255)) / white;
        black = (1 - white);

        return new float[]{cyan,magenta,yellow,black};
    }
}