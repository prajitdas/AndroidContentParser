package com.prajitdas.parserapp.contentparsers.media;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;

public class ImageActivity extends Activity {
	private ImageView mImageView;
	private Cursor queryCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);

		mImageView = (ImageView) findViewById(R.id.imageViewForPicture);
		
		Log.v(ParserApplication.getDebugTag(), Media.EXTERNAL_CONTENT_URI.toString());
		getLatestCameraPhoto();
		
		try {
	    	if (queryCursor == null) {
	    		ParserApplication.makeToast(this, "No data found!");
//	    		mImageView.setImageDrawable(getResources().getDrawable(R.drawable.dummy));
			}
    		else {
	    		queryCursor.moveToFirst();
	    		int idx = queryCursor.getColumnIndex(ImageColumns._ID);
	    		String imageUri = queryCursor.getString(idx);
	    		Log.v(ParserApplication.getDebugTag(), "I came to display the id: "+imageUri);
	    		if(!imageUri.contains("content"))
	    			mImageView.setImageBitmap(Media.getBitmap(this.getContentResolver(), Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, imageUri)));
	    		else
	    			mImageView.setImageBitmap(Media.getBitmap(this.getContentResolver(), Uri.parse(imageUri)));
	    		queryCursor.close();
	    	}
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	    }
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

	private void getLatestCameraPhoto() {
//		if(ParserApplication.isImageAccessPolicyAllowed()) 
		queryCursor = this.getContentResolver().query(ImageQuery.baseUri,
									ImageQuery.projection, 
									ImageQuery.selection, 
									ImageQuery.selectionArgs, 
									ImageQuery.sort);
//		else
//			queryCursor = null;
	}

	/**
     * This interface defines constants for the Cursor and CursorLoader, based on constants defined
     * in the {@link Images.Media} class.
     */
    private interface ImageQuery {
    	/**
    	 * TODO This is the point where the URI for image access has been changed to the one we have here 
    	 * from content://media/external/images/media 
    	 */
		Uri baseUri = Uri.parse(ParserApplication.getConstSprivacyContentUri()+
				ParserApplication.getConstSlash()+
				ParserApplication.getConstImages());
//		Uri baseUri = Images.Media.getContentUri("external");
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