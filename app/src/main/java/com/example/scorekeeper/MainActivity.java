package com.example.scorekeeper;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTeam1, scoreTeam2;
    private int score1 = 0;
    private int score2 = 0;

    private ImageButton minusteam1,minusteam2, plusteam1, plusteam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            if (savedInstanceState != null) {
                score1 = savedInstanceState.getInt("STATE_SCORE_1");
                score2 = savedInstanceState.getInt("STATE_SCORE_2");

                scoreTeam1.setText(String.valueOf(score1));
                scoreTeam2.setText(String.valueOf(score2));
            }

            return insets;
        });


        scoreTeam1 = findViewById(R.id.scoreTeam1);
        scoreTeam2 = findViewById(R.id.scoreTeam2);

        minusteam1 = findViewById(R.id.minusTeam1);
        minusteam1.setOnClickListener(v -> {
            if (score1 - 1 >= 0) {
                score1--;
                scoreTeam1.setText(String.valueOf(score1));
            }

        });

        plusteam1 = findViewById(R.id.plusTeam1);
        plusteam1.setOnClickListener(v -> {
            score1++;
            scoreTeam1.setText(String.valueOf(score1));
        });

        minusteam2 = findViewById(R.id.minusTeam2);
        minusteam2.setOnClickListener(v -> {
            if (score2 - 1 >= 0) {
                score2--;
                scoreTeam2.setText(String.valueOf(score2));
            }
        });

        plusteam2 = findViewById(R.id.plusTeam2);
        plusteam2.setOnClickListener(v -> {
            score2++;
            scoreTeam2.setText(String.valueOf(score2));
        });



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
//Save the scores
        outState.putInt("STATE_SCORE_1", score1);
        outState.putInt("STATE_SCORE_2", score2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);


        //Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int night_mode = AppCompatDelegate.getDefaultNightMode();

            if(night_mode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);;
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}