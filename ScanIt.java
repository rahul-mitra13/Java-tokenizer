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
                line = line + ' ' + ' ';
                int i = 0;
                while( i < line.length()){
                    if ( Character.isLetter(line.charAt(i)) || line.charAt(i) == '_'){//handles identifiers
                        String ident = "";
                        while ( Character.isLetter(line.charAt(i)) || line.charAt(i) == '_'){
                            ident += line.charAt(i);
                            i++;
                        }
                        System.out.println(k+": "+"identifier"+" '"+ ident +"' "+"found");
                        System.out.println();
                    }
                    else if(Character.isDigit(line.charAt(i)) || (line.charAt(i) == '.' && Character.isDigit(line.charAt(i + 1)))){//handles integers and floats
                        String num = "";//both floats and integers
                        int c = 0;
                        while ((Character.isDigit(line.charAt(i)) || line.charAt(i) == '.')){
                            if ( line.charAt(i) == '.'){
                                c++;
                            }
                            if ( c > 1){
                                break;
                            }
                            num += line.charAt(i);
                            i++;
                        }
                        if ( num.indexOf('.') == - 1){//there is no decimal 
                            System.out.println(k+": "+"integer"+" '"+ num +"' "+"found");
                            System.out.println();
                        }
                        else{//there is a decimal
                            System.out.println(k+": "+"float"+" '"+ num +"' "+"found");
                            System.out.println();
                        }
                    }
                    else if (isOperator(line.charAt(i))){
                        System.out.println(k+": "+"operator"+" '"+line.charAt(i)+"' "+"found");
                        System.out.println();
                        i++;
                    }
                    else if (line.charAt(i) == '"'){//handle strings
                        i = i + 1;
                        String newstring = "";
                        while(line.charAt(i) != '"'){
                            if ( line.charAt(i) == '\\' && line.charAt(i + 1) == '\\'){
                                newstring += '\\';
                                i = i + 2;
                            }
                            else if ( line.charAt(i) == '\\' && line.charAt(i + 1) == '"'){
                                i = i + 1;
                            }
                            newstring += line.charAt(i);
                            i++;
                        }
                        System.out.println(k+": "+"string"+" '"+newstring+"' "+"found");
                        System.out.println();
                        i = i + 1;
                    }
                    else{
                        if ( line.charAt(i) != ' '){
                            System.out.println(k+": "+"unknown character"+" 0x"+Integer.toHexString((int)line.charAt(i)));
                            System.out.println();
                        }
                        i++;
                    }
                }
                k++;
                line = br.readLine();
            }
            br.close();
        }
    }
    public static boolean isOperator(char ch){
        if(ch == '&' || ch == '=' || ch == '!' || ch == ':' || ch == ',' || ch == '.' || ch == '>' || ch == '<' || ch == '[' || ch == ']' || ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == ';') {
            return true;
        }
        else 
            return false;
    }
}