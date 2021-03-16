package com.example.tictactoe0.Controller;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe0.R;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private Button[][] buttons = new Button[3][3];
  private  boolean player1Turn = true;
  private int  player1Points, player2Points;
  private TextView player1, player2;
  private Button buttonReset;
  private static final String TAG = "MainActivity";
  private int roundCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //force the icon to show on devices with Android 5.0
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        player1 = findViewById(R.id.activity_main_player1_txt);
        player2 = findViewById(R.id.activity_main_player2_txt);
        buttonReset = findViewById(R.id.activity_main_reset_btn);



        /*
        inflate nine buttons at the same time
        each one of the nine buttons when it is clicked, is gpoing to do the same function
         */
        for (int i = 0; i < 3; i++) {
            for (int j= 0; j < 3; j++) {
              String buttonID = "activity_main_btn_" + i + j;
              int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
              buttons[i][j] = findViewById(resID);
              buttons[i][j].setOnClickListener(this);

                Typeface copperplateGothicLight = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/right-hand-bold-dash.ttf");
                buttons[i][j].setTypeface(copperplateGothicLight);
            }
        }

     updatePointsText();


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetGame();
            }
        });



    }

    @Override
    public void onClick(View v) {

        if (!((Button)v).getText().toString().equals("")) {
          return;
        }



        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;
        Log.d(TAG, "onClick() called with: "+ roundCount);

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            }
            else {
               plaer2Wins();
            }
        }
        if (roundCount == 9) {
            draw();
        }
        else {
            player1Turn = !player1Turn;
        }


    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
              field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
          && field[i][0].equals(field[i][2])
          && !field[i][0].equals("")) {
              return true;
          }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
        && field[0][0].equals(field[2][2])
        && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
        && field[0][2].equals(field[2][0])
        && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this,"Player 1 wins!" ,Toast.LENGTH_SHORT).show();
         updatePointsText();
         resetBoard();
    }

    private void plaer2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    private void resetBoard() {
       for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
               buttons[i][j].setText("");
           }

       }

        roundCount = 0;
        player1Turn = true;
    }

    private void updatePointsText() {
       String name1 = getIntent().getStringExtra("name1");
       String name2 = getIntent().getStringExtra("name2");
       player1.setText(name1 + ":" + player1Points);
       player2.setText(name2 + ":" + player2Points);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}