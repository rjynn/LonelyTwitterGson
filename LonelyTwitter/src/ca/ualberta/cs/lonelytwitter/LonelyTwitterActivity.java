package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ca.ualberta.cs.lonelytwitter.data.GsonDataManager;
import ca.ualberta.cs.lonelytwitter.data.IDataManager;

public class LonelyTwitterActivity extends Activity {

	private IDataManager dataManager;

	private EditText bodyText;

	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets;

	private ArrayAdapter<Tweet> tweetsViewAdapter;
	
	private SummaryData summaryData;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dataManager = new GsonDataManager(this);
		summaryData = new SummaryData();
		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
	}
	

	public void save(View v) {

		String text = bodyText.getText().toString();

		Tweet tweet = new Tweet(new Date(), text);
		tweets.add(tweet);

		tweetsViewAdapter.notifyDataSetChanged();

		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}

	public void clear(View v) {

		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
	

	public void showSummary(View view){
		createSummary();
		String string = "Going to summary";
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(LonelyTwitterActivity.this, SummaryActivity.class);
		intent.putExtra("summary", summaryData);
		startActivity(intent);
		//intent to new activity and goes there
	}
	
	private void createSummary(){
		summaryData.setTweetNum(getAverageNumber());
		summaryData.setAveLength(getAverageLength());
	
	}
	private int getAverageNumber(){
		return tweets.size();
	}
	private long getAverageLength(){
		long num = tweets.size();
		long sum = 0;
		for(int i = 0; i<num; i++){
			sum = sum + tweets.get(i).size();
		}
		return (long) sum/num;
	}
	
}