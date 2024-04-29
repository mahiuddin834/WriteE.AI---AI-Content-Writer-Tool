package com.itnation.writee_aicontentwritertool.Activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itnation.writee_aicontentwritertool.MVVM.Content;
import com.itnation.writee_aicontentwritertool.MVVM.ContentViewModel;
import com.itnation.writee_aicontentwritertool.MainActivity;
import com.itnation.writee_aicontentwritertool.R;

public class ContentInsertActivity extends AppCompatActivity {


    FloatingActionButton doneFloatingActionButton;
    EditText paragraphEditTxt;
    ImageView boldBtn, underLineBtn, italianBtn, alignLeftBtn, alignCenterBtn, alignRightBtn, copyBtn;

    ContentViewModel contentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_edit);


        doneFloatingActionButton = findViewById(R.id.doneFloatingActionButton);
        paragraphEditTxt=findViewById(R.id.paragraphEditTxt);
        boldBtn=findViewById(R.id.boldBtn);
        underLineBtn=findViewById(R.id.underLineBtn);
        italianBtn=findViewById(R.id.italianBtn);
        alignLeftBtn=findViewById(R.id.alignLeftBtn);
        alignCenterBtn=findViewById(R.id.alignCenterBtn);
        alignRightBtn=findViewById(R.id.alignRightBtn);
        copyBtn=findViewById(R.id.copyBtn);


        Window window = ContentInsertActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(ContentInsertActivity.this,R.color.primary));


        contentViewModel= new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ContentViewModel.class);

        Intent intent= getIntent();
        String contentTopic = intent.getStringExtra("topic");
        String paragraph = intent.getStringExtra("resultTxt");

        paragraphEditTxt.setText(paragraph);




        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonBold(v);


            }
        });


        underLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonUnderline(v);

            }
        });


        italianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonItalics(v);
            }
        });

        alignLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonAlignmentLeft(v);

            }
        });


        alignCenterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonAlignmentCenter(v);

            }
        });


        alignRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonAlignmentRight(v);

            }
        });


        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager cm = (ClipboardManager)ContentInsertActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(paragraphEditTxt.getText());
                Toast.makeText(ContentInsertActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });




        doneFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contentParagraph = paragraphEditTxt.getText().toString();


                Content content= new Content(contentTopic, contentParagraph);
                contentViewModel.insert(content);
                Toast.makeText(ContentInsertActivity.this, "Content Added On The List", Toast.LENGTH_SHORT).show();

                finish();

                /*Intent intent = new Intent(ContentInsertActivity.this, MainActivity.class);
                intent.putExtra("contentParagraph", paragraphEditTxt.getText().toString());
                startActivity(intent);

                 */
            }
        });

    }//--------- close onCreate Method --------------------------------

    private void txtContentEdit() {





    }//


    public void buttonBold(View view){
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                paragraphEditTxt.getSelectionStart(),
                paragraphEditTxt.getSelectionEnd(),
                0);

        paragraphEditTxt.setText(spannableString);
    }
    public void buttonItalics(View view){
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                paragraphEditTxt.getSelectionStart(),
                paragraphEditTxt.getSelectionEnd(),
                0);

        paragraphEditTxt.setText(spannableString);

    }
    public void buttonUnderline(View view){
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        spannableString.setSpan(new UnderlineSpan(),
                paragraphEditTxt.getSelectionStart(),
                paragraphEditTxt.getSelectionEnd(),
                0);

        paragraphEditTxt.setText(spannableString);
    }

    public void buttonNoFormat(View view){
        String stringText = paragraphEditTxt.getText().toString();
        paragraphEditTxt.setText(stringText);
    }


    public void buttonAlignmentLeft(View view){
        paragraphEditTxt.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        paragraphEditTxt.setText(spannableString);
    }

    public void buttonAlignmentCenter(View view){
        paragraphEditTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        paragraphEditTxt.setText(spannableString);
    }

    public void buttonAlignmentRight(View view){
        paragraphEditTxt.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString = new SpannableStringBuilder(paragraphEditTxt.getText());
        paragraphEditTxt.setText(spannableString);
    }


}