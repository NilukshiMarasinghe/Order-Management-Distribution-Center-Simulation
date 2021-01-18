package com.distribution.simulation.service.impl;

import com.distribution.simulation.entity.simulation.Item;
import com.distribution.simulation.exception.ComplexValidationException;
import com.distribution.simulation.repository.simulation.ItemRepo;
import com.distribution.simulation.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public List<Item> getAllItems() {
        try {
            return this.itemRepo.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("item", "getAllItems");
        }
    }

    @Override
    public Item view(Long id) {
        Optional<Item> exRole = this.itemRepo.findById(id);
        if (!exRole.isPresent()) {
            throw new ComplexValidationException("item", "view.itemNotExists");
        }
        return exRole.get();
    }

    @Override
    public Item viewByProductId(String prodId) {
        Item item = null;
        try {
            item = this.itemRepo.findByProductId(prodId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComplexValidationException("item", "viewByProduct");
        }

        if (item == null)
            throw new ComplexValidationException("item", "itemNotExists");

        return item;
    }
}
