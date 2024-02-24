package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface RepositoryInterface<T> {
    T create(T object);
    T edit(T object);
    T delete(String objectId);
    T findById(String objectId);
    Iterator<T> findAll();
    void validateObject(T object);
}
