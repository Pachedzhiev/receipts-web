package com.example.demo.data.repositories;

import com.example.demo.data.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,String> {
    @Transactional
    Optional<Receipt> findById(String id);

    @Query("select r from Receipt r inner join r.user u where u.username in :username")
    List<Receipt> getAllByUsername(String username);


}
