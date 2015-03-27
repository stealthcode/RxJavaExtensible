package rx.simple;

import rx.dual.DualConversion;
import rx.dual.DualObservable;
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
        return extend(new SimpleDualConversion<R1, R2, T1, T2>(operator));
    }
   
    @Override
    public <O> O extend(DualConversion<O, T1, T2> operator) {
        return operator.convert(onSubscribe);
    }

}
