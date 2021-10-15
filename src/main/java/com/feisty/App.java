package com.feisty;
import com.feisty.javaDiscordTest.FileReader;
import java.util.ArrayList;
import com.feisty.javaDiscordTest.FeistyBot;

public class App
{
    public static void main( String[] args )
    {
        try {
            FeistyBot f = new FeistyBot("authentication.txt", 0);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
