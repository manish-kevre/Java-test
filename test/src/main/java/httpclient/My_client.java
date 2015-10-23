package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonParser;

import model.Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class My_client extends CloseableHttpClient{
	
	public String movie_title;
	public String imdbRatingString;
	public HttpClient client;

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	
	public URI makeUrl()
	{
		URI uri = null;
		try {
			uri = new URIBuilder().setScheme("http")
					.setHost("www.omdbapi.com")
					.setPath("/")
					.setParameter("type", "movie")
			        .setParameter("r", "json")
			        .setParameter("t", movie_title)
			        .build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uri;
	}
	
	public void mapJsonToData(HttpResponse response)
	{
		 ObjectMapper mapper = new ObjectMapper();  
		    Data my_data = null;
			try {
				my_data = mapper.readValue(response.getEntity().getContent(), Data.class);
				
				imdbRatingString = my_data.getImdbRating().toString();
						
				
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedOperationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		    try {
				System.out.println(mapper.writeValueAsString(my_data));
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		
	}
	
	public void executeGetRequest(HttpGet http_get) throws Exception, IOException
	{
		HttpResponse response = null;
		
		response = client.execute(http_get);
		mapJsonToData(response);
		//BufferedReader rd;
		try {
				//rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
				//String line = "";
				//while ((line = rd.readLine()) != null)
				//{
			//		System.out.println(line);
				//}
				//rd.close();
			} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public My_client() throws ClientProtocolException, IOException 
	{
		this.client  =  HttpClientBuilder.create().build();
	}

	@Override
	protected CloseableHttpResponse doExecute(HttpHost arg0, HttpRequest arg1, HttpContext arg2)
			throws IOException, ClientProtocolException {

		
		return null;
	}

	@SuppressWarnings("deprecation")
	public ClientConnectionManager getConnectionManager() {
		this.getConnectionManager();
		return null;
	}

	@SuppressWarnings("deprecation")
	public HttpParams getParams() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() throws IOException {
		
		
	}
}
		
	  
	 
