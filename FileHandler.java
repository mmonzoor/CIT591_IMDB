import java.util.LinkedHashMap;
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
                    sb.append(stats);
                    sb.append(",");
                }
                sb.append("\r\n");
            }
            
            pw.print(sb.toString().toLowerCase());
            pw.flush();
            
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }      

    }
}