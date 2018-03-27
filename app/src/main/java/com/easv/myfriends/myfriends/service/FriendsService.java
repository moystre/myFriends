package com.easv.myfriends.myfriends.service;

import android.content.Context;

import com.easv.myfriends.myfriends.DAL.repository.FriendsRepository;
import com.easv.myfriends.myfriends.DAL.repository.IFriendsRepository;
import com.easv.myfriends.myfriends.model.Friend;

/**
 * Created by miesz on 23.03.2018.
 */

public class FriendsService {
    // TODO toast service with resultString
    private IFriendsRepository repo;

    public FriendsService(Context context)
    {
        repo = new FriendsRepository(context);
    }

    //    <------------------- CREATING FUNCTION ------------------->
    void addFriend(Friend friend)
    {
       repo.insert(friend);
    }

    //    <------------------- READING FUNCTIONS ------------------->
    Iterable<Friend> getAll()
    {
       return repo.getAll();
    }
    Iterable<Friend> getAlllById(Iterable<Integer> ids)
    {
        return repo.getAlllByIds(ids);
    }
    Friend getById(int id)
    {
        return repo.getById(id);
    }

    //    <------------------- UPDATING FUNCTION ------------------->
    void update(Friend friend)
    {
        repo.update(friend);
    }

    //    <------------------- DELETING FUNCTIONS ------------------->
    void delete(Friend friend)
    {
        repo.delete(friend);
    }
    void deleteAll()
    {
        repo.deleteAll();
    }

    void deleteById(int id)
    {
        repo.deleteById(id);
    }
}
