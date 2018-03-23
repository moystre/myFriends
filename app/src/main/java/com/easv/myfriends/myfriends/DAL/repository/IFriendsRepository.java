package com.easv.myfriends.myfriends.DAL.repository;

import com.easv.myfriends.myfriends.model.Friend;

import java.util.Optional;

//    <------------------- FRIENDS CRUD INTERFACE ------------------->

public interface IFriendsRepository {

    //    <------------------- CREATING FUNCTION ------------------->
    Friend insert(Friend entity);

    //    <------------------- READING FUNCTIONS ------------------->
    Iterable<Friend> getAll();
    Iterable<Friend> getAlllById(Iterable<Integer> ids);
    Friend getById(int id);

    //    <------------------- UPDATING FUNCTION ------------------->
    void update(Friend entity);

    //    <------------------- DELETING FUNCTIONS ------------------->
    void delete(Friend entity);
    void deleteAll();
    void deleteAllByEntites(Iterable<Friend> entities);
    void deleteById(int id);

}
