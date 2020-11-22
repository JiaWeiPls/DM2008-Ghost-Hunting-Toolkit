package com.example.vmac.WatBot;

import android.animation.ValueAnimator;
import java.text.DecimalFormat;

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
              startActivity(intent);
          }
      });

      btnRadio.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(v.getContext(), MainActivity.class);
              startActivity(intent);
          }
      });
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

  }
}



