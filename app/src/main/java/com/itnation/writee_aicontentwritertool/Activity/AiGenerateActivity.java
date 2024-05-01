package com.itnation.writee_aicontentwritertool.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itnation.writee_aicontentwritertool.AiModel.AiModel;
import com.itnation.writee_aicontentwritertool.AiModel.ResponseCallback;
import com.itnation.writee_aicontentwritertool.R;

public class AiGenerateActivity extends AppCompatActivity {

    FloatingActionButton generateFloatingActionButton;
    EditText topicEdtTxt;
    Spinner mediaFlatformSpinner, contentToneSpinner, targetAudianceSpinner, contentLanguageSpinner;


    String[] mediaFlatForm = {
            "Select Media FlatForm", "Facebook Post", "Facebook Video", "Youtube Video", "Youtube Post", "Twitter Post", "Instagram Post",
            "WhatsApp Post", "WeChat Post", "TikTok Post", "Telegram Post", "Snapchat Video", "Website Post", "Website Video"
    };

    String[] contentTone = {
            "Select Content Tone", "Informative Tone", "Enthusiastic Tone", "Persuasive Tone", "Friendly Tone", "Authoritative Tone"
    };

    String[] targetAudiance = {
            "Select Audience", "EveryBody", "18+", "Male", "Female", "Male(8-12)", "Female(8-12)", "Male(13-17)", "Female(13-17)", "Male(18-22)", "Female(18-22)", "Male(18-22)", "Female(18-22)",
            "Male(23-30)", "Female(23-30)", "Male(30+)", "Female(30+)"
    };

    String[] contentLanguage = {
            "Select Language", "Afrikaans", "Albanian", "Amharic", "Arabic", "Armenian", "Azerbaijani",
            "Basque", "Belarusian", "Bengali", "Bosnian", "Bulgarian", "Burmese",
            "Catalan", "Cebuano", "Chichewa", "Chinese", "Corsican", "Croatian",
            "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian",
            "Filipino", "Finnish", "French", "Frisian", "Galician", "Georgian",
            "German", "Greek", "Gujarati", "Haitian Creole", "Hausa", "Hawaiian",
            "Hebrew", "Hindi", "Hmong", "Hungarian", "Icelandic", "Igbo",
            "Indonesian", "Irish", "Italian", "Japanese", "Javanese", "Kannada",
            "Kazakh", "Khmer", "Korean", "Kurdish", "Kyrgyz", "Lao",
            "Latin", "Latvian", "Lithuanian", "Luxembourgish", "Macedonian", "Malagasy",
            "Malay", "Malayalam", "Maltese", "Maori", "Marathi", "Mongolian",
            "Nepali", "Norwegian", "Pashto", "Persian", "Polish", "Portuguese",
            "Punjabi", "Romanian", "Russian", "Samoan", "Scots Gaelic", "Serbian",
            "Sesotho", "Shona", "Sindhi", "Sinhala", "Slovak", "Slovenian",
            "Somali", "Spanish", "Sundanese", "Swahili", "Swedish", "Tajik",
            "Tamil", "Telugu", "Thai", "Turkish", "Turkmen", "Ukrainian",
            "Urdu", "Uyghur", "Uzbek", "Vietnamese", "Welsh", "Xhosa",
            "Yiddish", "Yoruba", "Zulu"
    };


    String topicTxt, mediaFlatformSPtxt, contentToneSPtxt, AudianceSPtxt, contentLanguageSPtxt;
    String finalQueryTxt;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ai_generate);



        generateFloatingActionButton = findViewById(R.id.generateFloatingActionButton);
        topicEdtTxt = findViewById(R.id.topicEdtTxt);
        mediaFlatformSpinner = findViewById(R.id.mediaFlatformSpinner);
        contentToneSpinner = findViewById(R.id.ContentToneSpinner);
        targetAudianceSpinner = findViewById(R.id.targetAudianceSpinner);
        contentLanguageSpinner = findViewById(R.id.contentLanguageSpinner);
        progressBar = findViewById(R.id.progressBar);


        Window window = AiGenerateActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(AiGenerateActivity.this,R.color.primary));


