package com.github.prgrms.configures.web;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.apache.commons.lang3.math.NumberUtils.toLong;
import static org.springframework.util.StringUtils.hasText;

public class SimplePageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final String DEFAULT_OFFSET_PARAMETER = "offset";

  private static final String DEFAULT_SIZE_PARAMETER = "size";

  private static final long DEFAULT_OFFSET = 0;

  private static final int DEFAULT_SIZE = 5;

  private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;

  private String sizeParameterName = DEFAULT_SIZE_PARAMETER;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Pageable.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
    MethodParameter methodParameter,
    ModelAndViewContainer mavContainer,
    NativeWebRequest webRequest,
    WebDataBinderFactory binderFactory
  ) {
    String offsetString = webRequest.getParameter(offsetParameterName);
    String sizeString = webRequest.getParameter(sizeParameterName);

    long offset = hasText(offsetString) ? toLong(offsetString, DEFAULT_OFFSET) : toLong(offsetString);
    int size = hasText(sizeString) ? toInt(sizeString, DEFAULT_SIZE) : toInt(sizeString);

    offset = offset >= Long.MAX_VALUE ? Long.MAX_VALUE : offset < 0 ? 0 : offset;
    size = size > 5 ? 5 : Math.max(size, 1);

    long finalOffset = offset;
    int finalSize = size;
    return new Pageable() {
      @Override
      public long getOffset() {
        return finalOffset;
      }

      @Override
      public int getSize() {
        return finalSize;
      }
    };
  }

  public void setOffsetParameterName(String offsetParameterName) {
    this.offsetParameterName = offsetParameterName;
  }

  public void setSizeParameterName(String sizeParameterName) {
    this.sizeParameterName = sizeParameterName;
  }

}