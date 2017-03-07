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
package org.enjket.panda.whitevault.models;

import javax.persistence.Column;

/**
 * The Class Pan.
 */
public class Pan {

	@Column(name = "pan")
	private String pan;

	/**
	 * Instantiates a new pan.
	 */
	public Pan(){}
	
	/**
	 * Instantiates a new pan.
	 *
	 * @param pan the pan
	 */
	public Pan(long pan){
		this.pan=String.valueOf(pan);
	}
	
	/**
	 * Instantiates a new pan.
	 *
	 * @param pan the pan
	 */
	public Pan(String pan) {
		this.pan=pan;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getBIN(){
		return pan.substring(0, 6);
	}
	
	public String getLastFour() {
		return pan.substring(pan.length()-4, pan.length());
	}

	/**
	 * To long.
	 *
	 * @return the long
	 */
	public long toLong(){
		return Long.parseLong(pan);
	}
	
	/**
	 * Length.
	 *
	 * @return the int
	 */
	public int length(){
		return pan.length();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pan [pan=" + getBIN() +"XXXX" + getLastFour() + "]";
	}

	
}
