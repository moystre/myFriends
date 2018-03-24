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
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.easv.myfriends.myfriends.adapter.FriendsAdapter;
import com.easv.myfriends.myfriends.model.Friend;
import com.easv.myfriends.myfriends.DAL.repository.FriendsRepository;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //    <------------------- DECLARATIONS ------------------->
    ArrayList<Friend> friendsList;
    FriendsRepository friendsRepository;
    FriendsAdapter adapter;
    ListView listView;

    //filtering and sorting -> adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //region MOCKING DATA
        friendsList = new ArrayList<Friend>();
        friendsList.add(new Friend(0,"TEST","TEST","TEST","TEST","TEST",new Date(),"TEST",new Location("")));
        friendsList.add(new Friend(0,"TEST","TEST","TEST","TEST","TEST",new Date(),"TEST",new Location("")));
        //endregion
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //    <------------------- DEFINITIONS ------------------->

        friendsRepository = new FriendsRepository();
        //TODO SEPERATE THREAD FOR LOADING STUFF FROM DB
        // assignDataBaseDataToFriendsList();
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
        return true;
    }

    public boolean removeElement (final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Friend?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //TODO TEST \/
                friendsRepository.deleteById(friendsList.get(position).getmId());
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
        friendsList = (ArrayList<Friend>) friendsRepository.getAll();
    }
}
