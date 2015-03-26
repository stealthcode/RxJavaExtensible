package rx.dual;

import rx.Observable.OnSubscribe;
import rx.functions.Func2;

public interface SingleToDualObservableFactory<O extends DualObservable<R1, R2>, R1, R2, T> extends Func2<SingleToDualOperator<? extends R1, ? extends R2, ? super T>, OnSubscribe<T>, O> {
    @Override
    public O call(SingleToDualOperator<? extends R1, ? extends R2, ? super T> t1, OnSubscribe<T> t2);
}
