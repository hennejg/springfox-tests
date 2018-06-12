package com.example.demo;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class GreetingFacet implements Facet {

  @Override
  public String getName() {
    return "greeting";
  }

  @Override
  public JavaType getValueType(final TypeFactory f) {
    return f.constructType(Greeting.class);
  }

}
