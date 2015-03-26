package rx.single;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.functions.Func2;

public interface SingleObservableFactory<O extends SingleObservable<R>, R, T> extends Func2<Operator<? extends R, ? super T>, OnSubscribe<T>, O> {
    @Override
    public O call(Operator<? extends R, ? super T> operator, OnSubscribe<T> onSubscribe);
}
