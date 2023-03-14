package com.bootcamp.currentaccount.repository;

import com.bootcamp.currentaccount.model.CurrentAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrentAccountRepository
    extends MongoRepository<CurrentAccount,String> {

    CurrentAccount findCurrentAccountByAccountNumberAndCustomerType(String accountNumber, String customerType);

}
