package me.rob.bankapp.persistence;

import java.util.List;

public interface Database<T> {

    void save(List<T> list);

    List<T> load();
}