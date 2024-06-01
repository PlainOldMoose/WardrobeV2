package me.plainoldmoose.wardrobe.DataManager;

import java.io.File;
import java.io.IOException;

public class DataCreator {
    public static File dir = new File("plugins/Wardrobe");
    public static File Page1;
    public static File Page2;

    public static void CreatePage1() {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!Page1.exists()) {
            try {
                Page1.createNewFile();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

    }

    public static void CreatePage2() {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!Page2.exists()) {
            try {
                Page2.createNewFile();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

    }

    static {
        Page1 = new File(dir.getPath() + "/Page1.yml");
        Page2 = new File(dir.getPath() + "/Page2.yml");
    }
}