package rx.single;

import org.junit.Test;

import static rx.simple.operator.CoreOperators.*;
import rx.Subscriber;
import rx.simple.SimpleSingleObservable;

public class SimpleSingleObservableTest {

    @Test
    public void test() {
        SimpleSingleObservable.<Integer>create(sub -> {
            sub.onNext(new Integer(1));
            sub.onCompleted();
        })
        .extend(filter( i -> {
            return i % 3 == 0;
        }))
        .extend(map( i -> {
            return i.toString();
        }))
        .extend(scan("", (a, n) -> {
            return a + "," + n;
        }))
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
            }});
    }
}
