package com.feisty.javaDiscordTest;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public abstract class FileReader {
    public static String readToString(String fname) throws FileNotFoundException {
        File f = new File(fname);
        Scanner s = new Scanner(f);
        String toReturn = new String();
        while (s.hasNextLine()) {
            toReturn += s.nextLine();
        }
        return toReturn;
    }
}
