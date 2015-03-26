package rx.dual;

import rx.Observer;

public interface DualObserver<T1, T2> extends Observer<T1> {
    public void onNext(T1 t1, T2 t2);
}
