package com.example.vmac.WatBot;

import android.animation.ValueAnimator;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import android.content.Intent;
import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class EMFActivity extends AppCompatActivity {

    private ImageButton btnTherm;
    private ImageButton btnRadio;
    private TextView emfDisplay;
    private static DecimalFormat df = new DecimalFormat("00.00");
    public boolean ghostNear = false;
    public final static String EXTRA_MESSAGE = "com.example.WatBot.MESSAGE";
    private int valueRan;
    private int thresholdGN = 50;
    private String ghostNearStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emf_detector);

        Intent radiosfx = new Intent(EMFActivity.this, RadioSFXService.class);
        stopService(radiosfx);
        Intent emfsfx = new Intent(EMFActivity.this, EMFSFXService.class);
        startService(emfsfx);

        btnTherm = findViewById(R.id.thermButton);
        btnRadio = findViewById(R.id.radioButton);
        emfDisplay = findViewById(R.id.emfreading);
        //displayFreq();
        if(!ghostNear) {
            animateTextView(3.5f, 4.6f, 600000, emfDisplay);
        } else {
            animateTextView(5.2f, 56.79f, 60000, emfDisplay);
        }

        //View.invalidate();

        btnTherm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThermActivity.class);
                if(ghostNear) {
                    ghostNearStr = "y";
                } else {
                    ghostNearStr = "n";
                }
                intent.putExtra(EXTRA_MESSAGE, ghostNearStr);
                startActivity(intent);
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                if(ghostNear) {
                    ghostNearStr = "y";
                } else {
                    ghostNearStr = "n";
                }
                intent.putExtra(EXTRA_MESSAGE, ghostNearStr);
                startActivity(intent);
            }
        });
        /*
        private void startCountAnimation() {
            ValueAnimator animator = ValueAnimator.ofInt(0, 600);
            animator.setDuration(5000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    emf.setText(animation.getAnimatedValue().toString());
                }
            });
            animator.start();
        } */

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message == "y") {
            ghostNear = true;
        } else {
            ghostNear = false;
        }
    }

    public void animateTextView(float initialValue, float finalValue, int duration, final TextView textview) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue);
        valueAnimator.setDuration(duration);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                textview.setText(df.format(valueAnimator.getAnimatedValue()));

            }
        });
        valueAnimator.start();

        valueRan = ThreadLocalRandom.current().nextInt(1, 100);
        if(valueRan > thresholdGN) {
            ghostNear = true;
        } else {
            ghostNear = false;
        }
    }

}



