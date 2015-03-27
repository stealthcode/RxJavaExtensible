package rx.dual;

import rx.Observable.OnSubscribe;

public interface MonoToDualConversion<O extends DualObservable<R1, R2>, R1, R2, T> {
    public O convert(OnSubscribe<T> onSubscribe);
}
