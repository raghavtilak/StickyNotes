package com.raghav.gfgstickynotes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

class StickyNote {

    //this function will return the content from the text file(if any)
    String getStick(Context context) {

//get the file from path
        File fileEvents = new File(context.getFilesDir().getPath() + "/gfg.txt");
//create a StringBuilder to store the text from file
        StringBuilder text = new StringBuilder();
        try {
            //use the BufferedReader to Read the file efficiently
            BufferedReader br = new BufferedReader(new FileReader(fileEvents));
            String line;
            //read a single line at a time
            //append newline character after each line
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            //close the BufferedReader
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //finally return the string i.e. the retrieved data from file
        return text.toString();
    }

    //this function saves the new content in the file if it exists
    //or will create a new one
    void setStick(String textToBeSaved, Context context) {


        String text = textToBeSaved;
        //create the FileOutputStream to efficiently write the file
        FileOutputStream fos = null;

        try {
            //get the file from storage
            fos = context.getApplicationContext().openFileOutput("gfg.txt", MODE_PRIVATE);
            //write to the file at once
            fos.write(text.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

