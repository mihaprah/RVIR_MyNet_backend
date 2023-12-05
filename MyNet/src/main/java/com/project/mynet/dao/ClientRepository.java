package com.project.mynet.dao;

import com.project.mynet.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String> {
}
