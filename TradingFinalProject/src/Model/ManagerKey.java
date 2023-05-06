package Model;

import javax.management.InstanceNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class ManagerKey {
    private String key;

    public ManagerKey(int lookupId) {
        String currentPath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/";
        String filePath = currentPath + "Manager.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                if (id == lookupId) {
                    key = parts[4].trim();
                    break;
                }
            }
            if (key == null) {
                throw new InstanceNotFoundException("ID not found.");
            }
        } catch (IOException | InstanceNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getKeyValue() {
        return key;
    }
}
