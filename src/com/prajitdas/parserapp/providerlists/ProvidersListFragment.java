package com.prajitdas.parserapp.providerlists;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.prajitdas.parserapp.ParserApplication;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProvidersListFragment extends ListFragment {
	// Container Activity must implement this interface
    public interface OnProviderSelectedListener {
        public void onProviderSelected(int position);
    }
	public static final String ARG_ITEM_ID = "position";
	private ArrayAdapter<String> mAdapter;

	private OnProviderSelectedListener mCallback;

    public ProvidersListFragment() {
	}
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnProviderSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    
    @Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
		mAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, 
				ParserApplication.getITEMSStringVal());
		setListAdapter(mAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);  
	}  
	
	@Override  
	public void onListItemClick(ListView listView, View view, int position, long id) {  
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallback.onProviderSelected(position);
	}  
}