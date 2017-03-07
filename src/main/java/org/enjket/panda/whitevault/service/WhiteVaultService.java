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
package org.enjket.panda.whitevault.service;

import org.enjket.panda.whitevault.models.Pad;
import org.enjket.panda.whitevault.models.Pan;
import org.enjket.panda.whitevault.models.Panda;
import org.enjket.panda.whitevault.models.Token;

/**
 * The Interface WhiteVaultService is the interface used by the handler service. When the autowire is invoked it
 * picks up the handler but uses it via the interface. 
 */
public interface WhiteVaultService {
	
	/**
	 * Store the token and panda in the vault.  They are passed in separately so that the
	 * handler can package the data in any way required by the database. The method signature
	 * also matches what the rest of the application uses for its data.
	 *
	 * @param token the token
	 * @param panda the panda
	 */
	public void store(Token token, Panda panda);
	
	/**
	 * Gets the panda from the database by the Token key value.
	 *
	 * @param token the token
	 * @return the panda
	 */
	public Panda getPanda(Token token);
	
	/**
	 * Delete token will delete the token and panda from the database.
	 *
	 * @param token the token
	 */
	public void deleteToken(Token token);
	
	/**
	 * Creates the token. Given a credit card number (PAN) the createToken
	 * method creates a synthetic token that is not Luhn valid. That ensures
	 * the token will not be mistaken for a real PAN.
	 *
	 * @param pan the pan
	 * @return the token
	 */
	public Token createToken(Pan pan);

	/**
	 * Restore PAN given the Panda pulled from the local database and from
	 * the Pad returned from the Black Vault.
	 *
	 * @param panda the panda
	 * @param pad the pad
	 * @return the pan
	 */
	public Pan restorePan(Panda panda, Pad pad);
	
	/**
	 * Creates the pad which is a 16 digit number to be added to the
	 * PAN in order to create a padded PAN. Each pad is unique to the
	 * Token/Pan so that there is not a common algorithm that can crack
	 * the values.
	 *
	 * @return the pad
	 */
	public Pad createPad();
	
	/**
	 * Creates the panda by adding the 16-digit pad value to the 
	 * 15-digit or 16-digit PAN. 
	 *
	 * @param pad the pad
	 * @param pan the pan
	 * @return the panda
	 */
	public Panda createPanda(Pad pad, Pan pan);

}
