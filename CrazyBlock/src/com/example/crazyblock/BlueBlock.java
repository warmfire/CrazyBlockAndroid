package com.example.crazyblock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BlueBlock extends View {
	
	Context ct;
	
	MainActivity activity;
	
	Paint block1 = new Paint();
	Paint block2 = new Paint();
	Paint block3 = new Paint();
	Paint block4 = new Paint();
	
	Block b1,b2,b3,b4;
	
	public BlueBlock(Context context) {
		super(context);
		ct = context;
		activity = (MainActivity) context;
		b1 = new Block(150, 150, 150, 150, 0, block1, ct, "1");
		b2 = new Block(800, 150, 100, 250, 0, block2, ct, "2");
		b3 = new Block(150, 1200, 250, 80, 0, block3, ct, "3");
		b4 = new Block(800, 1200, 150, 100, 0, block4, ct, "4");
	}
	
	public BlueBlock(Context context, AttributeSet attrs){  
        super(context,attrs);  
		ct = context;
		activity = (MainActivity) context;
		b1 = new Block(150, 150, 150, 150, 0, block1, ct, "1");
		b2 = new Block(800, 150, 100, 250, 0, block2, ct, "2");
		b3 = new Block(150, 1200, 250, 80, 0, block3, ct, "3");
		b4 = new Block(800, 1200, 150, 100, 0, block4, ct, "4");
    }  
  
    public BlueBlock(Context context,AttributeSet attrs,int defStyle){  
        super(context,attrs,defStyle); 
		ct = context; 
		activity = (MainActivity) context;
		b1 = new Block(150, 150, 150, 150, 0, block1, ct, "1");
		b2 = new Block(800, 150, 100, 250, 0, block2, ct, "2");
		b3 = new Block(150, 1200, 250, 80, 0, block3, ct, "3");
		b4 = new Block(800, 1200, 150, 100, 0, block4, ct, "4");
    }
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		b1.paint(canvas);
		b2.paint(canvas);
		b3.paint(canvas);
		b4.paint(canvas);
	}

}
