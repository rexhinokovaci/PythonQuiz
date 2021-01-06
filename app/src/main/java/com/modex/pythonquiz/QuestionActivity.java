package com.modex.pythonquiz;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static java.lang.String.*;



public class QuestionActivity extends AppCompatActivity {
    private static final long COUNT_DOWN_TIME = 30000;
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILlIS_LEFT   = "keyMilisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    public static final String EXTRA_SCORE = "extraScore";
    private final String AD = "";

    private long backPressed;

    private TextView textViewQuestion, textViewQuestionCount, textViewCountDown, textViewScores;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private RadioGroup radioGroup;
    public int QuestionCounter, QuestionCountTotal, score;
    private QuestionsClass currentQuestions;
    private ColorStateList  textColorDefaultCD;
    private CountDownTimer countDownTimer;
    private long timeMillis;
    private boolean answered;
    private ArrayList<QuestionsClass> questionsClassList;
    private AdView adView;

    public QuestionActivity() {

    }
    FrameLayout adContainerView;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
       FirebaseAnalytics mFirebaseAnalytics;
       mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       mFirebaseAnalytics.getAppInstanceId();


       textViewScores = findViewById(R.id.textViewScore);

//      AD STARTS HERE
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });

       adContainerView = findViewById(R.id.ad_view_container);
       adView = new AdView(this);
       adView.setAdUnitId(getString(R.string.adaptive_banner_ad_unit_id));
       adContainerView.addView(adView);
        loadBanner();




    // AD FINISHED HERE

       adView.setAdListener(new AdListener() {
           @Override
           public void onAdLoaded() {
               // Code to be executed when an ad finishes loading.
               Log.d(AD,"Ad finished Loading");
           }

           @Override
           public void onAdFailedToLoad(LoadAdError adError) {
               // Code to be executed when an ad request fails.
               Log.d(AD,"Ad FAILED");
           }

           @Override
           public void onAdOpened() {
               // Code to be executed when an ad opens an overlay that
               // covers the screen.
               Log.d(AD,"Ad OPENED");
           }

           @Override
           public void onAdClicked() {
               // Code to be executed when the user clicks on an ad.
               Log.d(AD,"Ad CLICKED");
           }

           @Override
           public void onAdLeftApplication() {
               // Code to be executed when the user has left the app.
               Log.d(AD,"Ad finished Loading");
           }

           @Override
           public void onAdClosed() {
               // Code to be executed when the user is about to return
               // to the app after tapping on an ad.
               Log.d(AD,"Ad CLOSED");
           }
       });





        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestionCount = findViewById(R.id.questionCount);
        textViewCountDown = findViewById(R.id.timeView);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        textColorDefaultCD = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        String difficulty  = intent.getStringExtra(MainActivity.EXTRA_DIFFICULTY);
        Log.d("difficulty", difficulty);

        if (savedInstanceState == null) {

            QuizDbHelper quizDbHelper = new QuizDbHelper(this);
            questionsClassList = quizDbHelper.getQuestions(difficulty);
            QuestionCountTotal = questionsClassList.size();
            Collections.shuffle(questionsClassList);

            showNextQuestion();

        } else {
            questionsClassList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionsClassList == null) {
                finish();
            }
            QuestionCountTotal = questionsClassList.size();
            QuestionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestions = questionsClassList.get(QuestionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeMillis = savedInstanceState.getLong(KEY_MILlIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);


            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }





        // the if condition with incremented i = 0; variable



        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution();

            }
        });


        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution();

            }
        });
        
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showSolution();
                
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution();

            }
        });



    }



    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        // to get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this
        // device."
        AdRequest adRequest =
                new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();

        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);


        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }


    private void showNextQuestion() {

        radioGroup.clearCheck();

        if (QuestionCounter < QuestionCountTotal) {

            currentQuestions = questionsClassList.get(QuestionCounter);
            textViewQuestion.setText(currentQuestions.getQuestion());

            radioButton1.setText(currentQuestions.getOption1());
            radioButton2.setText(currentQuestions.getOption2());
            radioButton3.setText(currentQuestions.getOption3());
            radioButton4.setText(currentQuestions.getOption4());
            QuestionCounter++;
            textViewQuestionCount.setText(format(Locale.ENGLISH,"Question: %d/%d", QuestionCounter, QuestionCountTotal));
            answered = false;
            // confirm

            timeMillis = COUNT_DOWN_TIME;
//            if (incremented) {
//                timeMillis = 1000;
//            }

            startCountDown();
        } else {
            finishQuiz();
        }

    }

    private void startCountDown() {

        countDownTimer= new CountDownTimer(timeMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {

                timeMillis = 0;
                updateCountDownText();
                checkAnswer();
                showNextQuestion();

            }
        }.start();
    }

    private void updateCountDownText() {

        int minutes = (int) (timeMillis/ 1000) / 60;
        int seconds = (int) (timeMillis/ 1000) % 60;

        String timeFormatted = format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeMillis < 6000) {
            textViewCountDown.setTextColor(Color.RED);
        }

        else {
            textViewCountDown.setTextColor(textColorDefaultCD);
        }

//
    }





    public void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(radioButton) + 1;

        if (answerNo == currentQuestions.getAnswerNr()) {
            score++;

            textViewScores.setText(String.format(Locale.ENGLISH,"Score: %d", score));

        } else if (answerNo != currentQuestions.getAnswerNr()) {
            score--;
            textViewScores.setText(String.format(Locale.ENGLISH,"Score: %d", score));


        }

    }

    private void showSolution() {

        switch (currentQuestions.getAnswerNr()) {
            case 1:
            case 2:
            case 3:
            case 4:
                textViewQuestion.setText(R.string.bugs_work_text_user);
                checkAnswer();
                showNextQuestion();
            break;
        }
    }





    private void finishQuiz() {
        Intent resultsIntent = new Intent();
        resultsIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultsIntent);
        finish();
    }




    @Override
    public void onBackPressed() {


        if (backPressed + 2000 > System.currentTimeMillis()) {
            finishQuiz();

        } else {

            Toast.makeText(QuestionActivity.this, "Press Back To Exit!", Toast.LENGTH_SHORT).show();


        }

        backPressed = System.currentTimeMillis();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE,score);
        outState.putLong(KEY_QUESTION_COUNT, QuestionCounter);
        outState.putLong(KEY_MILlIS_LEFT, timeMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionsClassList);
    }
}


// TODO: 10/25/2020 Part 5, Problem on the "Show Next Question" Void, and the variables for the moment are useless as I don't have any memory on remembering them, so you have to fix that!!!.
//  Having the radio button double tap to confirm is not resolved yet and no idea on that for the moment.
//  CHECK AND RESEARCH FOR RADIO BUTTON DOUBLE TAP TO INTENT (CONFIRM) Don't Forget To Update!!! - Reaserch showed to use Gesture Detector on this class
//  11/1/2020 Part 5, After Checking it out i figured it out that i should do a if statement and create and i = 0 ; variable and incremenet it with 2;
//  Kinda Solved with the handler Class and postDelayed() method but some say that it is ineffective.
//   I put 1,2,3,4 radio button variables: i,j,k,x with the onClick() method and with if conditions. Finished the showNextQuestion and checkSolution methods but have to change it if it makes any mistakes to it in the future. November 7th 2020, PART 6
//  TODO: 11/14/2020 PART 11 - HERE) Just got done finishing the categories and implement to the database, next step is PART 11.
// TODO: 12/13/2020 CHECK COLORS WITH BLUE AND YELLOW ARE MAIN COLORS BECAUSE IT IS PYTHON!!!!! 