package com.group.libraryapp.controller.homework;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
public class HomeworkController {

    @GetMapping("/api/v1/calc")
    public HomeworkResponse getCalculatorResult(
            @RequestParam("num1") Integer num1,
            @RequestParam("num2") Integer num2) {

        Calculator calculator = new Calculator(num1, num2);
        return new HomeworkResponse(calculator.add(), calculator.minus(), calculator.multiply());
    }

    public static class HomeworkResponse {
        private Integer add;
        private Integer minus;
        private Integer multiply;

        public HomeworkResponse(Integer add, Integer minus, Integer multiply) {
            this.add = add;
            this.minus = minus;
            this.multiply = multiply;
        }

        public Integer getAdd() {
            return add;
        }

        public Integer getMinus() {
            return minus;
        }

        public Integer getMultiply() {
            return multiply;
        }
    }

    public static class Calculator {

        private Integer num1;
        private Integer num2;

        public Calculator(Integer num1, Integer num2) {
            Assert.notNull(num1, "num1은 null이 허용되지 않습니다.");
            Assert.notNull(num2, "num2은 null이 허용되지 않습니다.");
            this.num1 = num1;
            this.num2 = num2;
        }

        public Calculator() {
        }

        public Integer add() {
            return num1 + num2;
        }

        public Integer minus() {
            return num1 - num2;
        }

        public Integer multiply() {
            return num1 * num2;
        }

        public Integer add(List<Integer> numbers) {
            if (numbers.isEmpty()) {
                return null;
            }
            return numbers.stream().mapToInt(it->it).sum();
        }
    }

    @GetMapping("/api/v1/day-of-the-week")
    public DateResponse getDate(@RequestParam("date") String queryDate) {
        String text = LocalDateUtil.getDayOfTheWeek(queryDate);
        return new DateResponse(text);
    }

    public static class LocalDateUtil {
        public static String getDayOfTheWeek(String queryDate) {
            LocalDate date = LocalDate.parse(queryDate, DateTimeFormatter.ISO_LOCAL_DATE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ccc", new Locale("US","KR"));
            return date.format(formatter);
        }
    }


    public static class DateResponse {
        private final String dayOfTheWeek;

        public DateResponse(String dayOfTheWeek) {
            this.dayOfTheWeek = dayOfTheWeek;
        }

        public String getDayOfTheWeek() {
            return dayOfTheWeek;
        }
    }

    @PostMapping("/api/v1/calc")
    public Integer getCalcSumAll(@RequestBody CalculatorSumRequest request) {
        Calculator calculator = new Calculator();
        return calculator.add(request.getNumbers());
    }

    public static class CalculatorSumRequest {
        private List<Integer> numbers;

        public CalculatorSumRequest() {
        }

        public CalculatorSumRequest(List<Integer> numbers) {
            System.out.println("numbers = " + numbers);
            this.numbers = numbers;
        }

        public List<Integer> getNumbers() {
            return numbers;
        }
        @Override
        public String toString() {
            return "CalculatorSumRequest{" +
                    "numbers=" + numbers +
                    '}';
        }
    }
}
