package com.feisty;
import com.feisty.javaDiscordTest.FileReader;
import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        try {
        ArrayList<String> f = FileReader.readToArrayList("test.txt");
        System.out.println(f);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }
}
