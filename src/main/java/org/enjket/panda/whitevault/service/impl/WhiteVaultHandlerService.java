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
import org.enjket.panda.whitevault.models.Pan;
import org.enjket.panda.whitevault.models.Panda;
import org.enjket.panda.whitevault.models.Token;
import org.enjket.panda.whitevault.service.BlackVaultService;
import org.enjket.panda.whitevault.service.DatabaseService;
import org.enjket.panda.whitevault.service.WhiteVaultService;
import org.enjket.panda.whitevault.utils.RandomNumberPumps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class WhiteVaultHandlerService is a facade for the rest of the
 * application to interact with the data store and the Black Vault in a unified
 * fashion.
 */
@Service("whiteVaultService")
public class WhiteVaultHandlerService implements WhiteVaultService {

	/** The database. */
	@Autowired
	DatabaseService database;

	/** The bv service. */
	@Autowired
	BlackVaultService bvService;
	
	private static final Logger logger = LoggerFactory.getLogger(WhiteVaultHandlerService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#createToken(org.
	 * enjket.panda.whitevault.models.Pan)
	 */
	@Override
	public Token createToken(Pan pan) {
		//TODO Do a luhnCheck to ensure the Pan received is not actually
		//a token. Exception? Return Pan as Token?
		
		//TODO This is currently treating the card as a Visa or MC. It should
		//check the length and if it is 15 the getFiveDigitStr should be called
		//and if 16 length the getSixDigitStr should be called.
		StringBuffer buffer = new StringBuffer();
		String BIN = pan.getBIN();
		String lastFour = pan.getLastFour();
		//A Luhn valid string is indicative of a valid credit card number (PAN).
		//All tokens created are not Luhn valid so that they are never confused
		//with actual credit card numbers.
		while (isNotValidToken(buffer)) {
			if(buffer.length()>0)
				logger.info("Token " + buffer.toString() + " is not a valid token.");
			buffer.delete(0, buffer.length());
			buffer.append(BIN);
			buffer.append(RandomNumberPumps.getSixDigitStr());
			buffer.append(lastFour);
		}
		return new Token(buffer.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.enjket.panda.whitevault.service.WhiteVaultService#createPad()
	 */
	@Override
	public Pad createPad() {
		return new Pad(RandomNumberPumps.getPadStr());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#createPanda(org.
	 * enjket.panda.whitevault.models.Pad,
	 * org.enjket.panda.whitevault.models.Pan)
	 */
	@Override
	public Panda createPanda(Pad pad, Pan pan) {
		long panda = pan.toLong() + pad.toLong();
		return new Panda(String.valueOf(panda));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#restorePan(org.
	 * enjket.panda.whitevault.models.Panda,
	 * org.enjket.panda.whitevault.models.Pad)
	 */
	@Override
	public Pan restorePan(Panda panda, Pad pad) {
		long pan = panda.toLong() - pad.toLong();
		return new Pan(String.valueOf(pan));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#store(org.enjket.
	 * panda.whitevault.models.Token, org.enjket.panda.whitevault.models.Panda)
	 */
	@Override
	public void store(Token token, Panda panda) {
		this.database.store(token, panda);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#getPanda(org.enjket
	 * .panda.whitevault.models.Token)
	 */
	@Override
	public Panda getPanda(Token token) {
		return this.database.getPanda(token);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.enjket.panda.whitevault.service.WhiteVaultService#deleteToken(org.
	 * enjket.panda.whitevault.models.Token)
	 */
	@Override
	public void deleteToken(Token token) {
		this.database.deleteToken(token);
	}
	
	/**
	 * Checks if is valid token.
	 *
	 * @param tokenStr the token str
	 * @return the boolean
	 */
	private Boolean isNotValidToken(StringBuffer tokenStr) {
		//Null or empty are not valid tokens.
		if (tokenStr == null || tokenStr.length()==0)
			return Boolean.TRUE;
		//If not null or empty, then check to ensure
		//the token. If it is Luhn valid it indiates that
		//the number could be mistaken for a valid PAN.
		return luhnCheck(tokenStr);
	}

	/**
	 * Luhn verify logic to check if this is a valid PAN
	 *
	 * @param tokenStr
	 *            the token str
	 * @return the boolean
	 */
	private Boolean luhnCheck(StringBuffer tokenStr) {

		int sum = 0;
		int value;
		int idx = tokenStr.length(); // Start from the end of string
		boolean alt = false;

		while (idx-- > 0) {
			// Get value. Throws error if it isn't a digit
			value = Integer.parseInt(tokenStr.substring(idx, idx + 1));
			if (alt) {
				value *= 2;
				if (value > 9)
					value -= 9;
			}
			sum += value;
			alt = !alt; // Toggle alt-flag
		}
		return (sum % 10) == 0;
	}

}
