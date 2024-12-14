package com.se300.ledger.controller;

import com.se300.ledger.model.Account;
import com.se300.ledger.service.LedgerAPI;
import com.se300.ledger.service.LedgerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "basicAuth")
public class LedgerRestController {

        @Autowired
        LedgerAPI ledgerService;

        @Operation(summary = "Create Account", tags = { "accounts" })
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = Account.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
        })
        @PostMapping("/accounts")
        public Account createAccount(@RequestBody Account account) throws LedgerException {

                return ledgerService.createAccount(account.getAddress(), account.getBalance());
        }

        @Operation(summary = "Retrieve Account By Address", tags = { "accounts" })
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = Account.class), mediaType = "application/json") }),
                        @ApiResponse(responseCode = "204", description = "No account found with the given address", content = {
                                        @Content(schema = @Schema()) }),
                        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
        })
        @GetMapping("/accounts/{address}")
        public Account getAccount(@PathVariable String address) throws LedgerException {
                ledgerService.getAccount(address);

                return ledgerService.getAccount(address);
        }
}