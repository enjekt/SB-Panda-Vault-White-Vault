/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enjket.panda.whitevault.utils;

/**
 * The Class RandomFixedLengthPump reads from the random digit channel
 * and constructs a fixed width random string based on the string length
 * specified.  The pump continues to generated fixed width random strings
 * until the channel is blocked.  When a consumer pulls random numbers from
 * the channel the pump refills it until it blocks.
 */
public class RandomFixedLengthPump implements Runnable {
	private final Channel outputChannel;
	private final Channel inputChannel;
	private final int strLength;

	/**
	 * Instantiates a new random fixed length pump.
	 *
	 * @param strLength the str length
	 * @param inputChannel the input channel
	 * @param outputChannel the output channel
	 */
	public RandomFixedLengthPump(int strLength, Channel inputChannel, Channel outputChannel) {
		this.outputChannel = outputChannel;
		this.inputChannel = inputChannel;
		this.strLength = strLength;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < strLength; i++) {
				buffer.append(inputChannel.take());
			}

			outputChannel.put(buffer.toString());

		}
	}
}
