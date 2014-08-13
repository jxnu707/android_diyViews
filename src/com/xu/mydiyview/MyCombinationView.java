package com.xu.mydiyview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*组合控件
 * 把一个button和textview组合成一个title控件
 */

public class MyCombinationView extends FrameLayout {

	private Button button_left;
	private TextView title_text;
	

	public MyCombinationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setBackgroundColor(Color.GRAY);
		//填充titlt_layout 
		LayoutInflater inflater=LayoutInflater.from(context);
		inflater.inflate(R.layout.title_layout, this);
		
		button_left=(Button) findViewById(R.id.button_left);
		title_text=(TextView) findViewById(R.id.title_text);
		
		button_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
//				Activity fatherActivity=(Activity)context;
			}
		});
	}


}
