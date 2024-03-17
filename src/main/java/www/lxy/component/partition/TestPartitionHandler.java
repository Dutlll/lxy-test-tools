package www.lxy.component.partition;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.StepExecutionSplitter;

import java.util.Collection;

public class TestPartitionHandler implements PartitionHandler {

    @Override
    public Collection<StepExecution> handle(StepExecutionSplitter stepExecutionSplitter, StepExecution stepExecution) throws Exception {
        return null;
    }
}
