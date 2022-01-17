package com.uralkeser.dao;

import com.uralkeser.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client,Long> {

    List<Client> findAll();

    List<Client> findClientByUserName(String userName);


}
