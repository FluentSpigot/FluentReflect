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

package io.github.jwdeveloper.reflect.implementation.old.packages;

import org.jetbrains.annotations.Nullable;

public interface IPacketOperator {

  /**
   * Used to extract the player-name from a LoginIn-Packet
   * @param requester The interceptor which requested to extract a name
   * @param packet Any packet, maybe a LoginIn-Packet
   * @return Name on success, null otherwise
   */
 // @Nullable String tryExtractName(Interceptor requester, Object packet) throws Exception;

  /**
   * Used to send a packet using a network manager instance
   * @param packet Packet instance to send
   * @param completion Optional completion callback, nullable
   * @param networkManager Network manager reference to invoke sending on
   */
  void sendPacket(Object packet, @Nullable Runnable completion, Object networkManager) throws Exception;

}
