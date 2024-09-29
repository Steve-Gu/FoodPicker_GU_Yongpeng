package org.abc.foodpicker;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: must have 1 argument!");
            return;
        }
        try {
            List<FoodFacility> foodFacilityList = new DataExtractor().extract();
            List<FoodFacility> result = FoodFinder.find(args[0], foodFacilityList);
            if (result.isEmpty()) {
                System.out.println("NO Data found.");
                return;
            }
            System.out.println("Data found:");
            for (FoodFacility ff : result) {
                System.out.println("--------------------------------------");
                System.out.print(ff.getLocationid() + " || ");
                System.out.print(ff.getApplicant() + " || ");
                System.out.print(ff.getAddress() + " || ");
                System.out.print(ff.getFacilityType() + " || ");
                System.out.print(ff.getFoodItems() + " || ");
                System.out.println(ff.getZipCode());
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}