package com.easv.myfriends.myfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.easv.myfriends.myfriends.adapter.FriendsAdapter;
import com.easv.myfriends.myfriends.model.Friend;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friendsList = new ArrayList<>();
    ListAdapter adapter = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO POPULATE FRIENDSLIST
        adapter = new FriendsAdapter(this, friendsList);
        listView = (ListView) findViewById(R.id.friendsListView);
        listView.setAdapter(adapter);

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
        Friend selectedItem = friendsList.get(position);
        // TODO ADD ID TO FRIEND CLASS SO I CAN SEND IT IN INTENT AS EXTRAS AND START NEW ACTIVITY. cya im too drunk.
        }

        });
    }


    public boolean removeElement (final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Friend?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                friendsList.remove(position);
                //  ??  Collections.sort(shoppingList, Item.COMPARE_BY_NAME);
                //TODO SQL LOGIC FOR REMOVING (CREATE REPOSITORY FOR SQL ACCESS PURPOSE, NOTIFY ADAPTER ABOUT CHANGES
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
}
