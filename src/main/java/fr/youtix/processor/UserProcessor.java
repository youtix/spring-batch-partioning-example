package fr.youtix.processor;

import java.util.Random;

import org.springframework.batch.item.ItemProcessor;

import fr.youtix.User;

public class UserProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User item) throws Exception {
		
		long randomInt = new Random().nextInt(3);
		
		Thread.sleep(randomInt * 1000);
		
		return item;
	}

}
