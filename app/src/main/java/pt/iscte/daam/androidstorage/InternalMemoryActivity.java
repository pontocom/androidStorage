package pt.iscte.daam.androidstorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class InternalMemoryActivity extends Activity {

	EditText nome, email;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_memory);

		String filename = "myfile";
		FileInputStream inputstream;
		
		nome = (EditText) findViewById(R.id.imNomeText);
		email = (EditText) findViewById(R.id.imEmailText);

		try{
			inputstream = openFileInput(filename);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));
			String _nome = br.readLine();
			String _email = br.readLine();
			
			nome.setText(_nome);
			email.setText(_email);
			
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_memory, menu);
		return true;
	}
	
	public void onGuardarButton (View v)
	{
		nome = (EditText) findViewById(R.id.imNomeText);
		email = (EditText) findViewById(R.id.imEmailText);
		
		String filename = "myfile";
		FileOutputStream outputstream;
		
		try {
			outputstream = openFileOutput(filename, Context.MODE_PRIVATE);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputstream));
			
			bw.write(nome.getText().toString());
			bw.newLine();
			bw.write(email.getText().toString());
			bw.newLine();
			
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
