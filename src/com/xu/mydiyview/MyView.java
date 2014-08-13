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
 * 自绘师徒
 * 一个计数view
 * mCount:计数次数
 * mBountds:边界大小
 * mPaint：画笔
 * canvas：画布
 */
public class MyView extends View implements OnClickListener {
	
	private int mCount;
	private Rect mBounds;
	private Paint mPaint;


	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnClickListener(this);
		//抗锯齿画笔
		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mCount=0;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setColor(Color.RED);
		mBounds=new Rect();
		//这里的坐标指的是这个view的内部坐标 0 0 就是这个View的左上角
		canvas.drawRect(0,0,this.getWidth(),this.getHeight(),mPaint);//整个view
		mPaint.setColor(Color.BLACK);
		String text=String.valueOf(mCount);
		mPaint.setTextSize(40);
		//获得一个能容纳这个text的最小矩形
		mPaint.getTextBounds(text, 0, text.length(), mBounds);
		float textwidth=mBounds.width();
		float textheigth=mBounds.height();
		//x y指这个容纳text的矩形的左边框和上边框到view的距离 内部坐标
		canvas.drawText(text,((float)this.getWidth()-textwidth)/2 ,((float)this.getHeight()+textheigth)/2  ,mPaint);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCount++;
		//重绘
		this.invalidate();
	}

	
	
}
