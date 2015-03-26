package rx.simple;

import rx.dual.DualExtendingOperator;
import rx.dual.DualObservable;
import rx.dual.DualObservableFactory;
import rx.dual.DualOnSubscribe;
import rx.dual.DualOperator;
import rx.dual.DualSubscriber;

public class SimpleDualObservable<T1, T2> implements DualObservable<T1, T2> {
    
    private final DualOnSubscribe<T1, T2> onSubscribe;

    private SimpleDualObservable(DualOnSubscribe<T1, T2> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }
    
    public static <T1, T2> SimpleDualObservable<T1, T2> create(DualOnSubscribe<T1, T2> onSubscribe) {
        return new SimpleDualObservable<T1, T2>(onSubscribe);
    }

    @Override
    public void subscribe(DualSubscriber<T1, T2> subscriber) {
        onSubscribe.call(subscriber);
    }

    @Override
    public <R1, R2> DualObservable<R1, R2> lift(DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2> operator) {
        return lift(new SimpleDualObservableFactory<R1, R2, T1, T2>(), operator);
    }

    @Override
    public <R1, R2, O extends DualObservable<R1, R2>> O lift(DualObservableFactory<O, R1, R2, T1, T2> factory, DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2> operator) {
        return factory.call(operator, onSubscribe);
    }
    
    @Override
    public <R1, R2, O extends DualObservable<R1, R2>> O extend(DualExtendingOperator<O, R1, R2, T1, T2> operator) {
        return operator.compose(onSubscribe);
    }

}
