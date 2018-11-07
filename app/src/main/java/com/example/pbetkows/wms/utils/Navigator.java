package com.example.pbetkows.wms.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.pbetkows.wms.MainMenuFragment;
import com.example.pbetkows.wms.R;

public class Navigator extends Fragment{

    public static void navigate(FragmentManager fragmentManager, Fragment fragment) {

        if (fragmentManager != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }
}
