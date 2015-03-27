package rx.dual;

import rx.Subscription;


public abstract class DualSubscriber<T1, T2> implements DualObserver<T1, T2>, Subscription {

    @Override
    public abstract void onCompleted();

    @Override
    public abstract void onError(Throwable e);

    @Override
    public abstract void onNext(T1 t1, T2 t2);

    public void onStart() {
        
    }
    
    @Override
    public void unsubscribe() {
        
    }

    @Override
    public boolean isUnsubscribed() {
        return false;
    }

}
