package com.example.createjwtsb.repositories;

import com.example.createjwtsb.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users,Integer> {
}
