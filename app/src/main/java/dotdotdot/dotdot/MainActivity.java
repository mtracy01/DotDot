package dotdotdot.dotdot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;



public class MainActivity extends ActionBarActivity {
    private static List<String> menuItems = new ArrayList<String>();
    private static ListView list;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
