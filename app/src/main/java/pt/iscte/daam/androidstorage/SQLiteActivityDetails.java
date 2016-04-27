package pt.iscte.daam.androidstorage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SQLiteActivityDetails extends Activity {
	TextView nomeTxt, phoneTxt;
	
	long id = 0;
	
	private ContactsDataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_activity_details);

		ds = new ContactsDataSource(this);
		ds.open();
		
		nomeTxt = (TextView) findViewById(R.id.nameDetailTxt);
		phoneTxt = (TextView) findViewById(R.id.phoneDetailTxt);
		
		Intent i = getIntent();
		Bundle data = i.getExtras();
		id = data.getLong("ID");
		
		Contact c = ds.getContact(id);
		
		nomeTxt.setText(c.getName());
		phoneTxt.setText(c.getTelephone());
		
		ds.close();
		
	}
	
	public void deleteContactButton(View v)
	{
		ds.open();
		ds.deleteThisContact(id);
		ds.close();
		
		
		
		setResult(Activity.RESULT_OK);
		
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_activity_details, menu);
		return true;
	}

}
