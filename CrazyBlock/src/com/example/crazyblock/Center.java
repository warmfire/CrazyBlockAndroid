package com.example.crazyblock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Center extends View{
	
	Paint paint = null;

	public Center(Context context) {
		super(context);
		paint= new Paint();
	}
	
	public Center(Context context, AttributeSet attrs){  
        super(context,attrs);  
    }  
  
    public Center(Context context,AttributeSet attrs,int defStyle){  
        super(context,attrs,defStyle);  
    }
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.GRAY);
		
	}

}
