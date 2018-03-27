package com.easv.myfriends.myfriends.DAL.repository;

import com.easv.myfriends.myfriends.model.Friend;

import java.util.Optional;

//    <------------------- FRIENDS CRUD INTERFACE ------------------->

public interface IFriendsDbAdapter {

    //    <------------------- CREATING FUNCTION ------------------->
    long insert(Friend entity);

    //    <------------------- READING FUNCTIONS ------------------->
    Iterable<Friend> getAll();
    Iterable<Friend> getAlllByIds(Iterable<Integer> ids);
    Friend getById(int id);

    //    <------------------- UPDATING FUNCTION ------------------->
    Boolean update(Friend entity);

    //    <------------------- DELETING FUNCTIONS ------------------->
    Boolean delete(int id);
    Boolean deleteAll();

}
