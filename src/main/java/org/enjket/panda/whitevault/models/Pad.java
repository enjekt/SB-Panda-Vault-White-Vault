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
 * The Class Pad.
 */
@Entity
public class Pad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "pad")
	private String pad;
	
	/**
	 * Instantiates a new pad.
	 */
	public Pad(){}

	/**
	 * Instantiates a new pad.
	 *
	 * @param pad the pad
	 */
	public Pad(String pad) {
		this.pad=pad;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getPad() {
		return pad;
	}


	public void setPad(String pad) {
		this.pad = pad;
	}

	/**
	 * To long.
	 *
	 * @return the long
	 */
	public long toLong(){
		return Long.parseLong(pad);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pad [id=" + id + ", pad=" + pad + "]";
	}


}
