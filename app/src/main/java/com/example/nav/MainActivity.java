package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //XTabLayout的操作
    XTabLayout tab;
    ViewPager2 pager2;
    List<Fragment> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //XTabLayout的操作

        //1.找到XTableLayout
        tab = findViewById(R.id.tab);

        //2.动态添加内容(财经，军事，科技，视频，体育)
        //a.实例化导航块
        XTabLayout.Tab t1 = tab.newTab();

        //b.设置名字
        t1.setText("财经");
        //c.添加工作
        tab.addTab(t1);

        tab.addTab(tab.newTab().setText("军事"));
        tab.addTab(tab.newTab().setText("科技"));
        tab.addTab(tab.newTab().setText("视频"));
        tab.addTab(tab.newTab().setText("体育"));


        //3.设置切换效果
        tab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            /**
             * tab被选中时触发的方法
             * @param tab
             */
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                //获取当前导航块的位置和文本
                int position = tab.getPosition();
                String text = tab.getText().toString();
                Toast.makeText(getApplicationContext(),position+"------"+text,Toast.LENGTH_SHORT).show();
                pager2.setCurrentItem(position);

            }

            /**
             * tab没被选中时触发的方法
             * @param tab
             */
            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            /**
             * tab重新被选中时触发的方法
             * @param tab
             */
            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }


        });

        pager2 = findViewById(R.id.pagers);
        //添加Fragement
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        list.add(new Fragment5());

        pager2.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),getLifecycle()) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tab.getTabAt(position).select();//设置导航块被选中
            }
        });


    }
}