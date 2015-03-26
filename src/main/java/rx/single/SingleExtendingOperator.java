package rx.single;

import rx.Observable.OnSubscribe;

public interface SingleExtendingOperator<O extends SingleObservable<R>, R, T> {
    public O compose(OnSubscribe<T> onSubscribe);
}
