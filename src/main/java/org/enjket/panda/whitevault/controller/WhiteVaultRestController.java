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
package org.enjket.panda.whitevault.controller;

import org.enjket.panda.whitevault.models.Pad;
import org.enjket.panda.whitevault.models.Pan;
import org.enjket.panda.whitevault.models.Panda;
import org.enjket.panda.whitevault.models.Token;
import org.enjket.panda.whitevault.service.BlackVaultService;
import org.enjket.panda.whitevault.service.WhiteVaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



/**
 * The Class WhiteVaultRestController.
 */
@RestController
public class WhiteVaultRestController {

	/** The white vault service. */
	@Autowired
	WhiteVaultService whiteVaultService;  //Service which will do all data retrieval/manipulation work

	/** The black vault service. */
	@Autowired
	BlackVaultService blackVaultService;
	//-------------------Retrieve a single PAN--------------------------------------------------------
	
	/**
	 * Gets the pan.
	 *
	 * @param id the id
	 * @return the pan
	 */
	@RequestMapping(value = "/token/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pan> getPan(@PathVariable("token") long id) {
		System.out.println("Fetching token with id " + id);
		Token token = new Token(String.valueOf(id));
		System.out.println("The token value: " + token.getToken());
		Panda panda = whiteVaultService.getPanda(token);
		//TODO Error handling and reporting
		/*if (panda == null) {
			return new ResponseEntity<Pan>(HttpStatus.NOT_FOUND);
		}*/
		System.out.println("The panda value: " + panda.getPanda());
		Pad pad = blackVaultService.getPad(token);
		System.out.println("Retreived pad: "+ pad);
		Pan pan = whiteVaultService.restorePan(panda, pad);
		
		return new ResponseEntity<Pan>(pan, HttpStatus.OK);
	}

	
	
	//-------------------Create a Token and store it and the PAN --------------------------------------------------
	
	/**
	 * Creates the token.
	 *
	 * @param panNo the pan no
	 * @param ucBuilder the uc builder
	 * @return the response entity
	 */
	@RequestMapping(value = "/pan/{pan}", method = RequestMethod.POST)
	public ResponseEntity<Token> createToken(@PathVariable("pan") long panNo, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating token ");
		Pan pan = new Pan(panNo);
		Token token = whiteVaultService.createToken(pan);
		Pad pad = whiteVaultService.createPad();
		Panda panda = whiteVaultService.createPanda(pad, pan);
		whiteVaultService.store(token, panda);
		blackVaultService.store(token, pad);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/pan/{token}").buildAndExpand(token.getToken()).toUri());
		return new ResponseEntity<Token>(token, HttpStatus.CREATED);
	
	}

	
	

	//------------------- Delete a User --------------------------------------------------------
	
	/**
	 * Delete token.
	 *
	 * @param id the id
	 */
	@RequestMapping(value = "/token/{token}", method = RequestMethod.DELETE)
	public void deleteToken(@PathVariable("token") long id) {
		System.out.println("Fetching & Deleting Token with id " + id);

		whiteVaultService.deleteToken(new Token(String.valueOf(id)));
		//TODO Send the delete to the Black Vault as well.
	}



}
