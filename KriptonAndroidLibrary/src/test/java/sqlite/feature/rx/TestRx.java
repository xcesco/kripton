package sqlite.feature.rx;

import com.abubusoft.kripton.common.One;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestRx {

    @Test
    public void test() {
            final One<String> result = new One<String>("");
            Observable<String> observer = Observable.just("Hello"); // provides datea
            observer.subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                        result.value0=s;
                }
            });
            // Callable as subscriber
            assertTrue(result.value0.equals("Hello"));
    }
}
