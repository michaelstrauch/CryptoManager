package com.engeto.projects.CryptoManager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    private final CryptoService cryptoService = new CryptoService();

    @PostMapping()
    public String addCrypto(@RequestBody Crypto crypto) {
        cryptoService.addCryptoCurrency(crypto);
        return "Cryptocurrency added: " + crypto.getName();
    }

    @GetMapping()
    public List<Crypto> getCryptos(@RequestParam(required = false) String sort) {
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
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong sorting method");
            }
        }
        return cryptoService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public Crypto getCryptoById(@PathVariable int id) {
        Crypto foundCrypto = cryptoService.getCryptoByID(id);
        if (foundCrypto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Crypto not found");
        }
        return foundCrypto;
    }

    @PutMapping("/{id}")
    public String updateCrypto(@RequestBody Crypto updatedCrypto, @PathVariable int id) {
        Crypto replacedCrypto = cryptoService.getCryptoByID(id);
        if (replacedCrypto == null) {
            return "Crypto not found";
        }
        replacedCrypto.setName(updatedCrypto.getName());
        replacedCrypto.setPrice(updatedCrypto.getPrice());
        replacedCrypto.setQuantity(updatedCrypto.getQuantity());
        replacedCrypto.setSymbol(updatedCrypto.getSymbol());
        return "Crypto " + id + " updated.";
    }

    @GetMapping("/portfolio-value")
    public String totalPortfolioValue() {
        double totalValue = 0;
        for (Crypto crypto : cryptoService.currenciesList) {
            totalValue += crypto.getPrice() * crypto.getQuantity();
        }
        return "Total portfolio value is: " + totalValue;
    }
}