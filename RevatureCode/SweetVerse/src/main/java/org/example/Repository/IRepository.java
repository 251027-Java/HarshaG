package org.example.Repository;

import java.util.List;
public interface IRepository<T, ID> {
    void create(T entity);
    T get(ID id);
    void update(T entity);
    void delete(ID id);
    List<T> findAll();
    void saveAll(List<T> entities);
}
