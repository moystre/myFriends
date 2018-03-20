package com.easv.myfriends.myfriends.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.easv.myfriends.myfriends.R;
import com.easv.myfriends.myfriends.model.Friend;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FriendsAdapter extends BaseAdapter {

    private LayoutInflater lInflater;
    private Context mContext;
    private ArrayList<Friend> friends = new ArrayList<>();

    public FriendsAdapter(Context c, ArrayList<Friend> friends) {
        mContext = c;
        this.friends = friends;
    }

    public int getCount() {
        try {
            int size = friends.size();
            return size;
        } catch(NullPointerException ex) {
            return 0;
        }
    }

    public Friend getItem(int position) {
        return friends.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = lInflater.inflate(R.layout.friendslist_row, parent, false);
        }
        Friend friend = getItem(position);
        // TODO ((ImageView) view.findViewById(R.id.friendListImageView)).set???  IF IMAGE THEN IMAGE ELSE
        // TODO IMAGE PROMPT DEFAULT PICTURE);
        ((TextView) view.findViewById(R.id.friendListName)).setText(friend.getmName());
        ((TextView) view.findViewById(R.id.friendListPhone)).setText(friend.getmPhoneNumber());
        ((TextView) view.findViewById(R.id.friendListAddress)).setText(friend.getmAddress());

        return view;
    }

    public void removeItem(int position){
        friends.remove(position);
        notifyDataSetChanged();
    }



}
