package Database;
import Model.Customer;
import Model.Person;

import java.io.*;
import java.util.ArrayList;


public class ReadFile {
    private Person person;
    private static ArrayList<Person> fileStream = new ArrayList<>();

    public static ArrayList<Person> getFileStream(){
        return fileStream;
    }
    public static void setFileStream(ArrayList<Person> fileStream) {
        ReadFile.fileStream = fileStream;
    }



    public static ArrayList<Person> readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            //jump the first line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();
                String password = data[3].trim();

                Customer person = new Customer(id, name, email, password);
                fileStream.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileStream;
    }

    public static void writeFile(String fileName, ArrayList<Person> fileStream) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // write file head
            bw.write("id, name, email, password\n");

            for (Person person : fileStream) {
                String line = person.getID() + ", " + person.getName() + ", " + person.getEmail() + ", " + person.getPassword() + "\n";
                bw.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

