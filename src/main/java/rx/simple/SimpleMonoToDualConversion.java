package rx.simple;

import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.dual.DualOnSubscribe;
import rx.dual.DualSubscriber;
import rx.dual.MonoToDualOperator;
import rx.exceptions.OnErrorNotImplementedException;
import rx.single.MonoConversion;

public class SimpleMonoToDualConversion<R1, R2, T> implements MonoConversion<SimpleDualObservable<R1, R2>, T> {
    private MonoToDualOperator<R1, R2, T> operator;

    public SimpleMonoToDualConversion(MonoToDualOperator<R1, R2, T> operator) {
        this.operator = operator;
    }

    @Override
    public SimpleDualObservable<R1, R2> convert(OnSubscribe<T> onSubscribe) {
        return SimpleDualObservable.create(wrapSubscriber(onSubscribe));
    }

    private DualOnSubscribe<R1, R2> wrapSubscriber(OnSubscribe<T> onSubscribe) {
        return (DualSubscriber<? super R1, ? super R2> o) -> {
            try {
                Subscriber<? super T> st = operator.call(o);
                try {
                    // new Subscriber created and being subscribed with so 'onStart' it
                    st.onStart();
                    onSubscribe.call(st);
                } catch (Throwable e) {
                    // localized capture of errors rather than it skipping all operators 
                    // and ending up in the try/catch of the subscribe method which then
                    // prevents onErrorResumeNext and other similar approaches to error handling
                    if (e instanceof OnErrorNotImplementedException) {
                        throw e;
                    }
                    st.onError(e);
                }
            } catch (Throwable e) {
                if (e instanceof OnErrorNotImplementedException) {
                    throw e;
                }
                // if the lift function failed all we can do is pass the error to the final Subscriber
                // as we don't have the operator available to us
                o.onError(e);
            }
        };
    }

}
