package settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SettingsParser
{
    private static File settingsFile = new File("src\\main\\settings\\settings.ini");
    private static File defaultsFile = new File("src\\main\\settings\\defaults.ini");

    public static HashMap<String, Integer> getSettings() throws FileNotFoundException
    {
        return getSettingsFromFile(settingsFile);
    }

    public static HashMap<String, Integer> getDefaults() throws FileNotFoundException
    {
        return getSettingsFromFile(defaultsFile);
    }

    public static void saveSettings(HashMap<String, Integer> settingsDict) throws IOException
    {
        FileWriter fw = new FileWriter(settingsFile);
        fw.write("size=" + settingsDict.get("size"));   fw.write("\n");
        fw.write("delay=" + settingsDict.get("delay")); fw.write("\n");
        fw.write("obstacles=" + settingsDict.get("obstacles"));
        fw.close();
    }

    private static HashMap<String, Integer> getSettingsFromFile(File file) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(file);
        HashMap<String, Integer> settingsDict = new HashMap<>();
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            settingsDict.put(line.split("=")[0], Integer.valueOf(line.split("=")[1]));
        }
        return settingsDict;
    }
}
