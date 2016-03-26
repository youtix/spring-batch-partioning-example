package fr.youtix;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class App {
	
	private static final Logger log = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		App app = new App();
		app.cleanCsvFileIfExist();
		app.run();
	}

	private void run() {

		String[] springConfig = { "spring/batch/config/context-loader.xml" };

		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig)) {
			
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("partitionJob");

			jobLauncher.run(job, new JobParameters());

		} catch (Exception exception) {
			log.error(exception);
		}
		
	}

	private void cleanCsvFileIfExist() {
		try {
			Resource folder = new FileSystemResource("csv/outputs/");
			File[] files = folder.getFile().listFiles();
		    if(files!=null) {
		        for(File f: files) {
		            f.delete();
		        }
		    }
	    } catch (IOException exception) {
	    	log.error(exception);
		}
	}
}
