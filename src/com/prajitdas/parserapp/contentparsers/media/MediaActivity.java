package com.prajitdas.parserapp.contentparsers.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Files.FileColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;

public class MediaActivity extends Activity {
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media);
		ParserApplication.makeToast(this, MediaQuery.baseUri.toString());
		
		mTextView = (TextView) findViewById(R.id.textViewMediaFile);
		FileInputStream fis = getTextFile();
		StringBuffer result = new StringBuffer();
		try {
			while(fis.available() > 0)
				result.append(fis.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	private FileInputStream getTextFile() {
		if(ParserApplication.isMediaAccessPolicyAllowed()) {
			Cursor cursor = this.getContentResolver().query(
										MediaQuery.baseUri,
										MediaQuery.projection, 
										MediaQuery.selection, 
										MediaQuery.selectionArgs, 
										MediaQuery.sort);
			try {
		    	if (cursor != null && cursor.moveToFirst()) {
		    		return new FileInputStream(new File(FileColumns.TITLE));
		    	}
		    	else
		    		return null;
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		    	cursor.close();
		    }
		}
		return null;
	}
    /**
     * This interface defines constants for the Cursor and CursorLoader, based on constants defined
     * in the {@link Files.Media} class.
     */
    private interface MediaQuery {
		Uri baseUri = Files.getContentUri("external");
		// every column, although that is huge waste, you probably need
		// BaseColumns.DATA (the path) only.
		String[] projection = null;
		// every column, although that is huge waste, you probably need
		// BaseColumns.DATA (the path) only.
		String sort = null; // unordered
		// exclude media files, they would be here also.
		String selection = FileColumns.MEDIA_TYPE + "=" + FileColumns.MEDIA_TYPE_NONE;
//		String selection = FileColumns.MIME_TYPE + "=?";
//		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("txt");
		String[] selectionArgs = null; // there is no ? in selection so null here
//		String[] selectionArgs = new String[]{ mimeType };
    }
}