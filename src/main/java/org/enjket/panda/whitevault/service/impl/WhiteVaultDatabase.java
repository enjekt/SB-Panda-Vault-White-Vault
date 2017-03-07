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

import org.enjket.panda.whitevault.models.Panda;
import org.enjket.panda.whitevault.models.Token;
import org.enjket.panda.whitevault.models.TokenPandaPair;
import org.enjket.panda.whitevault.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class WhiteVaultDatabase is the facade the White Vault uses to interact with the
 * repository.  
 */
@Service("databaseService")
@Transactional
public class WhiteVaultDatabase implements DatabaseService{

	/** The repository. */
	@Autowired
	WhiteVaultRepository repository;
	
	/* (non-Javadoc)
	 * @see org.enjket.panda.whitevault.service.DatabaseService#store(org.enjket.panda.whitevault.models.Token, org.enjket.panda.whitevault.models.Panda)
	 */
	@Override
	public void store(Token token, Panda panda) {
		repository.saveAndFlush(new TokenPandaPair(token,panda));
		
	}

	/* (non-Javadoc)
	 * @see org.enjket.panda.whitevault.service.DatabaseService#getPanda(org.enjket.panda.whitevault.models.Token)
	 */
	@Override
	public Panda getPanda(Token token) {
		System.out.println("Get panda for token: "+token.getToken());
		TokenPandaPair pair = repository.findByToken(token.getToken());
		System.out.println(pair.toString());
		return new Panda(pair.getPanda());
	}

	/* (non-Javadoc)
	 * @see org.enjket.panda.whitevault.service.DatabaseService#deleteToken(org.enjket.panda.whitevault.models.Token)
	 */
	@Override
	public void deleteToken(Token token) {
		
	}
}
