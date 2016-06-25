package com.example.crazyblock;

import android.util.Log;

//控制线程类
public class BlockThread extends Thread{

	private int flag = 0;//判断是否启动线程
	private BlueBlock bb;//获取IndexSence场景中的4个块
	
	public BlockThread(int flag, BlueBlock bb)
	{
		this.flag = flag;
		this.bb = bb;
	}
	
	@Override
	public void run() {
		try
		{
			while (flag == 1) 
			{
				bb.b1.MovingTrack();
				bb.b2.MovingTrack();
				bb.b3.MovingTrack();
				bb.b4.MovingTrack();
				Thread.sleep(10);
				bb.postInvalidate();
			}
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
}
