package rx.dual;

public interface DualExtendingOperator<O extends DualObservable<R1, R2>, R1, R2, T1, T2> {
    public O compose(DualOnSubscribe<T1, T2> onSubscribe);
}
