package rx.dual;

import rx.Subscriber;
import rx.functions.Func1;

public interface SingleToDualOperator<R, T1, T2> extends Func1<Subscriber<? super R>, DualSubscriber<? super T1, ? super T2>> {
    @Override
    public DualSubscriber<? super T1, ? super T2> call(Subscriber<? super R> subscriber);
}