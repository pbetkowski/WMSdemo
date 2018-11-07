package com.example.pbetkows.wms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import com.example.pbetkows.wms.utils.Navigator;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {

    @BindView(R.id.loginButton) Button loginButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.login_fragment, container, false);

        ButterKnife.bind(this, view);

        loginButton.setOnClickListener(v -> {
            Navigator.navigate(getFragmentManager(), new MainMenuFragment());
        });

        return view;
    }

}
