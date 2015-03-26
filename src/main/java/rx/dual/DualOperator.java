package rx.dual;

import rx.functions.Func1;

public interface DualOperator<R1, R2, T1, T2> extends Func1<DualSubscriber<? super R1, ? super R2>, DualSubscriber<? super T1, ? super T2>> {
    @Override
    public DualSubscriber<? super T1, ? super T2> call(DualSubscriber<? super R1, ? super R2> subscriber);
}