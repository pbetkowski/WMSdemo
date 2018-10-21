package com.example.pbetkows.wms;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pbetkows.wms.adapters.PageAdapter;
import com.example.pbetkows.wms.goodsReceipt.GoodsReceipt;
import com.example.pbetkows.wms.goodsReceipt.GoodsReceiptMainMenu;
import com.example.pbetkows.wms.tests.SampleList;

public class MainActivity extends AppCompatActivity {

    private PageAdapter pageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);

        setupPager(viewPager);
    }

    private void setupPager(ViewPager viewPager) {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(), "Login");
        adapter.addFragment(new MainMenuFragment(), "MainMenu");
        adapter.addFragment(new GoodsReceiptMainMenu(), "GoodsReceiptMenu");
        adapter.addFragment(new SampleList(), "Sample");
        adapter.addFragment(new GoodsReceipt(), "GoodsReceiptN");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }
}
