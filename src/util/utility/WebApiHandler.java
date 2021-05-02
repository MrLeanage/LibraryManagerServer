package util.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bean.Book;
import org.json.JSONArray;
import org.json.JSONObject;


public class WebApiHandler {

    public static List<Book> searchBook(String searchTitle) throws IOException {
        String USER_AGENT = "Mozilla/5.0";
        // "http://openlibrary.org/search.json?title=the+village+by+the+sea";
        String url = "http://openlibrary.org/search.json?title=" + searchTitle;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic get request
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        List<Book> bookList = new ArrayList<>();
        JSONObject responseObj = new JSONObject(response.toString());
        JSONArray booksArray = responseObj.getJSONArray("docs");

        for (int i = 0; i < booksArray.length(); i++) {
            Book newBook = new Book();

            if (booksArray.getJSONObject(i).has("title")) {
                newBook.setbName(booksArray.getJSONObject(i).getString("title"));
            }

            if (booksArray.getJSONObject(i).has("author_name")) {
                JSONArray authorsArray = (JSONArray) booksArray.getJSONObject(i).get("author_name");
                newBook.setbAuthor(authorsArray.get(0).toString());
            }

            if (booksArray.getJSONObject(i).has("isbn")) {
                JSONArray isbnArray = (JSONArray) booksArray.getJSONObject(i).get("isbn");
                newBook.setbISBN(isbnArray.get(0).toString());
            }

            if (newBook.getbName() != null && newBook.getbAuthor() != null && newBook.getbISBN() != null) {
                bookList.add(newBook);
            }
        }
        return bookList;
    }
}
