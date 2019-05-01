import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "inkContainerValue")
    public Object[][] dataProviderWorkContainerValue(){
        return new Object[][]{
                {10, "1234 6789"},
                {5, "1234 6"},
                {0, ""}};
    }

    @DataProvider(name="sizeLetter")
    public Object[][]dataProviderWorkSizeLetter(){
        return new Object[][]{
                {10, 1, "1234 6789"},
                {10, 0, ""},
                {10, 2, "1234 6"},
                {20, 2, "1234 6789"}
        };
    }

    @DataProvider (name = "colors")
    public Object[][] dataProviderWriteColors(){
        return new Object[][]{
                {10, 1, "red", "1234 6789"},
                {10, 1, "", ""}
        };
    }

    @DataProvider (name = "isWork")
    public Object[][] dataProviderIsWork(){
        return new Object[][]{
                {10, true},
                {0, false}
        };
    }
}
