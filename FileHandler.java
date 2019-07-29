import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;

public class FileHandler{

    public static void MovieSearchedWriter(String fileNameNew, LinkedHashMap<Integer, ArrayList<String>> moviesSearched){
        File f = new File(fileNameNew);

        try {
            if(!f.exists()){
                f.createNewFile();
            }


            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw);
            
            StringBuilder sb = new StringBuilder();
            for (Integer key: moviesSearched.keySet()) {
                sb.append(key);
                sb.append(",");
                ArrayList<String> item = moviesSearched.get(key);
                for (String stats: item){
                    // check to see word begins with quotes
                    if(!stats.startsWith("\"")){
                        sb.append("\"");
                    }
                    sb.append(stats);
                    // check to see ends with quotes
                    if(!stats.endsWith("\"")){
                        sb.append("\"");
                    }
                    sb.append(",");
                }
                sb.append("\n");
            }
            
            pw.print(sb.toString().toLowerCase());
            pw.flush();
            
            pw.close();
            System.out.println("The results have been saved into file: "+ fileNameNew);
        } catch (IOException e) {
            e.printStackTrace();
        }      

    }
}