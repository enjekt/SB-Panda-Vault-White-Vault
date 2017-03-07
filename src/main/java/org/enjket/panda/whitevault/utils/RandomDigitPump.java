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

import java.security.SecureRandom;

/**
 * The Class RandomDigitPump generates secure stream of single digits that are pumped into
 * the assigned channel. It continues to fill the channel until it blocks due to the queue
 * being full.  The pump starts generating digits again as they are removed from the channel.
 * The random pumps are used to ensure randomness and optimize the random number generation.
 */
public class RandomDigitPump implements Runnable {
	private Channel outputChannel;
	private static final SecureRandom random = new SecureRandom();

	/**
	 * Instantiates a new random digit pump.
	 *
	 * @param outputChannel the output channel
	 */
	public RandomDigitPump(Channel outputChannel) {
		this.outputChannel = outputChannel;
		random.setSeed(System.nanoTime());
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (true) {
			//We use 0 to 8 from the random number generator
			//and then addd 1 to eliminate any leading
			//0 that will cause truncation when converted to a long
			//for mathematical purposes. 
			//This also ensures that when the pad is generated it will be
			//at a minimum, 1111111111111111 instead of zeroes. This
			//ensures that an unmasked/unpadded PAN will never be stored
			//and it also ensures that the padded PAN will be of sufficiently
			//large magnitude to ensure the impossibility of guess the number.
			//
			outputChannel.put(Integer.toString(random.nextInt(9)+1));
		}
	}
}
