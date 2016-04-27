package pt.iscte.daam.androidstorage;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SharedPreferencesActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	
	EditText nome, email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);

		nome = (EditText) findViewById(R.id.editTextNome);
		email = (EditText) findViewById(R.id.editTextEmail);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		String nom = settings.getString("nome", "");
		String emai = settings.getString("email", "");
		
		nome.setText(nom);
		email.setText(emai);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_preferences, menu);
		return true;
	}
	
	public void onGravarButton(View v)
	{
		nome = (EditText) findViewById(R.id.editTextNome);
		email = (EditText) findViewById(R.id.editTextEmail);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("nome", nome.getText().toString());
		editor.putString("email", email.getText().toString());
		editor.commit();
		
		finish();
	}

}
