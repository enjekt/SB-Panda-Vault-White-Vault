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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * The Class Channel is a wrapper class around the blocking queue used to hold
 * random numbers.
 */
public class Channel {
	private final BlockingQueue<String> queue;

	/**
	 * Instantiates a new channel.
	 */
	public Channel(){
		queue=new ArrayBlockingQueue<String>(200);
	}
	
	/**
	 * Instantiates a new channel.
	 *
	 * @param capacity the capacity
	 */
	public Channel(int capacity){
		queue=new ArrayBlockingQueue<String>(capacity);
	}
	
	
	/**
	 * Put.
	 *
	 * @param e the e
	 */
	public void put(String e)  {
		try {
			queue.put(e);
		} catch (InterruptedException e1) {
			//Nothing to do...expected.
		}
	}
	
	/**
	 * Take.
	 *
	 * @return the string
	 */
	public String take()  {
		String value="";
		try {
			value = queue.take();
		} catch (InterruptedException e) {
			//Nothing to do... expected.
		}
		return value;
	}
}
