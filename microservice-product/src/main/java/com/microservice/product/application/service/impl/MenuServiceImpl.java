package com.microservice.product.application.service.impl;

import com.microservice.product.application.service.MenuService;
import com.microservice.product.domain.model.Menu;
import com.microservice.product.domain.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con id: " + id));
    }

    @Override
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Long id, Menu updatedMenu) {
        Menu existing = getById(id);

        existing.setName(updatedMenu.getName());
        existing.setDescription(updatedMenu.getDescription());
        existing.setTotalPrice(updatedMenu.getTotalPrice());
        existing.setActive(updatedMenu.getActive());
        existing.setIncludedProducts(updatedMenu.getIncludedProducts());

        return menuRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        getById(id); // asegura existencia
        menuRepository.deleteById(id);
    }
}