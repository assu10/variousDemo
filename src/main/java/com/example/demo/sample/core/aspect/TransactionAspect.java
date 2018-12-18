package com.example.demo.sample.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class TransactionAspect {

    @Autowired
    PlatformTransactionManager txManager;

    Map<String, TransactionStatus> storeOfTransaction; // 트랜잭션캐싱

    public TransactionAspect() {
        storeOfTransaction = new HashMap<String, TransactionStatus>();
    }

    @Before("execution(* com.example.demo.sample.service.*Service.*(..))")
    public void start(JoinPoint jp) {
        storeOfTransaction.put(jp.toString(), createTransaction(jp.toString()));
    }

    @AfterReturning("execution(* com.example.demo.sample.service.*Service.*(..))")
    public void success(JoinPoint jp) {
        txManager.commit(storeOfTransaction.get(jp.toString()));
        storeOfTransaction.remove(jp.toString());
    }

    @AfterThrowing("execution(* com.example.demo.sample.service.*Service.*(..))")
    public void error(JoinPoint jp) {
        txManager.rollback(storeOfTransaction.get(jp.toString()));
        storeOfTransaction.remove(jp.toString());
    }

    public TransactionStatus createTransaction(String name) {
        /* Transaction 선언 */
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName(name);
        def.setReadOnly(false);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /* Transaction 상태 */
        return txManager.getTransaction(def);
    }
}
