package com.kn.amqp.common.model.core;

import java.util.Map;

import org.immutables.value.Value;

public interface ToMap {

  @Value.Auxiliary
  Map<String, Object> toMap();
}