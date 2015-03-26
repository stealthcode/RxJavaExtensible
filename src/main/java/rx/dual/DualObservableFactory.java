package rx.dual;

import rx.functions.Func2;

public interface DualObservableFactory<O extends DualObservable<R1, R2>, R1, R2, T1, T2> extends Func2<DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2>, DualOnSubscribe<T1, T2>, O> {
    @Override
    public O call(DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2> operator, DualOnSubscribe<T1, T2> onSubscribe);
}
