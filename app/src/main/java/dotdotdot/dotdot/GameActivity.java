package dotdotdot.dotdot;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Random;

public class GameActivity extends FragmentActivity {
    int score=0;
    int gameEnded=0;
    int highScore=0;
    int firstHit=0;
    ImageButton[] dots = new ImageButton[20];
    ImageButton[] blanks= new ImageButton[20];
    View[] rows = new View[5];
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Attempt to retrieve a stored high score
        SharedPreferences scoreHistory = getSharedPreferences("scoreLog",MODE_PRIVATE);
        if(scoreHistory.contains("HighScore")){
            highScore=scoreHistory.getInt("HighScore",0);
        }
        else{
            SharedPreferences.Editor e = scoreHistory.edit();
            e.putInt("HighScore",0);
            e.commit();
        }
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        //Get Rows from bottom to top
        rows[0]=  (View)findViewById(R.id.textureView4);
        rows[1]=  (View)findViewById(R.id.textureView3);
        rows[2]=  (View)findViewById(R.id.textureView2);
        rows[3]=  (View)findViewById(R.id.textureView1);
        rows[4]=  (View)findViewById(R.id.textureView);

        //Get dots from left to right, bottom to top
        dots[0]=(ImageButton)findViewById(R.id.imageView);
        dots[1]=(ImageButton)findViewById(R.id.imageView2);
        dots[2]=(ImageButton)findViewById(R.id.imageView3);
        dots[3]=(ImageButton)findViewById(R.id.imageView4);
        dots[4]=(ImageButton)findViewById(R.id.imageView5);
        dots[5]=(ImageButton)findViewById(R.id.imageView6);
        dots[6]=(ImageButton)findViewById(R.id.imageView7);
        dots[7]=(ImageButton)findViewById(R.id.imageView8);
        dots[8]=(ImageButton)findViewById(R.id.imageView9);
        dots[9]=(ImageButton)findViewById(R.id.imageView10);
        dots[10]=(ImageButton)findViewById(R.id.imageView11);
        dots[11]=(ImageButton)findViewById(R.id.imageView12);
        dots[12]=(ImageButton)findViewById(R.id.imageView13);
        dots[13]=(ImageButton)findViewById(R.id.imageView14);
        dots[14]=(ImageButton)findViewById(R.id.imageView15);
        dots[15]=(ImageButton)findViewById(R.id.imageView16);
        dots[16]=(ImageButton)findViewById(R.id.imageView17);
        dots[17]=(ImageButton)findViewById(R.id.imageView18);
        dots[18]=(ImageButton)findViewById(R.id.imageView19);
        dots[19]=(ImageButton)findViewById(R.id.imageView20);


        blanks[0]=(ImageButton)findViewById(R.id.blankView);
        blanks[1]=(ImageButton)findViewById(R.id.blankView2);
        blanks[2]=(ImageButton)findViewById(R.id.blankView3);
        blanks[3]=(ImageButton)findViewById(R.id.blankView4);
        blanks[4]=(ImageButton)findViewById(R.id.blankView5);
        blanks[5]=(ImageButton)findViewById(R.id.blankView6);
        blanks[6]=(ImageButton)findViewById(R.id.blankView7);
        blanks[7]=(ImageButton)findViewById(R.id.blankView8);
        blanks[8]=(ImageButton)findViewById(R.id.blankView9);
        blanks[9]=(ImageButton)findViewById(R.id.blankView10);
        blanks[10]=(ImageButton)findViewById(R.id.blankView11);
        blanks[11]=(ImageButton)findViewById(R.id.blankView12);
        blanks[12]=(ImageButton)findViewById(R.id.blankView13);
        blanks[13]=(ImageButton)findViewById(R.id.blankView14);
        blanks[14]=(ImageButton)findViewById(R.id.blankView15);
        blanks[15]=(ImageButton)findViewById(R.id.blankView16);
        blanks[16]=(ImageButton)findViewById(R.id.blankView17);
        blanks[17]=(ImageButton)findViewById(R.id.blankView18);
        blanks[18]=(ImageButton)findViewById(R.id.blankView19);
        blanks[19]=(ImageButton)findViewById(R.id.blankView20);
        final EditText mTextField = (EditText)findViewById(R.id.editText);

