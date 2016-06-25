package com.example.crazyblock;

import android.util.Log;

//�����߳���
public class BlockThread extends Thread{

	private int flag = 0;//�ж��Ƿ������߳�
	private BlueBlock bb;//��ȡIndexSence�����е�4����
	
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
