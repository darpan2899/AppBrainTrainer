package com.example.appbraintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button,button0,button1,button2,button3;
    Boolean buttonPressed;
    Random rand;
    int a,b;
    TextView timeText,scoreText,resultText,taskText;
    int locationOfCorrectAns,score,noOfQuestions,choice;
    androidx.gridlayout.widget.GridLayout gl;
    ArrayList<Integer> answers;

    public void start(View view){

        buttonPressed=true;

        if(buttonPressed){

            button.setVisibility(View.INVISIBLE);
            gl.setVisibility(View.VISIBLE);
            taskText.setVisibility(View.VISIBLE);
            scoreText.setVisibility(View.VISIBLE);
            timeText.setVisibility(View.VISIBLE);

            buttonPressed=false;

            new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeText.setText(String.valueOf(millisUntilFinished / 1000) + "s");

                }

                @Override
                public void onFinish() {
                    resultText.setText("Time up");
                    button.setText("Play Again");
                    button.setVisibility(View.VISIBLE);
                    buttonPressed=true;

                    gl.setVisibility(View.INVISIBLE);
                    taskText.setVisibility(View.INVISIBLE);
                    scoreText.setVisibility(View.INVISIBLE);
                    timeText.setVisibility(View.INVISIBLE);
                    resultText.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(),"Time UP",Toast.LENGTH_SHORT).show();



                    score=0;
                    noOfQuestions=0;

                    scoreText.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));

                    newQuestion();


                }
            }.start();


        }

    }


     public void chooseAnswer(View view)
     {
         resultText.setVisibility(View.VISIBLE);

         if(locationOfCorrectAns==(Integer.parseInt(String.valueOf(view.getTag())))){
             resultText.setText("Correct");
             score++;
         }
         else {
             resultText.setText("Wrong");
         }
         noOfQuestions++;
         scoreText.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));

         newQuestion();

     }

     public void  newQuestion(){
         a=rand.nextInt(51);
         b=rand.nextInt(51);


         locationOfCorrectAns= rand.nextInt(4);

         choice=rand.nextInt(3);

         answers.clear();

         for(int i=0;i<4;i++)
         {
             switch (choice) {

                 case 0:{
                     taskText.setText(Integer.toString(a) + "+" + Integer.toString(b));
                     if(i==locationOfCorrectAns){
                         answers.add(a+b);
                     }
                     else {
                         int wrongAns= rand.nextInt(101);

                         while(wrongAns==(a+b)){
                             wrongAns=rand.nextInt(101);
                         }

                         answers.add(wrongAns);

                     }
                     break;
                 }
                 case 1:{
                     taskText.setText(Integer.toString(a) + "-" + Integer.toString(b));
                     if(i==locationOfCorrectAns){
                         answers.add(a-b);
                     }
                     else {
                         int wrongAns= rand.nextInt(101);

                         while(wrongAns==(a-b)){
                             wrongAns=rand.nextInt(101);
                         }

                         answers.add(wrongAns);
                     }
                     break;
                 }
                 case 2:{
                     taskText.setText(Integer.toString(a) + "*" + Integer.toString(b));
                     if(i==locationOfCorrectAns){
                         answers.add(a*b);
                     }
                     else {
                         int wrongAns= rand.nextInt(101);

                         while(wrongAns==(a*b)){
                             wrongAns=rand.nextInt(101);
                         }

                         answers.add(wrongAns);
                     }
                     break;
                 }


             }

         }

         button0.setText(Integer.toString(answers.get(0)));
         button1.setText(Integer.toString(answers.get(1)));
         button2.setText(Integer.toString(answers.get(2)));
         button3.setText(Integer.toString(answers.get(3)));


     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        taskText=findViewById(R.id.taskText);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        resultText=findViewById(R.id.resultText);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        gl=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.gl);
        answers= new ArrayList<Integer>();


        gl.setVisibility(View.INVISIBLE);
        taskText.setVisibility(View.INVISIBLE);
        resultText.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.INVISIBLE);
        timeText.setVisibility(View.INVISIBLE);

        buttonPressed=false;

        rand=new Random();

        newQuestion();



    }
}
