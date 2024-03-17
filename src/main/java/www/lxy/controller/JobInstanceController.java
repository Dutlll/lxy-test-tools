package www.lxy.controller;

import www.lxy.component.jobConfig.JobConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.ReferenceJobFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.scope.context.JobSynchronizationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping("/i")
public class JobInstanceController {
    @Resource
    private JobConfig jobConfig;
    @Resource
    private JobOperator jobOperator;
    @Resource
    private JobLauncher jobLauncher;
    @Resource
    private JobRegistry jobRegistry;
    @Resource
    private JobExplorer jobExplorer;


    @RequestMapping("/stop")
    @ResponseBody
    public String stop(@RequestParam long executionID) {
        try {
            JobExecution jobExecution = jobExplorer.getJobExecution(executionID);
            assert jobExecution != null;
            JobSynchronizationManager.register(jobExecution);
            final boolean stop = jobOperator.stop(executionID);
            return String.valueOf(stop);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/restart")
    @ResponseBody
    public String restart(@RequestParam long executionId) {
        try {
            jobRegistry.register(new ReferenceJobFactory(jobConfig.getTestJob()));
            long restart = jobOperator.restart(executionId);
            return String.valueOf(restart);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private static final String RANDOM_VALUE = "RANDOM_VALUE";
    @RequestMapping("/run")
    @ResponseBody
    public String run(@RequestParam String name) {
        try {
            final Job testJob = jobConfig.getTestJob();
            final JobParameters jobParameters = new JobParameters();
            jobParameters.getParameters().put(RANDOM_VALUE,new JobParameter(UUID.randomUUID().toString()));
            final JobExecution run = jobLauncher.run(testJob, jobParameters);
            System.out.println(run);
            return run.toString();
//            return JSONUtils.toJSONString(run);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
