package com.distribution.simulation.service;

import com.distribution.simulation.entity.simulation.Item;

import java.util.List;

public interface ItemService {

    public List<Item> getAllItems();

    public Item view(Long id);

    public Item viewByProductId(String prodId);
}
