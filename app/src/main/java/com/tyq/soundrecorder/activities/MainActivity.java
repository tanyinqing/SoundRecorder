package com.tyq.soundrecorder.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import com.lidroid.xutils.view.annotation.ContentView;
import com.tyq.soundrecorder.R;
import com.tyq.soundrecorder.fragments.FileViewerFragment;
import com.tyq.soundrecorder.fragments.RecordFragment;

import frameworkandroid.tan.com.bottomframework.activity.TitleBarActivity;

@ContentView(R.layout.activity_main)
public class MainActivity extends TitleBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;


    @Override
    protected void setListeners() {

    }

    @Override
    protected void initDatas() {
        setTitle("主页面");
        setButtonRight("", R.drawable.image_search);
        setButtonLeft(R.drawable.icon_back);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivityByClass(SplashActivity.class);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = {getString(R.string.tab_title_record),
                getString(R.string.tab_title_saved_recordings)};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //        导航条的选中事件
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return RecordFragment.newInstance(position);
                }
                case 1: {
                    return FileViewerFragment.newInstance(position);
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    public MainActivity() {
    }

    @Override
    protected void RightButtonClicked() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    @Override
    protected void LeftButtonClicked() {
        startActivityByClass(SplashActivity.class);
    }
}
