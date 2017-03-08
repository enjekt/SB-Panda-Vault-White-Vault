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
package org.enjket.panda.whitevault.service.impl;

import org.enjket.panda.whitevault.models.Pad;
import org.enjket.panda.whitevault.models.Token;
import org.enjket.panda.whitevault.models.TokenPadPair;
import org.enjket.panda.whitevault.service.BlackVaultService;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * The Class BlackVaultServiceImpl is used to send messages to the Black Vault
 * to store and retrieve pads based on a token.
 */
@Service("blackVaultService")
@Transactional
public class BlackVaultServiceImpl implements BlackVaultService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.BlackVaultService#getPad(org.enjket.
	 * panda.whitevault.models.Token)
	 */
	@Override
	public Pad getPad(Token token) {
		RestTemplate restTemplate = createTemplate();
		Pad pad = restTemplate.getForObject("http://localhost:8089/pad/" + token.getToken(), Pad.class);

		return pad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.BlackVaultService#store(org.enjket.
	 * panda.whitevault.models.Token, org.enjket.panda.whitevault.models.Pad)
	 */
	public void store(Token token, Pad pad) {
		RestTemplate restTemplate = createTemplate();

		TokenPadPair pair = new TokenPadPair(token, pad);
		restTemplate.postForLocation("http://localhost:8089/pad", pair);
	}

	@Override
	public void deleteToken(Token deleteToken) {
		RestTemplate restTemplate = createTemplate();
		restTemplate.delete("http://localhost:8089/token/" + deleteToken.getToken(), Token.class);

	}

	private RestTemplate createTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "password"));
		return restTemplate;
	}

}
