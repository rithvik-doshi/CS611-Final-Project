package Database;


import Model.Person;

import java.nio.file.Paths;
import java.util.ArrayList;

public class DBTest {
    public static void main(String[] args) {
        //read file code
        //name is before change is Rithvik Doshi
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/";
        ArrayList<Person> fileStream = CustomerFile.readFile(currentPath + "Customer.txt");
        for(int i = 0; i < fileStream.size();i++){
            System.out.println("Name: " + fileStream.get(i).getName());
            System.out.println("Email: " + fileStream.get(i).getEmail());
        }

        //now make some changes and over-write the txt file
        fileStream.get(0).setName("Boston");

        CustomerFile.writeFile(currentPath + "Customer.txt", fileStream);
        //re-read file again to see the changes
        System.out.println("After changing, we get");
        fileStream = CustomerFile.readFile(currentPath + "Customer.txt");
        for (Person person : fileStream) {
            System.out.println("Name: " + person.getName());
            System.out.println("Email: " + person.getEmail());
        }
    }
}
