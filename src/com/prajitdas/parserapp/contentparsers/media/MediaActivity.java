package com.prajitdas.parserapp.contentparsers.media;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Files.FileColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.TextView;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;

public class MediaActivity extends Activity {
	private TextView mTextView;
	private String defaultText = "No data found!";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media);
		
		String result = defaultText; 
		mTextView = (TextView) findViewById(R.id.textViewMediaFile);
		
		try {
			result = getTextFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mTextView.setText(result.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media, menu);
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
	
	private String getTextFile() throws Exception {
//		if(ParserApplication.isMediaAccessPolicyAllowed()) {
		Cursor cursor = this.getContentResolver().query(
														MediaQuery.baseUri,
														MediaQuery.projection, 
														MediaQuery.selection, 
														MediaQuery.selectionArgs, 
														MediaQuery.sort);
		try {
	    	if (cursor != null) {
				int idx = cursor.getColumnIndexOrThrow(FileColumns.DATA);
			    cursor.moveToFirst();
	    		return getStringFromFile(cursor.getString(idx));
	    	}
	    	ParserApplication.makeToast(this, "No data found!");
    		return defaultText;
	    } finally {
	    	cursor.close();
	    }
//		}
//		return defaultText;
	}

	private String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		reader.close();
		return sb.toString();
	}
	
	private String getStringFromFile (String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		//Make sure you close all streams.
		fin.close();        
		return ret;
	}
	
	/**
     * This interface defines constants for the Cursor and CursorLoader, based on constants defined
     * in the {@link Files.Media} class.
     */
    private interface MediaQuery {
    	/**
    	 * TODO This is the point where the URI for image access has been changed to the one we have here 
    	 * from content://media/external/file
    	 */
    	Uri baseUri = Uri.parse(ParserApplication.getConstSprivacyContentUri()+
				ParserApplication.getConstSlash()+
				ParserApplication.getConstFiles());
//		Uri baseUri = Files.getContentUri("external");
		// every column, although that is huge waste, you probably need
		// BaseColumns.DATA (the path) only.
		String[] projection = null;
		// every column, although that is huge waste, you probably need
		// BaseColumns.DATA (the path) only.
		String sort = FileColumns._ID + " DESC"; // get all files
		// exclude media files, they would be here also.
//		String selection = FileColumns.MEDIA_TYPE + "=" + FileColumns.MEDIA_TYPE_NONE;
		String selection = FileColumns.MIME_TYPE + "=?";
		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("txt");
//		String[] selectionArgs = null; // there is no ? in selection so null here
		String[] selectionArgs = new String[]{ mimeType };
    }
}