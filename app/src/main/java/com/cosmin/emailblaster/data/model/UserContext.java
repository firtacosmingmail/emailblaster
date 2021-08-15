package com.cosmin.emailblaster.data.model;

import microsoft.exchange.webservices.data.core.ExchangeService;

public class UserContext {
    private LoggedInUser user;
    private ExchangeService exchangeService;

    public LoggedInUser getUser() {
        return user;
    }

    public void setUser(LoggedInUser user) {
        this.user = user;
    }

    public ExchangeService getExchangeService() {
        return exchangeService;
    }

    public void setExchangeService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    public void clear() {
        user = null;
        exchangeService = null;
    }

    public void saveContext(String email, String password, ExchangeService retrievedExchangeService) {
        setUser(new LoggedInUser(email, password));
        setExchangeService(retrievedExchangeService);
    }
}
