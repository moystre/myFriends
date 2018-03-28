package com.easv.myfriends.myfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.easv.myfriends.myfriends.adapter.FriendsAdapter;
import com.easv.myfriends.myfriends.model.Friend;
import com.easv.myfriends.myfriends.service.FriendsService;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //    <------------------- DECLARATIONS ------------------->
    ArrayList<Friend> friendsList;
    FriendsService friendsService;
    FriendsAdapter adapter;
    ListView listView;

    //filtering and sorting -> adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        friendsList = new ArrayList<Friend>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //    <------------------- DEFINITIONS ------------------->

        friendsService = new FriendsService(this);
        //TODO SEPERATE THREAD FOR LOADING STUFF FROM DB
         assignDataBaseDataToFriendsList();
        adapter = new FriendsAdapter(this, friendsList);
        listView = (ListView) findViewById(R.id.friendsListView);

        //    <------------------- SETTING LISTVIEW ADAPTER ------------------->

        listView.setAdapter(adapter);

        //    <------------------- LISTENERS ------------------->

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    public boolean onItemLongClick(AdapterView parent, View view, final int position, long id)
    {
        //  Item selectedItem = shoppingList.get(position);
        if (!removeElement(position))
            Toast.makeText(getApplicationContext(), "Error occured during removing friend", Toast.LENGTH_LONG).show();
        return true;
    }

});

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
public void onItemClick(AdapterView parent, View view, final int position, long id)
        {
        Integer selectedItemId = friendsList.get(position).getmId();
        // TODO test
            Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
            i.putExtra("friendId", selectedItemId);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.action_new_friend) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add new friend?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                    // detailsActivity will check for extra new value and if it exist it means that it should load blank object instead of loading actual friendFromDb
                    i.putExtra("isNewFriend", true);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean removeElement (final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Friend?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //TODO TEST \/
                friendsService.delete(friendsList.get(position).getmId());
                assignDataBaseDataToFriendsList();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        builder.show();
        return true;
    }

    public void assignDataBaseDataToFriendsList()
    {
        friendsList = (ArrayList<Friend>) friendsService.getAll();
    }
}
