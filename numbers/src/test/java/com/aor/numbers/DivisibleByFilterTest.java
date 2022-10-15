package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class DivisibleByFilterTest {
    @Test
    void acceptDivisible2(){
        DivisibleByFilter filter=new DivisibleByFilter(2);
        List<Integer> divisible= Arrays.asList(0,2,4,8,240,1000,100000);
        List<Integer>NotDivisilbe=Arrays.asList(1,3,5,7,9);

        for(int n:divisible){
            Assertions.assertTrue(filter.accept(n));
        }
        for(int n:NotDivisilbe){
            Assertions.assertFalse(filter.accept(n));
        }
    }

    @Test
    void acceptDivisible3(){

        DivisibleByFilter filter=new DivisibleByFilter(3);
        List<Integer>NotDivisible= Arrays.asList(1,2,4,5,10);
        List<Integer>divisible=Arrays.asList(0,3,6,12,9);

        for(int n:divisible){
            Assertions.assertTrue(filter.accept(n));
        }
        for(int n:NotDivisible){
            Assertions.assertFalse(filter.accept(n));
        }
    }
    }

