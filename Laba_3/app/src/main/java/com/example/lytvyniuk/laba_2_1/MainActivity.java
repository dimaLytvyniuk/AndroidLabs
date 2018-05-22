package com.example.lytvyniuk.laba_2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ContentFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String text) {
        ResultFragment fragment = (ResultFragment) getFragmentManager().findFragmentById(R.id.resultFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(text);
        }
    }
}
