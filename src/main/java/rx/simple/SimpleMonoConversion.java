package rx.simple;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.OnErrorNotImplementedException;
import rx.single.MonoConversion;

public class SimpleMonoConversion<R, T> implements MonoConversion<SimpleMonoObservable<R>, T> {
    
    private Operator<? extends R, ? super T> operator;

    public SimpleMonoConversion(Operator<? extends R, ? super T> operator) {
        this.operator = operator;
    }

    @Override
    public SimpleMonoObservable<R> convert(OnSubscribe<T> onSubscribe) {
        return SimpleMonoObservable.create(wrapSubscriber(onSubscribe));
    }

    private OnSubscribe<R> wrapSubscriber(OnSubscribe<T> onSubscribe) {
        return (Subscriber<? super R> o) -> {
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
