package com.elefher.wolfhuntergame;

import java.util.List;

import com.db.framework.Player;
import com.db.framework.PlayersDataSource;
import com.elefher.wolfhuntergame.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CreatePlayer extends ListActivity implements OnClickListener {

	private PlayersDataSource datasource;
	ArrayAdapter<Player> adapter = null;
	EditText usernameEdit;
	Button createButton;
	ListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.form);		
		
		datasource = new PlayersDataSource(this);
		datasource.open();

		List<Player> values = datasource.getAllPlayers();
		
		list = (ListView) findViewById(R.id.listview);
		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

		//listview.setAdapter(adapter);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
				// This function used when click the list item
		        Object o = parent.getAdapter().getItem(position);
		        String uname = o.toString();
		        //select the player that you use
		        if(datasource.setPlayer(uname) == 1){
		            Toast.makeText(view.getContext(), "You have chosen the player: " + " " + uname, Toast.LENGTH_LONG).show();
		        }else{
		        	Toast.makeText(view.getContext(), "Sorry but an error has occured durring database task!!", Toast.LENGTH_LONG).show();
		        }
		        finish();
			}
			
		});
		
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> arg0, final View arg1,
					final int arg2, final long arg3) {
				//get the palyer name
				final Object o = arg0.getAdapter().getItem(arg2);
		        final String playerName = o.toString();
		        
				AlertDialog.Builder builder1 = new AlertDialog.Builder(arg1.getContext());
				builder1.setTitle("Delete player");
	            builder1.setMessage("Are you sure that you want to delete this player?");
	            builder1.setCancelable(true);
	            builder1.setPositiveButton("Delete",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	// delete player form DB
	                	datasource.deletePlayer(playerName);
	                	Toast.makeText(arg1.getContext(), "Player "+ playerName +" deleted.", Toast.LENGTH_LONG).show();
	                	// delete player from listview
	                	list.removeViewInLayout(arg1);
	                	adapter.remove((Player) o);
	                }
	            });
	            builder1.setNegativeButton("Cancel",
	                    new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });

	            AlertDialog alert11 = builder1.create();
	            alert11.show();
				return true;
			}
			
		});

		//setListAdapter(adapter);
		//listview.setAdapter(adapter);
		// get the Button reference
		// Button is a subclass of View
		// buttonClick is defined in form.xml "@+id/buttonClick"
		createButton = (Button) findViewById(R.id.buttonCreateClick);

		usernameEdit = (EditText) findViewById(R.id.username);
		
		// set event listener
		createButton.setOnClickListener(this);
		datasource.close();
	}
	
	@Override
	public void onClick(View view) {
		// This function used when click the createButton
		@SuppressWarnings("unchecked")
		//adapter = (ArrayAdapter<Player>) getListAdapter();
		Player player = null;
		if (view.getId() == R.id.buttonCreateClick) {
			// save the new player to the database
			player = datasource.createPlayer(usernameEdit.getText().toString());
			adapter.add(player);
		}
		adapter.notifyDataSetChanged();
	}
	
	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// This function used when click the list item
		super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        String uname = o.toString();
        //select the player that you use
        if(datasource.setPlayer(uname) == 1){
            Toast.makeText(this, "You have chosen the player: " + " " + uname, Toast.LENGTH_LONG).show();
        }else{
        	Toast.makeText(this, "Sorry but an error has occured durring database task!!", Toast.LENGTH_LONG).show();
        }
        finish();
    }*/

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
