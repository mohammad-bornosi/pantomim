package com.e.adabazi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Contest_of_game extends Activity{
    int point_is_choose;
    String question;
    Intent intent1;
    Intent bargasht;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_of_game);
        intent1 = new Intent(Contest_of_game.this, executive_section.class);
        bargasht=getIntent();


    }

    public void onClick1 (View v) {
            LinearLayout linearLayout = findViewById(R.id.lenearlayout_contains_of_point);
            if(v.getId()==R.id.btn_animal)
                question="animal";
            else if(v.getId()==R.id.btn_celebrities)
                question="celebrities";
            else if(v.getId()==R.id.btn_food)
                question="food";
            else if(v.getId()==R.id.btn_fruit)
                question="fruit";
            else if(v.getId()==R.id.btn_job)
                question="job";
            else if(v.getId()==R.id.btn_objects)
                question="object";
            else if(v.getId()==R.id.btn_omomi)
                question="omomi";
            else if(v.getId()==R.id.btn_place)
                question="place";
            else if(v.getId()==R.id.sport)
                question="sport";
            intent1.putExtra("category",question);
        linearLayout.setVisibility(View.VISIBLE );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 11) {
            if(resultCode == Activity.RESULT_OK) {
                bargasht.putExtra("score", data.getIntExtra("score", -5));
                setResult(Activity.RESULT_OK, bargasht);
                finish();
            }
        }

    }
    public void point_of_kalamat(View v){
        if(v.getId()==R.id.point_2)
            point_is_choose=2;
        else if(v.getId()==R.id.point_4)
            point_is_choose=4;
        else if(v.getId()==R.id.point_6)
            point_is_choose=6;
        intent1.putExtra("on_off",1);
        intent1.putExtra("level",point_is_choose);
        startActivityForResult(intent1,11);
    }
    public void point_of_proverb(View v){
        if(v.getId()==R.id.btn_proverb_3)
            point_is_choose=3;
        else if (v.getId()==R.id.btn_proverb_5)
            point_is_choose=5;
        else if(v.getId()==R.id.btn_proverb_7)
            point_is_choose=7;
        intent1.putExtra("level",point_is_choose);
        startActivityForResult(intent1,11);
    }
}

