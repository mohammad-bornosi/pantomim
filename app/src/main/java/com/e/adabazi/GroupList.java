package com.e.adabazi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupList extends Activity  {
    private int turn=0,tedad=1;
    TextView textView;
    List<Integer>choose_winner=new ArrayList<>();
    List<Group> groupList = new ArrayList<>();
    int seekbar_number;
    int numberpicker_number,point_is_earnd;
    GroupListadaptor groupListadaptor;
    public void game_start(View v) {
        Intent intent = new Intent(GroupList.this, Contest_of_game.class);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specifications);
        ListView listView=findViewById(R.id.list_view);
        textView=findViewById(R.id.tedad_dor);
        numberpicker_number=getIntent().getIntExtra("numberpicker_number", 0);
        seekbar_number=getIntent().getIntExtra("seekbar_number",0);
        for(int i = 0; i < Integer.valueOf(numberpicker_number); i++) {
            groupList.add(new Group(i+1, 0));
        }
        groupListadaptor=new GroupListadaptor(this, groupList);
        groupListadaptor.notifyDataSetChanged();
        listView.setAdapter(groupListadaptor);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            if(resultCode==Activity.RESULT_OK){
                if(tedad==seekbar_number&&turn==numberpicker_number-1){
                    for (int i = 0; i <numberpicker_number ; i++) {
                        choose_winner.add(groupList.get(i).getPoint());
                    }
                    int max=choose_winner.get(0);
                    int index=0;
                    for (int i = 1; i <numberpicker_number ; i++) {
                        if(choose_winner.get(i)>max){
                            max=choose_winner.get(i);
                            index=i;
                        }
                    }
                    index++;
                    Toast.makeText(GroupList.this,index+"is winner",Toast.LENGTH_LONG).show();
                    finish();
                }
                if(turn==numberpicker_number){
                    turn=0;
                    tedad++;
                }
                textView.setText(String.valueOf(tedad)+"از"+String.valueOf(seekbar_number));
                point_is_earnd=data.getIntExtra("score",-5);
                groupList.get(turn).setPoint(groupList.get(turn).getPoint() + point_is_earnd);
                groupListadaptor.update(groupList);
                turn++;
            }
        }
    }
}
