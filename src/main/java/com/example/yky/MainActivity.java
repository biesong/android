package com.example.yky;

import java.util.Properties;

import com.example.yky.util.PropertiesUtil;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	 private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn=(Button)findViewById(R.id.btnReg);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,Regiser.class);
				startActivity(intent);
			}
		});
	}
    public void success(View v){
    	Intent intent=new Intent(MainActivity.this,Welcome.class);
		startActivity(intent);
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
		
		switch (item.getItemId()) {
		case R.id.action_regiser://注册用户界面
//			Intent intent=new Intent(MainActivity.this,NewActivity.class);
//			startActivity(intent);
			setContentView(R.layout.news);
			webView = (WebView) findViewById(R.id.webView_news);
			 //需要加载的网页的url
			    webView.loadUrl(PropertiesUtil.getValueByKey("url")+"/news.jsp");
			    WebSettings s = webView.getSettings();
			 // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
			    s.setJavaScriptEnabled(true);
			webView.setWebViewClient(new WebViewClient(){
				 public boolean shouldOverrideUrlLoading(WebView view, String url){
			         view.loadUrl(url);
			         return true;
			       }
			});
			
			  webView.setWebViewClient(new WebViewClient() {
			         @Override 
			      public void onReceivedSslError(WebView view,SslErrorHandler handler, SslError error) {
			       //等待证书响应
			         handler.proceed(); 
			      } 
			   });
			break;
		case R.id.action_userlist://用户列表界面
			Intent user=new Intent(MainActivity.this,Employee.class);
			startActivity(user);
			break;
		case R.id.action_employeelist://数据列表界面
//			Uri uri = Uri.parse("http://192.168.1.6:8080/web/index.jsp");
//			Intent employee = new Intent(Intent.ACTION_VIEW, uri);
//			startActivity(employee);
			setContentView(R.layout.server);
			webView = (WebView) findViewById(R.id.webView);
			 //需要加载的网页的url
			    webView.loadUrl(PropertiesUtil.getValueByKey("url")+"/index.jsp");
			    WebSettings settings = webView.getSettings();
			 // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
			    settings.setJavaScriptEnabled(true);
			webView.setWebViewClient(new WebViewClient(){
				 public boolean shouldOverrideUrlLoading(WebView view, String url){
			         view.loadUrl(url);
			         return true;
			       }
			});
			
			  webView.setWebViewClient(new WebViewClient() {
			         @Override 
			      public void onReceivedSslError(WebView view,SslErrorHandler handler, SslError error) {
			       //等待证书响应
			         handler.proceed(); 
			      } 
			   });
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
