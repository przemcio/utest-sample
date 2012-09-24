package pl.testng;


import au.com.bytecode.opencsv.CSVReader;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvReadTest {


    @Test(dataProvider = "getCsvData")
    public void test(String a,String b,String c) {
        Reporter.log("read header");
    }

    @DataProvider
    public Iterator<Object[]> getCsvData() throws IOException {

        List<Object[]> lines = new ArrayList<Object[]>();

        try {
            CSVReader reader = new CSVReader(
                    new InputStreamReader(getClass().getResourceAsStream("/data.csv")));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return lines.iterator();

    }
}
