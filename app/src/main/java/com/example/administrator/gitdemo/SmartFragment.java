package com.example.administrator.gitdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SmartFragment extends Fragment {


    public static SmartFragment newInstance(Bundle args) {
        SmartFragment fragment = new SmartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
