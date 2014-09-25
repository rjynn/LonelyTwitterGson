package ca.ualberta.cs.lonelytwitter.data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.lonelytwitter.Tweet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonDataManager implements IDataManager {

	private static final String FILENAME = "gsonfile.sav";

	private Gson gson; //need this object to work
	private Context ctx; //need context to use for services

	public GsonDataManager(Context ctx) {	//constructor needs context(instance of activity
		this.ctx = ctx;
		gson = new Gson();
	}

	public ArrayList<Tweet> loadTweets() {
		ArrayList<Tweet> lts = new ArrayList<Tweet>();  //create this that will accept the list of tweets

		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					ctx.openFileInput(FILENAME)));		//reading from this part of memory
			String line;		
			StringBuffer fileContent = new StringBuffer(); //where youre going to put the things read

			while ((line = input.readLine()) != null) {	
				fileContent.append(line);		//reallly need to make sure not read garbage so use buffer(binary representation)
			}

			Type collectionType = new TypeToken<Collection<Tweet>>() {}.getType();	//TypeToken comes from gson. this line gets type
			lts = gson.fromJson(fileContent.toString(), collectionType); //get from the content and get string representation from the bytes
			//then expect collection type(found earlier line)

		} catch (Exception e) {
			Log.i("LonelyTwitter", "Error loading tweets");
			e.printStackTrace();
		}

		return lts;
	}

	public void saveTweets(List<Tweet> lts) {
		try {
			FileOutputStream fos = ctx.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);	//open a devices memory. context has permission to do this so need to pass it off
//filename is constant** using mode private ...no other activity can access this!
			String jsonTweetList = gson.toJson(lts); //this is the magic part for gson standard ===> tojson --> giving list of tweets to 
			//gson manager
			fos.write(jsonTweetList.getBytes()); //string i created give me bytes of your string and write it into memory
			fos.close();
			
			Log.i("Persistence", "Saved: " + jsonTweetList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
