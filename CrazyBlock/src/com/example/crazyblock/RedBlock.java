package com.example.crazyblock;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RedBlock extends View {

	Paint rblock = null;  
	private int lastX,lastY,i = 0,t = 0;
	Context ct;
	BlueBlock bb;
	private BlockThread bt;
	MainActivity activity;
	public int x,y,rawx,rawy;

	public RedBlock(Context context) {
		super(context);
		ct = context;
		activity = (MainActivity) context;
	}

	public RedBlock(Context context, AttributeSet attrs) {
		super(context, attrs);
		ct = context;
		activity = (MainActivity) context;
	}

	public RedBlock(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		ct = context;
		activity = (MainActivity) context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		rblock = new Paint();
		rblock.setColor(Color.RED);
		canvas.drawRect(500, 600, 650, 750, rblock);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 获取到手指处的横坐标和纵坐标
		x = (int) event.getX();
		y = (int) event.getY();
		rawx = (int) event.getRawX();
		rawy = (int) event.getRawY()-300;
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if(t == 0){
					//设置点击开始线程				
					t = 1;
					bb = activity.bb;
					bt = new BlockThread(t, bb);
					bt.start();
					activity.starttime = System.currentTimeMillis();	
				}
				lastX = x;
				lastY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				// 计算移动的距离
				int offX = x - lastX;
				int offY = y - lastY;

				activity.overtime = System.currentTimeMillis();
				if(getLeft() < -440 || getTop() < -540 || getRight() > 1470 || getBottom() > 2250){
					if(i == 0){
						Thread.currentThread().interrupt();
						Dialog alertDialog = new AlertDialog.Builder(ct). 
				                setTitle("抱歉"). 
				                setMessage("Game Over!"). 
				                setIcon(R.drawable.ic_launcher). 
				                setPositiveButton("确定", new DialogInterface.OnClickListener() { 
				                     
				                    @Override 
				                    public void onClick(DialogInterface dialog, int which) { 
				                    	Intent intent = new Intent();
				                    	intent.setClass(ct, OverActivity.class);
				                    	intent.putExtra("time", activity.overtime - activity.starttime);
				                    	ct.startActivity(intent);
				                    } 
				                }).
				                create(); 
				        alertDialog.show();
				        i = 1; 
				        return false;
					}
				}
				
				// 调用layout方法来重新放置它的位置
				layout(getLeft() + offX, getTop() + offY, getRight() + offX,
						getBottom() + offY);
				break;
		}
		return true;
	}

}
