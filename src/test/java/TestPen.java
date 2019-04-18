import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestPen {
    private Pen pen;
    private String word = "1234 6789";
    private int inkCV = 10;
    private double sizeLetter = 1;
    private String color = "RED";
    private String path = "..\\testPen\\temp.txt";
    private String fieldInkContainerValue = "inkContainerValue";
    private String fieldSizeLetter = "sizeLetter";
    private String fieldColor = "color";

    @Test (dataProvider = "inkContainerValue", dataProviderClass = DataProviders.class)
    public void testWrite_different_inkContainerValue(int inkCV, String expected) {
        pen = new Pen(inkCV);
        String actual = pen.write(word);
        Assert.assertEquals(actual, expected);
    }

    @Test (dataProvider = "sizeLetter", dataProviderClass = DataProviders.class)
    public void testWrite_different_sizeLetter(int inkCV, double sizeLetter, String expected) {
        String actual;
        try {
            pen = new Pen(inkCV, sizeLetter);
            actual = pen.write(word);
        }catch (StringIndexOutOfBoundsException e){
            actual = "";
        }
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testGetColor (){
        pen = new Pen(inkCV, sizeLetter, color);
        String actual = pen.getColor();
        Assert.assertEquals(actual, color);
    }

    @Test (dataProvider = "colors", dataProviderClass = DataProviders.class)
    public void testWrite_setColor(int inkCV, double sizeLetter, String color, String expected){
        pen = new Pen(inkCV, sizeLetter, color);
        String actual = pen.write(word);
        Assert.assertEquals(actual, expected);
    }

    @Test (dataProvider = "isWork", dataProviderClass = DataProviders.class)
    public void testIsWork(int inkCV, boolean expected){
        pen = new Pen(inkCV);
        boolean actual = pen.isWork();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoSomethingElse() throws IOException {
        pen = new Pen(inkCV, sizeLetter, color);
        File file = new File(path);
        PrintStream ps = new PrintStream(file);
        PrintStream standardOut = System.out;
        System.setOut(ps);
        pen.doSomethingElse();
        Assert.assertEquals(Files.readAllLines(Paths.get(file.toURI())).get(0), color);
        System.setOut(standardOut);
    }

    @Test
    public void testConstructorWithIntCV() throws NoSuchFieldException, IllegalAccessException {
        pen = new Pen(inkCV);
        Field field = Pen.class.getDeclaredField(fieldInkContainerValue);
        field.setAccessible(true);
        int actual = (Integer) field.get(pen);
        Assert.assertEquals(actual, inkCV);
    }

    @Test
    public void testConstructorWithSize() throws NoSuchFieldException, IllegalAccessException {
        pen = new Pen(inkCV, sizeLetter);
        Field field = Pen.class.getDeclaredField(fieldSizeLetter);
        field.setAccessible(true);
        double actual = (Double) field.get(pen);
        Assert.assertEquals(actual, sizeLetter);
    }

    @Test
    public void testConstructorWithColor() throws NoSuchFieldException, IllegalAccessException {
        pen = new Pen(inkCV, sizeLetter, color);
        Field field = Pen.class.getDeclaredField(fieldColor);
        field.setAccessible(true);
        String actual = (String) field.get(pen);
        Assert.assertEquals(actual, color);
    }

}
