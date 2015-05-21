package rx.simple;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorFilter;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorScan;
import rx.single.MonoConversion;

public class CoreObservable<T> {
    protected OnSubscribe<T> onSubscribe;
    
    private CoreObservable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> CoreObservable<T> create(OnSubscribe<T> onSubscribe) {
        return new CoreObservable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<T> subscriber) {
        onSubscribe.call(subscriber);
    }

    public <R> CoreObservable<R> lift(Operator<? extends R, ? super T> operator) {
        return extend(new CoreObservableConversion<R, T>(operator));
    }

    public <R, O> O extend(MonoConversion<O, T> operator) {
        return operator.convert(onSubscribe);
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
        
//        return map(func).extend(new MonoConversion<CoreObservable<R>, R>(){
//
//            @Override
//            public CoreObservable<R> convert(OnSubscribe<R> onSubscribe) {
//            }});
        return null;
    }

}
