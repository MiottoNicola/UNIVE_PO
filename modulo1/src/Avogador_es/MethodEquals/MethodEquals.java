package Avogador_es.MethodEquals;

class CreditCard {
    private String owner;
    private double monthlyAllowance;
    private long cardNumber;
    private int verificationCode;

    public CreditCard( String owner, double monthlyAllowance, long cardNumber, int verificationCode ) {
        this.owner = owner;
        this.monthlyAllowance = monthlyAllowance;
        this.cardNumber = cardNumber;
        this.verificationCode = verificationCode;
    }

    public boolean equals( Object o ) {
        if (o == null || !(o instanceof CreditCard)) return false;
        if (o == this) return true;
        CreditCard c = ( CreditCard ) o;
        return this.monthlyAllowance == c.monthlyAllowance && this.cardNumber == c.cardNumber && this.verificationCode == c.verificationCode && this.owner.equals(c.owner);
    }

}
