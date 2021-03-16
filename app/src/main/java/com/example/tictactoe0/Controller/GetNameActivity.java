package com.example.tictactoe0.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe0.R;

@SuppressWarnings("ALL")
public class GetNameActivity extends AppCompatActivity {


     Button mPlay;
     EditText mPlayer1, mPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);

        //Inflation of activity_get_name
        mPlay = findViewById(R.id.activity_get_name_play_btn);
        mPlayer1 = findViewById(R.id.activity_get_name_player1_input);
        mPlayer2 = findViewById(R.id.activity_get_name_player2_input);

        /*
        when the players clicks playButton:
         - go to MainActivity
         - plass the player1 and player2 to MainActivity
         */
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = mPlayer1.getText().toString().trim();
                String name2 = mPlayer2.getText().toString().trim();

                if ((name2.isEmpty() && name1.isEmpty()) || name2.isEmpty() || name1.isEmpty()) {
                    Toast.makeText(GetNameActivity.this,"Please enter the fields",Toast.LENGTH_SHORT).show();
                }
               else {
                    Intent intent = new Intent(GetNameActivity.this, MainActivity.class);
                    intent.putExtra("name1", name1);
                    intent.putExtra("name2", name2);
                    startActivity(intent);
                }

            }
        });

    }
}