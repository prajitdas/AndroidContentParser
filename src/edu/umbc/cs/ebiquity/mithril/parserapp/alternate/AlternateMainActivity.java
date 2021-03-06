package edu.umbc.cs.ebiquity.mithril.parserapp.alternate;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import edu.umbc.cs.ebiquity.mithril.parserapp.ParserApplication;
import edu.umbc.cs.ebiquity.mithril.parserapp.R;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.contacts.ContactsListActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.media.AudioActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.media.ImageActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.media.MediaActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.media.VideoActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.providerlists.ProvidersMainActivity;
/**
 * @author prajit.das
 */
public class AlternateMainActivity extends Activity {
	private String [] permissionsList = ParserApplication.getPermissionsParserApp();
	private Button mAudioButton;
	private Button mContactLoaderButton;
	private Button mContactQueryButton;
	private Button mListOfProvidersButton;
	private Button mMediaButton;
	private Button mVideoButton;
	private Button mImageButton;
	private Button mAndroidIDButton;
	
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
		
		mAndroidIDButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//         	   Toast.makeText(getApplicationContext(), getAndroidId(), Toast.LENGTH_LONG).show();
		        // Use the Builder class for convenient dialog construction
		        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		        builder.setMessage(R.string.dialog_android_id)
		               .setPositiveButton(R.string.dialog_copy_data, new DialogInterface.OnClickListener() {
		            	   public void onClick(DialogInterface dialog, int id) {
		            		   ClipboardManager clipboard = (ClipboardManager)
		            			        getSystemService(Context.CLIPBOARD_SERVICE);
		            		   // Creates a new text clip to put on the clipboard
		            		   ClipData clip = ClipData.newPlainText("android_id",getAndroidId());
		            		   // Set the clipboard's primary clip.
		            		   clipboard.setPrimaryClip(clip);
		            		   Toast.makeText(getApplicationContext(), "Copied the deviceId: "
		            				   +getAndroidId()+" to clipboard!", Toast.LENGTH_LONG).show();
		            	   }
		               })
		               .setNegativeButton(R.string.dialog_done, new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   Toast.makeText(getApplicationContext(), "Bye!", Toast.LENGTH_LONG).show();
		                   }
		               });

				// create alert dialog
				AlertDialog alertDialog = builder.create();

				// show it
				alertDialog.show();
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
		checkMarshMallowPermissions();
	}

	/**
	 * Since we are using Marshmallow we have to check for these permissions now 
	 * <uses-permission android:name="android.permission.READ_CONTACTS" />
	 * <uses-permission android:name="android.permission.READ_CALL_LOG" />
	 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
	 * The next one isn't a dangerous permission so why care?
	 * <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
	 * <!-- Required permission not required at or below Android 4.3 API level -->
	 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
	 * <!-- Unusual but true! -->
	 */
	private void checkMarshMallowPermissions() {
		if (android.os.Build.VERSION.SDK_INT >= 23) {
			checkReadContactsPermission();
			checkReadCallLogsPermission();
			checkReadExternalStoragePermission();
			checkWriteExternalStoragePermission();
		     // only for Marshmallow and newer versions 
		}
	}

	private void checkReadExternalStoragePermission() {
		String [] tempList = {permissionsList[0]}; 
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
				Log.d(ParserApplication.getDebugTag(),"shouldShowRequestPermissionRationale");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} 
			else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(this, tempList, ParserApplication.getConstPermissionReadExternalStorage());
				Log.d(ParserApplication.getDebugTag(),"requestPermissions");
				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
	}

	private void checkReadCallLogsPermission() {
		String [] tempList = {permissionsList[0]}; 
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALL_LOG)) {
				Log.d(ParserApplication.getDebugTag(),"shouldShowRequestPermissionRationale");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} 
			else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(this, tempList, ParserApplication.getConstPermissionReadCallLog());
				Log.d(ParserApplication.getDebugTag(),"requestPermissions");
				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
	}

	private void checkWriteExternalStoragePermission() {
		String [] tempList = {permissionsList[0]}; 
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				Log.d(ParserApplication.getDebugTag(),"shouldShowRequestPermissionRationale");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} 
			else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(this, tempList, ParserApplication.getConstPermissionWriteExternalStorage());
				Log.d(ParserApplication.getDebugTag(),"requestPermissions");
				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
	}

	private void checkReadContactsPermission() {
		String [] tempList = {permissionsList[0]}; 
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
				Log.d(ParserApplication.getDebugTag(),"shouldShowRequestPermissionRationale");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.
			} 
			else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(this, tempList, ParserApplication.getConstPermissionReadContacts());
				Log.d(ParserApplication.getDebugTag(),"requestPermissions");
				// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
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
		mAndroidIDButton = (Button) findViewById(R.id.btnAndroidID);
		mListOfProvidersButton = (Button) findViewById(R.id.buttonForListOfProviders);
		mListOfProvidersButton.setVisibility(View.GONE);
	}
	
	/**
	 * This is for testing the Android ID content provider
	 */
	private static final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
	private static final String ID_KEY = "android_id";
	private String getAndroidId() {
		String[] selectionArgs = { ID_KEY };
		Cursor c = getContentResolver().query(URI, null, null, selectionArgs, null);

		if (!c.moveToFirst() || c.getColumnCount() < 2)
			return null;
		try {
			return Long.toHexString(Long.parseLong(c.getString(1)));
		} catch (NumberFormatException e) {
			return null;
		}
	}
}