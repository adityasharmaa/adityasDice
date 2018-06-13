package com.example.adi.dice2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button holdBtn, rollBtn, resetBtn, addBtn;
    TextView tvGameStatus, tvPlayerScore, tvComputerScore, tvStatus;
    ImageView imageView;
    Random ran = new Random();
    int playerScore = 0, compScore = 0, r, n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvComputerScore = (TextView) findViewById(R.id.scoreComp);
        tvGameStatus = (TextView) findViewById(R.id.gameStatus);
        tvStatus = (TextView) findViewById(R.id.status);
        tvPlayerScore = (TextView) findViewById(R.id.scorePlayer);

        imageView = (ImageView) findViewById(R.id.imgView);
        addBtn = (Button) findViewById(R.id.add);
        holdBtn = (Button) findViewById(R.id.hold);
        resetBtn = (Button) findViewById(R.id.reset);
        rollBtn = (Button) findViewById(R.id.roll);

        start();
            rollBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    n = 1;
                    rollBtn.setEnabled(false);
                    r = ran.nextInt(6) + 1;
                    switch (r) {
                        case 1:
                            imageView.setImageResource(R.drawable.dice1);
                            tvStatus.setText("Computer's Turn");
                            compTurn();
                            rollBtn.setEnabled(true);
                            n = 0;
                            return;
                        case 2:
                            imageView.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            imageView.setImageResource(R.drawable.dice6);
                            break;
                    }
                }
            });

        holdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n == 0)
                    return;
                rollBtn.setEnabled(true);
                n = 0;
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n==0)
                    return;
                playerScore += r;
                tvPlayerScore.setText(playerScore + "");
                if(playerScore>=100) {
                    tvStatus.setText("YOU WIN!");
                    holdBtn.setEnabled(false);
                    rollBtn.setEnabled(false);
                    addBtn.setEnabled(false);
                    imageView.setImageResource(R.drawable.dice1);
                    return;
                }
                tvStatus.setText("Computer's Turn");
                compTurn();
                n = 0;
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerScore = 0;
                compScore = 0;
                r = 0;
                n = 0;
                tvComputerScore.setText("0");
                tvPlayerScore.setText("0");
                start();
            }
        });
    }

    public void enable() {
        holdBtn.setEnabled(true);
        addBtn.setEnabled(true);
        rollBtn.setEnabled(true);
        resetBtn.setEnabled(true);
    }

    public void disable() {
        holdBtn.setEnabled(false);
        addBtn.setEnabled(false);
        rollBtn.setEnabled(false);
        resetBtn.setEnabled(false);
    }

    public void start() {
        tvStatus.setText("Welcome To SCARNE'S DICE game");
        disable();
        Handler h = new Handler();
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                enable();
                tvStatus.setText("Your Turn");
            }
        };
        h.postDelayed(rn, 3000);
    }

    public void compTurn() {
        Handler h = new Handler();
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                int a = ran.nextInt(6) + 1;
                switch (a) {
                    case 1:
                        imageView.setImageResource(R.drawable.dice1);
                        tvStatus.setText("Your Turn");
                        rollBtn.setEnabled(true);
                        return;
                    case 2:
                        imageView.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.dice6);
                        break;
                }
                compScore += a;
                tvComputerScore.setText(compScore + "");
                if(compScore>=100) {
                    tvStatus.setText("COMPUTER WINS!");
                    holdBtn.setEnabled(false);
                    rollBtn.setEnabled(false);
                    addBtn.setEnabled(false);
                    imageView.setImageResource(R.drawable.dice1);
                    return;
                }
                tvStatus.setText("Your Turn");
                rollBtn.setEnabled(true);
            }
        };
        h.postDelayed(rn, 2000);
    }
}


