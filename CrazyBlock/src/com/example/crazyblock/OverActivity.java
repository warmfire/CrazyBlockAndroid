package com.example.crazyblock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.bmob.Grades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OverActivity extends Activity {
	
	Button btn1,btn2;
	TextView lab2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_over);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		lab2 = (TextView) findViewById(R.id.lab2);
		
		Intent it = getIntent();
		long time = it.getLongExtra("time",0);
		final String str = String.valueOf(time/1000.000);
		lab2.setText(str);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(OverActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final EditText txt = new EditText(OverActivity.this);
				new AlertDialog.Builder(OverActivity.this)  
				.setTitle("请输入")  
				.setIcon(android.R.drawable.ic_dialog_info)  
				.setView(txt)  
				.setPositiveButton("确定", new AlertDialog.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Grades grades = new Grades();
						grades.setUsername(txt.getText().toString());
						grades.setGrades(Float.parseFloat(str));
						grades.save(OverActivity.this, new SaveListener() {
						    @Override
						    public void onSuccess() {
						        // TODO Auto-generated method stub
						        Toast.makeText(OverActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
						        Intent intent = new Intent();
						        intent.putExtra("username", txt.getText().toString());
						        intent.putExtra("grades", str);
						        intent.setClass(OverActivity.this, ResultActivity.class);
						        startActivity(intent);
						    }

						    @Override
						    public void onFailure(int code, String msg) {
						        // TODO Auto-generated method stub
						        Toast.makeText(OverActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
						    }
						});
					}
				})  
				.setNegativeButton("取消", null)  
				.show();
			}
		});
	}
	

}
