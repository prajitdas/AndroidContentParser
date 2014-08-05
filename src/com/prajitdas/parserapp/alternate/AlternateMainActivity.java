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
import com.prajitdas.parserapp.contentparsers.media.AudioActivity;
import com.prajitdas.parserapp.contentparsers.media.ImageActivity;
import com.prajitdas.parserapp.contentparsers.media.MediaActivity;
import com.prajitdas.parserapp.contentparsers.media.VideoActivity;
import com.prajitdas.parserapp.providerlists.ProvidersMainActivity;

public class AlternateMainActivity extends Activity {
	private Button mAudioButton;
	private Button mContactLoaderButton;
	private Button mContactQueryButton;
	private Button mListOfProvidersButton;
	private Button mMediaButton;
	private Button mVideoButton;
	private Button mImageButton;
	
	private void addListenerOnButton() {
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

		mMediaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MediaActivity.class);
				startActivity(intent);
			}
		});
		
		mVideoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), VideoActivity.class);
				startActivity(intent);
			}
		});
		
		mAudioButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), AudioActivity.class);
				startActivity(intent);
			}
		});
		
		mListOfProvidersButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ProvidersMainActivity.class);
				startActivity(intent);
			}
		});
		
		mImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ImageActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alternate_main);
		ParserApplication.setQueryOrLoader(new String());
		
		setViews();
//		setDefaultPolicies();		
		addListenerOnButton();
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

//	private void setDefaultPolicies() {
//		ParserApplication.setContactsAccessPolicyAllowed(true);
//		ParserApplication.setMediaAccessPolicyAllowed(true);
//		ParserApplication.setImageAccessPolicyAllowed(true);
//		ParserApplication.setVideoAccessPolicyAllowed(true);
//		ParserApplication.setAudioAccessPolicyAllowed(true);
//	}

	private void setViews() {
		mContactQueryButton = (Button) findViewById(R.id.buttonContactProviderQuery);
		mContactLoaderButton = (Button) findViewById(R.id.buttonContactProviderLoader);
		mMediaButton = (Button) findViewById(R.id.buttonMediaProvider);
		mVideoButton = (Button) findViewById(R.id.buttonVideoProvider);
		mVideoButton.setVisibility(View.GONE);
		mAudioButton = (Button) findViewById(R.id.buttonAudioProvider);
		mAudioButton.setVisibility(View.GONE);
		mImageButton = (Button) findViewById(R.id.buttonImageProvider);
		mListOfProvidersButton = (Button) findViewById(R.id.buttonForListOfProviders);
		mListOfProvidersButton.setVisibility(View.GONE);
	}
}