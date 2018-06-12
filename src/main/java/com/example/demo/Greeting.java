package com.example.demo;

public class Greeting {
  private String message;

  public Greeting() {
  }
  
  public Greeting(final String g) {
    this.message = g;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String greeting) {
    this.message = greeting;
  }
}
