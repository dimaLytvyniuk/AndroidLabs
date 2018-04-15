package com.example.lytvyniuk.laba_1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView resultTextView;
    EditText editText;
    SeekBar seekBar;
    String color;
    String text;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView4);
        resultTextView = (TextView) findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText2);
        resultTextView.setMovementMethod(new android.text.method.ScrollingMovementMethod());

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
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();

        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.green:
                if (checked){
                    color = "green";
                }
                break;
            case R.id.blue:
                if (checked){
                    color = "blue";
                }
                break;
            case R.id.orange:
                if (checked){
                    color = "orange";
                }
                break;
            case R.id.red:
                if (checked){
                    color = "red";
                }
                break;
        }
    }

    public void onOkClicked(View view) {
        resultTextView.setText(resultTextView.getText() + "Comment: " + editText.getText() + "; Color: " + color + "; Quantity: " + count + "\n");
    }
}
