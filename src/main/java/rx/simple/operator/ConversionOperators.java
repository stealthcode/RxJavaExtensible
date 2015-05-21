package rx.simple.operator;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.simple.CoreObservable;
import rx.simple.CoreObservableConversion;
import rx.single.MonoConversion;

public class ConversionOperators {

    public static <T, R> MonoConversion<CoreObservable<R>, T> mapToCore(Func1<? super T, ? extends R> mapFunc) {
        return new CoreObservableConversion<R, T>(new Operator<R, T>() {

            @Override
            public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
                return new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(T t) {
                        try {
                            R r = mapFunc.call(t);
                            subscriber.onNext(r);
                        } catch (Throwable e) {
                            subscriber.onError(e);
                        }
                    }
                };
            }});
    }
}
