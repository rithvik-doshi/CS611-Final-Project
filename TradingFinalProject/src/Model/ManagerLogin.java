package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManagerLogin {
    private String managerDataPath;
    private Manager manager;

    public ManagerLogin(String managerDataPath) {
        this.managerDataPath = managerDataPath;
    }

    public boolean checkLogin(String username, String password, String managerKey) {
        try (BufferedReader br = new BufferedReader(new FileReader(managerDataPath))) {
            br.readLine(); // Skip the first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int ID = Integer.parseInt(data[0].trim());
                String fileUsername = data[1].trim();
                String fileemail = data[2].trim();
                String filePassword = data[3].trim();
                String fileManagerKey = data[4].trim();

                if (fileUsername.equalsIgnoreCase(username) && filePassword.equals(password) && fileManagerKey.equals(managerKey)) {
                    manager = new Manager(1,fileUsername,fileemail,filePassword);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Manager getManager() {
        return manager;
    }
}
