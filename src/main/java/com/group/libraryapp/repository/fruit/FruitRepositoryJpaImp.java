package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.homework.Fruit;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Primary
@Repository
public class FruitRepositoryJpaImp implements FruitRepositoryInterface {

    private final FruitRepositoryJpa repositoryJpa;

    public FruitRepositoryJpaImp(FruitRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public void save(String name, LocalDate warehousingDate, long price) {
        Fruit fruit = new Fruit(name, price,null,"HAVING");
        repositoryJpa.save(fruit);
    }

    @Override
    public void updateSellStatus(long id) {
        Fruit fruit = repositoryJpa.findById(id).orElseThrow(() -> new IllegalArgumentException("데이터 없음"));
        fruit.selling();
        repositoryJpa.save(fruit);
    }

    @Override
    public List<Fruit> getSalesInfo(String fruitName) {
        return repositoryJpa.findAllByName(fruitName);
    }

    @Override
    public long getAmountBy(String name) {
        return repositoryJpa.countByName(name);
    }

    @Override
    public List<Fruit> getFruitLte(Long price) {
        return repositoryJpa.findAllByPriceLessThanEqual(price);
    }

    @Override
    public List<Fruit> getFruitGte(Long price) {
        return repositoryJpa.findAllByPriceGreaterThanEqual(price);
    }
}
