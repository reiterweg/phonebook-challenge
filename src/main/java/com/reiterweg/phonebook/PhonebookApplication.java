package com.reiterweg.phonebook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(PhonebookApplication.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {
    System.out.println("Starting phonebook challenge...");
  }
}
