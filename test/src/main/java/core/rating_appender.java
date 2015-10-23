package core;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import httpclient.My_client;

public class rating_appender {

	public static void main(String[] args)  
	{
		final File folder = new File("E:\\Movies");
		listFilesForFolder(folder);


	}
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
	} 
	
	public String getIMDB_Rating(String movie_title)
	{
		
		return MakeQueryToInternet(movie_title);
		
	}
	
	public String MakeQueryToInternet(String movie_title)
	{
		String IMDB_Rating_String = "";
		try {
			My_client m = new My_client();
			m.setMovie_title(movie_title);
			URI uri = m.makeUrl();
			HttpGet http_get = new HttpGet(uri);
			System.out.println(uri.toString());
			m.executeGetRequest(http_get);
			IMDB_Rating_String = m.imdbRatingString;
			System.out.println("ImDB Rating is "+ m.imdbRatingString);
			m.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return IMDB_Rating_String;
		
	}

}
