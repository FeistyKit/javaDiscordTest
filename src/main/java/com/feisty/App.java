package com.feisty;
import com.feisty.javaDiscordTest.FileReader;

public class App
{
    public static void main( String[] args )
    {
        try {
        String f = FileReader.readToString("test.txt");
        System.out.println(f);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }
}
