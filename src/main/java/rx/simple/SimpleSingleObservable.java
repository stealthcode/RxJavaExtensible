package rx.simple;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.dual.DualObservable;
import rx.dual.SingleToDualExtendingOperator;
import rx.dual.SingleToDualObservableFactory;
import rx.dual.SingleToDualOperator;
import rx.single.SingleExtendingOperator;
import rx.single.SingleObservable;
import rx.single.SingleObservableFactory;

public class SimpleSingleObservable<T> implements SingleObservable<T> {

    private OnSubscribe<T> onSubscribe;
    
    public static <T> SimpleSingleObservable<T> create(OnSubscribe<T> onSubscribe) {
        return new SimpleSingleObservable<T>(onSubscribe);
    }

    private SimpleSingleObservable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        onSubscribe.call(subscriber);
    }

    @Override
    public <R> SimpleSingleObservable<R> lift(Operator<? extends R, ? super T> operator) {
        return lift(new SimpleSingleObservableFactory<R, T>(), operator);
    }
    
    @Override
    public <R, O extends SingleObservable<R>> O lift(final SingleObservableFactory<O, R, T> factory, final Operator<? extends R, ? super T> operator) {
        return factory.call(operator, onSubscribe);
    }
    
    @Override
    public <R1, R2, O extends DualObservable<R1, R2>> O lift(SingleToDualObservableFactory<O, R1, R2, T> factory, SingleToDualOperator<? extends R1, ? extends R2, ? super T> operator) {
        return factory.call(operator, onSubscribe);
    }

    @Override
    public <R, O extends SingleObservable<R>> O extend(SingleExtendingOperator<O, R, T> operator) {
        return operator.compose(onSubscribe);
    }

    @Override
    public <R1, R2, O extends DualObservable<R1, R2>> O extend(SingleToDualExtendingOperator<O, R1, R2, T> operator) {
        return operator.compose(onSubscribe);
    }

}
