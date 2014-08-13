package com.xu.mydiyview;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xu.mydiyview.MyExtendsView.OnDeleteListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> contentList=new ArrayList<String>();
	private MyExtendsView myList;
	private MyAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_main);
		
		
		MyExtendsView myList=new MyExtendsView(this, null);
		initArryList();
		myList=(MyExtendsView) findViewById(R.id.my_list_view);
		myAdapter=new MyAdapter(this, 0, contentList);
		myList.setOnDeleteListener(new OnDeleteListener() {
			
			@Override
			public void onDelete(int index) {
				contentList.remove(index);
				myAdapter.notifyDataSetChanged();
			}
		});
		myList.setAdapter(myAdapter);
	}


	private void initArryList() {
			
	        for(int i=0;i<50;i++)
	        	contentList.add("Content Item "+(i+1));  
		
	}

	class MyAdapter extends ArrayAdapter<String>{

		public MyAdapter(Context context, int resource,List<String> objects) {
			super(context, resource, objects);
			
		}

		/*
		 * @param convertView 超出屏幕显示后变成一个待重用的ListItemView
		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if(convertView==null){
				
				view=LayoutInflater.from(getContext()).inflate(R.layout.mylist_layout, null);
			}else{
				view=convertView;
				
			}
//			view.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					System.out.println("activity clicked");
//				}
//			});

			TextView tv=(TextView) view.findViewById(R.id.text_view);
			tv.setText(getItem(position));
			
			return view;
		}

	}	
}


		

	