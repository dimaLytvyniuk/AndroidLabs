package com.example.lytvyniuk.laba_2_1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    TextView textView;
    SeekBar seekBar;
    String color;
    int count;
    Button okButton;
    RadioGroup radioGroup;
    EditText editText;
    Button readButton;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);



        seekBar = (SeekBar) view.findViewById(R.id.quantitySeekBar);
        textView = (TextView) view.findViewById(R.id.textView4);
        okButton = (Button) view.findViewById(R.id.buttonOk);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup3);
        editText = (EditText) view.findViewById(R.id.editTextMessage);
        readButton = (Button) view.findViewById(R.id.buttonRead);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textView.setText(String.valueOf(progress));
                count = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper sqlHelper = new DatabaseHelper(getActivity());
                SQLiteDatabase db = sqlHelper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_COMMENT, editText.getText().toString());
                cv.put(DatabaseHelper.COLUMN_COUNT, count);
                cv.put(DatabaseHelper.COLUMN_COLOR, color);

                db.insert(DatabaseHelper.TABLE, null, cv);
                db.close();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.green:
                        color = "green";
                        break;
                    case R.id.blue:
                        color = "blue";
                        break;
                    case R.id.orange:
                        color = "orange";
                        break;
                    case R.id.red:
                        color = "red";
                        break;
                }
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(String text);
    }

    @Override
    public void onAttach(Activity mainActivity) {
        super.onAttach(mainActivity);
        if (mainActivity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) mainActivity;
        } else {
            throw new RuntimeException(mainActivity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void updateDetail() {
        //String text = "Comment: " + editText.getText() + "; Color: " + color + "; Quantity: " + count + "\n";
        DatabaseHelper sqlHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        String text = "";

        Cursor query = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE , null);
        if (query.moveToFirst()) {
            text = "";

            do {
                int id = query.getInt(0);
                String dbComment = query.getString(1);
                int dbCount = query.getInt(2);
                String dbColor = query.getString(3);

                text += "Id: " + id + "; Comment: " + dbComment + "; Color: " + dbColor + "; Quantity: " + dbCount + "\n";
            }
            while (query.moveToNext());
        }
        else {
            text = "Database is empty";
        }
        query.close();
        db.close();
        mListener.onFragmentInteraction(text);
    }
}
