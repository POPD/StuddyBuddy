package com.cs616.studybuddy_mockup.utility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


/**
 * Created by 1102764 on 2015-12-16.
 */
public class FileWriter {

    public String Title;
    public String Containts;
    public String Path;


    public FileWriter(String title, String containts){

        this.Title     = title;
        this.Containts = containts;
        //this.Path      = getA;
    }

    /*
         // File file = new File(Path+"/"+Title);
         File file = new File(Title);


            file.createNewFile();  // creates the file
            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file);



            // Writes the content to the file
            writer.write("This\n is\n an\n example\n");
            writer.flush();
            writer.close();

            //Creates a FileReader Object
            FileReader fr = new FileReader(file);
            char [] a = new char[50];
            fr.read(a); // reads the content to the array
            for(char c : a)
                System.out.print(c); //prints the characters one by one
            fr.close();

        }
    }
*/


}
