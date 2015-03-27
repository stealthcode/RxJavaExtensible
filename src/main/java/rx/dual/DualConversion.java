package rx.dual;

public interface DualConversion<O, T1, T2> {
    public O convert(DualOnSubscribe<T1, T2> onSubscribe);
}
