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
 * The Class RandomNumberPumps sets up three random number pumps for generating the required
 * random numbers for creating pads and tokens.
 */
public class RandomNumberPumps {

	private static final Channel digitChannel = new Channel(3000);
	private static final Channel padChannel = new Channel();
	private static final Channel sixDigitChannel = new Channel();
	private static final Channel fiveDigitChannel = new Channel();


	static {
		new Thread(new RandomDigitPump(digitChannel)).start();
		new Thread(new RandomFixedLengthPump(16,digitChannel,padChannel)).start();
		new Thread(new RandomFixedLengthPump(6,digitChannel,sixDigitChannel)).start();
		new Thread(new RandomFixedLengthPump(5,digitChannel,fiveDigitChannel)).start();

	}

	/**
	 * Gets the pad str.
	 *
	 * @return the pad str
	 */
	public static String getPadStr(){
		return padChannel.take();
	}
	
	
	/**
	 * Gets the six digit str.
	 *
	 * @return the six digit str
	 */
	public static String getSixDigitStr(){
		return sixDigitChannel.take(); 
	}
	
	/**
	 * Gets the five digit str.
	 *
	 * @return the five digit str
	 */
	public static String getFiveDigitStr(){
		return fiveDigitChannel.take(); 
	}

}
