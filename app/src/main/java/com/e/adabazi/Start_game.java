package com.e.adabazi;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

import com.e.adabazi.R;

import java.util.HashMap;

public class Start_game extends Activity {
    HashMap hashMap=new HashMap();
    SeekBar seekBar;
    TextView textView;
    NumberPicker numberPicker;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        numberPicker=findViewById(R.id.numberPicker);
        textView=findViewById(R.id.seekBar_number_txt);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(6);
        numberPicker.setWrapSelectorWheel(false);
        seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hashMap.put("number_of_seekbar",progress );
                textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hashMap.put("number_of_numberpicker",newVal);
            }
        });
    }
    public void welcome_start(View v) {
        if(hashMap.containsKey("number_of_seekbar")&&hashMap.containsKey("number_of_numberpicker")) {
            Intent intent = new Intent(this, GroupList.class);
            intent.putExtra("seekbar_number", Integer.parseInt(hashMap.get("number_of_seekbar").toString()));
            intent.putExtra("numberpicker_number", Integer.parseInt(hashMap.get("number_of_numberpicker").toString()));
            startActivity(intent);
        }
    }
}
