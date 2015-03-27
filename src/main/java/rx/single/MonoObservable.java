package rx.single;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;


public interface MonoObservable<T> {
    
    public void subscribe(Subscriber<T> subscriber);
    
    public <R> MonoObservable<R> lift(final Operator<? extends R, ? super T> operator);
    
    public <R, O> O extend(MonoConversion<O, T> operator);
    
    public <R> MonoObservable<? extends R> compose(Func1<? super MonoObservable<? super T>, ? extends MonoObservable<? extends R>> composition);
}
