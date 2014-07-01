package com.prajitdas.parserapp.providerlists;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.prajitdas.parserapp.ParserApplication;
import com.prajitdas.parserapp.R;
import com.prajitdas.parserapp.contentparsers.contacts.ContactsListActivity;
import com.prajitdas.parserapp.util.ProviderContent;

public class ProvidersMainActivity extends Activity 
	implements ProvidersListFragment.OnProviderSelectedListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provider_main);

		//setting the list of providerInfo collected dynamically 
		setProviderList();

		// Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null)
                return;
            else {
            	ProvidersListFragment providersListFragment = new ProvidersListFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
            	providersListFragment.setArguments(getIntent().getExtras());
            	
                // Add the fragment to the 'fragment_container' FrameLayout
                getFragmentManager().beginTransaction()
						.add(R.id.fragment_container, providersListFragment).commit();
            }
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.provider_main, menu);
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
	 * Finds all the content providers on the phone and stores them in a static object accessible to the whole app 
	 */
	public void setProviderList() {
		ProviderInfo[] providers;
		int providerCount = 0;
		for (PackageInfo pack : getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS)) {
			providers = pack.providers;
			if (providers != null)
				for (ProviderInfo provider : providers)
					ProviderContent.addItem(Integer.toString(providerCount++), provider);
		}
		Log.d(ParserApplication.getDebugTag(), Integer.toString(providerCount));
	}

	@Override
	public void onProviderSelected(int position) {
		String whichProvider = ParserApplication.ITEMS.get(position).toString();
		if(whichProvider.contains(ParserApplication.getContactProvider())) {
			Intent contactIntent = new Intent(this, ContactsListActivity.class);
			contactIntent.putExtra(ProvidersListFragment.ARG_ITEM_ID, whichProvider);
			startActivity(contactIntent);
		}
		else if(whichProvider.contains(ParserApplication.getContactsProvider())) {
			Intent contactIntent = new Intent(this, ContactsListActivity.class);
			contactIntent.putExtra(ProvidersListFragment.ARG_ITEM_ID, whichProvider);
			startActivity(contactIntent);
		}
		else
			Toast.makeText(this, "Clicked on "+whichProvider+" we don't know what to do with that!", 
					Toast.LENGTH_LONG).show();
	}
}