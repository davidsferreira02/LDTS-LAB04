import com.aor.numbers.DivisibleByFilter
import com.aor.numbers.GenericListFilter
import com.aor.numbers.GenericListSorter
import com.aor.numbers.ListAggregator
import com.aor.numbers.ListDeduplicator
import com.aor.numbers.ListFilterer
import com.aor.numbers.ListSorter
import com.aor.numbers.PositiveFilter
import org.mockito.Mock
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

class ListDeduplicatorSpockTest extends Specification {
    private def list
    private def expected

    def setup() {
        list = Arrays.asList(1,2,4,2,5)
        expected = Arrays.asList(1,2,4,5)
    }

    def 'Testing deduplicate'() {
        given:
        GenericListSorter sorter = Mock(GenericListSorter.class)
        def deduplicator = new ListDeduplicator(sorter)

        sorter.sort(_) >> Arrays.asList(1, 2, 2, 4, 5)

        when:
        def distinct = deduplicator.deduplicate(list)

        then:
        expected == distinct;
    }

    def 'Testing deduplicate bug 8726'() {
        given:
        def sorter = Mock(GenericListSorter.class)
        def deduplicator = new ListDeduplicator(sorter)

        sorter.sort(_) >> Arrays.asList(1, 2, 2, 4)

        when:
        def distinct = deduplicator.deduplicate(Arrays.asList(1, 2, 4, 2))

        then:
        Arrays.asList(1, 2, 4) == distinct
    }
}


class ListFiltererSpockTest extends Specification {
    def 'Testing: filter'() {
        given:
        def filter = Mock(GenericListFilter.class)

        filter.accept(1) >> true
        filter.accept(2) >> false
        filter.accept(3) >> true
        filter.accept(4) >> false
        filter.accept(5) >> true

        when:
        def filterer = new ListFilterer(filter);

        then:
        Arrays.asList(1, 3, 5) == filterer.filter(Arrays.asList(1, 2, 3, 4, 5))
    }
}

class ListSorterSpockTest extends Specification {
    private def list
    private def expected

    def setup() {
        list = Arrays.asList(3, 2, 6, 1, 4, 5, 7)
        expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7)
    }

    def 'Testing: sort'() {
        given:
        def sorter = new ListSorter();

        when:
        def sorted = sorter.sort(list);

        then:
        expected == sorted;
    }


    def 'Testing deduplicate bug 8726'() {
        given:
        def sorter = new ListSorter();

        when:
        def sorted = sorter.sort(Arrays.asList(1, 2, 4, 2))

        then:
        Arrays.asList(1, 2, 2, 4) == sorted;
    }
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
