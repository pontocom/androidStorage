package pt.iscte.daam.androidstorage;

import java.util.ArrayList;

import pt.iscte.daam.androidstorage.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyContactBaseAdapter extends BaseAdapter {
	private static ArrayList<Contact> contactArrayList;
	
	private LayoutInflater mInflater;
	
	public MyContactBaseAdapter(Context context, ArrayList<Contact> contact)
	{
		contactArrayList = contact;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return contactArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return contactArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null)
		{
			convertView = mInflater.inflate(R.layout.custom_row_view, null);
			holder = new ViewHolder();
			holder.txtname = (TextView) convertView.findViewById(R.id.name);
			holder.txtTelephone = (TextView) convertView.findViewById(R.id.telephone);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txtname.setText(contactArrayList.get(position).getName());
		holder.txtTelephone.setText(contactArrayList.get(position).getTelephone());
		
		return convertView;
	}
	
	static class ViewHolder
	{
		TextView txtname;
		TextView txtTelephone;
	}

}
