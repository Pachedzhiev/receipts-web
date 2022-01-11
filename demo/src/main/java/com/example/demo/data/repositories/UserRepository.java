package com.example.demo.data.repositories;

import com.example.demo.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUsername(String username);
    User getById(String id);

    @Query("Select u from User u join u.receipts r where r.id in :id")
    User getByReceiptId(String id);
}
