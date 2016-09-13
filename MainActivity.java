package com.dobbs.ger.scores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button homeGoalBtn, homePointBtn,awayGoalBtn,awayPointBtn;
    private Button minusHomeGoalBtn,minusHomePtBtn,minusAwayGoalBtn, minusAwayPtBtn;
    private Button rugbyBtn,resetBtn;
    private Button minutesText;
    private ImageButton startBtn,stopBtn;
    private TextView totalHomePtsView,totalAwayPtsView;
    private TextView  secsText, helpText;
    private int homeGoals, homePoints,awayGoals,awayPoints;
    private int totalHomePts, totalAwayPts;
    private Handler myHandler;
    private long timeLeft = 0;
    private long pausedAt = 0;
    private boolean paused = true;
    private boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeGoalBtn = (Button) findViewById(R.id.homeGoals);
        homePointBtn = (Button) findViewById(R.id.homePoints);
        awayGoalBtn = (Button) findViewById(R.id.awayGoals);
        awayPointBtn = (Button) findViewById(R.id.awayPoints);
        minusHomeGoalBtn = (Button)findViewById(R.id.subtractHomeGoalButton);
        minusHomePtBtn = (Button)findViewById(R.id.subtractHomePointButton);
        minusAwayGoalBtn = (Button)findViewById(R.id.subtractAwayGoalButton);
        minusAwayPtBtn = (Button)findViewById(R.id.subtractAwayPointButton);
        rugbyBtn = (Button)findViewById(R.id.rugby);
        resetBtn = (Button)findViewById(R.id.ResetButton);
        startBtn = (ImageButton)findViewById(R.id.start);
        minutesText = (Button) findViewById(R.id.minsView);
        secsText = (Button)findViewById(R.id.secsView);
        stopBtn= (ImageButton)findViewById(R.id.stop);
        totalHomePtsView = (TextView)findViewById(R.id.totalHomePoints);
        totalAwayPtsView = (TextView)findViewById(R.id.totalAwayPoints);
        helpText = (TextView) findViewById(R.id.help);
        homeGoalBtn.setOnClickListener(this);
        homePointBtn.setOnClickListener(this);
        awayGoalBtn.setOnClickListener(this);
        awayPointBtn.setOnClickListener(this);
        minusHomeGoalBtn.setOnClickListener(this);
        minusHomePtBtn.setOnClickListener(this);
        minusAwayGoalBtn.setOnClickListener(this);
        minusAwayPtBtn.setOnClickListener(this);
        rugbyBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        minutesText.setOnClickListener(this);
        secsText.setOnClickListener(this);
        minutesText.setBackgroundColor(1);
        secsText.setBackgroundColor(1);
        startBtn.setEnabled(false);
        stopBtn.setEnabled(false);
        startBtn.setImageResource(R.drawable.start48);
        stopBtn.setImageResource(R.drawable.stop64);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if(view == homeGoalBtn){
            if(homeGoals<99) {
                homeGoals++;
                totalHomePts = totalHomePts + 3;
                changeGoals(homeGoalBtn,totalHomePtsView, homeGoals, totalHomePts);
            }
        } else if(view == homePointBtn){
            if(homePoints<99) {
                homePoints++;
                totalHomePts++;
                changePoints(homePointBtn, totalHomePtsView, homePoints, totalHomePts);
            }
        } else if(view == awayGoalBtn) {
            if(awayGoals<99) {
                awayGoals++;
                totalAwayPts = totalAwayPts + 3;
                changeGoals(awayGoalBtn,totalAwayPtsView, awayGoals, totalAwayPts);
            }
        } else if(view == awayPointBtn){
            if(awayPoints<99) {
                awayPoints++;
                totalAwayPts++;
                changePoints(awayPointBtn, totalAwayPtsView, awayPoints, totalAwayPts);
            }
        } else if(view ==minusHomeGoalBtn ){
            if(homeGoals>0) {
                homeGoals--;
                totalHomePts=totalHomePts-3;
                changeGoals(homeGoalBtn,totalHomePtsView, homeGoals, totalHomePts);
            }
        } else if(view ==minusHomePtBtn ){
            if(homePoints>0) {
                homePoints--;
                totalHomePts--;
                changePoints(homePointBtn, totalHomePtsView, homePoints, totalHomePts);
            }
        } else if(view ==minusAwayGoalBtn ){
            if(awayGoals>0) {
                awayGoals--;
                totalAwayPts=totalAwayPts-3;
                changeGoals(awayGoalBtn,totalAwayPtsView, awayGoals, totalAwayPts);
            }
        } else if(view ==minusAwayPtBtn ){
            if(awayPoints>0) {
                awayPoints--;
                totalAwayPts--;
                changePoints(awayPointBtn, totalAwayPtsView, awayPoints, totalAwayPts);
            }
        } else if(view==startBtn) {
            if(paused) {
                timeLeft = pausedAt;
                myHandler = new Handler();
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        timeLeft = timeLeft - 1000;
                        if (timeLeft >= 0) {
                            stopBtn.setEnabled(true);
                            rugbyBtn.setEnabled(false);
                            //Call the thread every 2nd to print time
                            myHandler.postDelayed(this, 1000);
                            //Call Method to output time
                            setClock();
                            running = true;
                        }
                        if (timeLeft == 0) {
                            minutesText.setText("00");
                            startBtn.setEnabled(false);
                            stopBtn.setEnabled(false);
                            rugbyBtn.setEnabled(true);
                            minutesText.setEnabled(true);
                            paused=true;
                            running = false;
                            startBtn.setImageResource(R.drawable.start48);
                            helpText.setText(getString(R.string.timer));
                            pausedAt=0;
                        }
                    }
                };
                //Run the thread every 2nd to print time
                myHandler.postDelayed(myRunnable, 1000);
                paused = false;
                startBtn.setImageResource(R.drawable.pause48);
                Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
            } else{
                pausedAt = timeLeft;
                timeLeft = 0;
                paused = true;
                startBtn.setImageResource(R.drawable.start48);
                Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
            }
        } else if(view==stopBtn) {
            resetTimer();
            running = false;
            Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
        } else if(view == resetBtn){
            homeGoalBtn.setText(getString(R.string.zero));
            homePointBtn.setText(getString(R.string.dblZero));
            awayGoalBtn.setText(getString(R.string.zero));
            awayPointBtn.setText(getString(R.string.dblZero));
            totalHomePtsView.setText(getString(R.string.zero));
            totalAwayPtsView.setText(getString(R.string.zero));
            homeGoals=0;
            homePoints=0;
            awayGoals = 0;
            awayPoints=0;
            totalHomePts=0;
            totalAwayPts=0;
        } else if(view == rugbyBtn){
            //Get context the Intent was created in (The rugby Button)
            //Pass the activity class into which to pass the Intent object
            Intent rugbyIntent = new Intent(view.getContext(),Main2Activity.class);
            //Pass Intent...Call other Activity...No Data needs to be passed
            startActivityForResult(rugbyIntent,0);
        } else if(view == minutesText){
            if(paused && !running && timeLeft<2100000) {
                startBtn.setEnabled(true);
                startBtn.setImageResource(R.drawable.start48);
                pausedAt = pausedAt + 300000;
                timeLeft = pausedAt;
                stopBtn.setEnabled(true);
                setClock();
                helpText.setText(getString(R.string.blank));
            }
        } else if(view == secsText){
            if(paused && !running && timeLeft>300000) {
                startBtn.setEnabled(true);
                pausedAt = pausedAt - 300000;
                timeLeft = pausedAt;
                stopBtn.setEnabled(true);
                setClock();
            }else if(paused && !running){
                resetTimer();
            }
        }
    }
    //Method to output time
    private void setClock(){
        minutesText.setText(String.valueOf(timeLeft / 60000));
        if((timeLeft % 60000 / 1000)>=10)
            secsText.setText(String.valueOf((timeLeft % 60000 / 1000)));
        else
            secsText.setText(getString(R.string.zero)+String.valueOf(timeLeft % 60000 / 1000));
    }
    //Method to add Points
    private void changePoints(Button ptBtn, TextView totalView, int pts, int total){
        if (pts < 10)
            ptBtn.setText(getString(R.string.zero) + String.valueOf(pts));
        else
            ptBtn.setText(String.valueOf(pts));
        totalView.setText(String.valueOf(total));
    }
    //Method to add goals
    private void changeGoals(Button glBtn,TextView totalView, int gls, int total){
        glBtn.setText(String.valueOf(gls));
        totalView.setText(String.valueOf(total));
    }
    //Method to reset timer
    private void resetTimer(){
        paused = true;
        timeLeft = pausedAt = 0;
        startBtn.setEnabled(false);
        minutesText.setEnabled(true);
        stopBtn.setEnabled(false);
        rugbyBtn.setEnabled(true);
        minutesText.setText(getString(R.string.dblZero));
        secsText.setText(getString(R.string.dblZero));
        startBtn.setImageResource(R.drawable.start48);
        helpText.setText(getString(R.string.timer));
    }
}

