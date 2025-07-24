package me.rob.bankapp.persistence;

import java.util.ArrayList;
import java.util.List;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDatabase<T> implements Database<T> {

    private final String path;

    public FileDatabase(String path) { this.path = path; }

    @Override
    public void save(List<T> list) {

        try (var oos = new ObjectOutputStream(new FileOutputStream(path))) {
            
            oos.writeObject(list);
    
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<T> load() {

        try (var ois = new ObjectInputStream(new FileInputStream(path))) {

            return (List<T>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {

            return new ArrayList<>();
        }
    }
}