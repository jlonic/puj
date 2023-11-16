//import java.awt.Color;
@SuppressWarnings("SpellCheckingInspection")

public class ColorConverter {
    public static void main(String[] args) {

        String hexColor = "0x1FF0FF";

        MyColor color = MyColor.decode(hexColor);
        System.out.println(color);

        float[] hsbCode = new float[3];

        MyColor.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsbCode);

        System.out.println("Boja u HEX formatu: " + hexColor);
        System.out.println("Boja u RGB formatu: " + color.getRed() + ", " +
                color.getGreen() + ", " + color.getBlue());
        System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");

        float[] cmyk=MyColor.RGBtoCMYK(color.getRed(), color.getGreen(), color.getBlue());

        System.out.println("Cyan: " + (cmyk[0] * 100) + "%, Magenta: " + (cmyk[1] * 100)
                + "%, Yellow: " + (cmyk[2] * 100) + "%, Black: " + (cmyk[3] * 100) + "%");
    }
}