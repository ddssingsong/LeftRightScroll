package com.jhs.leftrightscroll;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Fragment currentFragment;
    private Tab1Fragment tab1Fragment;
    private Tab2Fragment tab2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        tab1Fragment = new Tab1Fragment();
        tab2Fragment = new Tab2Fragment();

        showFragment(R.id.content, tab1Fragment);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                button1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                button2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                showFragment(R.id.content, tab1Fragment);
                break;

            case R.id.button2:
                button1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                button2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                showFragment(R.id.content, tab2Fragment);
                break;
        }
    }


    //切换Fragment
    public void showFragment(int contentID, Fragment toFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (currentFragment != null) {
            currentFragment.onPause();
            ft.hide(currentFragment);
        }
        String tag = toFragment.getClass().getSimpleName();
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            ft.add(contentID, toFragment, tag);
        } else {
            toFragment.onResume();
            ft.show(toFragment);
        }
        ft.commit();
        currentFragment = toFragment;
    }
}
