package me.blvckbytes.bbreflect;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tuple<A, B> {
  private final A a;
  private final B b;
}
