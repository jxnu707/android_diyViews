package com.xu.mydiyview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View.OnTouchListener;

/*继承控件
 * 继承一个控件 在保持原有控件方法前提下 扩展其功能
 * 在LIstView的某个条目上滑动 呼出删除按钮进行删除
 * 
 */
public class MyExtendsView extends ListView implements OnTouchListener,OnGestureListener{

	private boolean isButtonShown;
	private ViewGroup itemList;
	private int itemPosition;
	private GestureDetector gestureDetector;  
	private View deleteButton;
	
	private OnDeleteListener listener;
	
	public interface OnDeleteListener{
		void onDelete(int index);
	}
	
	public MyExtendsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		gestureDetector=new GestureDetector(context, this);
		this.setOnTouchListener(this);
	}

	public void setOnDeleteListener(OnDeleteListener onDeleteListener){
		this.listener=onDeleteListener;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(isButtonShown){//如果点击屏幕时删除button在显示 则删除
			itemList.removeView(deleteButton);
			deleteButton=null;
			isButtonShown=false;
			return false;
		}
		else{//没有显示 则交给gestureDdtector.onTouchEvent来识别手势 并做处理
			return gestureDetector.onTouchEvent(event);
		}
	}
	
	
	@Override
	public boolean onDown(MotionEvent e) {
		if(!isButtonShown){
			//根据坐标获得点击位置属于listview的哪一行 这个行指的是这个item在所有（包括没在屏幕上显示的）item中的第几行 即绝对位置
			itemPosition=this.pointToPosition((int)e.getX(), (int)e.getY());
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
		if(!isButtonShown&&(Math.abs(velocityX)>Math.abs(velocityY))){//如果按钮没显示 并且 手势是左右滑动
			deleteButton=LayoutInflater.from(getContext()).inflate(R.layout.delete_button,null);
			//deleteButton是R.layout.delete_button的实例 是一个layout 直接在它上面添加onClick是无效的
			//找到deleteButton里的button 添加onClick
			Button b_delete=(Button) deleteButton.findViewById(R.id.delete_button);
			//deleteButton.setOnClickListener();
			b_delete.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("MyListView", "button clicked!!");
					itemList.removeView(deleteButton);
					isButtonShown=false;
					deleteButton=null;
					listener.onDelete(itemPosition);
					Log.i("MyListView", "button removed");
				}
			});
			//将滑动的条目View转化成Viewgroup 作为容器来准备添加deleteButton getChildAt（position） position指的是在屏幕里的位置 即相对位置
 			itemList=(ViewGroup) this.getChildAt(itemPosition 
                    - getFirstVisiblePosition());
			//获得一个布局参数params（width，height）
			RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//		    params.setLayoutDirection(LAYOUT_DIRECTION_LTR);
			//设定在RelativeLayout中放在右边中间
		    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		    params.addRule(RelativeLayout.CENTER_IN_PARENT);
		    itemList.addView(deleteButton,params);
		    Log.i("deleteButton.VISIBLE",deleteButton.VISIBLE+"");
		    Log.i("View.VISIBLE.",View.VISIBLE+"");
		    Log.i("deleteButton.toString()",deleteButton.toString());
		    Log.i("listitem count", itemList.getChildCount()+"");
		    for(int i=0;i<itemList.getChildCount();i++)
		    	Log.i("every childview in itemlist", itemList.getChildAt(i).toString());
		    
		    int tag=itemPosition;
		    deleteButton.setTag(tag);
		    itemList.setTag(tag);
		    Log.i("ListView", ""+tag);
		    
		    isButtonShown=true;
		}
		return false;
	}




}
