package main.java.org.magento.base;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "order_data")
    public static Object[][] orderData(){
        return new Object[][]{
                {"Bolo Sport Watch", "$49.00"},
                {"Clamber Watch", "$54.00"},
                {"Didi Sport Watch", "$92.00"}
        };
    }
}
