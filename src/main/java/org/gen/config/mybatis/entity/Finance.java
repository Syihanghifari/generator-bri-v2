package org.gen.config.mybatis.entity;

public class Finance {
    private String account;
    private String accountName;
    private String amount;
    private String bic;
    private String creditCardCVV;
    private String creditCardIssuer;
    private String creditCardNumber;
    private String currencyCode;
    private String currencyName;
    private String currencySymbol;
    private String ethereumAddress;
    private String iban;
    private String mask;
    private String pin;
    private String routingNumber;
    private String transactionDescription;
    private String transactionType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(String creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public String getCreditCardIssuer() {
        return creditCardIssuer;
    }

    public void setCreditCardIssuer(String creditCardIssuer) {
        this.creditCardIssuer = creditCardIssuer;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getEthereumAddress() {
        return ethereumAddress;
    }

    public void setEthereumAddress(String ethereumAddress) {
        this.ethereumAddress = ethereumAddress;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "account='" + account + '\'' +
                ", accountName='" + accountName + '\'' +
                ", amount='" + amount + '\'' +
                ", bic='" + bic + '\'' +
                ", creditCardCVV='" + creditCardCVV + '\'' +
                ", creditCardIssuer='" + creditCardIssuer + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", currencySymbol='" + currencySymbol + '\'' +
                ", ethereumAddress='" + ethereumAddress + '\'' +
                ", iban='" + iban + '\'' +
                ", mask='" + mask + '\'' +
                ", pin='" + pin + '\'' +
                ", routingNumber='" + routingNumber + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
