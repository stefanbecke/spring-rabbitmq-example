package com.kn.amqp.common.model.core;

import java.util.function.Supplier;

import org.immutables.value.Value;

public interface IdSupplier extends Supplier<String> {

  @Value.Parameter
  String getId();

  @Value.Auxiliary
  @Override
  default String get() {
    return getId();
  }
}