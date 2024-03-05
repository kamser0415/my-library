package com.group.libraryapp.controller.homework;

import com.group.libraryapp.domain.homework.Fruit;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(JdbcTemplate jdbcTemplate, FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public ResponseEntity<Object> saveFruit(@RequestBody CreateFruitRequest request) {
        Assert.notNull(request.getName(), "이름필수");
        Assert.notNull(request.getWarehousingDate(), "이름필수");
        Assert.isTrue(request.getPrice() > 0, "금액은 0보다 큽니다.");

        fruitService.saveFruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        return ResponseEntity.ok().build();
    }

    public static class CreateFruitRequest {
        private String name;
        private LocalDate warehousingDate;
        private long price;

        public CreateFruitRequest() {
        }

        public String getName() {
            return name;
        }

        public LocalDate getWarehousingDate() {
            return warehousingDate;
        }

        public long getPrice() {
            return price;
        }
    }

    @PutMapping("/api/v1/fruit")
    public FruitSellResponse sellFruit(@RequestBody SellFruitRequest request) {
        Assert.isTrue(request.getId() > 0, "과일 정보 고유번호는 0 보다 큽니다.");
        long sellId = fruitService.sellFruit(request.getId());
        return new FruitSellResponse(sellId);
    }

    public static class FruitSellResponse {
        private long id;

        public FruitSellResponse(final long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }
    }

    public static class SellFruitRequest {
        private long id;

        public long getId() {
            return id;
        }
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitSalesResponse getSalesInfo(@RequestParam("name") String fruitName) {
        return fruitService.getSalesInfo(fruitName);
    }

    public static class FruitSalesResponse {
        private long salesAmount;
        private long notSalesAmount;

        public FruitSalesResponse(final long salesAmount, final long notSalesAmount) {
            this.salesAmount = salesAmount;
            this.notSalesAmount = notSalesAmount;
        }

        public long getSalesAmount() {
            return salesAmount;
        }

        public long getNotSalesAmount() {
            return notSalesAmount;
        }

    }

    @GetMapping("/api/v1/fruit/count")
    public FruitAmountResponse getFruitAmountBy(@RequestParam("name") String name) {
        Assert.hasText(name,"이름은 필수입니다.");
        return fruitService.getAmountBy(name);
    }

    public static class FruitAmountResponse {
        private final long count;

        public FruitAmountResponse(long amount) {
            count = amount;
        }

//        public FruitAmountResponse() {
//        }

        public long getCount() {
            return count;
        }
    }


    @GetMapping("/api/v1/fruit/list")
    public List<FruitHavingResponse> getHavingFruitByOption(FruitHavingRequest request) {
        return fruitService.getHavingFruitByOption(request.getOption(),request.getPrice());
    }
    public static class FruitHavingRequest {
        private String option;
        private Long price;

        public FruitHavingRequest() {
        }

        public String getOption() {
            return option;
        }

        public Long getPrice() {
            return price;
        }
//
        public void setOption(String option) {
            Assert.hasText(option,"값이 없으면 안됩니다.");
            this.option = option;
        }

        public void setPrice(Long price) {
            Assert.notNull(price,"값이 없으면 안됩니다.");
            this.price = price;
        }

        @Override
        public String toString() {
            return "FruitHavingRequest{" +
                    "option='" + option + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class FruitHavingResponse {
        private String name;
        private long price;
        private LocalDate warehousingDate;

        public FruitHavingResponse(String name, long price, LocalDate warehousingDate) {
            this.name = name;
            this.price = price;
            this.warehousingDate = warehousingDate;
        }

        public FruitHavingResponse() {
        }

        public FruitHavingResponse(Fruit fruit) {
            this.name = fruit.getName();
            this.warehousingDate = fruit.getWarehousingDate();
            this.price = fruit.getPrice();
        }

        public String getName() {
            return name;
        }

        public long getPrice() {
            return price;
        }

        public LocalDate getWarehousingDate() {
            return warehousingDate;
        }
    }
}
