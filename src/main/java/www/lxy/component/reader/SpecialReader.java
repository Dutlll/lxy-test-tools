package www.lxy.component.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import www.lxy.utils.GlobalPrint;

import java.util.function.Supplier;

public class SpecialReader<OUT> implements ItemReader<OUT>, GlobalPrint {
    private final Supplier<OUT> supplier;
    private final String name = "specialReader";

    public SpecialReader(Supplier<OUT> supplier, String suffix) {
        this.supplier = supplier;
    }

    @Override
    public OUT read() throws Exception {
        Supplier<OUT> wrapper = () -> {
            Object[] ans = new Object[1];
            globalPrint(() -> ans[0] = this.supplier.get());
            return (OUT) ans[0];
        };
        return wrapper.get();
    }
}
