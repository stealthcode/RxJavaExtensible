package rx.dual;


public interface DualObserver<T1, T2> {
    public void onNext(T1 t1, T2 t2);

    public void onCompleted();

    public void onError(Throwable e);
}
