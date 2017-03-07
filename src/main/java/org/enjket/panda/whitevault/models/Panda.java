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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Panda.
 */
@Entity
public class Panda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "panda")
	private String panda;
	
	/**
	 * Instantiates a new panda.
	 */
	public Panda(){}
	
	/**
	 * Instantiates a new panda.
	 *
	 * @param panda the panda
	 */
	public Panda(String panda) {
		this.panda=panda;
	}
	public String getPanda() {
		return panda;
	}
	public void setPanda(String panda) {
		this.panda = panda;
	}
	
	/**
	 * To long.
	 *
	 * @return the long
	 */
	public long toLong(){
		return Long.parseLong(panda);
	}


}
