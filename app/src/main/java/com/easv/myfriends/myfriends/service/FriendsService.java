package com.easv.myfriends.myfriends.service;

import android.content.ContentValues;
import android.content.Context;

import com.easv.myfriends.myfriends.DAL.repository.FriendsDbAdapter;
import com.easv.myfriends.myfriends.DAL.repository.IFriendsDbAdapter;
import com.easv.myfriends.myfriends.model.Friend;

/**
 * Created by miesz on 23.03.2018.
 */

public class FriendsService {
    // TODO toast service with resultString
    private IFriendsDbAdapter dbAdapter;
    private MessageService messageService;
    private Context context;

    public FriendsService(Context context)
    {
        dbAdapter = new FriendsDbAdapter(context);
        this.context = context;
        messageService = new MessageService();
    }

    //    <------------------- CREATING FUNCTION ------------------->
    public void addFriend(Friend friend)
    {
        if(dbAdapter.insert(friend)>=0)
        {
            messageService.message(context, "Friend Added!");
        }else{
            messageService.message(context, "Adding Error.");
        }

    }

    //    <------------------- READING FUNCTIONS ------------------->
    public Iterable<Friend> getAll()
    {
       return dbAdapter.getAll();
    }
    public Iterable<Friend> getAlllById(Iterable<Integer> ids)
    {
        return dbAdapter.getAlllByIds(ids);
    }
    public Friend get(int id)
    {
        return dbAdapter.getById(id);
    }

    //    <------------------- UPDATING FUNCTION ------------------->
    public void update(Friend friend)
    {
        if(dbAdapter.update(friend))
        {
            messageService.message(context, "Friend Updated!");
        }else{
            messageService.message(context, "Update Error.");
        }
    }

    //    <------------------- DELETING FUNCTIONS ------------------->
    public void delete(int id)
    {
        dbAdapter.delete(id);
    }
    public void deleteAll()
    {
        dbAdapter.deleteAll();
    }
}
