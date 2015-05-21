package rx.simple;

import rx.Observable.OnSubscribe;
import rx.Observable.Operator;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.OnErrorNotImplementedException;
import rx.single.MonoConversion;

public class CoreObservableConversion<R, T> implements MonoConversion<CoreObservable<R>, T> {

    private Operator<? extends R, ? super T> operator;

    public CoreObservableConversion(Operator<? extends R, ? super T> operator) {
        this.operator = operator;
    }

    @Override
    public CoreObservable<R> convert(OnSubscribe<T> onSubscribe) {
        return CoreObservable.create(wrapSubscriber(onSubscribe, operator));
    }

    protected OnSubscribe<R> wrapSubscriber(OnSubscribe<T> onSubscribe, Operator<? extends R, ? super T> operator) {
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
