package com.kervinramen.myspotfinder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.os.Bundle;

public class index extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.GetSpot();
		
		setContentView(R.layout.main);
	}

	public String GetSpot() 
	{
		HttpResponse response = null;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://myspotfinder.appspot.com/find");
		try {
			response = httpClient.execute(httpGet, localContext);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.toString();
		
	}
	public static String GetSpots(/*double latitude, double longitude*/) {
		String str = null;
		
		try {
			URL myURL = new URL("http://myspotfinder.appspot.com/find");
			URLConnection conn = myURL.openConnection();

			InputStream streamFromWS = conn.getInputStream();

			BufferedInputStream bufferStream = new BufferedInputStream(
					streamFromWS);

			ByteArrayBuffer baf = new ByteArrayBuffer(50);
			int current = 0;
			while ((current = bufferStream.read()) != -1) {
				baf.append((byte) current);
			}

			/* Convert the Bytes read to a String. */
			str = new String(baf.toByteArray());
		} catch (Exception ex) {

			ex.printStackTrace();
			str = ex.getMessage();
		}

		return str;
	}

}