package com.project.mynet.dao;

import com.project.mynet.models.Vault;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaultRepository extends JpaRepository<Vault, Long> {
}
