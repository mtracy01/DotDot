package dotdotdot.dotdot;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Random;

public class GameActivity extends ActionBarActivity {
    int score=0;
    int highScore=0;
    ImageButton[] dots = new ImageButton[20];
    View[] rows = new View[5];
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        //Debug check to see if we are registering properly
        /*AlertDialog.Builder adb=new AlertDialog.Builder(context);
        adb.setTitle("Success!");
        adb.setPositiveButton("Ok...",new DialogInterface.OnClickListener(){ public void onClick(DialogInterface dialog, int id) {dialog.cancel();}});
        AlertDialog ad=adb.create();
        ad.show();*/

        //Set Listeners for dots in first row
        for(int i=0;i<4;i++){

            dots[i].setClickable(true);
            dots[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //if timer has not started, start it
                    if(view.getVisibility()==View.VISIBLE){
                        score++;

                        //Debug check to see if we are registering properly
                        /*AlertDialog.Builder adb=new AlertDialog.Builder(context);
                        adb.setTitle("Success!");
                        adb.setPositiveButton("Ok...",new DialogInterface.OnClickListener(){ public void onClick(DialogInterface dialog, int id) {dialog.cancel();}});
                        AlertDialog ad=adb.create();
                        ad.show();*/
                        advanceRows();
                    }
                    else
                        gameOver(-1);
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
                    dots[j].setVisibility(View.INVISIBLE);
                }
                x++;
            }
            //once we have column number of dot,
            //apply it to row below
            dots[(i-1)*4+colNum].setVisibility(View.VISIBLE);
        }
        //On the top row, set a new random configuration
        clearAndSet(4);

    }

    //Set a row to blank
    public void clearRow(int rowNum){
        int j=rowNum*4+4;
        for(int i=rowNum*4;i<j;i++){
            if(dots[i].getVisibility()==View.VISIBLE)
                    dots[i].setVisibility(View.INVISIBLE);
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
            if(x==nextDot)
                dots[i].setVisibility(View.VISIBLE);
            else
                dots[i].setVisibility(View.INVISIBLE);
        }

    }

    //End game conditions
    public void gameOver(int condition){
        if(condition==-1){
            //player hit a wrong tile
        }
        else{
            //player finished after time
        }
    }
}
