package com.modex.pythonquiz;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Locale;
import java.util.Objects;
import java.util.Timer;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

import static java.lang.String.*;


@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_QUIZ = 1;
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String SHARED_PRFSS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    private int highScore;
    private long backPressed;
    private Dialog twitterDialog;
    private TextView textViewHighScore;
    private Spinner spinnerDifficulty;
    private ViewPager viewPageId;
    private int customPosition = 0;




  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       FirebaseAnalytics mFirebaseAnalytics;
       mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       mFirebaseAnalytics.getAppInstanceId();


       viewPageId = findViewById(R.id.viewPageId);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPageId.setAdapter(viewPagerAdapter);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(), 2000, 5000);

        AppRate.with(this)
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);



        textViewHighScore = findViewById(R.id.textView_highScore);
        loadHighscore();

        spinnerDifficulty = findViewById(R.id.spinner_difficulty);


        String[] difficultyLevels = QuestionsClass.getAllDifficultyLevels();


        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficultyLevels);

        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);


        QuestionActivity qActQSize = new QuestionActivity();





    }



    public class TimerTask extends java.util.TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    viewPageId.setCurrentItem(customPosition % 20);
                    customPosition++;

                           }
            });

        }
    }

    public void OpenConfirmation(View view) {


        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);

        try {
            startActivityForResult(intent, REQ_CODE_QUIZ);
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this,"Please Select Difficulty!",Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuestionActivity.EXTRA_SCORE,0);
                if (score > highScore) {
                    updateHighscore(score);
                }
            }
        }
    }

    public void updateHighscore(int highScoreTwo) {
        highScore = highScoreTwo;
        textViewHighScore.setText(format(Locale.ENGLISH,"High Score: %d", highScore));

        SharedPreferences preferences = getSharedPreferences(SHARED_PRFSS,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();

    }

    public void loadHighscore() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PRFSS, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText(format(Locale.ENGLISH,"High Score: %d", highScore));

    }

    public void twitterVoid (View view) {
        twitterDialog = new Dialog(this);
        twitterDialog.setContentView(R.layout.distributedmodex);
        ImageView close = twitterDialog.findViewById(R.id.popUpClose);
        Button btnAccept = twitterDialog.findViewById(R.id.buttonAccept);
        TextView titleTv = twitterDialog.findViewById(R.id.titleText);
        TextView messageTv = twitterDialog.findViewById(R.id.message);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterDialog.dismiss();
            }
        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/modexapps";
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        Objects.requireNonNull(twitterDialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        twitterDialog.show();


    }


    @Override
    public void onBackPressed() {

        if (backPressed + 2000 > System.currentTimeMillis()) {
            finish();

        } else {

            Toast.makeText(MainActivity.this, "Press Back To Exit!", Toast.LENGTH_SHORT).show();

        }

        backPressed = System.currentTimeMillis();

    }
}


class ViewPagerAdapter extends PagerAdapter {

    private final Context context;
    private final Integer [] imagesViewPage = {

            R.drawable.slide1,
            R.drawable.slide2,R.drawable.slide3,R.drawable.slide4,
            R.drawable.slide5,  R.drawable.slide6,R.drawable.slide7,
            R.drawable.slide8,
            R.drawable.slide9,R.drawable.slide10,R.drawable.slide11,
            R.drawable.slide12,R.drawable.slide13,R.drawable.slide14,
            R.drawable.slide15,R.drawable.slide16,R.drawable.slide17,
            R.drawable.slide18,R.drawable.slide19,R.drawable.slide20,

    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagesViewPage.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewPager viewPager = (ViewPager) container;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customlayoutview, viewPager,false);
        ImageView imageView = view.findViewById(R.id.imageView2);
        imageView.setImageResource(imagesViewPage[position]);

        viewPager.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}






// TODO: 11/7/2020 PART 6 Done with shared preferences and onCreate, wait for more. Not finished with part 6
//  TODO: 11/8/2020 Part 7 done with CountDown and check to make a dialog if a user wants to get out of the app! PART 7