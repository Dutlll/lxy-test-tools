package www.lxy.component.flow;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFlowBuilder {
    @Bean
    public Flow splitFlow() {
        return new FlowBuilder<SimpleFlow>("")
                .split(null)
                .add(null,null).build();
    }
    public Flow getSimpleFlow(){
        return new MyFlowBuilder().getSimpleFlow();
    }
}
