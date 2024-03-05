package com.group.libraryapp.play;

import java.util.*;

public class Refactoring {

    public static void main(String[] args) {
        
        // 배열보다 리스트를 사용하는 이유, 배열 보다 리스트를 사용하는 이유,
        // 타입 안정성 리스트 사용
        // 제네릭의 공변, 반공변

        Dice dice = new Dice();
        List<DiceResult> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            result.add(new DiceResult(i + 1, 0));
        }

        for (int i = 0; i < 6; i++) {
        }

        for (int i = 0; i < 6; i++) {
            int d = dice.roll();// 주사위를 굴린 결과가 1이면 0번째 result의 count를 1만큼 더해준다.
            result.get(d-1).addCount();

        }

    }
}


class Dice {

    private static final int MAX_NUMBER = 6;
    public static final int MIN_NUMBER = 1;
    private final Random random = new Random();

    // 난수를 생성하는 기능
    
    // 일급 컬렉션
    public int roll() {
        // 난수를 생성하는 방법 만번씩 돌려서 성능을 확인해보는 방법.
        return random.nextInt(MAX_NUMBER+1);
    }
}

class DiceResult {
    private final int number; // 어떤 숫자가 주사위에서 나왔는지
    private int count; // 몇번 나왔는지

    public DiceResult(int number, int count) {
        this.number = number;
        this.count = count;
    }

    public void addCount(){
        ++this.count;
    }
}

class DiceResults {
    private final Map<Integer, Integer> results = new HashMap<>();

    public DiceResults() {
        
    }
}