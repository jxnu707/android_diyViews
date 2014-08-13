package com.xu.mydiyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

/*
 * �Ի�ʦͽ
 * һ������view
 * mCount:��������
 * mBountds:�߽��С
 * mPaint������
 * canvas������
 */
public class MyView extends View implements OnClickListener {
	
	private int mCount;
	private Rect mBounds;
	private Paint mPaint;


	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnClickListener(this);
		//����ݻ���
		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mCount=0;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setColor(Color.RED);
		mBounds=new Rect();
		//���������ָ�������view���ڲ����� 0 0 �������View�����Ͻ�
		canvas.drawRect(0,0,this.getWidth(),this.getHeight(),mPaint);//����view
		mPaint.setColor(Color.BLACK);
		String text=String.valueOf(mCount);
		mPaint.setTextSize(40);
		//���һ�����������text����С����
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textwidth=mBounds.width();
		float textheigth=mBounds.height();
		//x yָ�������text�ľ��ε���߿���ϱ߿�view�ľ��� �ڲ�����
		canvas.drawText(text,((float)this.getWidth()-textwidth)/2 ,((float)this.getHeight()+textheigth)/2  ,mPaint);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCount++;
		//�ػ�
		this.invalidate();
	}

	
	
}
