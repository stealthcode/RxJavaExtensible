package rx.simple;

import org.junit.Test;

import static rx.simple.operator.CoreOperators.*;
import static rx.simple.operator.ConversionOperators.*;
import rx.Subscriber;
import rx.dual.DualSubscriber;
import rx.simple.SimpleSingleObservable;

public class SimpleSingleObservableTest {

    @Test
    public void test() {
        SimpleSingleObservable.<Integer>create(sub -> {
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
        SimpleSingleObservable.<Integer>create(sub -> {
            for (int i = 0; i < 10; i++)
                sub.onNext(new Integer(i));
            sub.onCompleted();
        })
        .extend(generate(i -> i.toString()))
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
