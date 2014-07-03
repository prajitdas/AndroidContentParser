package com.prajitdas.parserapp.alternate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;
import com.prajitdas.parserapp.contentparsers.contacts.ContactsListActivity;

public class AlternateMainActivity extends Activity {
	private Button mContactQueryButton;
	private Button mContactLoaderButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alternate_main);
		mContactQueryButton = (Button) findViewById(R.id.buttonCursorQuery);
		mContactLoaderButton = (Button) findViewById(R.id.buttonCursorLoader);
		ParserApplication.setQueryOrLoader(new String());
		mContactQueryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
				ParserApplication.setQueryOrLoader(ParserApplication.getButtonQuery());
				startActivity(intent);
			}
		});

		mContactLoaderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ContactsListActivity.class);
				ParserApplication.setQueryOrLoader(ParserApplication.getButtonLoader());
				startActivity(intent);
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