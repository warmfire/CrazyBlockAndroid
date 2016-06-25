package com.example.crazyblock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bmob.Grades;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.listener.FindListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ResultActivity extends ListActivity {
	
	List<Map<String, Object>> list;
	int lv;
	String getusername,getgrades;
	Button btn_radd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		Intent it = getIntent();
		getusername = it.getStringExtra("username");
		getgrades = it.getStringExtra("grades");

		BmobQuary();
		btn_radd = (Button) findViewById(R.id.btn_radd);
		btn_radd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Intent intent = new Intent();
		        intent.setClass(ResultActivity.this, MainActivity.class);
		        startActivity(intent);
			}
		});
		Search();
	}
	
	public void init(){
		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, queryData(), 
				R.layout.listitem, new String[] {"lv","username", "grades"}, 
				new int[] {R.id.lv, R.id.username, R.id.grades});
		setListAdapter(adapter);
	}
	
	public List<Map<String, Object>> queryData(){
        Log.d("warmfire", list.size()+"2");
		return list;		
	}
	
	public void BmobQuary(){
		list = new ArrayList<Map<String, Object>>();
		BmobQuery<Grades> query = new BmobQuery<Grades>(); 
		query.setLimit(10);
		query.order("-grades");
		query.findObjects(ResultActivity.this, new FindListener<Grades>() {
		        @Override
		        public void onSuccess(List<Grades> object) {
	            	lv = 1;
		            for (Grades gameScore : object) {
		               	String username = gameScore.getUsername();
		               	float grades = gameScore.getGrades();
		               	Map<String, Object> map = new HashMap<String, Object>();
		               	map.put("lv", "第 "+lv+" 名");
		        		map.put("username", username);
		        		map.put("grades", grades+" 秒");
		        		list.add(map);
		        		lv++;
		            }
		            Log.d("warmfire", list.size()+"1");
		        }
		        @Override
		        public void onError(int code, String msg) {
		            Toast.makeText(ResultActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
	        		return;
		        }
		});
	}
	
	public void Search(){
		list = new ArrayList<Map<String, Object>>();
		BmobQuery<Grades> query = new BmobQuery<Grades>(); 
		query.order("-grades");
		query.findObjects(ResultActivity.this, new FindListener<Grades>() {
		        @Override
		        public void onSuccess(List<Grades> object) {
	            	lv = 1;
		            for (Grades gameScore : object) {
		               	String username = gameScore.getUsername();
		               	String grades = String.valueOf(gameScore.getGrades());
		               	if(username.equals(getusername)&&grades.equals(getgrades)){
		               		Dialog alertDialog = new AlertDialog.Builder(ResultActivity.this). 
		                            setTitle("排名结果"). 
		                            setMessage(getusername + "，您的成绩是：" + getgrades + "秒，位于第"+lv+"名，继续加油哦！"). 
		                            setIcon(R.drawable.ic_launcher). 
		                            setPositiveButton("确定", new DialogInterface.OnClickListener() { 
		                                 
		                                @Override 
		                                public void onClick(DialogInterface dialog, int which) { 

		                            		init();
		                                } 
		                            }).
		                            create(); 
		                    alertDialog.show();
		               	};
		        		lv++;
		            }
		        }
		        @Override
		        public void onError(int code, String msg) {
		            Toast.makeText(ResultActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
	        		return;
		        }
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_refresh) {
			init();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
