package rx.dual;

import rx.Subscriber;

public abstract class DualSubscriber<T1, T2> extends Subscriber<T1> implements DualObserver<T1, T2> {

    @Override
    public abstract void onCompleted();

    @Override
    public abstract void onError(Throwable e);

    @Override
    public void onNext(T1 t) {}

    @Override
    public abstract void onNext(T1 t1, T2 t2);

}
