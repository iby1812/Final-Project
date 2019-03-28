package com.cinema.cintix.home_screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.cintix.R;

import androidx.fragment.app.Fragment;

public class QuickOrder extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
          return inflater.inflate(R.layout.quick_order, container, false);
    }
}

