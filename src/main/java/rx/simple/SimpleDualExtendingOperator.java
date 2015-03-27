package rx.simple;

import rx.dual.DualExtendingOperator;
import rx.dual.DualOnSubscribe;
import rx.dual.DualOperator;
import rx.dual.DualSubscriber;
import rx.exceptions.OnErrorNotImplementedException;

public class SimpleDualExtendingOperator<R1, R2, T1, T2> implements DualExtendingOperator<SimpleDualObservable<R1, R2>, R1, R2, T1, T2> {
    
    private DualOperator<R1, R2, T1, T2> operator;

    public SimpleDualExtendingOperator(DualOperator<R1, R2, T1, T2> operator) {
        this.operator = operator;
    }

    @Override
    public SimpleDualObservable<R1, R2> compose(DualOnSubscribe<T1, T2> onSubscribe) {
        return SimpleDualObservable.create(wrapSubscriber(onSubscribe));
    }

    private DualOnSubscribe<R1, R2> wrapSubscriber(DualOnSubscribe<T1, T2> onSubscribe) {
        return (DualSubscriber<? super R1, ? super R2> o) -> {
            try {
                DualSubscriber<? super T1, ? super T2> st = operator.call(o);
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
