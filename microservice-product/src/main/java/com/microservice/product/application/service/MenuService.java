package com.microservice.product.application.service;

import com.microservice.product.domain.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAll();
    Menu getById(Long id);
    Menu create(Menu menu);
    Menu update(Long id, Menu updatedMenu);
    void delete(Long id);
}