package com.devsuperior.uri2602;

import com.devsuperior.uri2602.DTO.CustomerNameDTO;
import com.devsuperior.uri2602.projections.CustomerNameProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CustomerNameProjection> list = repository.search1("rs");

		List<CustomerNameDTO> result1 = list.stream().map(CustomerNameDTO::new).collect(Collectors.toList());
		List<CustomerNameDTO> result2 = repository.search2("rs");


		System.out.println("\n \n*****SQL Result***");
		for (CustomerNameDTO obj : result1) {
			System.out.println(obj.getName());
		}

		System.out.println("\n \n*****JPQL Result***");
		for (CustomerNameDTO obj : result2) {
			System.out.println(obj.getName());
		}

	}
}
