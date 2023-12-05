package com.project.mynet.dao;

import com.project.mynet.models.Vault;
import org.springframework.data.repository.CrudRepository;

public interface VaultRepository extends CrudRepository<Vault, String> {
}
