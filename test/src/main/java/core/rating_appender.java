package core;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream.Filter;
import java.security.GeneralSecurityException;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import httpclient.My_client;

public class rating_appender {
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else 
	        {
	        	FileNameExtensionFilter filter = new FileNameExtensionFilter("videop file",
	        			"mkv", "avi", "mp4",
	        			"mpeg", "wmv", "vob");
	            String title, rating, fileName;
				if(filter.accept(fileEntry))
				{
					fileName = fileEntry.getName().toString();
					title = fileName.substring(0, fileName.lastIndexOf('.'));
					rating = getIMDB_Rating(title);
					System.out.print(title+" ---  "+rating + "\n");
				}
	        }
	    }
	} 
	
	public static String getIMDB_Rating(String movie_title)
	{
		
		return MakeQueryToInternet(movie_title);
		
	}
	
	public static String MakeQueryToInternet(String movie_title)
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

	public static void main(String[] args)  
	{
		final File folder = new File("I:\\Movies");
		listFilesForFolder(folder);


	}
	


}
