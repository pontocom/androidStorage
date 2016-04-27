package pt.iscte.daam.androidstorage;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class SQLiteActivity extends Activity {
	
	private ContactsDataSource ds;
	
	ListView cLV;
	ArrayList<Contact> contactList;
	MyContactBaseAdapter myContactAdapter;
	
	EditText nameT;
	EditText phoneT;
	
	static int RESULT_REQUEST = 0;
	
	int currPos = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		
		ds = new ContactsDataSource(this);
		ds.open();
		
		contactList = new ArrayList<Contact>();
		
		contactList = ds.getAllContacts();

		cLV = (ListView) findViewById(R.id.listaTelefonicaListView);
		myContactAdapter = new MyContactBaseAdapter(this, contactList);
		
		cLV.setAdapter(myContactAdapter);
		
		cLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Context ctx = getApplicationContext();
				Intent i = new Intent(ctx, SQLiteActivityDetails.class);
				
				currPos = position;
				
				Contact s = (Contact) myContactAdapter.getItem(position);
				
				Bundle detailsInfo = new Bundle();
				detailsInfo.putLong("ID", s.getId());
				i.putExtras(detailsInfo);
				
				startActivityForResult(i, RESULT_REQUEST);
				
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK)
		{
			contactList.remove(currPos);
			myContactAdapter.notifyDataSetChanged();
		}
	}
	
	
	/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, SQLiteActivityDetails.class);
		
		startActivity(i);
	}
	*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

	public void onSQLAddButton(View v){
		
		nameT = (EditText) findViewById(R.id.nameTelText);
		phoneT = (EditText) findViewById(R.id.telefoneTelText);
		
		Contact c = new Contact();
		c = ds.createContact(nameT.getText().toString(), phoneT.getText().toString());
				
		contactList.add(c);
		
		nameT.setText("");
		phoneT.setText("");
				
	}
	
	@Override
	  protected void onResume() {
		myContactAdapter.notifyDataSetChanged();
	    ds.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
		myContactAdapter.notifyDataSetChanged();
	    ds.close();
	    super.onPause();
	  }
}
