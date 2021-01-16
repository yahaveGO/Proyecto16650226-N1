package com.example.proyecto1665026_n1;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Locale;
public class MainActivity extends Activity implements View.OnClickListener {
    private TextToSpeech mTTS;
    private EditText txthablar;
    private SeekBar mSeekBarPitch;
    private SeekBar mSeekBarSpeed;
    private Button mButtonSpeak;

    public String echo = "eco,"; //Alexa, eco, amazon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControl();

        mButtonSpeak = findViewById(R.id.btnhablar);
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale("spa", "MEX");// Español mexico
                    int result = mTTS.setLanguage(locale);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
        txthablar = findViewById(R.id.txthablar);
        mSeekBarPitch = findViewById(R.id.seek_bar_pitch);
        mSeekBarSpeed = findViewById(R.id.seek_bar_speed);
        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }//onCreate


    private void speak() {
        String text = txthablar.getText().toString();
        text = echo + text;
        float pitch = (float) mSeekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speak2(String text) {
        //String text = txthablar.getText().toString();
        text = echo + text;
        float pitch = (float) mSeekBarPitch.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) mSeekBarSpeed.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;
        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    private void initControl() {
        //Buen dia
        ImageButton btnhora = (ImageButton) findViewById(R.id.btnhora);
        btnhora.setOnClickListener(this);
        ImageButton btndia = (ImageButton) findViewById(R.id.btndia);
        btndia.setOnClickListener(this);
        ImageButton btnclima = (ImageButton) findViewById(R.id.btnclima);
        btnclima.setOnClickListener(this);

        //Musica
        findViewById(R.id.btnmusica).setOnClickListener(this);
        findViewById(R.id.btnpara).setOnClickListener(this);
        findViewById(R.id.btnsubevolumen).setOnClickListener(this);
        findViewById(R.id.btnbajavolumen).setOnClickListener(this);
        findViewById(R.id.btnmispistas).setOnClickListener(this);
        findViewById(R.id.btnqueestoyescuchando).setOnClickListener(this);
        findViewById(R.id.btnanterior).setOnClickListener(this);
        findViewById(R.id.btnpausa).setOnClickListener(this);
        findViewById(R.id.btnsiguiente).setOnClickListener(this);
        findViewById(R.id.btnvolumen2).setOnClickListener(this);
        findViewById(R.id.btnvolumen4).setOnClickListener(this);
        findViewById(R.id.btnvolumen6).setOnClickListener(this);



        //Mas
        Button btnchiste = (Button) findViewById(R.id.btnchiste);
        btnchiste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Buen dia
            case R.id.btnhora:
                speak2("¿Que hora es?");
                break;
            case R.id.btndia:
                speak2("¿Que dia es hoy?");
                break;
            case R.id.btnclima:
                speak2("¿Cual es el clima de hoy?");
                break;

            //Musica
            case R.id.btnmusica:
                speak2("Pon musica");
                break;
            case R.id.btnpara:
                speak2("Para la musica");
                break;
            case R.id.btnsubevolumen:
                speak2("Sube el volumen");
                break;
            case R.id.btnbajavolumen:
                speak2("Baja el volumen");
                break;
            case R.id.btnmispistas:
                speak2("Pon mis pistas de spotify");
                break;
            case R.id.btnqueestoyescuchando:
                speak2("Que estoy escuchando");
                break;
            case R.id.btnanterior:
                speak2("anterior");
                break;
            case R.id.btnpausa:
                speak2("pausa");
                break;
            case R.id.btnsiguiente:
                speak2("siguiente");
                break;
            case R.id.btnvolumen2:
                speak2("volumen 2");
                break;
            case R.id.btnvolumen4:
                speak2("volumen 4");
                break;
            case R.id.btnvolumen6:
                speak2("volumen 6");
                break;

            //Mas
            case R.id.btnchiste:
                speak2("Cuentame un chiste");
                break;

        }
    }
}