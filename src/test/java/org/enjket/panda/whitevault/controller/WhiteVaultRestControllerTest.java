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

import static org.junit.Assert.*;

import org.enjket.panda.whitevault.models.Pad;
import org.enjket.panda.whitevault.models.Pan;
import org.enjket.panda.whitevault.models.Panda;
import org.enjket.panda.whitevault.models.Token;
import org.enjket.panda.whitevault.service.BlackVaultService;
import org.enjket.panda.whitevault.service.WhiteVaultService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Brad on 3/8/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WhiteVaultRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private BlackVaultService blackVaultService;

	@MockBean
	private WhiteVaultService whiteVaultService;

	@Before
	public void setup() {
		//given(this.whiteVaultService.getPanda(token)).willReturn(panda);
	}

	/**
	 * Test get PAN.
	 */
	@Test
	public void testGetPAN() {
		//this.restTemplate.getForEntity("/{username}/vehicle", String.class, "Phil");
		assertTrue(true);
	}

}