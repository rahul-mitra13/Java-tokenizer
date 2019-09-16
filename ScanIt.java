/**
 * Rahul Mitra
 * CPSC316, Assignment 1 
 * Fall 2019
 * A Simple Scanner
 */
import java.io.*;
public class ScanIt {
    public static void main(String[] args)throws Exception {
        if (args.length == 0){//if no file is passed on the command line
            System.out.println("No command line arguments were passed.");
            System.exit(0);
        }
        else {//if a file is passed in
            System.out.println();
            File file = new File(args[0]);
    
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            int k = 1;//to store line number
            String line = br.readLine();
            while(line != null){
                line = line + ' ';//to prevent out of bounds exception at the end of the string
                int i = 0;//iterating variable
                while( i < line.length()){
                    if ( Character.isLetter(line.charAt(i)) || line.charAt(i) == '_'){//handles identifiers
                        String ident = "";
                        while ( Character.isLetter(line.charAt(i)) || line.charAt(i) == '_' || Character.isDigit(line.charAt(i))){
                            ident += line.charAt(i);
                            i++;
                        }
                        System.out.println(k+": "+"identifier"+" '"+ ident +"' "+"found");
                        System.out.println();
                    }
                    else if(Character.isDigit(line.charAt(i)) || (line.charAt(i) == '.' && Character.isDigit(line.charAt(i + 1)))){//handles integers and floats
                        String num = "";//both floats and integers
                        int c = 0;//counting the number of decimal points
                        while ((Character.isDigit(line.charAt(i)) || line.charAt(i) == '.')){
                            if ( line.charAt(i) == '.'){
                                c++;
                            }
                            if ( c > 1){//if points is more than one, we can break
                                break;
                            }
                            num += line.charAt(i);
                            i++;
                        }
                        if ( num.indexOf('.') == - 1){//there is no decimal so it is a integer
                            System.out.println(k+": "+"integer"+" '"+ num +"' "+"found");
                            System.out.println();
                        }
                        else{//there is a decimal so it is a float
                            System.out.println(k+": "+"float"+" '"+ num +"' "+"found");
                            System.out.println();
                        }
                    }
                    else if (isOperator(line.charAt(i))){//handles operators
                        System.out.println(k+": "+"operator"+" '"+line.charAt(i)+"' "+"found");
                        System.out.println();
                        i++;
                    }
                    else if (line.charAt(i) == '"'){//handles strings
                        i = i + 1;
                        String newstring = "";
                        while(line.charAt(i) != '"'){
                            if (line.charAt(i) == '\\' && line.charAt(i + 1) == '\\'){//if it's an escape character for '\'
                                //add a backslash and skip
                                newstring += '\\';
                                i = i + 2;
                            }
                            else if ( line.charAt(i) == '\\' && line.charAt(i + 1) == '"'){//if it's an escape character for '"'
                                //add a quotation mark and skip
                                newstring += '"';
                                i = i + 2;
                            }
                            else{
                            newstring += line.charAt(i);
                            i++;
                            }
                        }
                        System.out.println(k+": "+"string"+" '"+newstring+"' "+"found");
                        System.out.println();
                        i = i + 1;
                    }
                    else{//handles unknown characters 
                        if ( line.charAt(i) != ' '){//if it's not a blank space then it's an unknown character
                            System.out.println(k+": "+"unknown character"+" 0x"+Integer.toHexString((int)line.charAt(i)));
                            System.out.println();
                        }
                        i++;
                    }
                }//inner while
                k++;//increment line number
                line = br.readLine();//read the next line
            }//outer while
            br.close();
        }//else
    }//main
    /**
     * This function checks if a particular character is an operator
     * @param ch character to test
     * @return true if character is an operator or false otherwise
     */
    public static boolean isOperator(char ch){
        if(ch == '&' || ch == '=' || ch == '!' || ch == ':' || ch == ',' || ch == '.' || ch == '>' || ch == '<' || ch == '[' || ch == ']' || ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == ';'){
            return true;
        }
        else 
            return false;
    }//method
}//class