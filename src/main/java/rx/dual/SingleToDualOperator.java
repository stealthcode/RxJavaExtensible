package rx.dual;

import rx.Subscriber;
import rx.functions.Func1;

public interface SingleToDualOperator<R1, R2, T> extends Func1<DualSubscriber<? super R1, ? super R2>, Subscriber<? super T>> {
    @Override
    public Subscriber<? super T> call(DualSubscriber<? super R1, ? super R2> subscriber);
}