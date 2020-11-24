package com.example.vmac.WatBot;

import android.animation.ValueAnimator;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ThermActivity extends AppCompatActivity {

  private ImageButton btnEMF;
  private ImageButton btnRadio;
  private TextView thermDisplay;
  private TextView negativeSign;
  private static DecimalFormat df = new DecimalFormat("00.0");
  public boolean ghostNear = false;
  public final static String EXTRA_MESSAGE = "com.example.WatBot.MESSAGE";
  private int valueRan;
  private int thresholdGN = 50;
    private String ghostNearStr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.thermometer);

      Intent emfsfx = new Intent(ThermActivity.this, EMFSFXService.class);
      stopService(emfsfx);
      Intent radiosfx = new Intent(ThermActivity.this, RadioSFXService.class);
      stopService(radiosfx);

      btnEMF = findViewById(R.id.emfButton);
      btnRadio = findViewById(R.id.radioButton);
      thermDisplay = findViewById(R.id.temperature);
      negativeSign = (TextView)findViewById(R.id.sign);
      //displayFreq();
      if(!ghostNear) {
          animateTextView(25.5f, 27.6f, 600000, thermDisplay);
          negativeSign.setText("");
      } else {
          animateTextView(5.2f, 56.79f, 60000, thermDisplay);
          negativeSign.setText("-");
      }


      btnEMF.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(v.getContext(), EMFActivity.class);
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



