package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import springfox.documentation.annotations.ApiIgnore;

public class Foo extends ResourceSupport {
  private final Map<String, Object> facets = new HashMap<>();

  @ApiIgnore
  @JsonAnyGetter
  public Map<String, Object> getFacets() {
    return facets;
  }
}