        for(int i=0;i<20;i++){
            blanks[i].setVisibility(View.GONE);
            dots[i].setVisibility(View.GONE);
            if(i<4) {
                blanks[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        gameOver(-1);
                    }
                });
            }
        }
        //Set Listeners for dots in first row
        for(int i=0;i<4;i++){

            dots[i].setClickable(true);

            dots[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //if timer has not started, start it
                    if(view.getVisibility()==View.VISIBLE){
                        if(firstHit==0){
                            firstHit=1;
                            new CountDownTimer(30000, 1) {
                                public void onTick(long millisUntilFinished) {
                                     if(gameEnded !=1) mTextField.setText("" + millisUntilFinished);
                                }

                                public void onFinish() {
                                    gameOver(-1);
                                }
                            }.start();
                        }
                        if(gameEnded!=1){
                            score++;
                            advanceRows();
                        }
                        else
                            gameOver(-1);

                    }

                }
            });
        }
        startGame();
        //TODO: Once First Dot is pressed, start the timer


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //game progression begins here
    public void startGame(){
        score=0;
        gameEnded=0;
        firstHit=0;
        for(int i=0;i<5;i++)
            clearAndSet(i);

        //TODO: Reset Timer
        //TODO: Once first dot clicked, start timer

    }

    //advance the dots for each row and set a new top row
    public void advanceRows(){
        //first, clear first row, then take the row above
        //and set dot below
        clearRow(0);
        for(int i=1;i<5;i++){
            int colNum=0;
            int x=0;
            for(int j=i*4;j<i*4+4;j++){
                if(dots[j].getVisibility()==View.VISIBLE){
                    colNum=x;
                    dots[j].setVisibility(View.GONE);
                    blanks[j].setVisibility(View.VISIBLE);
                }
                x++;
            }
            //once we have column number of dot,
            //apply it to row below
            blanks[(i-1)*4+colNum].setVisibility(View.GONE);
            dots[(i-1)*4+colNum].setVisibility(View.VISIBLE);
        }
        //On the top row, set a new random configuration
        clearAndSet(4);

    }

    //Set a row to blank
    public void clearRow(int rowNum){
        int j=rowNum*4+4;
        for(int i=rowNum*4;i<j;i++) {
            if (dots[i].getVisibility() == View.VISIBLE) {
                dots[i].setVisibility(View.GONE);
                blanks[i].setVisibility(View.VISIBLE);

                //dots[i].setBackgroundColor();
            }
        }
    }
    //set all images in the selected row to hidden with one item
    //in row visible
    public void clearAndSet(int rowNum){

        //Choose row's new dot at random
        Random rand=new Random();
        int nextDot=rand.nextInt(4);

        //Reset row with only 1 visible dot
        //chosen at random
        int x=0;
        int j=4*rowNum+4;
        for(int i=4*rowNum;i<j;i++, x++){
            if(x==nextDot){
                blanks[i].setVisibility(View.GONE);
                dots[i].setVisibility(View.VISIBLE);
            }
            else {
                dots[i].setVisibility(View.GONE);
                blanks[i].setVisibility(View.VISIBLE);
            }
        }

    }

    //End game conditions
    public void gameOver(int condition){
        gameEnded=1;
        if(condition==-1){
            //player hit a wrong tile
            AlertDialog.Builder adb=new AlertDialog.Builder(context);
            adb.setTitle("Game Over!");
            if(score > highScore) {
                highScore = score;
                SharedPreferences scoreHistory = getSharedPreferences("scoreLog",MODE_PRIVATE);
                SharedPreferences.Editor e = scoreHistory.edit();
                e.putInt("HighScore",highScore);
                e.commit();

            }
            adb.setMessage("Your Score: "+ score +"\nHigh Score: " + highScore + "\n");
            adb.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startGame();
                    dialog.cancel();
                }
            });
            adb.setNegativeButton("Main Menu",new DialogInterface.OnClickListener(){ public void onClick(DialogInterface dialog, int id) {Intent intent = new Intent(GameActivity.this, MainActivity.class); startActivity(intent); }});
            AlertDialog ad=adb.create();
            ad.show();
        }
        else{
            //player finished after time
        }
    }
}
