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
 * The Class TokenPandaPair.
 */
@Entity
public class TokenPandaPair {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "panda")
	private String panda;
	
	/**
	 * Instantiates a new token panda pair.
	 */
	public TokenPandaPair(){}
	
	/**
	 * Instantiates a new token panda pair.
	 *
	 * @param token the token
	 * @param panda the panda
	 */
	public TokenPandaPair(Token token, Panda panda){
		this.token=token.getToken();
		this.panda=panda.getPanda();
	}
	
	/**
	 * Instantiates a new token panda pair.
	 *
	 * @param token the token
	 * @param panda the panda
	 */
	public TokenPandaPair(String token, String panda){
		this.token=token;
		this.panda=panda;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPanda() {
		return panda;
	}

	public void setPanda(String panda) {
		this.panda = panda;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenPadPair [token=" + token + ", panda.lengh()=" + panda.length() + "]";
	}

}
