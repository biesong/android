package com.example.yky.util;




import java.util.List;

import org.json.JSONException;


import com.example.yky.entity.NewsList;
import com.example.yky.entity.Title;
import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utility {
	 public static NewsList parseJsonWithGson(final String requestText){
	        Gson gson = new Gson();
	        return gson.fromJson(requestText, NewsList.class);
	    }
	 public static void main(String[] args) {
		  String str="{'code':200,'msg':'success','newslist':[{'title':'fsfsfsf：“史上最大数据窃取案”告破','description':'凤凰社会','picUrl':'http://d.ifengimg.com/w150_h95/p2.ifengimg.com/a/2018_34/a364dd394518867_size176_w550_h381.png'},{'title':'微博“被加粉”的秘密：“史上最大数据窃取案”告破','description':'凤凰社会','picUrl':'http://d.ifengimg.com/w150_h95/p2.ifengimg.com/a/2018_34/a364dd394518867_size176_w550_h381.png'}]}";
          
          // NewsList newList=Utility.parseJsonWithGson(str);
        	JSONObject jo=JSONObject.fromObject(str);
			System.out.println(jo.get("msg"));
			List<Title> list=(List<Title>)JSONArray.toCollection(jo.getJSONArray("newslist"), Title.class);
			for (Title title : list) {
				System.out.println(title.getTitle());
    	 }
	 }
}
