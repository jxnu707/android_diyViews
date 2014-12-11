package com.xu.mydiyview.listView;

import java.util.List;

import com.xu.mydiyview.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {

	private final String TAG="MyAdapter";
	
	public MyAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder viewHolder;
		if(convertView==null){
			convertView=LayoutInflater.from(getContext()).inflate(R.layout.mylist_layout,null);
			viewHolder=new ViewHolder();
			viewHolder.b=(Button)convertView.findViewById(R.id.deleteButton);
			viewHolder.tv=(TextView)convertView.findViewById(R.id.text_view);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		if(MyListView.POSITION==position){
			if(viewHolder.b.getVisibility()==View.INVISIBLE){
				viewHolder.b.setVisibility(View.VISIBLE);
				viewHolder.b.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
					//	contentList.remove(position);
						Log.i(TAG, "onclick");
					}
					
				});
			}
//			else
//				viewHolder.b.setVisibility(View.INVISIBLE);
			
		}else{
			if(viewHolder.b.getVisibility()==View.VISIBLE)
				viewHolder.b.setVisibility(View.INVISIBLE);
		}
		
		viewHolder.tv.setText(getItem(position));
		
		return convertView;
	}

	class ViewHolder {
		TextView tv;
		Button b;
	}

}
