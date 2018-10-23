package com.example.pbetkows.wms;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //private PageAdapter pageAdapter;
    //private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new LoginFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.contentFragment, fragment);
        transaction.commit();

        //pageAdapter = new PageAdapter(getSupportFragmentManager());

        //viewPager = findViewById(R.id.container);

       // setupPager(viewPager);
    }
// prepared pagerView
//    private void setupPager(ViewPager viewPager) {
//        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
//        adapter.addFragment(new LoginFragment(), "Login");
//        adapter.addFragment(new MainMenuFragment(), "MainMenu");
//        adapter.addFragment(new GoodsReceiptMainMenu(), "GoodsReceiptMenu");
//        adapter.addFragment(new SampleList(), "Sample");
//        adapter.addFragment(new GoodsReceipt(), "GoodsReceiptN");
//        adapter.addFragment(new DataWedgeFragment(), "Data Wedge");
//        viewPager.setAdapter(adapter);
//    }

//    public void setViewPager(int fragmentNumber) {
//        viewPager.setCurrentItem(fragmentNumber);
//    }
}
