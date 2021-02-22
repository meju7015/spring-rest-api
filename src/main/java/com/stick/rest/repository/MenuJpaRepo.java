package com.stick.rest.repository;

import com.stick.rest.entity.Menu;
import com.stick.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuJpaRepo extends JpaRepository<Menu, Integer> {
    Optional<Menu> findAllBy();
}
