package rx.simple;

import static rx.simple.operator.ConversionOperators.mapToCore;
import static rx.simple.operator.CoreOperators.filter;
import static rx.simple.operator.CoreOperators.map;
import static rx.simple.operator.CoreOperators.scan;

import org.junit.Test;

import rx.Subscriber;

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
                mapToCore(i -> i.toString())
        )
        .filter(s -> s.length() > 1)
        .subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String t2) {
                System.out.println("next: ("+t2+")");
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
