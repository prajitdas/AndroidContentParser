package com.prajitdas.parserapp.contentparsers.media;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;
import com.prajitdas.sprivacy.contentprovider.PrivacyAwareContentContracts;

public class ImageActivity extends Activity {
	private ImageView mImageView;
	private boolean imageFound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		imageFound = false;
		mImageView = (ImageView) findViewById(R.id.imageViewForPicture);
		
		mImageView.setImageBitmap(getLatestCameraPhoto());
		
		if(!imageFound)
			mImageView.setImageDrawable(getResources().getDrawable(R.drawable.dummy));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
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

	private Bitmap getLatestCameraPhoto() {
		if(ParserApplication.isImageAccessPolicyAllowed()) {
			Cursor cursor = Media.query(this.getContentResolver(),
										ImageQuery.baseUri,
										ImageQuery.projection, 
										ImageQuery.selection, 
										ImageQuery.selectionArgs, 
										ImageQuery.sort);
		    try {
		    	int idx = cursor.getColumnIndex(ImageColumns._ID);
		    	if (cursor != null && cursor.moveToFirst()) {
		    		imageFound = true;
		    		return Media.getBitmap(this.getContentResolver(), 
		    				Uri.withAppendedPath(ImageQuery.baseUri, cursor.getString(idx)));
		    	}
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
     * in the {@link Images.Media} class.
     */
    private interface ImageQuery {
		Uri baseUri = PrivacyAwareContentContracts.getImageContentUri();
		String[] projection = { ImageColumns._ID };
		String selection = ImageColumns.BUCKET_DISPLAY_NAME + " = 'Camera'";
	    String[] selectionArgs = null;
	    String sort = ImageColumns._ID + " DESC LIMIT 1";
    }
 // // Get relevant columns for use later.
//  String[] projection = {
//      MediaStore.Files.FileColumns._ID, 
//      MediaStore.Files.FileColumns.DATA,
//      MediaStore.Files.FileColumns.DATE_ADDED,
//      MediaStore.Files.FileColumns.MEDIA_TYPE,
//      MediaStore.Files.FileColumns.MIME_TYPE,
//      MediaStore.Files.FileColumns.TITLE
//  };
//
//  // Return only video and image metadata.
//  String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
//           + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE 
//           + " OR "
//           + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
//           + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
//
//  Uri queryUri = MediaStore.Files.getContentUri("external");
//
//  CursorLoader cursorLoader = new CursorLoader(
//      this,
//      queryUri,
//      projection,
//      selection,
//      null, // Selection args (none).
//      MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
//    );
//
//  Cursor cursor = cursorLoader.loadInBackground();
}