package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton xBtn,oBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xBtn=findViewById(R.id.imageButtonX);
        oBtn=findViewById(R.id.imageButtonO);

        xBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("Letter",'x');
                startActivity(intent);
            }
        });

        oBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("Letter",'o');
                startActivity(intent);
            }
        });


    }
}