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

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class WhiteVaultServiceTest {

	@MockBean
	private BlackVaultService remoteService;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private WhiteVaultRepository repository;

	private static final String TOKEN = "4929695988240755";
	private static final String  PANDA = "14929694241140755";
	@Test
	public void testStoreAndRetrieve() {
		this.entityManager.persist(new TokenPandaPair(TOKEN, PANDA));
		TokenPandaPair pair = this.repository.findByToken(TOKEN);
		assertEquals(pair.getToken(),TOKEN);
		assertEquals(pair.getPanda(),PANDA);
	}

}
