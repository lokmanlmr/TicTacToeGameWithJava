package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {


    ArrayList<Character> xoList=new ArrayList<>();
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    TextView textView;
    public ResultFragment resultFragment=new ResultFragment(0);
    int i=0;
    char firstPlayer, secondPlayer,Letter;
    int firstColor,secondColor,Fimg,Simg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Knowing who's gonna start first
        Intent intent=getIntent();
        Letter=intent.getCharExtra("Letter",'0');
        if(Letter=='x'){
            firstPlayer='X';
            secondPlayer='O';
            firstColor=ContextCompat.getColor(this, R.color.pink);;
            secondColor=ContextCompat.getColor(this, R.color.green);
            Fimg=R.drawable.justx;
            Simg=R.drawable.justo;
        }else{
            firstPlayer='O';
            secondPlayer='X';
            firstColor=ContextCompat.getColor(this, R.color.green);
            secondColor=ContextCompat.getColor(this, R.color.pink);;
            Fimg=R.drawable.justo;
            Simg=R.drawable.justx;
        }

        //declar all image buttons
        textView=findViewById(R.id.player_turn_text);



        for (int i = 0; i < 9; i++) {
            xoList.add('0');
        }


        textView.setTextColor(firstColor);
    }

    char currentChar(char first,char second){
        if(i%2==0){
            return first;
        }
        return second;
    }

    int currentImgRessource(int first,int second){
        if(i%2==0){
            return first;
        }
        return second;
    }

    int currentTextColor(int first,int second){
        if(i%2==0){
            textView.setText("Player 2's Turn");
            return second;
        }

        textView.setText("Player 1's Turn");
        return first;
    }

    public void setimg(View view) {
        ImageView img =(ImageView) view;
        if(((xoList.get(Integer.parseInt(img.getTag().toString()))) =='0')&&!checkWinner(xoList)){

            fadeInImage(img, currentImgRessource(Fimg, Simg));
            fadeInTextColor(textView, currentTextColor(firstColor, secondColor));
            xoList.set(Integer.parseInt(img.getTag().toString()),currentChar(firstPlayer,secondPlayer));
            i++;
            checkWinner(xoList);
        }else {
            img.setActivated(false);
            checkWinner(xoList);
        }

    }

    public void fadeInImage(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);

        // ObjectAnimator for fade-in animation on alpha property
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        fadeIn.setDuration(400); // Set the duration in milliseconds

        // Start the fade-in animation
        fadeIn.start();
    }

    private void fadeInTextColor(TextView textView, int color) {
        // Set the text color immediately (before the animation starts)
        textView.setTextColor(color);

        // ObjectAnimator for fade-in animation on alpha property
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
        fadeIn.setDuration(400); // Set the duration in milliseconds

        // Start the fade-in animation
        fadeIn.start();
    }

    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.constraintLayout, fragment,"MyTag")
                .commit();
    }


    // Function to remove a fragment from a ConstraintLayout
    public static void removeFragment(
            FragmentManager fragmentManager,
            Fragment fragment) {
        // Begin the transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Remove the fragment
        transaction.remove(fragment);
        // Commit the transaction
        transaction.commit();
    }

    public boolean checkWinner(ArrayList<Character> list){
        boolean result=false;
        if(i>4){
            int state,color=firstColor;
            String row1= String.valueOf(list.get(0)+list.get(1)+list.get(2)),
            row2= String.valueOf(list.get(3)+list.get(4)+list.get(5)),
            row3= String.valueOf(list.get(6)+list.get(7)+list.get(8)),
            column1= String.valueOf(list.get(0)+list.get(3)+list.get(6)),
            column2= String.valueOf(list.get(1)+list.get(4)+list.get(7)),
            column3= String.valueOf(list.get(2)+list.get(5)+list.get(8)),
            diagonal1= String.valueOf(list.get(0)+list.get(4)+list.get(8)),
            diagonal2= String.valueOf(list.get(2)+list.get(4)+list.get(6)),
            Firstplayerstr= String.valueOf(firstPlayer+firstPlayer+firstPlayer),
            Secondplayerstr= String.valueOf(secondPlayer+secondPlayer+secondPlayer);

            if(row1.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(row1.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(row2.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(row2.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(row3.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(row3.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column1.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column1.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column2.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column2.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column3.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(column3.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(diagonal1.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(diagonal1.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(diagonal2.equals(Firstplayerstr)){
                state=0;color=firstColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(diagonal2.equals(Secondplayerstr)){
                state=1;color=secondColor;
                resultFragment=new ResultFragment(state,color);
                loadFragment(resultFragment);
                return true;
            }else if(i>8){
                state=2;
                resultFragment=new ResultFragment(state);
                loadFragment(resultFragment);
                return true;
            }

        }
        return result;
    }
    public void reset(View view){
        removeFragment(getSupportFragmentManager(),resultFragment);
        textView.setText("Player 1's Turn");
        i=0;
        textView.setTextColor(firstColor);
        xoList.clear();
        for (int i = 0; i < 9; i++) {
            xoList.add('0');
        }

        ImageView img1=findViewById(R.id.x00);
        ImageView img2=findViewById(R.id.x10);
        ImageView img3=findViewById(R.id.x20);
        ImageView img4=findViewById(R.id.x01);
        ImageView img5=findViewById(R.id.x11);
        ImageView img6=findViewById(R.id.x21);
        ImageView img7=findViewById(R.id.x02);
        ImageView img8=findViewById(R.id.x12);
        ImageView img9=findViewById(R.id.x22);


        img1.setImageResource(0);
        img2.setImageResource(0);
        img3.setImageResource(0);
        img4.setImageResource(0);
        img5.setImageResource(0);
        img6.setImageResource(0);
        img7.setImageResource(0);
        img8.setImageResource(0);
        img9.setImageResource(0);
    }

    public void back(View view){
        Intent i=new Intent(GameActivity.this,MainActivity.class);
        startActivity(i);
    }

}