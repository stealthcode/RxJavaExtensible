package rx.simple.operator;

import rx.Subscriber;
import rx.dual.DualSubscriber;
import rx.dual.MonoToDualOperator;
import rx.functions.Func1;
import rx.simple.SimpleDualObservable;
import rx.simple.SimpleMonoToDualConversion;
import rx.single.MonoConversion;

public class ConversionOperators {

    public static <T1, R1> MonoConversion<SimpleDualObservable<T1, R1>, T1> generate(Func1<? super T1, ? extends R1> generatorFunc) {
        return new SimpleMonoToDualConversion<T1, R1, T1>(new MonoToDualOperator<T1, R1, T1>() {

            @Override
            public Subscriber<? super T1> call(DualSubscriber<? super T1, ? super R1> subscriber) {
                return new Subscriber<T1>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T1 t0) {
                        try {
                            R1 t1 = generatorFunc.call(t0);
                            subscriber.onNext(t0, t1);
                        } catch (Throwable e) {
                            subscriber.onError(e);
                        }
                    }
                };
            }});
    }
}
