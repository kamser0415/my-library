package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

//    @GetMapping("/add") // HTTP Method
//    public int addTwoNumbers(@RequestParam int number1,
//                             @RequestParam int number2) {
//        return number1+number2;
//    }

    @GetMapping("/add") // HTTP Method
    public int addTwoNumbers(@ModelAttribute CalculatorAddRequest request) {
        return request.getNumber1()+ request.getNumber2();
    }



    @PostMapping("/multiply") // HTTP Method
    public int multiplyNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNum1() * request.getNum2();
    }


}
