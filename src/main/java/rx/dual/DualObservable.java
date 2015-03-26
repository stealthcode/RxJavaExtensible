package rx.dual;


public interface DualObservable<T1, T2> {
    public void subscribe(DualSubscriber<T1, T2> subscriber);
    public <R1, R2> DualObservable<R1, R2> lift(final DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2> operator);
    public <R1, R2, O extends DualObservable<R1, R2>> O lift(DualObservableFactory<O, R1, R2, T1, T2> factory, DualOperator<? extends R1, ? extends R2, ? super T1, ? super T2> operator);
    public <R1, R2, O extends DualObservable<R1, R2>> O extend(DualExtendingOperator<O, R1, R2, T1, T2> operator);
}
