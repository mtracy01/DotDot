package dotdotdot.dotdot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends FragmentActivity {
    private static List<String> menuItems = new ArrayList<String>();
    private static ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        //Here, I associate the xml id of the ListView (the
        //scrollable list) with an object in Java so I can
        //modify it and set its parameters
        list = (ListView)findViewById(R.id.listView);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        int flag = menuItems.size();
        //Here I add items to the list of menu items which will
        //be added to the ListView later
        if (flag == 0)
        {
            menuItems.add("Start Game");
            menuItems.add("Leaderboard");
            menuItems.add("Settings");
            flag++;
        }



        //Call method to update the list with menuItems
        updateFiles();

        //Below, I set the onItemClickListener, which specifies
        //what action is taken when the item is clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {    //Start Game
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in,R.anim.left_out);
                }
                else if(position==1){   //Leaderboard
                    Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                    startActivity(intent);
                }
                else if(position==2) {   //Settings
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void updateFiles() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menuItems);
        list.setAdapter(adapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
