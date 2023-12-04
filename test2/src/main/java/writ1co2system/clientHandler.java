package writ1co2system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class clientHandler 
{
    private String clientID;

    public clientHandler(String id)
    {
        this.clientID = id;
    }

    public boolean storeDataCSV( String postcode, float co2)
    {
        String co2measure = String.valueOf(co2);

        String dataToStore = clientID+", "+postcode+", "+co2measure;

        boolean successfull = writeToFile("dataFile.txt", dataToStore);

        if(successfull)
        {
            return true;
        }
        else
        {
            return false;
        }        
        
    }

    private static boolean writeToFile(String fileName, String content) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            writer.write(content +"/n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
