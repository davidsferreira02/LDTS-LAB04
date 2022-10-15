package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class PositiveFilterTest {




    @Test
    void PositiveFilter() {
        PositiveFilter filter=new PositiveFilter();
        List<Integer> Positive = Arrays.asList(1, 2, 4, 8, 240, 1000, 100000);
        List<Integer> NotPositive = Arrays.asList(-2, -4, -1);

        for(int n:Positive){
            Assertions.assertTrue(filter.accept(n));
        }
        for(int n:NotPositive){
            Assertions.assertFalse(filter.accept(n));
        }
    }
}
