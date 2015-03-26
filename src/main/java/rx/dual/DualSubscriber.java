package rx.dual;

import rx.Subscriber;

public abstract class DualSubscriber<T1, T2> extends Subscriber<T1> implements DualObserver<T1, T2> {

}
