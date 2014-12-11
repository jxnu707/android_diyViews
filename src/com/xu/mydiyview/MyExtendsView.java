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

/*�̳пؼ�
 * �̳�һ���ؼ� �ڱ���ԭ�пؼ�����ǰ���� ��չ�书��
 * ��LIstView��ĳ����Ŀ�ϻ��� ����ɾ����ť����ɾ��
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
		if(isButtonShown){//��������Ļʱɾ��button����ʾ ��ɾ��
			itemList.removeView(deleteButton);
			deleteButton=null;
			isButtonShown=false;
			return false;
		}
		else{//û����ʾ �򽻸�gestureDdtector.onTouchEvent��ʶ������ ��������
			return gestureDetector.onTouchEvent(event);
		}
	}
	
	
	@Override
	public boolean onDown(MotionEvent e) {
		if(!isButtonShown){
			//���������õ��λ������listview����һ�� �����ָ�������item�����У�����û����Ļ����ʾ�ģ�item�еĵڼ��� ������λ��
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
		if(!isButtonShown&&(Math.abs(velocityX)>Math.abs(velocityY))){//�����ťû��ʾ ���� ���������һ���
			deleteButton=LayoutInflater.from(getContext()).inflate(R.layout.delete_button,null);
			//deleteButton��R.layout.delete_button��ʵ�� ��һ��layout ֱ�������������onClick����Ч��
			//�ҵ�deleteButton���button ���onClick
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
			//����������ĿViewת����Viewgroup ��Ϊ������׼�����deleteButton getChildAt��position�� positionָ��������Ļ���λ�� �����λ��
 			itemList=(ViewGroup) this.getChildAt(itemPosition 
                    - getFirstVisiblePosition());
			//���һ�����ֲ���params��width��height��
			RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//		    params.setLayoutDirection(LAYOUT_DIRECTION_LTR);
			//�趨��RelativeLayout�з����ұ��м�
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
