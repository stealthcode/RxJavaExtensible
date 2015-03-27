package rx.single;

import rx.Observable.OnSubscribe;

public interface MonoConversion<O, T> {
    public O convert(OnSubscribe<T> onSubscribe);
}