//spinner Adapter ---------------------------
        ArrayAdapter<String> mediaFlatformSpinnerAdapter = new ArrayAdapter<>(AiGenerateActivity.this, android.R.layout.simple_spinner_item, mediaFlatForm);
        mediaFlatformSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mediaFlatformSpinner.setAdapter(mediaFlatformSpinnerAdapter);

        ArrayAdapter<String> contentToneAdapter = new ArrayAdapter<>(AiGenerateActivity.this, android.R.layout.simple_spinner_item, contentTone);
        contentToneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contentToneSpinner.setAdapter(contentToneAdapter);

        ArrayAdapter<String> targetAudianceAdapter = new ArrayAdapter<>(AiGenerateActivity.this, android.R.layout.simple_spinner_item, targetAudiance);
        targetAudianceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        targetAudianceSpinner.setAdapter(targetAudianceAdapter);

        ArrayAdapter<String> contentLanguageAdapter = new ArrayAdapter<>(AiGenerateActivity.this, android.R.layout.simple_spinner_item, contentLanguage);
        contentLanguageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contentLanguageSpinner.setAdapter(contentLanguageAdapter);


        inputTxtItem();

        generateFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (topicEdtTxt.getText().toString().length() >= 5) {

                    topicTxt = topicEdtTxt.getText().toString();

                    generateContent();


                } else {
                    topicEdtTxt.setError("Please Enter Your Topic");
                }


            }
        });


    }//---------------close OnCreate -----------------------------


    private void generateContent() {

        //I want to create a Facebook post content. whose content tone will be informative tone.
        // The target audience of the content is everyone and the language of my content will be Bengali. Content topic is "About Bangladesh"

        progressBar.setVisibility(View.VISIBLE);
        String topic = topicEdtTxt.getText().toString();
        String contentQuery = "I want to create a " + mediaFlatformSPtxt + " content." + " whose content tone will be " + contentToneSPtxt + ". The target audience of the content is "
                + AudianceSPtxt + " and the language of my content will be " + contentLanguageSPtxt + ". Content topic is \"" + topic + "\". Write a complete content with a great description.";

        //Toast.makeText(this, contentQuery, Toast.LENGTH_SHORT).show();


        AiModel model = new AiModel();

        model.getResponse(contentQuery, new ResponseCallback() {
            @Override
            public void onResponse(String response) {


                progressBar.setVisibility(View.GONE);


                Intent intent = new Intent(AiGenerateActivity.this, ContentInsertActivity.class);
                intent.putExtra("topic", topicEdtTxt.getText().toString());
                intent.putExtra("resultTxt", response);
                startActivity(intent);

                finish();

            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(AiGenerateActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    private void inputTxtItem() {

        mediaFlatformSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).toString() == "Select Media FlatForm") {

                    mediaFlatformSPtxt = "";


                } else {

                    mediaFlatformSPtxt = parent.getItemAtPosition(position).toString();

                    Toast.makeText(AiGenerateActivity.this, mediaFlatformSPtxt, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        contentToneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).toString() == "Select Content Tone") {

                    contentToneSPtxt = "";

                } else {

                    contentToneSPtxt = parent.getItemAtPosition(position).toString();

                    Toast.makeText(AiGenerateActivity.this, contentToneSPtxt, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        targetAudianceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).toString() == "Select Audience") {

                    AudianceSPtxt = "";

                } else {

                    AudianceSPtxt = parent.getItemAtPosition(position).toString();

                    Toast.makeText(AiGenerateActivity.this, AudianceSPtxt, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        contentLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).toString() == "Select Language") {

                    contentLanguageSPtxt = "";

                } else {


                    contentLanguageSPtxt = parent.getItemAtPosition(position).toString();

                    Toast.makeText(AiGenerateActivity.this, contentLanguageSPtxt, Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}