package com.group.libraryapp.play;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Playground {

    public static void main(String[] args) {
        System.out.println("숫자를 입력하세요 : ");
        Scanner scanner = new Scanner(System.in);
        // 변수명 수정
        int diceRollCount = scanner.nextInt();
    
        // 주사위를 만든다.
        int maxDiceNumber = 6;

        DiceRollCounterMachine diceRollCounterMachine = new DiceRollCounterMachine(maxDiceNumber);
        diceRollCounterMachine.roll(diceRollCount);
        diceRollCounterMachine.printRollResult();
    }

    private static Dice makeDice(int maxDiceNumber) {
        return new Dice(maxDiceNumber);
    }


    private static class Dice {
        private final int diceNumber;

        private Dice(int diceNumber) {
            validateDiceNumber(diceNumber);
            this.diceNumber = diceNumber;
        }

        private static void validateDiceNumber(int diceNumber) {
            if (diceNumber <= 0) {
                throw new IllegalArgumentException("응 주사위 아니야");
            }
        }
        public int roll() {
            return (int) (Math.random() * diceNumber);
        }

    }

    private static class DiceRollCounterMachine {
        private final int[] counterMemory;
        private final Dice dice;

        public DiceRollCounterMachine(int diceMaxNumber) {
            validate(diceMaxNumber);
            dice = new Dice(diceMaxNumber);
            counterMemory = new int[diceMaxNumber];
        }

        public void roll(int diceRollCount) {
            for (int i = 0; i < diceRollCount; i++) {
                int num = dice.roll();
                counterMemory[num]++;
            }
        }
        private void validate(int diceMaxNumber) {
            if (diceMaxNumber <= 0) {
                throw new IllegalArgumentException("응 주사위 안돌아가");
            }
        }

        public void printRollResult(){
            for (int i = 0; i < counterMemory.length; i++) {
                System.out.printf("%d는 %d번 나왔습니다\n",i+1,counterMemory[i]);
            }
        }
    }


}


interface RandomNumberGenerator {
    public int nextInt(int origin, int bound);
}

class DiceNumberGenerator implements RandomNumberGenerator {
    private final Random random = new Random();
    @Override
    public int nextInt(int origin, int bound) {
        return random.nextInt(bound);
    }
}

class StubNumberGenerator implements RandomNumberGenerator {
    private final List<Integer> mockData = List.of(3, 4, 3, 4);
    private int count = 0;
    @Override
    public int nextInt(int origin, int bound) {
        return mockData.get(count++);
    }
}