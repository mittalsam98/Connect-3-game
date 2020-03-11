package com.myappcompany.mittal.connect3game;

import android.graphics.LinearGradient;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        //0:yellow,1:red,:2:empty
        int activeplayer = 0;
        int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        boolean gameDecider = true;

        public void dropIn (View view){
            ImageView image = (ImageView) view;
            int temp = Integer.parseInt(image.getTag().toString());

            if (gameState[temp] == 2 && gameDecider) {
                gameState[temp] = activeplayer;
                image.setTranslationY(-1000);

                if (activeplayer == 0) {
                    image.setImageResource(R.drawable.yellow);
                    activeplayer = 1;
                }
                else {
                    image.setImageResource(R.drawable.red);
                    activeplayer = 0;
                }

                image.animate().translationYBy(1000).rotation(720).setDuration(300);

                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        //someone has won
                        String message ;
                        if (activeplayer == 0) {
                            message = "Red has won";
                        } else
                            message = "Yellow has won";
                        gameDecider = false;

                        Button but=(Button)findViewById(R.id.button);
                        TextView txt=(TextView)findViewById(R.id.textView);
                        txt.setText(message);
                        but.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.VISIBLE);


                    }
                }
            }
        }


    public void onClik(View view) {
        Button butt=(Button)findViewById(R.id.button);
        TextView txt=(TextView)findViewById(R.id.textView);
        Log.i("info","pressed");
        butt.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        GridLayout gridlayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0 ; i<gridlayout.getChildCount(); i++) {
            ImageView image = (ImageView) gridlayout.getChildAt(i);
            image.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++) {
            gameState[i] = 2;
        }

        activeplayer = 0;
        gameDecider = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
