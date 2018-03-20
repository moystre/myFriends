package com.easv.myfriends.myfriends.repository;

import com.easv.myfriends.myfriends.model.Friend;

/**
 * Created by miesz on 20.03.2018.
 */

public class FriendsRepository implements IFriendsRepository {
    @Override
    public Friend insert(Friend entity) {
        return null;
    }

    @Override
    public Iterable<Friend> getAll() {
        return null;
    }

    @Override
    public Iterable<Friend> getAlllById(Iterable<Integer> ids) {
        return null;
    }

    @Override
    public Friend getById(int id) {
        return null;
    }

    @Override
    public void update(Friend entity) {

    }

    @Override
    public void delete(Friend entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteAllByEntites(Iterable<Friend> entities) {

    }

    @Override
    public void deleteById(int id) {

    }
}
