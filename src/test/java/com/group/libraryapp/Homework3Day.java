package com.group.libraryapp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Homework3Day {

    @Test
    @DisplayName("동적 파라미터")
    void DynamicParameter(){
        Addable addable = (a, b) -> {
            return a + b;};

        int result = addable.calculate(5, 4);

        Assertions.assertThat(result).isEqualTo(9);
    }


}

interface Addable {
    // 추상 메서드가 하나이다
    int calculate(int a, int b);

    default void print() {
        System.out.println("함수형 인터페이스");
    }
}

