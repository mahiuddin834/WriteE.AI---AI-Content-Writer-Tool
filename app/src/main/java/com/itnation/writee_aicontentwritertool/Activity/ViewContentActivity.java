package com.itnation.writee_aicontentwritertool.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itnation.writee_aicontentwritertool.R;

public class ViewContentActivity extends AppCompatActivity {


    EditText updateParagraphEditTxt;
    TextView topicTxt;

    ImageView copyView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_content);

        updateParagraphEditTxt = findViewById(R.id.updateParagraphEditTxt);
        topicTxt = findViewById(R.id.topicTxt);
        copyView = findViewById(R.id.copyView);


        Intent intent= getIntent();
        String paragraph = intent.getStringExtra("paragraph");
        String title = intent.getStringExtra("title");


        updateParagraphEditTxt.setText(paragraph);
        topicTxt.setText(title);



        copyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ClipboardManager cm = (ClipboardManager)ViewContentActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(updateParagraphEditTxt.getText());
                Toast.makeText(ViewContentActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}