# Food Truck Finder
A project maintained by Yongpeng GU.
## Features
You can find your favorite food trucks in seconds through below criteria:

* locationid: it's a number which must be strictly matched.
* applicant: fuzzy search
* facilityType: there are several options, like Truck and Push Cart
* address: fuzzy search
* foodItems: food features, like Tacos, cold, hot and so on.
* zipCode: it's a number which must be strictly matched.

## How to Use
* unzip the file
* in command line, change to the root folder
* execute (you must have installed maven in your computer already)
```bash
mvn package
```
* execute something like: 
```bash 
java -cp target/FoodPicker_GU_Yongpeng-1.0-SNAPSHOT.jar org.abc.foodpicker.Main foodItems:cold
```
all food related with cold will be found. Moreover, foodItems can be replaced with locationid, applicant, facilityType, address, zipCode.
* if some foods match your search, the found data will display in the screen, otherwise, "NO Data found." will display. 

## ToDo
In a rather short period of time, it's far from perfection.
If more time is given, I will:
* visualize the human interface to make it easier to use
* write some test cases to ensure it's correctness and robustness
* enable more search criteria for use
