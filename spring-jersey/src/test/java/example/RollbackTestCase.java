package example;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

public abstract class RollbackTestCase extends BasicSpringTestCase {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private DataSourceTransactionManager txManager;

    @Before
    public void txSetup() {
        TransactionStatus tx = txManager.getTransaction(new DefaultTransactionDefinition());
        tx.setRollbackOnly();
        log.debug("The transaction is set as rollback only.");
    }

    @After
    public void txRollback() {
        TransactionStatus tx = txManager.getTransaction(new DefaultTransactionDefinition());
        txManager.rollback(tx);
        log.debug("The transaction is rolled back.");
    }

}
