//package com.group.libraryapp.repository.fruit;
//
//import com.group.libraryapp.domain.homework.Fruit;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
////@Primary
//@Repository
//public class FruitMemoryRepository implements FruitRepositoryInterface{
//
//    private static final Map<Long, Fruit> memory = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public void save(String name, LocalDate warehousingDate, long price) {
//        Fruit fruit = new Fruit(name, warehousingDate, price);
//        fruit.assignId(++sequence);
//        memory.put(fruit.getId(),fruit);
//    }
//
//    @Override
//    public void updateSellStatus(long id) {
//        Fruit fruit = memory.get(id);
//        fruit.selling();
//    }
//
//    @Override
//    public List<Fruit> getSalesInfo(String fruitName) {
//        return memory.values().stream()
//                .filter(fruit -> fruit.getName().equals(fruitName))
//                .collect(Collectors.toList());
//    }
//
//}
