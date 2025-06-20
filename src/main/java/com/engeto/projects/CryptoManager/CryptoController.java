package com.engeto.projects.CryptoManager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    private CryptoService cryptoService = new CryptoService();

    @PostMapping("/add")
    public String addCrypto(@RequestBody Crypto crypto) {
        cryptoService.addCryptoCurrency(crypto);
        return "Cryptocurrency added: " + crypto.getName();
    }

    @GetMapping("/list")
    public List<Crypto> listAll() {
        return cryptoService.printAllCurrencies();
    }

    @GetMapping("/sort")
    public List<Crypto> sortList(@RequestParam(required = false) String sort) {
        if (sort != null) {
            switch (sort.toLowerCase()) {
                case "price":
                    cryptoService.sortByPrice();
                    break;
                case "name":
                    cryptoService.sortByName();
                    break;
                case "quantity":
                    cryptoService.sortByQuantity();
                    break;
            }
        }
        return cryptoService.printAllCurrencies();
    }

    @GetMapping("/{id}")
    public Crypto getCryptoId(@PathVariable int id) {
        if (cryptoService.getCryptoByID(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crypto not found");
        }
        return cryptoService.getCryptoByID(id);

    }
}