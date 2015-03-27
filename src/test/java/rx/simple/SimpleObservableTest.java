package rx.simple;

import static rx.simple.operator.ConversionOperators.generate;
import static rx.simple.operator.CoreOperators.filter;
import static rx.simple.operator.CoreOperators.map;
import static rx.simple.operator.CoreOperators.scan;
import static rx.simple.operator.DualOperators.map1;

import org.junit.Test;

import rx.Subscriber;
import rx.dual.DualSubscriber;

public class SimpleObservableTest {

    @Test
    public void test() {
        SimpleMonoObservable.<Integer>create(sub -> {
            for (int i = 0; i < 100; i++)
                sub.onNext(new Integer(i));
            sub.onCompleted();
        })
        .extend(
                filter( i -> i % 3 == 0)
        )
        .extend(
                map( i -> i.toString())
        )
        .extend(
                scan((String a, String n) -> a + "," + n)
        )
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error: "+ e.getMessage());
            }

            @Override
            public void onNext(String t) {
                System.out.println("next: "+t);
            }
        });
    }
    
    @Test
    public void testConversion() {
        SimpleMonoObservable.<Integer>create(sub -> {
            for (int i = 0; i < 10; i++)
                sub.onNext(new Integer(i));
            sub.onCompleted();
        })
        .extend(
                generate(i -> i.toString())
        )
        .extend(
                map1((Integer i, String s) -> i * s.length())
        )
        .subscribe(new DualSubscriber<Integer, String>() {
            @Override
            public void onNext(Integer t1, String t2) {
                System.out.println("next: ("+t1+", "+t2+")");
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error: "+e.getMessage());
            }
        });
    }
}
