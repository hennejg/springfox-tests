package com.example.demo;

import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/foo", produces = {
    MediaType.APPLICATION_JSON_VALUE
})
@EnableEntityLinks
@Api(tags = "Document", authorizations = {
    @Authorization(value = "oauth")
})
public class FooResource {

  // GET - Returns a specific document by ID
  @GetMapping
  @ApiOperation("Returns a foo")
  public Foo get() {
    Foo foo = new Foo();
    
    foo.getFacets().put("greeting", new Greeting("Hello, world"));
    
    return foo;
  }
}