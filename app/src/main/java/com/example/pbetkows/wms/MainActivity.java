package com.example.pbetkows.wms;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.pbetkows.wms.utils.MessageBox;

public class MainActivity extends AppCompatActivity {

    //private PageAdapter pageAdapter;
    //private ViewPager viewPager;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new LoginFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},
                MY_PERMISSIONS_REQUEST_CAMERA);


        //pageAdapter = new PageAdapter(getSupportFragmentManager());

        //viewPager = findViewById(R.id.container);

        // setupPager(viewPager);
    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainMenuFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

//        if (count == 0) {
//            super.onBackPressed();
//            Log.d("TAG", "onBackPressed: 2");
//        } else {
//            getSupportFragmentManager().popBackStack();
//            Log.d("TAG", "onBackPressed: 3");
//        }
    }

    @Override
    public void recreate() {
        super.recreate();
        MessageBox.show(getApplicationContext(), "Recreate");
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new MainMenuFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1: {
                MessageBox.show(getApplicationContext(), "Not implemented yet");
            }
            case R.id.item2: {
                MessageBox.show(getApplicationContext(), "Not implemented yet");
            }
        }
        return super.onOptionsItemSelected(item);
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
