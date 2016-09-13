package com.dobbs.ger.scores;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button homeTry, homePen, homeCon;
    private Button awayTry, awayPen, awayCon;
    private Button gaaBtn;
    private Button resetBtn;
    private Button minusHomeTry, minusHomePen, minusHomeCon;
    private Button minusAwayTry, minusAwayPen, minusAwayCon;
    private Button minutesText,secsText;
    private ImageButton startBtn,stopBtn;
    private TextView homePtsText, awayPtsText;
    private TextView homeTryCount, homePenCount, homeConCount;
    private TextView awayTryCount, awayPenCount, awayConCount;
    private TextView helpText;
    private int homePoints, awayPoints;
    private int totalTryCount, totalPenCount, totalConCount;
    private int totalAwayTryCount, totalAwayPenCount, totalAwayConCount;
    private Handler myHandler2;
    private long timeLeft = 0;
    private long pausedAt = 0;
    private boolean paused = true;
    private boolean running = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gaaBtn = (Button) findViewById(R.id.gaa);
        homeTry = (Button) findViewById(R.id.homeTry);
        homePen = (Button) findViewById(R.id.homePen);
        homeCon = (Button) findViewById(R.id.homeCon);
        awayTry = (Button)findViewById(R.id.awayTry);
        awayPen = (Button)findViewById(R.id.awayPen);
        awayCon = (Button)findViewById(R.id.awayCon);
        startBtn = (ImageButton)findViewById(R.id.start);
        stopBtn= (ImageButton)findViewById(R.id.stop);
        resetBtn= (Button)findViewById(R.id.ResetButton) ;
        minusHomeTry = (Button) findViewById(R.id.subtractHomeTry);
        minusHomePen = (Button) findViewById(R.id.subtractHomePen);
        minusHomeCon = (Button) findViewById(R.id.subtractHomeCon);
        minusAwayTry = (Button) findViewById(R.id.subtractAwayTry);
        minusAwayPen = (Button) findViewById(R.id.subtractAwayPen);
        minusAwayCon = (Button) findViewById(R.id.subtractAwayCon) ;
        minutesText = (Button)findViewById(R.id.minsView);
        secsText = (Button)findViewById(R.id.secsView);
        homePtsText = (TextView) findViewById(R.id.homePoints);
        awayPtsText = (TextView) findViewById(R.id.awayPoints);
        homeTryCount = (TextView) findViewById(R.id.homeTryCount);
        homePenCount = (TextView) findViewById(R.id.homePenCount);
        homeConCount = (TextView) findViewById(R.id.homeConCount);
        awayTryCount = (TextView) findViewById(R.id.awayTryCount);
        awayPenCount = (TextView) findViewById(R.id.awayPenCount);
        awayConCount = (TextView)  findViewById(R.id.awayConCount);
        helpText = (TextView) findViewById(R.id.help);
        gaaBtn.setOnClickListener(this);
        homeTry.setOnClickListener(this);
        homePen.setOnClickListener(this);
        homeCon.setOnClickListener(this);
        awayTry.setOnClickListener(this);
        awayPen.setOnClickListener(this);
        awayCon.setOnClickListener(this);
        minusHomeTry.setOnClickListener(this);
        minusHomePen.setOnClickListener(this);
        minusHomeCon.setOnClickListener(this);
        minusAwayTry.setOnClickListener(this);
        minusAwayPen.setOnClickListener(this);
        minusAwayCon.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        minutesText.setOnClickListener(this);
        secsText.setOnClickListener(this);
        minutesText.setBackgroundColor(1);
        secsText.setBackgroundColor(1);
        startBtn.setImageResource(R.drawable.start48);
        stopBtn.setImageResource(R.drawable.stop64);
        startBtn.setEnabled(false);
        stopBtn.setEnabled(false);
    }@Override
    public void onClick(View view) {
        if (view == gaaBtn) {
            //Create empty Intent Object
            Intent gaaIntent = new Intent();
            setResult(RESULT_OK, gaaIntent);
            //Send Intent Back
            finish();
        } else if (view == homeTry) {
            if(homePoints<500) {
                homePoints = homePoints + 5;
                totalTryCount++;
                changeTry(homePtsText, homeTryCount, homePoints, totalTryCount);
            }
        } else if (view == homePen) {
            if(homePoints<500) {
                homePoints = homePoints + 3;
                totalPenCount++;
                changeTry(homePtsText, homePenCount, homePoints, totalPenCount);
            }
        } else if (view == homeCon) {
            if(totalTryCount>totalConCount && homePoints<500){
                homePoints = homePoints + 2;
                totalConCount++;
                changeTry(homePtsText, homeConCount,homePoints, totalConCount);
            }
        } else if (view == minusHomeTry) {
            if (totalTryCount>totalConCount) {
                homePoints = homePoints - 5;
                totalTryCount--;
                changeTry(homePtsText, homeTryCount,homePoints, totalTryCount);
            }
        } else if (view == minusHomePen) {
            if (totalPenCount > 0) {
                homePoints = homePoints - 3;
                totalPenCount--;
                changeTry(homePtsText, homePenCount,homePoints, totalPenCount);
            }
        } else if (view == minusHomeCon) {
            if (totalConCount > 0) {
                homePoints = homePoints - 2;
                totalConCount--;
                changeTry(homePtsText, homeConCount,homePoints, totalConCount);
            }
        } else if(view == awayTry) {
            if(awayPoints<500) {
                awayPoints = awayPoints + 5;
                totalAwayTryCount++;
                changeTry(awayPtsText, awayTryCount, awayPoints, totalAwayTryCount);
            }
        } else if(view == awayPen) {
            if(awayPoints<500) {
                awayPoints = awayPoints + 3;
                totalAwayPenCount++;
                changeTry(awayPtsText, awayPenCount, awayPoints, totalAwayPenCount);
            }
        } else if (view == awayCon) {
            if(totalAwayTryCount>totalAwayConCount && awayPoints<500) {
                awayPoints=awayPoints+2;
                totalAwayConCount ++;
                changeTry(awayPtsText, awayConCount,awayPoints, totalAwayConCount);
            }
        } else if(view == minusAwayTry){
            if (totalAwayTryCount>totalAwayConCount){
                awayPoints=awayPoints-5;
                totalAwayTryCount--;
                changeTry(awayPtsText, awayTryCount,awayPoints, totalAwayTryCount);
            }
        } else if (view == minusAwayPen){
            if (totalAwayPenCount > 0){
                awayPoints=awayPoints-3;
                totalAwayPenCount --;
                changeTry(awayPtsText, awayPenCount,awayPoints, totalAwayPenCount);
            }
        } else if (view == minusAwayCon){
            if (totalAwayConCount > 0){
                awayPoints=awayPoints-2;
                totalAwayConCount--;
                changeTry(awayPtsText, awayConCount,awayPoints, totalAwayConCount);
            }

        } else if(view==startBtn) {
            if(paused) {
                timeLeft = pausedAt;
                myHandler2 = new Handler();
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        timeLeft = timeLeft - 1000;
                        if (timeLeft >= 0) {
                            stopBtn.setEnabled(true);
                            gaaBtn.setEnabled(false);
                            //Call the thread every 2nd to print time
                            myHandler2.postDelayed(this, 1000);
                            //Call Method to output time
                            setClock();
                            running = true;
                        }
                        if (timeLeft == 0) {
                            minutesText.setText(getString(R.string.dblZero));
                            startBtn.setEnabled(false);
                            stopBtn.setEnabled(false);
                            gaaBtn.setEnabled(true);
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
                myHandler2.postDelayed(myRunnable, 1000);
                paused = false;
                Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
                startBtn.setImageResource(R.drawable.pause48);
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
            homePtsText.setText(getString(R.string.zero));
            homeTryCount.setText(getString(R.string.zero));
            homePenCount.setText(getString(R.string.zero));
            homeConCount.setText(getString(R.string.zero));
            awayPtsText.setText(getString(R.string.zero));
            awayTryCount.setText(getString(R.string.zero));
            awayPenCount.setText(getString(R.string.zero));
            awayConCount.setText(getString(R.string.zero));
            homePoints=0;
            awayPoints=0;
            totalTryCount = 0;
            totalPenCount=0;
            totalConCount=0;
            totalAwayTryCount=0;
            totalAwayPenCount=0;
            totalAwayConCount=0;
        } else if(view == minutesText){
            if(paused && !running && timeLeft<=2100000) {
                startBtn.setEnabled(true);
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
            @SuppressLint("SetTextI18n")
            private void setClock(){
                minutesText.setText(String.valueOf(timeLeft / 60000));
                if((timeLeft % 60000 / 1000)>=10)
                    secsText.setText(String.valueOf((timeLeft % 60000 / 1000)));
                else
                    secsText.setText(String.valueOf(0)+String.valueOf((timeLeft % 60000 / 1000)));
            }
            private void changeTry(TextView ptsView, TextView tryView,int pts, int count){
                ptsView.setText(String.valueOf(pts));
                tryView.setText(String.valueOf(count));
            }
    //Method to reset timer
    private void resetTimer(){
        paused = true;
        timeLeft = pausedAt = 0;
        startBtn.setEnabled(false);
        minutesText.setEnabled(true);
        stopBtn.setEnabled(false);
        gaaBtn.setEnabled(true);
        minutesText.setText(getString(R.string.dblZero));
        secsText.setText(getString(R.string.dblZero));
        startBtn.setImageResource(R.drawable.start48);
        helpText.setText(getString(R.string.timer));
    }
}
