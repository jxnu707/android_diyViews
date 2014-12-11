package com.xu.mydiyview.listView;

import com.xu.mydiyview.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnTouchListener;

public class MyListView extends ListView implements OnTouchListener,OnGestureListener {
	
	private final String TAG="MyListView";

	private View deleteButtonView;
	private GestureDetector gd;
	private Button deleteButton;
	private boolean isShown=false;
	
	public static int POSITION=-1;
	
	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gd=new GestureDetector(context,this);
		this.setOnTouchListener(this);
	}
	

	@Override
	public boolean onDown(MotionEvent e) {
		Log.i(TAG, "onDown");
		if(!isShown){
		POSITION=pointToPosition((int)e.getX(), (int)e.getY());
		return true;
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.i(TAG, "onFling");
		deleteButtonView=this.getChildAt(POSITION-getFirstVisiblePosition());
		deleteButton=(Button) deleteButtonView.findViewById(R.id.deleteButton);
			if(Math.abs(e2.getX()-e1.getX())>25.0&&!isShown){
				deleteButton.setVisibility(View.VISIBLE);
				isShown=true;
			}
		return false;
	}


	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.i(TAG, "onTouch");
	
		Log.i(TAG, isShown+"");
		if(isShown){
			deleteButton.setVisibility(View.INVISIBLE);
			deleteButton=null;
			isShown=false;
			return false;
		}else{
			return gd.onTouchEvent(event);
		}
		
	}

}
