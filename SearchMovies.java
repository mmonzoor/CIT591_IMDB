import java.io.UnsupportedEncodingException;
import java.net.URLEncoder; 

public class SearchMovies{

    // api key - 9d1bb888
    // base url - 
    private String title;
    // optional param
    private int year = 0;
    
    /**
     * constructor taking in the same search inputs as the user
     * @param title
     * @param year
     */
    SearchMovies(String title){
        this.title = title;
    }

    /**
     * method constructing the URL needed to fetch the data we need.
     * @return
     */
    public String constructSearch(){
        StringBuilder url = new StringBuilder("http://www.omdbapi.com/?apikey=9d1bb888&t=movie");
        String yearToString = new String();
        try {
            String enTitle = URLEncoder.encode(title, "utf-8");
            // add encoded title
            url.append("&s=");
            url.append(enTitle);

            if(year > 1901 && year < 2020){
                yearToString = Integer.toString(year);
                url.append("&y=");
                url.append(yearToString);
            }
            else{
                yearToString = "";
            }
            
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        
        return url.toString();   
    }

    public void setYear(int year) {
        this.year = year;
    }

}