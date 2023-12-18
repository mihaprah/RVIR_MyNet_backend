package com.project.mynet.dao;

import com.project.mynet.models.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface VaultRepository extends JpaRepository<Vault, Long> {

    @Query("select v from Vault v where v.client.id = ?1")
    Collection<Vault> findAllByClient_Id(Long id);
}
