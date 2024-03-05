package com.group.libraryapp.service.fruit;

import com.group.libraryapp.controller.homework.FruitController;
import com.group.libraryapp.domain.homework.Fruit;
import com.group.libraryapp.repository.fruit.FruitRepositoryInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

@Service
public class FruitService {

//    private final FruitRepository fruitRepository;
//    public FruitService(FruitRepository fruitRepository) {
//        this.fruitRepository = fruitRepository;
////    }
    private final FruitRepositoryInterface fruitRepository;


    public FruitService(FruitRepositoryInterface fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(String name, LocalDate warehousingDate, long price) {
        fruitRepository.save(name, warehousingDate, price);
    }

    public long sellFruit(long id) {
        fruitRepository.updateSellStatus(id);
        return id;
    }

    public FruitController.FruitSalesResponse getSalesInfo(String fruitName) {
        List<Fruit> result = fruitRepository.getSalesInfo(fruitName);
        Map<String, Long> collect = sumPriceByStatus(result);

        long selling = collect.get("SELLING") == null ? 0 : collect.get("SELLING");
        long having = collect.get("HAVING") == null ? 0 : collect.get("HAVING");
        return new FruitController.FruitSalesResponse(selling, having);
    }

    private Map<String, Long> sumPriceByStatus(List<Fruit> result) {
        return result.stream()
                .filter(it -> it.getSell_status() != null)
                .collect(groupingBy(
                        Fruit::getSell_status,
                        summingLong(Fruit::getPrice)));
    }

    public FruitController.FruitAmountResponse getAmountBy(String name) {
        long amount = fruitRepository.getAmountBy(name);
        return new FruitController.FruitAmountResponse(amount);
    }

    public List<FruitController.FruitHavingResponse> getHavingFruitByOption(String option, Long price) {
        isVerify(option);
        isVerify(price);

        List<Fruit> fruits = null;
        if (option.equals("LTE")) {
            fruits = fruitRepository.getFruitLte(price);
        } else {
            fruits = fruitRepository.getFruitGte(price);
        }

        return fruits.stream().map(FruitController.FruitHavingResponse::new).collect(Collectors.toList());
    }

    private void isVerify(String option) {
        List<String> options = List.of("LTE", "GTE");
        boolean isVerifyOption = options.contains(option);
        if (!isVerifyOption) {
            throw new IllegalArgumentException("값 확인");
        }
    }

    private void isVerify(Long price) {
        var isVerifyOption = price >= 0L;
        if (!isVerifyOption) {
            throw new IllegalArgumentException("값 확인");
        }
    }
}
