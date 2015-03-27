package rx.simple.operator;

import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorFilter;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorScan;
import rx.simple.SimpleMonoConversion;
import rx.simple.SimpleMonoObservable;
import rx.single.MonoConversion;

public class CoreOperators {

    public static <T> MonoConversion<SimpleMonoObservable<T>, T> filter(Func1<? super T, Boolean> predicate) {
        return new SimpleMonoConversion<T, T>(new OperatorFilter<T>(predicate));
    }
    
    public static <T, R> MonoConversion<SimpleMonoObservable<R>, T> map(Func1<? super T, ? extends R> mapFunc) {
        return new SimpleMonoConversion<R, T>(new OperatorMap<T, R>(mapFunc));
    }

    public static <T, R> MonoConversion<SimpleMonoObservable<R>, T> scan(R initialValue, Func2<R, ? super T, R> accumulator) {
        return new SimpleMonoConversion<R, T>(new OperatorScan<R, T>(initialValue, accumulator));
    }
    
    public static <T, R> MonoConversion<SimpleMonoObservable<R>, T> scan(Func2<R, ? super T, R> accumulator) {
        return new SimpleMonoConversion<R, T>(new OperatorScan<R, T>(accumulator));
    }
    
}
