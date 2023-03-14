package com.bootcamp.currentaccount.service.impl;


import com.bootcamp.currentaccount.model.CurrentAccount;
import com.bootcamp.currentaccount.repository.CurrentAccountRepository;
import com.bootcamp.currentaccount.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

    @Autowired
    CurrentAccountRepository currentAccountRepository;


    @Override
    public List<CurrentAccount> findAll() {
        return currentAccountRepository.findAll();
    }

    @Override
    public Optional<CurrentAccount> findById(String id) {
        return currentAccountRepository.findById(id);
    }

    @Override
    public Optional<CurrentAccount> save(CurrentAccount currentAccount) {
        //Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.
        //Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo, pero sí múltiples cuentas corrientes.

        CurrentAccount currentAccount1 = currentAccountRepository.findCurrentAccountByAccountNumberAndCustomerType(currentAccount.getAccountNumber(), currentAccount.getCustomerType());

        if (!ObjectUtils.isEmpty(currentAccount1) && currentAccount1.getCustomerType().equals("PERSONAL")) {
            throw new RuntimeException("No se puede realizar el registro, ya existe una cuenta corriente para el cliente personal!");
        } else if (currentAccount.getCustomerType().equals("EMPRESARIAL") && currentAccount.getAccountType().equals("SA") || currentAccount.getAccountType().equals("FTA")) {
            throw new RuntimeException("Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo!");
        } else {
            return Optional.of(this.currentAccountRepository.save(currentAccount));
        }
    }

}
