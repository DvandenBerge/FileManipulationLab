package filemanagerlab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author dvandenberge
 */
public class FileManager {
    private File inputFile;
    
    public FileManager(){
        inputFile=new File("C:"+File.separatorChar+"temp"+File.separatorChar+"lab1"+File.separatorChar+"mailingList.txt");
    }
    
    public void printEntries(){
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new FileReader(inputFile));
            
            String line=reader.readLine();
            
            while(line!=null){
                System.out.println(line);
                line=reader.readLine();
            }
            
        }catch(IOException e){
            System.out.println("File cannot be read!");
        }finally{
            try{
                reader.close();
            }catch(Exception e){
                System.out.println("Critical System Failure");
            }
    
        }
    }
    
    public void addEntries(){
        PrintWriter in=null;
        try{
            in=new PrintWriter(new BufferedWriter(new FileWriter(inputFile)));
            
            in.println("Kyle Wirtz");
            in.println("2867 N Farwell ave");
            in.print("Milwaukee, ");
            in.print("WI ");
            in.println("53211");
        }catch(IOException e){
            System.out.println("File cannot be written to");
        }finally{
            try{
                in.close();
            }catch(Exception e){
                System.out.println("Critical System Failure");
            }
        }
    }
    
    public void printEntry(int entry){
        
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new FileReader(inputFile));
            
            String name="";
            String state="";
            for(int i=1;i<entry;i++){
                name=reader.readLine();
                for(int j=i;j<2;j++){
                    state=reader.readLine();
                }
            }
            String[] parts=state.split(" ");
            
            switch(parts.length){
                case 3:System.out.println(name+" "+parts[1]);
                    break;
                case 4:System.out.println(name+" "+parts[2]);
                    break;
            }
            
        }catch(IOException e){
            System.out.println("File cannot be read!");
        }finally{
            try{
                reader.close();
            }catch(Exception e){
                System.out.println("Critical System Failure");
            }
    
        }
    }
}
