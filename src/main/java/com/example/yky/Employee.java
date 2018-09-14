package com.example.yky;

import java.io.FileInputStream;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Employee extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.employee);
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream inStream = this.openFileInput("aa.txt");

			byte[] buffer = new byte[1024];
			int hasRead = 0;
			while ((hasRead = inStream.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, hasRead));
			}

			inStream.close();
			
		} catch (Exception e) {

		}
		String[] data = sb.toString().split("\\|");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Employee.this, android.R.layout.simple_list_item_1,
				data);
		ListView listView = (ListView) findViewById(R.id.userlist);
		
		listView.setAdapter(adapter);
	}

}
