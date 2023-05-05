package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManagerLogin {
    private String managerDataPath;

    public ManagerLogin(String managerDataPath) {
        this.managerDataPath = managerDataPath;
    }

    public boolean checkLogin(String username, String password, String managerKey) {
        try (BufferedReader br = new BufferedReader(new FileReader(managerDataPath))) {
            br.readLine(); // Skip the first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String fileUsername = data[1].trim();
                String filePassword = data[3].trim();
                String fileManagerKey = data[4].trim();

                if (fileUsername.equalsIgnoreCase(username) && filePassword.equals(password) && fileManagerKey.equals(managerKey)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
