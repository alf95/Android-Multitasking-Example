package com.example.HW2.multitasking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    // Variable Declarations
    int red, pink, purple, deepPurple, indigo, blue, lightBlue, cyan, teal, green, lime;
    TextView firstNameText;
    TextView lastNameText;

    ProgressBar progressBar;
    Button userInfoButton, colorChangeButton;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Attaching Variables to their corresponding Views
        //dateTextView = findViewById(R.id.date_textView);
        userInfoButton = findViewById(R.id.user_info_button);
        colorChangeButton = findViewById(R.id.color_change_button);
        cardView = findViewById(R.id.card_view);
        firstNameText = findViewById(R.id.first_name_text_view);
        lastNameText = findViewById(R.id.last_name_text_view);
        progressBar = findViewById(R.id.progressBar);
        // Getting colour resources
        red = getResources().getColor(R.color.colorRed);
        pink = getResources().getColor(R.color.colorPink);
        purple = getResources().getColor(R.color.colorPurple);
        deepPurple = getResources().getColor(R.color.colorDeepPurple);
        indigo = getResources().getColor(R.color.colorIndigo);
        blue = getResources().getColor(R.color.colorBlue);
        lightBlue = getResources().getColor(R.color.colorLightBlue);
        cyan = getResources().getColor(R.color.colorCyan);
        teal = getResources().getColor(R.color.colorTeal);
        green = getResources().getColor(R.color.colorGreen);
        lime = getResources().getColor(R.color.colorLime);

        // Setting an OnClickListener on the button that changes the background colour of the cardView
        colorChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> colorList = Arrays.asList(red, pink, purple, deepPurple, indigo, blue, lightBlue, cyan, teal, green, lime);
                Random random = new Random();
                int randomColor = colorList.get(random.nextInt(colorList.size()));
                cardView.setBackgroundColor(randomColor);
            }
        });

        userInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating the Background Thread to enable task run in background

                ExecutorService service = Executors.newSingleThreadExecutor();
                service.execute(new Runnable() {


                    @Override
                    public void run() {
                        //do before execute
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                firstNameText.setText("");
                                lastNameText.setText("");
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });

                        //do in background
                        Map<String, String> userInfo;
                        try {
                            Thread.sleep(3000);
                            userInfo = BackgroundTaskInfo.getRandomUser();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        //do after execute
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                firstNameText.setText(userInfo.get("first_name"));
                                lastNameText.setText(userInfo.get("last_name"));
                            }
                        });
                    }
                });
            }
        });
    }

}