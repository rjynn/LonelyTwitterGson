package ca.ualberta.cs.lonelytwitter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	
	Intent i = getIntent();
	//SummaryData summaryData = (SummaryData) i.getSerializableExtra("summary");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		//TextView num = (TextView)findViewById(R.id.SummaryNumTweets);
		//num.setText("Number of Tweets:"+ Integer.toString(summaryData.getTweetNum()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}

}
