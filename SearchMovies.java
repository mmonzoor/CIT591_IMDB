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


    public String constructSearch(){
        StringBuilder url = new StringBuilder("http://www.omdbapi.com/?apikey=9d1bb888&type=movie");
        try {
            String enTitle = URLEncoder.encode(title, "utf-8");
            // add encoded title
            url.append("&t=");
            url.append(enTitle);

            if(year > 1901 && year < 2020){
                String yearToString = Integer.toString(year);
                url.append("&y=");
                url.append(yearToString);
            }
            
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }
        
        return url.toString();   
    }
    
    public static void main(String[] args) {
        int year = 2012;
        String title = "The Dark Knight Rises";

        SearchMovies sm = new SearchMovies(title);
        sm.year = year;
        System.out.println(sm.constructSearch());
    }

}