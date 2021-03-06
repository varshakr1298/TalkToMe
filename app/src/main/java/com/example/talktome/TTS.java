package com.example.talktome;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.spark.submitbutton.SubmitButton;

import java.util.Locale;

import static com.example.talktome.R.color.colorPrimary;

public class TTS extends AppCompatActivity {
    EditText inputText;
    Button buttonClear;
    SubmitButton buttonConvert;
    WebView webView;

    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_t_s);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#400082"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        inputText=findViewById(R.id.input);
        buttonClear=findViewById(R.id.clearbutton);
        buttonConvert=findViewById(R.id.convertButton);

        webView=findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String file="file:android_asset/tts2.gif";
        webView.loadUrl(file);

        textToSpeech = new TextToSpeech(getApplicationContext(),new android.speech.tts.TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int i) {
                if(i == android.speech.tts.TextToSpeech.SUCCESS){
                    int lang= textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= inputText.getText().toString();

                int speech=textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText("");
                buttonClear.setBackgroundResource(R.drawable.bg_round1);
            }
        });
    }
}