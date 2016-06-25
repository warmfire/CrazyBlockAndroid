package com.example.crazyblock;

import cn.bmob.v3.Bmob;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	RedBlock rb;
	BlueBlock bb;
	long starttime,overtime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bmob.initialize(this, "119f76347aa0971412f24fd7b7177e2f");
		rb = (RedBlock) findViewById(R.id.rb);
		bb = (BlueBlock) findViewById(R.id.bb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			 Dialog alertDialog = new AlertDialog.Builder(this). 
		                setTitle("游戏简介"). 
		                setMessage("拖动红块，躲避蓝块，不允许碰到边框，看你能坚持多久"). 
		                setIcon(R.drawable.ic_launcher). 
		                setPositiveButton("了解", new DialogInterface.OnClickListener() { 
		                     
		                    @Override 
		                    public void onClick(DialogInterface dialog, int which) { 
		                        // TODO Auto-generated method stub  
		                    } 
		                }).
		                create(); 
		        alertDialog.show(); 
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
