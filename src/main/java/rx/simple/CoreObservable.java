package rx.simple;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorFilter;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorScan;
import rx.single.MonoConversion;
import rx.single.MonoObservable;

public class CoreObservable<T> extends SimpleMonoObservable<T> {
    
    private CoreObservable(OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public static <T> CoreObservable<T> create(OnSubscribe<T> onSubscribe) {
        return new CoreObservable<T>(onSubscribe);
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {

    }

    @Override
    public <R> CoreObservable<R> lift(Operator<? extends R, ? super T> operator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R, O> O extend(MonoConversion<O, T> operator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <R> MonoObservable<? extends R> compose(Func1<? super MonoObservable<? super T>, ? extends MonoObservable<? extends R>> composition) {
        // TODO Auto-generated method stub
        return null;
    }

    public final <R> CoreObservable<R> map(Func1<? super T, ? extends R> func) {
        return lift(new OperatorMap<T, R>(func));
    }

    public final CoreObservable<T> filter(Func1<? super T, Boolean> predicate) {
        return lift(new OperatorFilter<T>(predicate));
    }
    
    public final <R> CoreObservable<R> scan(R initialValue, Func2<R, ? super T, R> accumulator) {
        return lift(new OperatorScan<R, T>(initialValue, accumulator));
    }

    public final <R> CoreObservable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func) {
//        return map(func).lift(OperatorMerge.<T>instance(false));
//        return map(func).extend(new CoreObservableConversion<R, Observable<R>>(OperatorMerge.<T>instance(false)));
        return null;
    }

}
