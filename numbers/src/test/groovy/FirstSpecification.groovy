import com.aor.numbers.DivisibleByFilter
import com.aor.numbers.ListAggregator
import com.aor.numbers.ListDeduplicator
import com.aor.numbers.ListSorter
import com.aor.numbers.PositiveFilter
import spock.lang.Specification



class DivisibleByFilterSpockTest extends Specification{

    def 'Divisible 2'(){
        given:
        def filter=new DivisibleByFilter(2)
        when:
        def divisible =filter.accept(2)
        then:
        true==divisible

        def notdivisible=filter.accept(3)
        then:
        false==notdivisible
    }

    def 'Divisible 3'(){
        given:
        def filter=new DivisibleByFilter(3)
        when:
        def divisible =filter.accept(3)
        then:
        true==divisible
        def notdivisible =filter.accept(2)
        then:
        false==notdivisible
    }


}
class ListAggregatorSpockTest extends Specification {
    private def list

    def setup() {
        list = Arrays.asList(1, 2, 4, 2, 5)
    }

    def 'Testing: sum'() {
        given:
        def aggregator = new ListAggregator()

        when:
        def sum = aggregator.sum(list);

        then:
        14 == sum
    }

    def 'Testing: max'() {
        given:
        def aggregator = new ListAggregator()

        when:
        def max = aggregator.max(list);

        then:
        5 == max
    }

    def 'Testing: min'() {
        given:
        def aggregator = new ListAggregator()

        when:
        def max = aggregator.min(list);

        then:
        1 == max
    }

    def 'Testing: distinct'() {
        given:
        def aggregator = new ListAggregator()
        def deduplicator = new ListDeduplicator(new ListSorter())

        when:
        def distinct = aggregator.distinct(list, deduplicator);

        then:
        4 == distinct
    }

    def 'Testing max bug 7263'() {
        given:
        def aggregator = new ListAggregator()

        when:
        def max = aggregator.max(Arrays.asList(-1, -4, -5))

        then:
        -1 == max
    }



    def 'testing distinct bug 8726 with expectation'() {
        given:
        def deduplicator = Mock(ListDeduplicator)
        def aggregator = new ListAggregator()

        when:
        def n_distinct = aggregator.distinct(Arrays.asList(1, 2, 4, 2), deduplicator)

        then:
        3 == n_distinct
        1 * deduplicator.deduplicate(Arrays.asList(1, 2, 4, 2)) >> Arrays.asList(1, 2, 4)
    }
}

class ListFiltererSpockTest extends Specification{

}


class PositiveFilterSpockTest extends Specification{



    def 'Positive Filter'(){
        given:
        PositiveFilter filter=new PositiveFilter();
        when:
        def positive=filter.accept(2)
        def negative=filter.accept(-1)
        then:
        true==positive
        false==negative
    }
}
