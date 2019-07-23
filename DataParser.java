import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataParser {

    String responseClob;


    DataParser(String responseClob){
        this.responseClob = responseClob;
    }

    int checkNumberofReturns(){

        String[] rc = responseClob.split("{");
        return rc.length;
    }

    /**
     * parse json into a format that can be split. 
     * Takes in only one movie result form listed json
     * @return
     */
    public String[] temporaryClob(String stringToParse){

        // delete first and last character of the clob
        String tempClob = stringToParse.replace("{", "").replace("}", "");

        // split by comma separared values 
        String[] keyValues = stringToParse.split(",");
        
        return keyValues;
    }

    ArrayList<String> splitData(String rawResponse){

        ArrayList<String> singularReponse = new ArrayList<String>();
        
        
        // split by comma
        String[] tempClob = temporaryClob(rawResponse);
        
        // loop through parsed objects and only keep title, year, genre, and director
        //TO DO CHECK THE RESPONSE: TRUE
        for(int i=0; i < tempClob.length; i++){
            if(tempClob[i].contains("Title")){
                String title = tempClob[i].split(":")[1];
                System.out.println("title: "+ title);
                singularReponse.add(title);                     
            }
            if(tempClob[i].contains("Year")){
                String year = tempClob[i].split(":")[1];
                System.out.println("y: "+ year);
                singularReponse.add(year);
            }
            if(tempClob[i].contains("Genre")){
                String genre = tempClob[i].split(":")[1];
                singularReponse.add(genre);
            }
            if(tempClob[i].contains("Director")){
                String director = tempClob[i].split(":")[1];
                singularReponse.add(director);
            }  

        }
        System.out.println(singularReponse.toString());
        return singularReponse;
    }
    public LinkedHashMap<Integer, ArrayList<String>> processClob(){
        LinkedHashMap<Integer, ArrayList<String>> lm = new LinkedHashMap<Integer, ArrayList<String>>();

    
            String[] splitRawResponse = responseClob.split("\\{");
            
            for(int j = 0; j < splitRawResponse.length; j++){
                ArrayList<String> splitData = splitData(splitRawResponse[j]);
                lm.put(j, splitData);
            }

            return lm;
    }

    public static void main(String[] args) {
        String url = new String("http://www.omdbapi.com/?apikey=9d1bb888&type=movie&t=batman&y=1991");
        MovieApiConnection c = new MovieApiConnection(url);
        String cs = c.requestGET();

        DataParser dpc = new DataParser(cs);
        dpc.processClob();

    }

}