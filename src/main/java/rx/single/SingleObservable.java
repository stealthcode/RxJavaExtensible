package rx.single;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.dual.DualObservable;
import rx.dual.SingleToDualObservableFactory;
import rx.dual.SingleToDualOperator;
import rx.dual.SingleToDualExtendingOperator;


public interface SingleObservable<T> {
    
    public void subscribe(Subscriber<T> subscriber);
    
    public <R> SingleObservable<R> lift(final Operator<? extends R, ? super T> operator);
    
    // Extension via Observable Factory
    public <R, O extends SingleObservable<R>> O lift(SingleObservableFactory<O, R, T> factory, Operator<? extends R, ? super T> operator);
    public <R1, R2, O extends DualObservable<R1, R2>> O lift(SingleToDualObservableFactory<O, R1, R2, T> factory, SingleToDualOperator<? extends R1, ? extends R2, ? super T> operator);
    
    // Extension via Extending Operator
    public <R, O extends SingleObservable<R>> O extend(SingleExtendingOperator<O, R, T> operator);
    public <R1, R2, O extends DualObservable<R1, R2>> O extend(SingleToDualExtendingOperator<O, R1, R2, T> operator);
}
