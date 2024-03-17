package www.lxy.component.writer;

import org.springframework.batch.item.ItemWriter;
import www.lxy.utils.GlobalPrint;

import java.util.List;
import java.util.function.Consumer;

public class SpecialWriter<Out> implements ItemWriter<Out>, GlobalPrint {
    private static final String name = "specialWriter";
    private final Consumer<List<Out>> consumerInner;

    public SpecialWriter(Consumer<List<Out>> consumerInner) {
        this.consumerInner = consumerInner;
    }

    @Override
    public void write(List<? extends Out> items) throws Exception {
        Consumer<List<? extends Out>> wrapper = (list) -> {
            globalPrint(() -> {
                for (Out cur : list) {

                }
            });
        };
        wrapper.accept(items);
    }
}
