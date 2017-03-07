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
 * The Class TokenPadPair.
 */
@Entity
public class TokenPadPair {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "pad")
	private String pad;
	
	/**
	 * Instantiates a new token pad pair.
	 */
	public TokenPadPair(){}
	
	/**
	 * Instantiates a new token pad pair.
	 *
	 * @param token the token
	 * @param pad the pad
	 */
	public TokenPadPair(Token token, Pad pad){
		this.token=token.getToken();
		this.pad=pad.getPad();
	}
	
	/**
	 * Instantiates a new token pad pair.
	 *
	 * @param token the token
	 * @param pad the pad
	 */
	public TokenPadPair(String token, String pad){
		this.token=token;
		this.pad=pad;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPad() {
		return pad;
	}

	public void setPad(String pad) {
		this.pad = pad;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenPadPair [token=" + token + ", pad=" + pad + "]";
	}



}
