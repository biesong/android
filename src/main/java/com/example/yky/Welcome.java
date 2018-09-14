package com.example.yky;


import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class Welcome extends FragmentActivity implements OnClickListener {
	    //声明ViewPager
	    private ViewPager mViewPager;
	    //适配器
	    private FragmentPagerAdapter mAdapter;
	    //装载Fragment的集合
	    private List<Fragment> mFragments;

	    //四个Tab对应的布局
	    private LinearLayout mTabLogin;
	    private LinearLayout mTabReg;
	    private LinearLayout mTabUser;

	    //四个Tab对应的ImageButton
	    private Button mLogin;
	    private Button mReg;
	    private Button mUser;
	   

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.welcome);
	        initViews();//初始化控件
	        initEvents();//初始化事件
	        initDatas();//初始化数据
	    }

	    private void initDatas() {
	        mFragments = new ArrayList<Fragment>();
	        //将四个Fragment加入集合中
	        mFragments.add(new LoginFragment());
	        mFragments.add(new UserFragment());
	        mFragments.add(new RegFragment());

	        //初始化适配器
	        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
	            @Override
	            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
	                return mFragments.get(position);
	            }

	            @Override
	            public int getCount() {//获取集合中Fragment的总数
	                return mFragments.size();
	            }

	        };
	        //不要忘记设置ViewPager的适配器
	        mViewPager.setAdapter(mAdapter);
	        //设置ViewPager的切换监听
	        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
				
				public void onPageSelected(int position) {
					// TODO Auto-generated method stub
					//设置position对应的集合中的Fragment
	                mViewPager.setCurrentItem(position);
	                resetImgs();
	                selectTab(position);
				}
				
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}
				
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}
			});
	    }

	    private void initEvents() {
	        //设置四个Tab的点击事件
	        mTabLogin.setOnClickListener(this);
	        mTabUser.setOnClickListener(this);
	        mTabReg.setOnClickListener(this);

	    }

	    //初始化控件
	    private void initViews() {
	        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

	        mTabLogin = (LinearLayout) findViewById(R.id.id_tab_login);
	        mTabUser = (LinearLayout) findViewById(R.id.id_tab_user);
	        mTabReg = (LinearLayout) findViewById(R.id.id_tab_reg);

	        mLogin = (Button) findViewById(R.id.btnLoginText);
	        mUser = (Button) findViewById(R.id.btnUser);
	        mReg = (Button) findViewById(R.id.btnRegiser);

	    }

	

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			//先将四个ImageButton置为灰色
	        resetImgs();
	        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
	        switch (v.getId()) {
	            case R.id.id_tab_login:
	                selectTab(0);
	                break;
	            case R.id.id_tab_user:
	                selectTab(1);
	                break;
	            case R.id.id_tab_reg:
	                selectTab(2);
	                break;
	            
	        }
		}



	    private void selectTab(int i) {
	        //根据点击的Tab设置对应的ImageButton为绿色
	    	 switch (i) {
	            case 0:
	            	mLogin.setBackgroundColor(Color.parseColor("#696969"));
	                break;
	            case 1:
	            	mUser.setBackgroundColor(Color.parseColor("#696969"));
	                break;
	            case 2:
	            	mReg.setBackgroundColor(Color.parseColor("#696969"));
	                break;
	         
	        }
	        mViewPager.setCurrentItem(i);
	    }
	    private void resetImgs(){
	    	mLogin.setBackgroundColor(Color.parseColor("#ffffff"));
	    	mUser.setBackgroundColor(Color.parseColor("#ffffff"));
	    	mReg.setBackgroundColor(Color.parseColor("#ffffff"));
	    }
	
    
}
