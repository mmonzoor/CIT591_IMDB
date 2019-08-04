import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataParser {

    String responseClob;

    DataParser(String responseClob) {
        this.responseClob = responseClob;
    }

    public boolean checkResponse() {
        boolean response = false;

        if (responseClob.contains("\"Response\":\"True\"")) {
            response = true;
        }

        return response;
    }

    /**
     * parse json into a format that can be split. Takes in only one movie result
     * form listed json
     * 
     * @return
     */
    public String[] temporaryClob(String stringToParse) {

        // delete first and last character of the clob
        String tempClob = stringToParse.replace("{", "").replace("}", "");

        // split by comma separared values
        String[] keyValues = tempClob.split(",");

        return keyValues;
    }

    ArrayList<String> splitData(String rawResponse) {

        ArrayList<String> singularReponse = new ArrayList<String>();

        // split by comma
        String[] tempClob = temporaryClob(rawResponse);

        // loop through parsed objects and only keep title, year, genre, and director
        // TO DO CHECK THE RESPONSE: TRUE
        for (int i = 0; i < tempClob.length; i++) {
            if (tempClob[i].contains("\"Title\":")) {
                String title = tempClob[i].split("\"Title\":")[1];
                singularReponse.add(title);
            }
            if (tempClob[i].contains("\"Year\":")) {
                //System.out.println(tempClob[i]);
                String year = tempClob[i].split("\"Year\":")[1];
                singularReponse.add(year);
            }
            if (tempClob[i].contains("\"imdbID\":")) {
                String imdbid = tempClob[i].split("\"imdbID\":")[1];
                singularReponse.add(imdbid);
            }

        }
        return singularReponse;
    }

    public LinkedHashMap<Integer, ArrayList<String>> processClob() {
        LinkedHashMap<Integer, ArrayList<String>> lm = new LinkedHashMap<Integer, ArrayList<String>>();
        
        // put initial header mapping the input values -- will represent the column names in final csv
        ArrayList<String> headerValues = new ArrayList<String>(); 
        headerValues.add("Title");
        headerValues.add("Year");
        headerValues.add("imdbID");
        // fake/ randomly generated data
        headerValues.add("Price");

        lm.put(0, headerValues);

        // process the innnerclob of nested json containing the actual search results
        String innerClob = new String();
        System.out.println("response"+ responseClob);
        // check stats on responseClob by looking at the value in the outer
        if (checkResponse()) {
            innerClob = responseClob.split("\\],")[0];

            // split everything by inside the elements of "search" key
            String[] splitRawResponse = innerClob.split("\\},");

            for (int j = 1; j < splitRawResponse.length; j++) {

                ArrayList<String> splitData = splitData(splitRawResponse[j]);
                // create a random price
                double price = 2.50 + Math.random()*(13.50);
                splitData.add(String.format("%.2f", price));
                lm.put(j, splitData);
            }
        } else {

            System.out.println("The search is invalid, no proper results were found.");
        }

        return lm;
    }
}