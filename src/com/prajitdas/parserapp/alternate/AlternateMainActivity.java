package com.prajitdas.parserapp.alternate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;
import com.prajitdas.parserapp.contentparsers.contacts.ContactsListActivity;

public class AlternateMainActivity extends Activity {
	private Button mContactQueryButton;
	private Button mContactLoaderButton;
	private Button mMediaQueryButton;
	private Button mMediaLoaderButton;
	private final String APOLOGY = "Sorry! I still am not sure what to do there...";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alternate_main);
		ParserApplication.setQueryOrLoader(new String());

		mContactQueryButton = (Button) findViewById(R.id.buttonContactProviderQuery);
		mContactLoaderButton = (Button) findViewById(R.id.buttonContactProviderLoader);
		mMediaQueryButton = (Button) findViewById(R.id.buttonMediaProviderQuery);
		mMediaLoaderButton = (Button) findViewById(R.id.buttonMediaProviderLoader);
		setOnClickListener();
	}

	private void setOnClickListener() {
		mContactQueryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
				ParserApplication.setQueryOrLoader(ParserApplication.getContactButtonQuery());
				startActivity(intent);
			}
		});

		mContactLoaderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
				ParserApplication.setQueryOrLoader(ParserApplication.getContactButtonLoader());
				startActivity(intent);
			}
		});

		mMediaQueryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
//				ParserApplication.setQueryOrLoader(ParserApplication.getContactButtonLoader());
//				startActivity(intent);
				Toast.makeText(getApplicationContext(), APOLOGY, Toast.LENGTH_LONG).show();
			}
		});
		
		mMediaLoaderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
//				ParserApplication.setQueryOrLoader(ParserApplication.getContactButtonLoader());
//				startActivity(intent);
				Toast.makeText(getApplicationContext(), APOLOGY, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alternate_main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}