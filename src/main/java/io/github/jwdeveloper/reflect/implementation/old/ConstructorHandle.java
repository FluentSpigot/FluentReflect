/*
 * MIT License
 *
 * Copyright (c) 2023 BlvckBytes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.jwdeveloper.reflect.implementation.old;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

@SuppressWarnings("rawtypes")
public class ConstructorHandle extends AHandle<Constructor> {

  private final @Nullable FCallTransformer callTransformer;

  public ConstructorHandle(Class<?> target, ServerVersion version, @Nullable FCallTransformer callTransformer, FMemberPredicate<Constructor> predicate) throws NoSuchElementException {
    super(target, Constructor.class, version, predicate);
    this.callTransformer = callTransformer;
  }

  /**
   * Create a new instance by invoking this constructor
   * @param args Args to pass when calling
   * @return Instance of the constructor's declaring class
   */
  public Object newInstance(Object... args) throws Exception {
    if (callTransformer != null)
      args = callTransformer.apply(args);
    return handle.newInstance(args);
  }

  /**
   * Get the number of parameters this constructor requires
   */
  public int getParameterCount() {
    return handle.getParameterCount();
  }

  @Override
  protected String stringify(Constructor member) {
    StringJoiner sj = new StringJoiner(" ");

    sj.add(Modifier.toString(member.getModifiers()));
    sj.add(member.getDeclaringClass().getName());
    sj.add("(");

    StringJoiner argJoiner = new StringJoiner(", ");
    for (Class<?> parameter : member.getParameterTypes())
      argJoiner.add(parameter.getName());

    sj.add(argJoiner.toString());
    sj.add(")");

    return sj.toString();
  }
}
