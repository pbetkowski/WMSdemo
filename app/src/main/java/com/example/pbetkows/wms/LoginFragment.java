package com.example.pbetkows.wms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import com.example.pbetkows.wms.utils.MessageBox;


public class LoginFragment extends Fragment {

    Button loginButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.login_fragment, container, false);


        loginButton = view.findViewById(R.id.loginButton);


        loginButton.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.loginPanel, new MainMenuFragment()).commit();
        });


        return view;
    }
}
