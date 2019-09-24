package com.example.user.api.simpleuserservice.service;

import com.example.user.api.simpleuserservice.api.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface IUserService<T, ID extends String> {

    List<T> findAll();

    T findById(ID id) throws EntityNotFoundException;

    <S extends T> List<S> saveAll(Iterable<S> var1);

    <S extends T> S save(S var1);

    T delete(ID var1) throws EntityNotFoundException;

    /**
     * @return {@code Map} of users by groupId
     */
    Map<String, List<T>> findAllGroupByGroupId();
}
