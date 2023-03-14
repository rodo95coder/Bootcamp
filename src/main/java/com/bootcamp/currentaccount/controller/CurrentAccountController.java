package com.bootcamp.currentaccount.controller;


import com.bootcamp.currentaccount.exceptions.GeneralException;
import com.bootcamp.currentaccount.model.CurrentAccount;
import com.bootcamp.currentaccount.model.dto.GlobalResponse;
import com.bootcamp.currentaccount.service.CurrentAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1")
@Slf4j
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService currentAccountService;

    @GetMapping("/findAll")
    public List<CurrentAccount> findAll() {
        log.info("All current accounts were consulted");

        return currentAccountService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<CurrentAccount> findById(@PathVariable("id") String id) {
        log.info("Current account consulted by id " + id);
        return currentAccountService.findById(id);

    }

    @PostMapping("/save")
    public ResponseEntity<GlobalResponse> save(@RequestBody CurrentAccount currentAccount) {
        log.info("A current account was created");
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(GlobalResponse.builder()
                            .data(currentAccountService.save(currentAccount)
                                    .get()).message("Registrado con exito")
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GlobalResponse.builder()
                            .data(GeneralException.builder()
                                    .message(e.getMessage())
                                    .build())
                            .build());
        }
    }

}
