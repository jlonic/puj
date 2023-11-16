import org.junit.Test;
import static org.junit.Assert.*;

public class ColorConverterTest {
    @Test
    public void testDecode()
    {
        MyColor testColor = MyColor.decode("0x1FF0FF");

        int red=testColor.getRed();
        int green=testColor.getGreen();
        int blue=testColor.getBlue();

        assertEquals(31, red); //1F = 31
        assertEquals(240, green); //F0 = 240
        assertEquals(255, blue); //FF = 255
    }

    @Test
    public void testRGBtoHSB()
    {
        float[] hsb = new float[3];
        MyColor.RGBtoHSB(31, 240, 255, hsb); //1FF0FF = 31, 240, 255

        float hue=(float) 0.511160667; // 184.01784/360
        float saturation=(float) 0.8784314; // 87.84314%/100
        float brightness=(float) 1.0; // 100%=1

        assertEquals(hue, hsb[0], 0.000001);
        assertEquals(saturation, hsb[1], 0.000001);
        assertEquals(brightness, hsb[2], 0.000001);
    }

    @Test
    public void testRGBtoCMYK()
    {
        float[] cmyk=MyColor.RGBtoCMYK(31, 240, 255);

        float cyan=(float) 0.8784314; // 87.84314%/100
        float magenta=(float) 0.058823526; // 5.882353%/100
        float yellow=(float) 0.0;
        float black=(float) 0.0;

        assertEquals(cyan, cmyk[0], 0.0000001);
        assertEquals(magenta, cmyk[1], 0.0000001);
        assertEquals(yellow, cmyk[2], 0.0000001);
        assertEquals(black, cmyk[3], 0.0000001);
    }
}