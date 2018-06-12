package com.example.demo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public interface Facet {
  String getName();
  
  JavaType getValueType(TypeFactory f);
}
