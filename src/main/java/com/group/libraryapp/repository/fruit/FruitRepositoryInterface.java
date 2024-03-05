package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.homework.Fruit;

import java.time.LocalDate;
import java.util.List;

public interface FruitRepositoryInterface {

    void save(String name, LocalDate warehousingDate, long price);
    void updateSellStatus(long id) ;
    List<Fruit> getSalesInfo(String fruitName);
    long getAmountBy(String name);

    List<Fruit> getFruitLte(Long price);

    List<Fruit> getFruitGte(Long price);
}
