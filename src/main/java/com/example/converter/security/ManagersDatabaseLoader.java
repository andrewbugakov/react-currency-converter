package com.example.converter.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ManagersDatabaseLoader implements CommandLineRunner {
    private final ManagerRepository managerRepository;

    public ManagersDatabaseLoader(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.managerRepository.save(new Manager("admin", "1111",
                "ROLE_MANAGER"));
    }
}