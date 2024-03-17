package www.lxy.component.process;

import org.springframework.batch.item.ItemProcessor;
import www.lxy.utils.GlobalPrint;

import java.util.function.Function;

public class SpecialProcessor<IN, OUT> implements ItemProcessor<IN, OUT>, GlobalPrint {
    private static final String name = "specialProcess";
    private final Function<IN, OUT> process;
    private String suffix;

    public SpecialProcessor(Function<IN, OUT> process) {
        this.process = process;
    }

    public SpecialProcessor(Function<IN, OUT> process, String suffix) {
        this.process = process;
        this.suffix = suffix;
    }

    public String getName() {
        return name + suffix;
    }

    @Override
    public OUT process(IN item) throws Exception {
        Function<IN, OUT> processWrapper = (it) -> {
            Object[] ans = new Object[1];
            globalPrint(() -> ans[0] = process.apply(it));
            return (OUT) ans[0];
        };
        return processWrapper.apply(item);
    }
}
