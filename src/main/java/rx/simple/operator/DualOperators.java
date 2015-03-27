package rx.simple.operator;

import rx.dual.DualConversion;
import rx.dual.DualOperator;
import rx.dual.DualSubscriber;
import rx.functions.Func2;
import rx.simple.SimpleDualConversion;
import rx.simple.SimpleDualObservable;

public class DualOperators {

    public static <T1, T2, R1> DualConversion<SimpleDualObservable<R1, T2>, T1, T2> map1(Func2<? super T1, ? super T2, ? extends R1> mapFunc) {
        return new SimpleDualConversion<R1, T2, T1, T2>(new DualOperator<R1, T2, T1, T2>() {

            @Override
            public DualSubscriber<? super T1, ? super T2> call(DualSubscriber<? super R1, ? super T2> subscriber) {
                return new DualSubscriber<T1, T2>() {

                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T1 t1, T2 t2) {
                        subscriber.onNext(mapFunc.call(t1, t2), t2);
                    }};
            }});
    }

}
