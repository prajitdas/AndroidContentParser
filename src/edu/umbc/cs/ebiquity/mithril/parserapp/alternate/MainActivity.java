package edu.umbc.cs.ebiquity.mithril.parserapp.alternate;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.umbc.cs.ebiquity.mithril.parserapp.ParserApplication;
import edu.umbc.cs.ebiquity.mithril.parserapp.R;
import edu.umbc.cs.ebiquity.mithril.parserapp.contentparsers.media.ImageActivity;
import edu.umbc.cs.ebiquity.mithril.parserapp.util.CheckPermissionsHelper;

public class MainActivity extends Activity {
	private TextView mDisplayTextView;
	private Button mUseCOMMANDButton;
	private Button mUseHeimdallButton;
	private Button mGetXPrivacyDataButton;
	private ImageView mImageView;
	private Cursor queryCursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CheckPermissionsHelper.checkMarshMallowPermissions(getApplicationContext(),this);		
		
		mDisplayTextView = (TextView) findViewById(R.id.textViewMainDisplay);
		mDisplayTextView.setText("");
		
		mUseCOMMANDButton = (Button) findViewById(R.id.useCOMMANDButton);
		mUseHeimdallButton = (Button) findViewById(R.id.useHeimdallButton);
		mGetXPrivacyDataButton = (Button) findViewById(R.id.getXPrivacyDataButton);
		
		mImageView = (ImageView) findViewById(R.id.heimdallImageView);
		mImageView.setVisibility(View.GONE);
		
		setOnclickListeners();

