package rx.dual;

import rx.functions.Action1;

public interface DualOnSubscribe<T1, T2> extends Action1<DualSubscriber<? super T1, ? super T2>> {
    @Override
    public void call(DualSubscriber<? super T1, ? super T2> subscriber);        
}