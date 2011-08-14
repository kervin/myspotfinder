package com.kervinramen.myspotfinder;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class index extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listPlaces();
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//on click, open Google Maps Activity
		Intent i = new Intent(this,  placesmap.class);
		startActivity(i);
	}
    
    
    private void listPlaces()
    {
    	
    	//getPlaces, display in list view
    	
		//SimpleCursorAdapter friends = new SimpleCursorAdapter(this, R.layout.place_row, notesCursor, from, to);
    	//setListAdapter();
    }
}