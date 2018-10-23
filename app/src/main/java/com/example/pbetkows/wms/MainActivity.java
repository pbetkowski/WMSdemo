package com.example.pbetkows.wms;

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

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
