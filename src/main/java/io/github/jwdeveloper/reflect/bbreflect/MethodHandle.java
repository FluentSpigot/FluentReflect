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

package me.blvckbytes.bbreflect;

import me.blvckbytes.bbreflect.version.ServerVersion;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class MethodHandle extends AHandle<Method> {

  private final @Nullable FCallTransformer callTransformer;

  protected MethodHandle(Class<?> target, ServerVersion version, @Nullable FCallTransformer callTransformer, FMemberPredicate<Method> predicate) throws NoSuchElementException {
    super(target, Method.class, version, predicate);
    this.callTransformer = callTransformer;
  }

  /**
   * Invoke this method on an object instance
   * @param o Target object to invoke on
   * @param args Arguments to pass when invoking the method
   * @return Method return value
   */
  public Object invoke(Object o, Object... args) throws Exception {
    if (callTransformer != null)
      args = callTransformer.apply(args);
    return handle.invoke(o, args);
  }

  @Override
  protected String stringify(Method member) {
    if(true)
    {
      return coloredVersion(member);
    }

    StringJoiner sj = new StringJoiner(" ");


    sj.add(Modifier.toString(member.getModifiers()));
    sj.add(member.getReturnType().getName());
    sj.add(member.getName());
    sj.add("(");

    StringJoiner argJoiner = new StringJoiner(", ");
    for (Class<?> parameter : member.getParameterTypes())
      argJoiner.add(parameter.getName());

    sj.add(argJoiner.toString());
    sj.add(")");

    return sj.toString();
  }

  private String coloredVersion(Method member)
  {
    StringBuilder sj = new StringBuilder(" ");


    sj.append(ChatColor.BLUE+" ").append(Modifier.toString(member.getModifiers()));
    sj.append(ChatColor.WHITE+" ").append(member.getReturnType().getName());
    sj.append(ChatColor.YELLOW+" ").append(member.getName());
    Parameter[] parameters = member.getParameters();
    if(parameters.length == 0)
    {
      sj.append(ChatColor.WHITE+"()");
      return sj.toString();
    }

    sj.append(ChatColor.WHITE+"(");

    StringJoiner argJoiner = new StringJoiner(ChatColor.WHITE+", ");
    for (Class<?> parameter : member.getParameterTypes())
      argJoiner.add(ChatColor.WHITE+parameter.getName());

    sj.append(argJoiner.toString());
    sj.append(ChatColor.WHITE+")");

    return sj.toString();
  }
}
