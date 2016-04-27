package pt.iscte.daam.androidstorage;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactsDataSource {
	private SQLiteDatabase db;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_PHONE};

	public ContactsDataSource(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		dbHelper.close();
	}
	
	public Contact createContact(String name, String phone)
	{
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, name);
		values.put(MySQLiteHelper.COLUMN_PHONE, phone);
		
		long insertId = db.insert(MySQLiteHelper.TABLE_CONTACTS, null, values);
		Cursor cursor = db.query(MySQLiteHelper.TABLE_CONTACTS, allColumns, MySQLiteHelper.COLUMN_ID + "=" + insertId, null, null, null, null);
		cursor.moveToFirst();
		
		Contact newcontact = cursorToContact(cursor);
		cursor.close();
		
		return newcontact;
	}
	
	public Contact getContact(long id)
	{
		
		Cursor cursor = db.rawQuery("SELECT * FROM " + MySQLiteHelper.TABLE_CONTACTS + " WHERE "+MySQLiteHelper.COLUMN_ID+" = " + id, null);
		
		Log.i("SQL QUERY", "SELECT * FROM " + MySQLiteHelper.TABLE_CONTACTS + " WHERE "+MySQLiteHelper.COLUMN_ID+" = " + id);

		cursor.moveToFirst();
		
		Contact findContact = cursorToContact(cursor);
		cursor.close();

		return findContact;
	}
	
	public void deleteContact(Contact contact) {
	    long id = contact.getId();
	    System.out.println("Comment deleted with id: " + id);
	    db.delete(MySQLiteHelper.TABLE_CONTACTS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	public void deleteThisContact(long id)
	{
		db.delete(MySQLiteHelper.TABLE_CONTACTS, MySQLiteHelper.COLUMN_ID+ " = " + id, null);
	}
	
	  public ArrayList<Contact> getAllContacts() {
		    ArrayList<Contact> contacts = new ArrayList<Contact>();

		    Cursor cursor = db.query(MySQLiteHelper.TABLE_CONTACTS,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Contact contact = cursorToContact(cursor);
		      contacts.add(contact);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return contacts;
		  }	
	  
	  
	private Contact cursorToContact(Cursor cursor){
		Contact contact = new Contact();
		contact.setId(cursor.getLong(0));
		contact.setName(cursor.getString(1));
		contact.setTelephone(cursor.getString(2));
		return contact;
	}
}
