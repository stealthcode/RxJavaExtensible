package rx.dual;

import rx.Observable.OnSubscribe;

public interface SingleToDualExtendingOperator<O extends DualObservable<R1, R2>, R1, R2, T> {
    public O compose(OnSubscribe<T> onSubscribe);
}
