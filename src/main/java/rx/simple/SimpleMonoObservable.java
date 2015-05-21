package rx.simple;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.single.MonoConversion;

public class SimpleMonoObservable<T> {

    protected OnSubscribe<T> onSubscribe;
    
    public static <T> SimpleMonoObservable<T> create(OnSubscribe<T> onSubscribe) {
        return new SimpleMonoObservable<T>(onSubscribe);
    }

    protected SimpleMonoObservable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public void subscribe(Subscriber<T> subscriber) {
        onSubscribe.call(subscriber);
    }

    public <R> SimpleMonoObservable<R> lift(Operator<? extends R, ? super T> operator) {
        return extend(new SimpleMonoConversion<R, T>(operator));
    }
    
    public <R, O> O extend(MonoConversion<O, T> operator) {
        return operator.convert(onSubscribe);
    }

    public <R> SimpleMonoObservable<? extends R> compose(Func1<SimpleMonoObservable<? super T>, SimpleMonoObservable<? extends R>> transformer) {
        return transformer.call(this);
    }
    
}
