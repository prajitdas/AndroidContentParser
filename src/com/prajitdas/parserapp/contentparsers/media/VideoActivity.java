package com.prajitdas.parserapp.contentparsers.media;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Video;
import android.view.Menu;
import android.view.MenuItem;

import com.prajitdas.parserapp.R;
/**
 * @author prajit.das
 */
public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
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
    /**
     * This interface defines constants for the Cursor and CursorLoader, based on constants defined
     * in the {@link Video.Media} class.
     */
	@SuppressWarnings("unused")
    private interface VideoQuery {
		Uri baseUri = Images.Media.EXTERNAL_CONTENT_URI;
		String selection = ImageColumns.BUCKET_DISPLAY_NAME + " = 'Camera'";
	    String[] selectionArgs = null;
	    String sort = ImageColumns._ID + " DESC LIMIT 1";
    }
}