package org.abc.foodpicker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataExtractor {
    private final static String FILE = "/Mobile_Food_Facility_Permit.csv";

    public List<FoodFacility> extract() throws IOException {
        InputStream is = getClass().getResourceAsStream(FILE);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        List<FoodFacility> result = new ArrayList<>();
        String line;
        String[] tokenizedLine;
        boolean isFirstRow = true;
        while ((line = br.readLine()) != null) {
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }
            tokenizedLine = parse(line);
            FoodFacility ff = new FoodFacility();
            ff.setLocationid(safeGet(0, tokenizedLine));
            ff.setApplicant(safeGet(1, tokenizedLine));
            ff.setFacilityType(safeGet(2, tokenizedLine));
            ff.setAddress(safeGet(5, tokenizedLine));
            ff.setFoodItems(safeGet(11, tokenizedLine));
            ff.setZipCode(safeGet(28, tokenizedLine));
            result.add(ff);
        }
        return result;
    }

    private String safeGet(int index, String[] tokenizedLine) {
        if (tokenizedLine.length > index) {
            return tokenizedLine[index];
        } else {
            return null;
        }
    }

    private String[] parse(String line) { // use split or Scanner
        return line.split(",");
    }

}
