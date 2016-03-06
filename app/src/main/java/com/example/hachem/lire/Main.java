package com.example.hachem.lire ;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main extends Activity implements  OnClickListener, OnInitListener{
    private Button 			mButton;
    private EditText 		mEditText;
    //private Spinner 		mSpinner;

    private TextToSpeech 	mTTS;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mButton=(Button)findViewById(R.id.parler);
        mButton.setOnClickListener(this);

        mEditText=(EditText)findViewById(R.id.input);

       // ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Locale.getAvailableLocales());
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      //  mSpinner=(Spinner)findViewById(R.id.spinner_locale);
       //  mSpinner.setAdapter(adapter);
       // mSpinner.setOnItemSelectedListener(this);

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, 0);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0)
        {
            if(resultCode==TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                mTTS=new TextToSpeech(this,this);
                mTTS.setLanguage(Locale.FRENCH);
            }
            else{
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.parler:
                mTTS.speak(mEditText.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                break;
        }

    }



    @Override
    public void onInit(int status) {
        mButton.setEnabled(true);
       // mSpinner.setEnabled(true);
        mEditText.setEnabled(true);
    }
}