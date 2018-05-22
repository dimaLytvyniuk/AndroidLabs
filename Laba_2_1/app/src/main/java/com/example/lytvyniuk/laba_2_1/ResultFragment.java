package com.example.lytvyniuk.laba_2_1;

import android.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

public class ResultFragment extends Fragment {
    TextView resultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_content, container, false);
        resultTextView = (TextView) view.findViewById(R.id.resultTextView);
        resultTextView.setMovementMethod(new android.text.method.ScrollingMovementMethod());
        return view;
    }

    public void setText(String item) {
        resultTextView.setText(item);
    }
}

