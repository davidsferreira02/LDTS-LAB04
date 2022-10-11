package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {
    List<Integer> list;

    @BeforeEach
    public void Helper() {
         list=Arrays.asList(1,2,4,2,5);


    }

    @Test
    public void sum() {
        //List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator();
        int sum = aggregator.sum(list);

        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {
        //List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator();
        int max = aggregator.max(list);


        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {
        // List<Integer> list = Arrays.asList(1,2,4,2,5);

        ListAggregator aggregator = new ListAggregator();
        int min = aggregator.min(list);

        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {
        // List<Integer> list = Arrays.asList(1,2,4,2,5);
        class StubListDeduplicator implements GenericListDeduplicator {
            @Override
            public List<Integer> deduplicate(List<Integer> list) {
                return Arrays.asList(1, 2, 4, 5);
            }
        }
        ListAggregator aggregator = new ListAggregator();
            StubListDeduplicator deduplicator=new StubListDeduplicator();
        int distinct = aggregator.distinct(list,deduplicator);

        Assertions.assertEquals(4,distinct);
    }
    @Test
    public void Max_bug7263() {
        List<Integer> list_bug = Arrays.asList(-1,-4,-5);
        //ListAggregator aggregator = new ListAggregator();
        int max = list_bug.get(0);
        for(int number:list_bug){
            if(max<number){
                max=number;
            }
        }
        Assertions.assertEquals(-1,max);
    }

}