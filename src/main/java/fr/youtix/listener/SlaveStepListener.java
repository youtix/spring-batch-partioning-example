package fr.youtix.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class SlaveStepListener implements StepExecutionListener {
	
	private static final Logger log = LogManager.getLogger(SlaveStepListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("Launching Step {}", stepExecution.getStepName());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		log.info(stepExecution.getSummary());
		return null;
	}

}
