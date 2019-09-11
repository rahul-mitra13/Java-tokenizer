import java.io.*;
import java.util.*;
public class ScanIt {
    public static void main(String[] args)throws Exception {
        if (args.length == 0){
            System.out.println("No command line arguments were passed.");
            System.exit(0);
        }
        else {
            System.out.println();
            File file = new File(args[0]);
    
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            int k = 1;//to store line number
            String line = br.readLine();
            while(line != null){
                String str = line;
            }
        }
    }
}