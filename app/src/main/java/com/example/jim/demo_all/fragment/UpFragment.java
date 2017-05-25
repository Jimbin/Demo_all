package com.example.jim.demo_all.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jim.demo_all.R;

/**
 * Created by Jim斌 on 2017/2/11.
 */

public class UpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.up_fragment,container,false);
        return view;
    }
}
