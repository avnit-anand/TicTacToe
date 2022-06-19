package com.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation : 0-X & 1-O & 2=null
     int activePlayer =0;
     int[] gamestate = {2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 };

     int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                             {0,4,8},{2,4,6}};

    int count=0;
    public void playerTap (View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive||count==9){
            gameReset(view);
        }
        if(gamestate[tappedImage]==2){
            count++;
            gamestate[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);

            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1 ;
                TextView status = findViewById(R.id.status);
                if(count==9){
                    status.setText("Game Tie, Tap to play");
                }else{
                    status.setText("O's Turn Tap to play");
                }

            }else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status = findViewById(R.id.status);
                if(count==9){
                    status.setText("Game Tie, Tap to play");
                }else{
                    status.setText("X's Turn Tap to play");
                }

            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int[] winPostion:winPositions){
            if(gamestate[winPostion[0]]==gamestate[winPostion[1]]&& gamestate[winPostion[1]]==gamestate[winPostion[2]]&&
                gamestate[winPostion[0]]!=2){
                // Somebody has won - Find out who has won the game
                String str;
                gameActive=false;
                if(gamestate[winPostion[0]]==0){
                    str="X has won\n Tap for next game";
                }else{
                    str="O has won\n Tap for next game";
                }
                // Update the status bar
                TextView status =findViewById(R.id.status);
                status.setText(str);
            }
        }
    }


    public void gameReset(View view){
        gameActive = true;
            count=0;
            activePlayer=0;
            for(int i=0;i<gamestate.length;i++){
                gamestate[i]=2;
            }
            ((ImageView)findViewById(R.id.zero1)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero2)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero3)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero4)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero5)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero6)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero7)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero8)).setImageResource(0);
            ((ImageView)findViewById(R.id.zero9)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}