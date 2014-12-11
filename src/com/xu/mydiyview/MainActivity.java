package com.xu.mydiyview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jorgecastilloprz.pagedheadlistview.utils.PageTransformerTypes;
import com.xu.mydiyview.MyExtendsView.OnDeleteListener;
import com.xu.mydiyview.listView.MyAdapter;
import com.xu.mydiyview.listView.MyListView;

public class MainActivity extends Activity {

	private List<String> contentList=new ArrayList<String>();
	//private MyExtendsView myList;
	private MyListView myListView;
	private MyAdapter myAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_main);
		
		myListView=(MyListView)findViewById(R.id.mylistview);
		myAdapter=new MyAdapter(this,0,contentList);
		initArryList();
		myListView.setAdapter(myAdapter);
	}
	
	
	//github 同步测试
	
		
		
//		MyExtendsView myList=new MyExtendsView(this, null);
//		initArryList();
//		myList=(MyExtendsView) findViewById(R.id.my_list_view);
//		myAdapter=new MyAdapter(this, 0, contentList);
//		myList.setOnDeleteListener(new OnDeleteListener() {
//			
//			@Override
//			public void onDelete(int index) {
//				contentList.remove(index);
//				myAdapter.notifyDataSetChanged();
//			}
//		});
//		myList.setAdapter(myAdapter);
//	}

		
		

	private void initArryList() {
			
	        for(int i=0;i<30;i++)
	        	contentList.add("Content Item "+i);  
		
	}
//
//	class MyAdapter extends ArrayAdapter<String>{
//
//		public MyAdapter(Context context, int resource,List<String> objects) {
//			super(context, resource, objects);
//			
//		}
//
//		/*
//		 * @param convertView 超出屏幕显示后变成一个待重用的ListItemView
//		 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
//		 */
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			
//			Log.i("getView", "Im position "+position);
//			
//			if(convertView==null){
//				
//				convertView=LayoutInflater.from(getContext()).inflate(R.layout.mylist_layout, null);
//				//Log.i("getView", "new");
//			}else{
//				Log.i("convertView.hashCode", convertView.hashCode()+"");	
////				view=convertView;
//				ViewGroup vp=(ViewGroup)convertView;
//				LinearLayout deleteButton=(LinearLayout)vp.getChildAt(1);
//				if(convertView.getTag()!=null&&deleteButton!=null){
//					Log.i("getView", "convertView.tag is not null and tag is "+ convertView.getTag()+"");
//				
//					//	Button deleteButton=(Button)vp.getChildAt(0);
//					//=(Button)(vp.findViewWithTag(position).findViewWithTag(position));
//					//
//					//LinearLayout deleteButton=(LinearLayout) vp.findViewWithTag(position);
//					
////				Log.i(" deleteButton.toString()",deleteButton.toString());
//					if((Integer)(convertView.getTag())==position){
//						Log.i("button set visible", "gonna set it visible");
//						if(deleteButton.getVisibility()==View.INVISIBLE){
//						
//							deleteButton.setVisibility(View.VISIBLE);
//							Log.i("button set visible", "set it visible");
//						}
//					}else{
//						if(deleteButton.getVisibility()==View.VISIBLE){
//							
//							deleteButton.setVisibility(View.INVISIBLE);
//							Log.i("button set visible", "set it gone");
//						}
//					}
//				}
//				//Log.i("getView", "reuse");
//			}
//			
//			TextView tv=(TextView) convertView.findViewById(R.id.text_view);
//			tv.setText(getItem(position));
//			
//			return convertView;
//		}
//
//	}	

}


		

	