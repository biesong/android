package com.example.yky.dao;

public interface HttpCallbackListener {
	 void onFinish(String response);

	    void onError(Exception e);
}
