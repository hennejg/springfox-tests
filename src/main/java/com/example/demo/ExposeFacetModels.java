package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.schema.ModelProperty;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelContext;

@Component
public class ExposeFacetModels implements ModelBuilderPlugin {

  @Autowired(required = false)
  private List<Facet> facets;

  @Autowired
  private TypeResolver typeResolver;

  @Override
  public boolean supports(final DocumentationType delimiter) {
    return true;
  }

  @Override
  public void apply(final ModelContext context) {
    if (context.resolvedType(typeResolver).getErasedType().equals(Foo.class)) {
      Map<String, ModelProperty> facetProperties = new HashMap<>();

      facets.forEach(f -> {
        JavaType valueType = f.getValueType(TypeFactory.defaultInstance());
        ModelPropertyBuilder builder = new ModelPropertyBuilder().name(f.getName()).type(fromJackson(valueType));
        facetProperties.put(f.getName(), builder.build());
      });

      context.getBuilder().properties(facetProperties);
    }
  }

  private ResolvedType fromJackson(final JavaType t) {
    if(t.isArrayType())
      return typeResolver.arrayType(fromJackson(t.getContentType()));

    if(t.isCollectionLikeType())
      return typeResolver.resolve(List.class, fromJackson(t.getContentType()));

    if(t.isMapLikeType())
      return typeResolver.resolve(Map.class, fromJackson(t.getKeyType()), fromJackson(t.getContentType()));
    
    return typeResolver.resolve(t.getRawClass());
  }
}
