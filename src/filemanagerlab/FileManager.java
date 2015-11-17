package filemanagerlab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dvandenberge
 */
public class FileManager {

    private File inputFile;

    public FileManager() {
        inputFile = new File("C:" + File.separatorChar + "temp" + File.separatorChar + "lab1" + File.separatorChar + "mailingList.txt");
    }

    public void printEntries() {
        BufferedReader reader = null;
        try {
            //Decorates reader from a simple fileReader
            reader = new BufferedReader(new FileReader(inputFile));
            //Grab the first line from the file
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File cannot be read!");
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("Critical System Failure");
            }
        }
    }

    public void addEntries() {
        PrintWriter in = null;
        try {
            in = new PrintWriter(new BufferedWriter(new FileWriter(inputFile, true)));
            in.println("Kyle Wirtz");
            in.println("2867 N Farwell ave");
            in.print("Milwaukee, ");
            in.print("WI ");
            in.println("53211");
        } catch (IOException e) {
            System.out.println("File cannot be written to");
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                System.out.println("Critical System Failure");
            }
        }
    }

    
    /**
     * A very dumbed down way of retrieving a name and state using a for loop that reads 3 lines at a time, storing the first and last line as String info 
     * @param entry used in the for loop to know when to stop reading lines
     */
    public void printEntry(int entry) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));

            String name = "";
            String state = "";
            //This loop will continue to read lines until it reaches the desired record
            for (int i = 1; i <= entry; i++) {
                name = reader.readLine(); //1st line is stored as the name
                reader.readLine();        //This line is skipped
                state = reader.readLine();//This line is stored as the City-State-Zip
            }
            String[] parts = state.split(" "); //Splits the line into individual parts

            //This switch finds out which part of the String[] to print out
            switch (parts.length) {
                case 3:
                    System.out.println(name + " " + parts[1]);
                    break;
                case 4:
                    System.out.println(name + " " + parts[2]);
                    break;
            }
        } catch (IOException e) {
            System.out.println("File cannot be read!");
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("Critical System Failure");
            }

        }
    }

    /**
     * This is a helper method used in the getEntry method. The getEntry method uses a Map to find a specific record
     * @return a Map populated with an integer key corresponding to an array. The array is a 3 line record
     */
    private Map<Integer,String[]> putEntriesInMap() {
        BufferedReader reader = null;
        //inititalize the map
        Map<Integer,String[]> mailingMap = new HashMap<Integer,String[]>();
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            //initialize and grab the first line from the file
            String line = reader.readLine();
            for (int i = 1;line!=null;i++) {
                String[] lines=new String[3]; //Create a new array to hold a record
                lines[0]=line; //Add the name(stored in line at the start of each loop) to the array
                for(int j=1;j<3;j++){
                    lines[j]=reader.readLine(); //put the address and city/state in the 1st and 2nd positions of the lines array
                }
                mailingMap.put(i,lines); //Store the integer key and array in the map
                line=reader.readLine(); //grab the next name, added to the array when the loop begins again
                                        //Also used to check when to terminate the loop
            }
        }catch(IOException ioe){
            System.out.println("Whoops");
        }
        return mailingMap;
    }
    
    /**
     * A method to retrieve a record in one line. Line data can be manipulated to output any info needed/wanted
     * @param i the key corresponding the the wanted record
     * @return a String line of info corresponding to the passed in key
     */
    public String getEntry(int i){
        Map myMap=putEntriesInMap(); //Populate a map with values read from the file
        String[] returnArray=(String[])myMap.get(i); //Grab the corresponding array
        return returnArray[0]+" "+returnArray[1]+" "+returnArray[2]; //print out the info stored in the array
    }
}
