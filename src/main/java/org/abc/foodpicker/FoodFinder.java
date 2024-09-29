package org.abc.foodpicker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodFinder {

    private static final List<FindElement> FIND_ELEMENTS = new ArrayList<FindElement>();

    static {
        FIND_ELEMENTS.add(new FindElement("locationid", false));
        FIND_ELEMENTS.add(new FindElement("applicant", true));
        FIND_ELEMENTS.add(new FindElement("facilityType", true));
        FIND_ELEMENTS.add(new FindElement("address", true));
        FIND_ELEMENTS.add(new FindElement("foodItems", true));
        FIND_ELEMENTS.add(new FindElement("zipCode", false));
    };

    public static List<FoodFacility> find(String criteria, List<FoodFacility> allFoodFacilities) {
        if (Objects.isNull(criteria) || criteria.isBlank()) {
            throw new IllegalArgumentException("criteria param can't be empty!");
        }
        String[] s = criteria.split(":");
        if (s.length != 2) {
            throw new IllegalArgumentException("the value of criteria param should be split by : character!");
        }
        String key = s[0];
        FindElement findElement = obtainFindElement(key);
        if (Objects.isNull(findElement)) {
            throw new IllegalArgumentException("the key " + key + " is not supported!");
        }
        String k = findElement.getKey();
        String expectedValue = s[1];
        String firstCapitalized = String.valueOf(k.charAt(0)).toUpperCase() + k.substring(1);
        List<FoodFacility> result = new ArrayList<>();
        for (FoodFacility foodFacility : allFoodFacilities) {
            try {
                Method method = foodFacility.getClass().getMethod("get" + firstCapitalized);
                String value = String.valueOf(method.invoke(foodFacility));
                if (findElement.isFuzzySearch()) {
                    if (value.contains(expectedValue)) {
                        result.add(foodFacility);
                    }
                } else {
                    if (value.equalsIgnoreCase(expectedValue)) {
                        result.add(foodFacility);
                    }
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("key " + key  + " is not supported!");
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private static FindElement obtainFindElement(String key) {
        for (FindElement fe : FIND_ELEMENTS) {
            if (key.equalsIgnoreCase(fe.getKey())) {
                return fe;
            }
        }
        return null;
    }
}

class FindElement {
    private String key;

    private boolean isFuzzySearch;

    public FindElement(String key, boolean isFuzzySearch) {
        this.key = key;
        this.isFuzzySearch = isFuzzySearch;
    }

    public String getKey() {
        return key;
    }

    public boolean isFuzzySearch() {
        return isFuzzySearch;
    }

}


