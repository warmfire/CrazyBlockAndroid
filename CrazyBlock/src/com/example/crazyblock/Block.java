package com.example.crazyblock;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


//中间4个方块类
public class Block {
	
	Context context;
	private int x, y, width, height;//块的位置、大小
	private int b = 1;//用来判断块所处的位置
	private int v;//块运动的速度，可改变
	private String name;
	private Paint is;//传递Paint参数用
	MainActivity activity;
	RedBlock rb;
	private int cIconX, cIconY, cIconWidth, cIconHeight;
	
	public Block(int x, int y, int width, int height, int v, Paint is, Context context, String name) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.v = v;
		this.is = is;
		this.context = context;
		this.name = name;
	}
	
	//画出矩形
	public void paint(Canvas canvas) {
		is.setColor(Color.BLUE);
		canvas.drawRect(x, y, width+x, height+y, is);
	}
	
	//运动方法
	public void MovingTrack()
	{
		//设置在块运动时就进行检测是否加速
		AdjustSpeed();
		
		//设置块的运动方向
		if(b == 1)
		{
			x += v;
			y += v;
		}
		else if(b == 2)
		{
			x -= v;
			y += v;
		}
		else if(b == 3)
		{
			x -= v;
			y -= v;
		}
		else if(b == 4)
		{
			x += v;
			y -= v;
		}
		
		
		//判断块来自哪里
		if(x >= 1000)//块的x,y在左上角，减去块的宽度使其正好在边界反弹
		{
			if(b == 1)
				b = 2;
			else
				b = 3;
		}
		if(y >= 1450)
		{
			if(b == 2)
				b = 3;
			else
				b = 4;
		}
		if(x <= 0)
		{
			if(b == 3)
				b = 4;
			else
				b = 1;
		}
		if(y <= 0)
		{
			if(b == 4)
				b = 1;
			else
				b = 2;
		}
		
		IsCrash();
	}
	
	//根据时间控制速度
	public void AdjustSpeed()
	{
		v = 5;
	}
	
	//块的碰撞检测
	public void IsCrash()
	{
		activity = (MainActivity) context;
		rb = activity.rb;
		cIconX = rb.rawx;
		cIconY = rb.rawy;
		cIconWidth = cIconX+150;
		cIconHeight = cIconY+150;
		if(((x + width) < cIconX) || (x > cIconWidth) || ((y + height) < cIconY) || (y > cIconHeight))
		{
			
		}
		else{
			Thread.currentThread().interrupt();
//			Log.d("warmfire","1");
//			Builder alertDialog = new AlertDialog.Builder(activity);
//			Log.d("warmfire","2");
//			alertDialog.setTitle("抱歉");
//			Log.d("warmfire","3");
//			alertDialog.setMessage("Game Over!");
//			Log.d("warmfire","4");
//			alertDialog.setIcon(R.drawable.ic_launcher);
//			Log.d("warmfire","5");
//			alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() { 
//	                     
//	                    @Override 
//	                    public void onClick(DialogInterface dialog, int which) { 
	                    	Intent intent = new Intent();
	                    	intent.setClass(activity, OverActivity.class);
	                    	intent.putExtra("time", activity.overtime - activity.starttime);
	                    	activity.startActivity(intent);
//	                    } 
//	                });
//			Log.d("warmfire","6");
//	        alertDialog.show();
//			Log.d("warmfire","7");
		}
	}
}