package com.example.musta.catchkennymultiple;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameScreen extends AppCompatActivity {

    TextView scoreText, timeLeft;
    ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11;
    int score;
    //buradan aşağısı deneme
    Handler handlerRK;
    Runnable runnableRK;
    TextView userNameH;
    Integer globalCount;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        sharedPreferences = this.getSharedPreferences("com.example.musta.catchkennymultiple", Context.MODE_PRIVATE);

        userNameH = findViewById(R.id.userNameH);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userNameInput");
        userNameH.setText(userName);

        timeLeft = findViewById(R.id.timeLeft);
        scoreText = findViewById(R.id.scoreText);

        imageView = findViewById(R.id.imageView);
       /* imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);*/

        score = 0;

        ResimKacir();

        globalCount = 10000;



            new CountDownTimer(globalCount, 1000){
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft.setText("Süre: " + millisUntilFinished/1000);

                }

                @Override
                public void onFinish() {
                    timeLeft.setText("Süre Bitti");
                    imageView.setVisibility(View.INVISIBLE);
                    handlerRK.removeCallbacks(runnableRK);

                    int HighScore = sharedPreferences.getInt("storedScore",0);

                    if (score>HighScore) {

                        sharedPreferences.edit().putInt("storedScore", score).apply();

                    }


                    AlertDialog.Builder alert = new AlertDialog.Builder(GameScreen.this);
                    alert.setCancelable(false);
                    alert.setTitle("Oyun Bitti");
                    alert.setMessage("Oyunu yeniden başlatmak istiyor musunuz?");
                    alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });

                    alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Intent goBack = new Intent(GameScreen.this,WelcomePage.class);

                            startActivity(goBack);
                        }
                    });

                    alert.show();

                }

            }.start();
        }


       /* new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft.setText("Süre: " + l/1000);
            }

            @Override
            public void onFinish() {




                timeLeft.setText("Süre Bitti");

            }
        }.start();*/




    public void increaseScore (View view) {

        score++;
        scoreText.setText("Puan: " + score);
        globalCount = globalCount + 3000;

        /*

        int isClicked = 0;

if isClicked == 0 {

score += 1

isClicked = 1

}
         */


    }

    /*public void resimKacir() {

        handler = new Handler(){

            runnable = (Runnable)


        }
*/

    public void ResimKacir(){

        handlerRK = new Handler();

        runnableRK = new Runnable() {

            @Override

            public void run() {

                imageView.setVisibility(View.VISIBLE);

                Random random = new Random();

                int x = random.nextInt(2);

                int y = random.nextInt(2);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams(imageView.getLayoutParams());

                params.rowSpec = GridLayout.spec(x,2);

                params.columnSpec = GridLayout.spec(y,2);

                imageView.setLayoutParams(params);

                handlerRK.postDelayed(this,500);

            }

        };

        handlerRK.post(runnableRK);

    }

}