		/**
		 * Facebook
		 */
//		hackApps("Facebook", "com.facebook.katana.provider.ContactsConnectionsProvider", "contacts_db2");
		/**
		 * Expense Manager
		 */
//		hackApps("Expense Manager", "at.markushi.expensemanager.provider.backup", "#");
		/**
		 * COMMAND
		 */
//		hackApps("COMMAND", "edu.umbc.cs.ebiquity.mithril.command.contentprovider.Content", "images");
		/**
		 * Mint
		 */
//		hackApps("Mint", "com.mint.integrations", "")
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mImageView.setVisibility(View.GONE);
	}

    private void setOnclickListeners() {
		mUseCOMMANDButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Hacky method to show the image because Banerjee wants this for the demo
				Intent intent = new Intent(v.getContext(), ImageActivity.class);
				startActivity(intent);
			}
		});
		
		mUseHeimdallButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getLatestCameraPhoto();
				try {
			    	if (queryCursor == null) {
			    		ParserApplication.makeToast(v.getContext(), "No data found!");
//			    		mImageView.setImageDrawable(getResources().getDrawable(R.drawable.dummy));
					}
		    		else {
			    		queryCursor.moveToFirst();
			    		int idx = queryCursor.getColumnIndex(ImageColumns._ID);
			    		String imageUri = queryCursor.getString(idx);
			    		Log.v(ParserApplication.getDebugTag(), "I came to display the id: "+imageUri);
			    		if(!imageUri.contains("content"))
			    			mImageView.setImageBitmap(Media.getBitmap(v.getContext().getContentResolver(), Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, imageUri)));
			    		else
			    			mImageView.setImageBitmap(Media.getBitmap(v.getContext().getContentResolver(), Uri.parse(imageUri)));
			    		mImageView.setVisibility(View.VISIBLE);
			    		queryCursor.close();
			    	}
			    } catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
			    }

			}
		});
		
		mGetXPrivacyDataButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v(ParserApplication.getDebugTag(), String.valueOf(0));				
				Cursor mCursor = getContentResolver().query( 
						XPrivaycQuery.baseUri,		// The content URI of the words table
						XPrivaycQuery.projection,	// The columns to return for each row 
						XPrivaycQuery.selection,	// Selection criteria 
						XPrivaycQuery.selectionArgs,// Selection arguments 
						XPrivaycQuery.sortOrder);	// Sorting order

				Log.v(ParserApplication.getDebugTag(), XPrivaycQuery.baseUri.toString());				
				Log.v(ParserApplication.getDebugTag(), XPrivaycQuery.projection.toString());				
				Log.v(ParserApplication.getDebugTag(), "null");				
				Log.v(ParserApplication.getDebugTag(), "null");				
				Log.v(ParserApplication.getDebugTag(), XPrivaycQuery.sortOrder.toString());				
				// Some providers return null if an error occurs, others throw an exception 
				if (null == mCursor) { 
				    /* 
				     * Insert code here to handle the error. Be sure not to use the cursor! You may want to 
				     * call android.util.Log.e() to log this error. 
				     * 
				     */
					Log.e(ParserApplication.getDebugTag(), "Something went wrong the curosr is null");
				// If the Cursor is empty, the provider found no matches 
				} else if (mCursor.getCount() < 1) { 
				 
				    /* 
				     * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily 
				     * an error. You may want to offer the user the option to insert a new row, or re-type the 
				     * search term. 
				     */ 
					Log.e(ParserApplication.getDebugTag(), "It would seem that no data was found!");
				} else { 
				    // Insert code here to do something with the results 
					Log.e(ParserApplication.getDebugTag(), "some data was found!");
					// Get some data
					int index = mCursor.getCount();
					Log.v(ParserApplication.getDebugTag(), String.valueOf(index));				
					if (mCursor != null) {
						while (mCursor.moveToNext()) {
							Log.v(ParserApplication.getDebugTag(), mCursor.getString(1));
						}
					}
				} 
			}
		});
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
		Uri baseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//		Uri baseUri = Images.Media.getContentUri("external");
		String[] projection = { ImageColumns._ID };
		String selection = ImageColumns.BUCKET_DISPLAY_NAME + " = 'Camera'";
	    String[] selectionArgs = null;
	    String sort = ImageColumns._ID + " DESC LIMIT 1";
    }

	/**
     * This interface defines constants for the Cursor and CursorLoader, based on constants defined
     * in the the data class.
     */
    private interface XPrivaycQuery {
    	/**
    	 * TODO This is the point where the URI for XPrivacy data access is inserted
    	 */
		Uri baseUri = Uri.parse(ParserApplication.getConstXprivacyContentUri());
//		Uri baseUri = Images.Media.getContentUri("external");
		String[] projection = { 
				ParserApplication.XPRIVACY_CONST_COL_UID, 
				ParserApplication.XPRIVACY_CONST_COL_METHOD, 
				ParserApplication.XPRIVACY_CONST_COL_RESTRICTION, 
				ParserApplication.XPRIVACY_CONST_COL_RESTRICTED,
				ParserApplication.XPRIVACY_CONST_COL_USED
				};
		String selection = null;
//		String selection = ParserApplication.XPRIVACY_CONST_COL_UID + " = 10096";
	    String[] selectionArgs = null;
	    String sortOrder = ParserApplication.XPRIVACY_CONST_COL_USED;
    }
    
	@SuppressWarnings("unused")
	private void hackApps(String appName, String provider, String tableName) {
		StringBuffer stringToSetOnTextView = new StringBuffer();
		stringToSetOnTextView.append(mDisplayTextView.getText());
		/**
		 * Can we really do this???
		 */
		Uri FBURI = Uri.parse("content://" + provider + "/" + tableName);//"com.facebook.katana.provider.ContactsConnectionsProvider/contacts_db2");
		Log.v(ParserApplication.getDebugTag(), "I am printing the URI: "+FBURI.toString());
//		com.facebook.katana.provider.messages
		ContentResolver FBcontentresolver = this.getContentResolver();
		String[] projection = { ImageColumns._ID };
		try {
			Cursor FBcursor = FBcontentresolver.query(FBURI, projection, null, null, null);
//			Cursor FBcursor = managedQuery(FBURI, projection, null, null, null);
			if(FBcursor == null) {
				Log.v(ParserApplication.getDebugTag(), "No data!");
				stringToSetOnTextView.append("For app "+appName+" there is no data!\n");
			}
			if(FBcursor.moveToFirst()) {
//				Toast.makeText(this, FBcursor.getColumnName(0)+FBcursor.getColumnCount(), Toast.LENGTH_LONG).show();
				Log.v(ParserApplication.getDebugTag(), Integer.toString(FBcursor.getCount()));
				stringToSetOnTextView.append("# rows for "+appName+" app: "+Integer.toString(FBcursor.getCount())+"\n");
			}
			else
				Toast.makeText(this, "Can't get it!", Toast.LENGTH_LONG).show();
		} catch(Exception e) {
			Toast.makeText(this, "Exception in query"+e.getMessage(), Toast.LENGTH_LONG).show();
		}
		mDisplayTextView.setText(stringToSetOnTextView.toString());
	}
}