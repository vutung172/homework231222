package com.ra.entity;

public interface ISearch<T> {
    T searchByID(String id);
    void searchByString(String string);
}
