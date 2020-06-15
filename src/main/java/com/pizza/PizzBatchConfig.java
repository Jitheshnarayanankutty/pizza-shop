package com.pizza;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pizza.batch.PizzaShopClosedJob;
import com.pizza.batch.PizzaShopOpenJob;

@Configuration
@EnableBatchProcessing
public class PizzBatchConfig {

	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Bean
	public Step shopOpen() {
		return steps.get("pizzaShopOpen").tasklet(new PizzaShopOpenJob()).build();
	}
	
	@Bean
	public Step shopClose() {
		return steps.get("pizzaShopClose").tasklet(new PizzaShopClosedJob()).build();
	}
	
	@Bean
	public Job runJobs() {
		return jobs.get("runJobs").incrementer(new RunIdIncrementer()).start(shopOpen()).next(shopClose()).build();
	}
}
