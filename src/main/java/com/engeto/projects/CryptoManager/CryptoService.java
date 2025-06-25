package com.engeto.projects.CryptoManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CryptoService {

    List<Crypto> currenciesList = new ArrayList<>();


    public void addCryptoCurrency(Crypto crypto) {
        currenciesList.add(crypto);
    }

    public Crypto getCryptoByID(int id) {
        for (Crypto crypto : currenciesList) {
            if (crypto.getId() == id) {
                return crypto;
            }
        }
        return null;
    }

    public List<Crypto> getAllCurrencies() {
        return new ArrayList<>(currenciesList);
    }

    public void printAllCurrencies() {
        for (Crypto crypto : currenciesList) {
            System.out.println(crypto);
        }
    }

    public void sortByName() {
        currenciesList.sort(Comparator.comparing(Crypto::getName));
    }

    public void sortByPrice() {
        currenciesList.sort(Comparator.comparing(Crypto::getPrice));
    }

    public void sortByQuantity() {
        currenciesList.sort(Comparator.comparing(Crypto::getQuantity));
    }

}
