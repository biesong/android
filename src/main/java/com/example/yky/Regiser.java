package com.example.yky;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.example.yky.dao.HttpCallbackListener;
import com.example.yky.util.HttpUtil;
import com.example.yky.util.PropertiesUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Regiser extends AppCompatActivity {
	//用于接收Http请求的servlet的URL地址，请自己定义
    private String originAddress =PropertiesUtil.getValueByKey("url")+PropertiesUtil.getValueByKey("regiser");
    //用于处理消息的Handler
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";

            if ("OK".equals(msg.obj.toString())){
                result = "success";
            }else if ("Wrong".equals(msg.obj.toString())){
                result = "fail";
            }else {
                result = msg.obj.toString();
            }
            //Toast.makeText(Regiser.this, result, Toast.LENGTH_SHORT).show();
        }
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regiser);
	}
	 
	public void click(View view){
		String name=((EditText)findViewById(R.id.name)).getText().toString();
		String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();
		String mail=((EditText)findViewById(R.id.mail)).getText().toString();
		
		//写入数据库
		 HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		params.put("pwd", pwd);
		params.put("mail", mail);
		try {
			//构造完整URL
            String compeletedURL = HttpUtil.getURLWithParams(originAddress, params);
            //发送请求
            HttpUtil.sendHttpRequest(compeletedURL, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.obj = response;
                    mHandler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Message message = new Message();
                    message.obj = e.toString();
                    mHandler.sendMessage(message);
                }
            });
		} catch (Exception e) {
			// TODO: handle exception
		}
		requestAllPower();
		
         try {  
        	 //EmployeeDao.add(name, mail, pwd); 
        	 FileOutputStream fos=openFileOutput("aa.txt", MODE_APPEND);
             fos.write((name+"-"+mail+"|").getBytes());  
             fos.close(); 
             FileInputStream inStream = this.openFileInput("aa.txt");
             
             byte[] buffer = new byte[1024];
             int hasRead = 0;
             StringBuilder sb = new StringBuilder();
             while ((hasRead = inStream.read(buffer)) != -1) {
                 sb.append(new String(buffer, 0, hasRead));
             }

             inStream.close();
             Toast.makeText(Regiser.this,  
                     "注册成功账户"+name,   
                     Toast.LENGTH_LONG).show();  
             Intent intent=new Intent(Regiser.this,MainActivity.class);
 			startActivity(intent);
         } catch (Exception e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
             Toast.makeText(Regiser.this,   
                     "写入文件失败"+e.getMessage(),   
                     Toast.LENGTH_LONG).show();  
         }  
		
         
		//Toast.makeText(Regiser.this,"注册成功"+name,Toast.LENGTH_SHORT).show();
	}
	public void cancel(View view){
		Intent intent=new Intent(Regiser.this,MainActivity.class);
			startActivity(intent);
	}
	public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                
                
            }
        }
    }
}
