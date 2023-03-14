package com.bootcamp.currentaccount.service;


import com.bootcamp.currentaccount.model.CurrentAccount;

import java.util.List;
import java.util.Optional;

public interface CurrentAccountService {

  List<CurrentAccount> findAll();

  Optional<CurrentAccount> findById(String id);

  Optional<CurrentAccount> save(CurrentAccount currentAccount);

}
