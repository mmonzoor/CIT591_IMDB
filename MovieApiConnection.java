import java.net.*;
import java.io.*;
import java.util.*;

public class MovieApiConnection {
    // base url - 
    // api key - 9d1bb888
    String SearchURL;
    MovieApiConnection(String SearchURL){
        this.SearchURL = SearchURL;
    }
    
    /**
     * checks the response code from the get request to ensure it was correct.
     */
    boolean checkValidResponse(int responseCode) {
    	if(responseCode == 200) {
    		return true;
    	}
    	else {
            System.out.println("Incorrect response code received.");
    		return false;
    	}
    }

    /** 
     * function that establishes the get request given a specific URL
    */
    public String requestGET(){
        // read the inputs
        StringBuilder responseStrBuilder = new StringBuilder();
        try{
            URL url = new URL(SearchURL);
            // call open connection to input stram
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // want to perform GET request
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if(!checkValidResponse(connection.getResponseCode())){
                throw new IOException();
            }
            // read the items from the connection
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputStr;
            while ((inputStr = in.readLine()) != null)
                responseStrBuilder.append(inputStr);
            //new JSONObject(responseStrBuilder.toString());

            //System.out.println(new JSONObject(responseStrBuilder.toString()));
            
        }
        catch (MalformedURLException mfe) {
            // case when new URL may have failed
            // message can be null, so print stack trace for good measures
            
            mfe.getMessage();
            mfe.printStackTrace();
        }
        catch (IOException ie){
            // case when open connection failed
            ie.printStackTrace();
        }
        
        return responseStrBuilder.toString();
    }
}