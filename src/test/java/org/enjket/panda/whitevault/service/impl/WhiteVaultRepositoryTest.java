package org.enjket.panda.whitevault.service.impl;

import static org.junit.Assert.*;

import org.enjket.panda.whitevault.models.TokenPandaPair;
import org.enjket.panda.whitevault.service.BlackVaultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.enjket.panda.whitevault.TestConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class WhiteVaultRepositoryTest {

	@MockBean
	private BlackVaultService remoteService;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private WhiteVaultRepository repository;


	
	@Test
	public void testStoreAndRetrieve() {
		this.entityManager.persist(new TokenPandaPair(TOKEN, PANDA));
		TokenPandaPair pair = this.repository.findByToken(TOKEN_STR);
		assertEquals(pair.getToken(),TOKEN);
		assertEquals(pair.getPanda(),PANDA);
	}

}
