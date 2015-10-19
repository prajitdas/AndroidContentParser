package edu.umbc.cs.ebiquity.mithril.parserapp.alternate;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import edu.umbc.cs.ebiquity.mithril.parserapp.ParserApplication;
import edu.umbc.cs.ebiquity.mithril.parserapp.R;

public class MainActivity extends Activity {
	private TextView mDisplayTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDisplayTextView = (TextView) findViewById(R.id.textViewMainDisplay);
		mDisplayTextView.setText("");
		/**
		 * Facebook
		 */
//		hackApps("Facebook", "com.facebook.katana.provider.ContactsConnectionsProvider", "contacts_db2");
		/**
		 * Expense Manager
		 */
		hackApps("Expense Manager", "at.markushi.expensemanager.provider.backup", "*");
		/**
		 * COMMAND
		 */
		hackApps("COMMAND", "edu.umbc.cs.ebiquity.mithril.command.contentprovider.Content", "images");
		/**
		 * Mint
		 */
//		hackApps("Mint", "com.mint.integrations", "")
	}

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
				Log.v(ParserApplication.getDebugTag(), "Got null cursor!");
				stringToSetOnTextView.append("For app "+appName+" got null cursor!\n");
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