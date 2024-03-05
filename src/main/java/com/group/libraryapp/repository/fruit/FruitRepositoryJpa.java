package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.homework.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitRepositoryJpa extends JpaRepository<Fruit, Long> {

    List<Fruit> findAllByName(String name);

    long countByName(String name);

    List<Fruit> findAllByPriceGreaterThanEqual(Long price);
    List<Fruit> findAllByPriceLessThanEqual(Long price);
}


