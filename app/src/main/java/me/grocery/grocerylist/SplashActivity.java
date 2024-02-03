package me.grocery.grocerylist;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 8000;
    String[] mealPlans = {
            "1400 calorie keto meal plan",
            "1800 calorie vegan meal plan",
            "1200 calorie paleo meal plan",
            "1600 calorie vegetarian meal plan",
            "2000 calorie gluten-free meal plan",
            "1500 calorie low-carb meal plan"
    };

    private ArrayList<String> questions;

    public void setQuestions(ArrayList<String> questions) {
        this.questions = questions;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }


    private String userInput;

    public String getUserInput() {
        return userInput;
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable typingAnimation;
    private boolean cursorBlinkState = false;
    private int textIndex = 0;
    private int charIndex = 0;
    private final long TYPING_INTERVAL = 150;
    private final long CURSOR_BLINK_INTERVAL = 500;
    private final long TEXT_PAUSE_DURATION = 2000;
    private Runnable cursorBlinkRunnable = null;

    //animation values
    private int DURATION = 2000;
    private int OFFSET = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        TextView titleTextView = findViewById(R.id.titleText);
        EditText editText = findViewById(R.id.inputEditText);
        Button submitButton = findViewById(R.id.submitButton);
        ImageView progressBar = findViewById(R.id.loadingImageView);
        Animation fadeInForLogo = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUpForLogo = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation fadeInForTitle = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUpForTitle = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation fadeInForText = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUpForText = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        Animation fadeInForEditText = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        Interpolator interpolator = AnimationUtils.loadInterpolator(this, R.anim.my_interpolator);
        slideUpForLogo.setInterpolator(interpolator);
        slideUpForText.setInterpolator(interpolator);
        slideUpForTitle.setInterpolator(interpolator);

        fadeInForEditText.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editText.setVisibility(View.VISIBLE);
            }

            final Handler handler = new Handler(Looper.getMainLooper());

            @Override
            public void onAnimationEnd(Animation animation) {
                animateEditTextHint(editText);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


        Animation fadeInForButton = AnimationUtils.loadAnimation(this, R.anim.fade_in);



        AnimationSet logoAnimationSet = new AnimationSet(false);
        logoAnimationSet.addAnimation(slideUpForLogo);
        logoAnimationSet.addAnimation(fadeInForLogo);

        AnimationSet titleAnimationSet = new AnimationSet(false);
        titleAnimationSet.addAnimation(slideUpForTitle);
        titleAnimationSet.addAnimation(fadeInForTitle);

        AnimationSet textAnimationSet = new AnimationSet(false);
        textAnimationSet.addAnimation(slideUpForText);
        textAnimationSet.addAnimation(fadeInForText);

        logoAnimationSet.setDuration(DURATION);
        textAnimationSet.setDuration(DURATION);
        titleAnimationSet.setStartOffset(0);
        textAnimationSet.setStartOffset(1000);
        fadeInForEditText.setStartOffset(3000);
        fadeInForButton.setStartOffset(3500);

        logoImageView.setVisibility(View.VISIBLE);
        logoImageView.startAnimation(logoAnimationSet);

        titleTextView.setVisibility(View.VISIBLE);
        titleTextView.startAnimation(textAnimationSet);

        welcomeTextView.setVisibility(View.VISIBLE);
        welcomeTextView.startAnimation(textAnimationSet);

        editText.setVisibility(View.VISIBLE);
        editText.startAnimation(fadeInForEditText);

        submitButton.setVisibility(View.VISIBLE);
        submitButton.startAnimation(fadeInForButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput = editText.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
            //  sendPromptToBackendAndReceiveQuestions(userInput)
                Log.d("user input:", userInput);
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });


    }
    private void animateEditTextHint(final EditText editText) {
        typingAnimation = new Runnable() {
            @Override
            public void run() {

                String text = mealPlans[textIndex];
                if(text == null)
                    return;
                if (charIndex < text.length()) {
                    editText.setHint(text.substring(0, ++charIndex) + (cursorBlinkState ? "|" : ""));
                    handler.postDelayed(this, TYPING_INTERVAL);
                } else {
                    Log.d("string index:", String.valueOf(textIndex) +(String) editText.getHint());
                    if (++textIndex >= mealPlans.length)
                        textIndex = 0;
                    charIndex = 0;
                    handler.postDelayed(this, TEXT_PAUSE_DURATION);
                }
                if (cursorBlinkRunnable == null) {
                    startCursorBlink(editText);
                }
            }
        };
        handler.post(typingAnimation);
    }

    private void startCursorBlink(final EditText editText) {
        cursorBlinkRunnable = new Runnable() {
            @Override
            public void run() {
                String currentText = (String)editText.getHint();
                cursorBlinkState = !cursorBlinkState;
                String currentTextWithoutCursor = editText.getHint().toString().replace("|", "");
                editText.setHint(currentTextWithoutCursor + (cursorBlinkState ? "|" : ""));

                handler.postDelayed(this, CURSOR_BLINK_INTERVAL);
            }
        };
        handler.post(cursorBlinkRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler == null)
            return;
        handler.removeCallbacks(typingAnimation);
        handler.removeCallbacks(cursorBlinkRunnable);


    }
}


