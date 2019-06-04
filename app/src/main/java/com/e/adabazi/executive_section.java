package com.e.adabazi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class executive_section extends AppCompatActivity{
    Button correctanswer;
    Button DoNotAnswer;
    Button changequestion;
    Button start,end;
    ProgressBar progressBar;
    DatabaseActivity dbActivity;
    int s,counter=0,counter1=0;
    long start_time=0;
    String s1;
    int on_off,point,secound = 120;
    TextView textView;
    List<Proverb>proverbs=new ArrayList<>();
    List<Words>words=new ArrayList<>();
    Intent bargasht;
    LinearLayout linearResult;
    Handler timehandler=new Handler();
    Runnable timerunnable=new Runnable() {
        @Override
        public void run() {
            progressBar.setProgress(secound * 5/6);
            secound -= 1;
            timehandler.postDelayed(this,1000);
            if(secound==0){
                timehandler.removeCallbacks(timerunnable);
                linearResult.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.executive_section);
        bargasht=getIntent();
        LinearLayout linearLayout=findViewById(R.id.linear_start);
        linearResult=findViewById(R.id.linear_result);
        correctanswer=findViewById(R.id.correctanswer);
        end=findViewById(R.id.end_btn);
        start=findViewById(R.id.start);
        DoNotAnswer=findViewById(R.id.wronganswer);
        textView=findViewById(R.id.question_text);
        changequestion=findViewById(R.id.change_question);
        progressBar=findViewById(R.id.progress_bar);
        dbActivity=new DatabaseActivity(executive_section.this);
        on_off=getIntent().getIntExtra("on_off",0);
        if(on_off==0) {
            s = getIntent().getIntExtra("level",0);
            Cursor cursor = dbActivity.getDb().rawQuery("SELECT * FROM proverb WHERE level = ?", new String[] {String.valueOf(s)});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Proverb p = new Proverb(cursor.getString(0), cursor.getInt(1));
                proverbs.add(p);
                cursor.moveToNext();
            }
            cursor.close();
            changequestion.setOnClickListener((View v)->{
                counter1++;
                if(counter1==proverbs.size()){
                    counter1=0;
                }
                textView.setText(proverbs.get(counter1).question);
            });
            start.setOnClickListener((View v)->{
                linearLayout.setVisibility(View.GONE);
                end.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                start_time=System.currentTimeMillis();
                timehandler.postDelayed(timerunnable,0);
            });
            end.setOnClickListener((View v)->{
                timehandler.removeCallbacks(timerunnable);
                linearResult.setVisibility(View.VISIBLE);
            });
            correctanswer.setOnClickListener((View v)->{
                point=proverbs.get(counter).level+(secound)/45;
                bargasht.putExtra("score", point);
                setResult(Activity.RESULT_OK, bargasht);
                finish();
            });
            DoNotAnswer.setOnClickListener((View v)->{
                point=0;
                bargasht.putExtra("score", point);
                setResult(Activity.RESULT_OK, bargasht);
                finish();
            });
            textView.setText(proverbs.get(counter1).question);
        }
        else if(on_off==1){
            s1=getIntent().getStringExtra("category");
            s=getIntent().getIntExtra("level",0);
            Cursor cursor = dbActivity.getDb().rawQuery("SELECT * FROM words WHERE category=? AND level=?", new String[]{s1, String.valueOf(s)});
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Words w = new Words(cursor.getString(0),cursor.getString(1),cursor.getInt(2));
                words.add(w);
                cursor.moveToNext();
            }
            cursor.close();
            changequestion.setOnClickListener((View v)->{
                counter++;
                if(counter==words.size()){
                    counter=0;
                }
                textView.setText(words.get(counter).question);
            });
            start.setOnClickListener((View v)->{
                linearLayout.setVisibility(View.GONE);
                end.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                start_time=System.currentTimeMillis();
                timehandler.postDelayed(timerunnable,0);
            });
            end.setOnClickListener((View v)->{
                timehandler.removeCallbacks(timerunnable);
                linearResult.setVisibility(View.VISIBLE);
            });
            correctanswer.setOnClickListener((View v)->{
                point=words.get(counter).level+(secound)/45;
                bargasht.putExtra("score", point);
                setResult(Activity.RESULT_OK, bargasht);
                finish();
            });
            DoNotAnswer.setOnClickListener((View v)->{
                point=0;
                bargasht.putExtra("score", point);
                setResult(Activity.RESULT_OK, bargasht);
                finish();
            });
            textView.setText(words.get(counter).question);
        }
    }
}