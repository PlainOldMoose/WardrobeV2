package me.plainoldmoose.wardrobe.DataManager;

import java.io.File;
import java.io.IOException;

/**
 * Manages the creation of data files for wardrobe pages.
 */
public class DataCreator {
    // Directory where data files are stored
    public static File dir = new File("plugins/Wardrobe");
    // File for wardrobe page 1
    public static File Page1;
    // File for wardrobe page 2
    public static File Page2;

    // Static initializer block to initialize Page1 and Page2 files
    static {
        Page1 = new File(dir.getPath() + "/Page1.yml");
        Page2 = new File(dir.getPath() + "/Page2.yml");
    }

    /**
     * Creates the data file for wardrobe page 1 if it doesn't exist.
     */
    public static void createPage1() {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!Page1.exists()) {
            try {
                Page1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the data file for wardrobe page 2 if it doesn't exist.
     */
    public static void createPage2() {
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (!Page2.exists()) {
            try {
                Page2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
