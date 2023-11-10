package com.example.tictactoegame;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    private int state,wincolor= Color.WHITE;

    TextView textView;

    public ResultFragment(int state,int winColor) {
        this.state=state;
        this.wincolor=winColor;
    }
    public ResultFragment(int state) {
        this.state=state;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String str ="";

        switch (state){
            case 0:
                str="Player 1 Wins";
            break;
            case 1:
                str="Player 2 Wins";
            break;
            case 2:
                str="Draw";
            break;
        }


        View view =inflater.inflate(R.layout.fragment_result, container, false);

            textView=view.findViewById(R.id.Win_or_Loss);
            textView.setText(str);
            textView.setTextColor(wincolor);

        view.setAlpha(0f);

        // ObjectAnimator for fade-in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        fadeIn.setDuration(400);  // Set the duration in milliseconds

        // Start the fade-in animation
        fadeIn.start();
        return view;
    }

}