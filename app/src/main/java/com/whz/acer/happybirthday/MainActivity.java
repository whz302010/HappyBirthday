package com.whz.acer.happybirthday;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_text= (TextView) findViewById(R.id.tv_center);
        tv_text.animate().rotation(360).alpha(1).setDuration(1000);
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                Intent parentActivityIntent = NavUtils.getParentActivityIntent(this);

                break;
            default:

                break;
        }
        return super.onOptionsItemSelected(item);

    }
//overflow打开时被执行
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId== Window.FEATURE_ACTION_BAR&&menu!=null){
            if (menu.getClass().getSimpleName().equals("MenuBuiler")){
                try {
                    Method setOptionalIconsVisible = menu.getClass().
                            getDeclaredMethod("setOptionalIconsVisible", Boolean.class);
                    setOptionalIconsVisible.setAccessible(true);
                    setOptionalIconsVisible.invoke(menu,true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
