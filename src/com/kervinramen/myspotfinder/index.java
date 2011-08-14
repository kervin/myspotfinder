package com.kervinramen.myspotfinder;




import com.kervinramen.myspotfinder.model.Spots;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    	//getPlaces,
    	Spots spots = Spots.getSpotsFromWS();
     
    	String[] spotNames = new String[spots.getSpots().size()];
    	
    	
    	for (int i = 0; i < spots.getSpots().size(); i++)
    	{
    		spotNames[i] = spots.getSpots().get(i).getName();
    	}
    	
       	//display in list view 	
    	setListAdapter(new ArrayAdapter<String>(this, R.layout.place_row,
				R.id.text1, spotNames));
    	
    }
}