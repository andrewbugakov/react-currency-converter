package com.example.converter.security;

import org.springframework.data.repository.Repository;

public interface ManagerRepository extends Repository<Manager, Long> {

	Manager save(Manager manager);

	Manager findByName(String name);

}
