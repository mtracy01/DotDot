package dotdotdot.dotdot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;


public class GameActivity extends ActionBarActivity {
    int score=0;
    ImageView[] dots = new ImageView[20];
    TextureView[] rows = new TextureView[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get Rows from bottom to top
        rows[0]=  (TextureView)findViewById(R.id.textureView4);
        rows[1]=  (TextureView)findViewById(R.id.textureView3);
        rows[2]=  (TextureView)findViewById(R.id.textureView2);
        rows[3]=  (TextureView)findViewById(R.id.textureView1);
        rows[4]=  (TextureView)findViewById(R.id.textureView);

        //Get dots from left to right, bottom to top
        dots[0]=(ImageView)findViewById(R.id.imageView);
        dots[1]=(ImageView)findViewById(R.id.imageView2);
        dots[2]=(ImageView)findViewById(R.id.imageView3);
        dots[3]=(ImageView)findViewById(R.id.imageView4);
        dots[4]=(ImageView)findViewById(R.id.imageView5);
        dots[5]=(ImageView)findViewById(R.id.imageView6);
        dots[6]=(ImageView)findViewById(R.id.imageView7);
        dots[7]=(ImageView)findViewById(R.id.imageView8);
        dots[8]=(ImageView)findViewById(R.id.imageView9);
        dots[9]=(ImageView)findViewById(R.id.imageView10);
        dots[10]=(ImageView)findViewById(R.id.imageView11);
        dots[11]=(ImageView)findViewById(R.id.imageView12);
        dots[12]=(ImageView)findViewById(R.id.imageView13);
        dots[13]=(ImageView)findViewById(R.id.imageView14);
        dots[14]=(ImageView)findViewById(R.id.imageView15);
        dots[15]=(ImageView)findViewById(R.id.imageView16);
        dots[16]=(ImageView)findViewById(R.id.imageView17);
        dots[17]=(ImageView)findViewById(R.id.imageView18);
        dots[18]=(ImageView)findViewById(R.id.imageView19);
        dots[19]=(ImageView)findViewById(R.id.imageView20);

        for(int i=0;i<5;i++){

        }
        //TODO: Selectively hide 3 of the 4 dots in each row
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
}
