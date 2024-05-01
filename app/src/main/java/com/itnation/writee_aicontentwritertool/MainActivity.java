package com.itnation.writee_aicontentwritertool;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itnation.writee_aicontentwritertool.Activity.AiGenerateActivity;
import com.itnation.writee_aicontentwritertool.Activity.ViewContentActivity;
import com.itnation.writee_aicontentwritertool.Adapter.ContentListAdapter;
import com.itnation.writee_aicontentwritertool.Adapter.ContentRVAdapter;
import com.itnation.writee_aicontentwritertool.MVVM.Content;
import com.itnation.writee_aicontentwritertool.MVVM.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ImageView floatingActionButton, guidenceBtn;
    LinearLayout startWriting;
    RecyclerView contentListRV;

    ContentListAdapter contentListAdapter;


    ContentViewModel contentViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        floatingActionButton = findViewById(R.id.floatingActionButton);
        contentListRV = findViewById(R.id.contentListRV);
        startWriting = findViewById(R.id.startWriting);
        guidenceBtn = findViewById(R.id.guidenceBtn);





        showList();



        guidenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        startWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AiGenerateActivity.class);
                startActivity(intent);
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                if (direction==ItemTouchHelper.RIGHT){

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Make sure you Delete this content?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes, Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    contentViewModel.delete(contentListAdapter.getContent(viewHolder.getAdapterPosition()));
                                    Toast.makeText(MainActivity.this, "Content Deleted", Toast.LENGTH_SHORT).show();

                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    showList();
                                    dialog.cancel();

                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }else {

                    Intent intent = new Intent(MainActivity.this, ViewContentActivity.class);
                    intent.putExtra("paragraph", contentListAdapter.getContent(viewHolder.getAdapterPosition()).getParagraph());
                    intent.putExtra("title", contentListAdapter.getContent(viewHolder.getAdapterPosition()).getTitle());
                    startActivity(intent);
                    showList();
                }


            }
        }).attachToRecyclerView(contentListRV);




    }

    void showList(){


        contentViewModel= new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ContentViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        contentListRV.setLayoutManager(linearLayoutManager);
        contentListRV.setLayoutManager(linearLayoutManager);
        contentListAdapter= new ContentListAdapter();
        contentListRV.setAdapter(contentListAdapter);





        contentViewModel.getAllContent().observe(MainActivity.this, new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contents) {

                contentListAdapter.submitList(contents);

                //setAdapter(contents);
            }
        });



    }

    ContentRVAdapter contentRVAdapter;
    List<Content> contentList;

    public void setAdapter(List<Content> contents){

        contentListRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        contentRVAdapter= new ContentRVAdapter(MainActivity.this, contents);
        contentListRV.setAdapter(contentRVAdapter);
    }

/*
    public void setAdapter(List<Content> contents){


        contentListAdapter= new ContentListAdapter(contents, MainActivity.this);
        contentListRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        contentListRV.setAdapter(contentListAdapter);
    }

 */
}