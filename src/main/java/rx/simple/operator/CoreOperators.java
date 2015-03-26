package rx.simple.operator;

import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorFilter;
import rx.internal.operators.OperatorMap;
import rx.internal.operators.OperatorScan;
import rx.simple.SimpleExtendingOperator;
import rx.simple.SimpleSingleObservable;
import rx.single.SingleExtendingOperator;

public class CoreOperators {

    public static <T> SingleExtendingOperator<SimpleSingleObservable<T>, T, T> filter(Func1<? super T, Boolean> predicate) {
        return new SimpleExtendingOperator<T, T>(new OperatorFilter<T>(predicate));
    }
    
    public static <T, R> SingleExtendingOperator<SimpleSingleObservable<R>, R, T> map(Func1<? super T, ? extends R> mapFunc) {
        return new SimpleExtendingOperator<R, T>(new OperatorMap<T, R>(mapFunc));
    }

    public static <T, R> SingleExtendingOperator<SimpleSingleObservable<R>, R, T> scan(R initialValue, Func2<R, ? super T, R> accumulator) {
        return new SimpleExtendingOperator<R, T>(new OperatorScan<R, T>(initialValue, accumulator));
    }
    
    public static <T, R> SingleExtendingOperator<SimpleSingleObservable<R>, R, T> scan(Func2<R, ? super T, R> accumulator) {
        return new SimpleExtendingOperator<R, T>(new OperatorScan<R, T>(accumulator));
    }
    
}
