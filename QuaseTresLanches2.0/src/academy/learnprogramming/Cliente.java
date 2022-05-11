package academy.learnprogramming;

public class Cliente {
    private String nomeDoCliente;
    private String enderecoDoCliente;
    private String numeroDeTelefoneDoCliente;
    private String cpf;
    private double taxa;

    public Cliente(String nomeDoCliente, String enderecoDoCliente, String numeroDeTelefoneDoCliente,
                   String cpf) {
        this.nomeDoCliente = nomeDoCliente;
        this.enderecoDoCliente = enderecoDoCliente;
        this.numeroDeTelefoneDoCliente = numeroDeTelefoneDoCliente;
        this.cpf = cpf;
        this.taxa = 5;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public String getEnderecoDoCliente() {
        return enderecoDoCliente;
    }

    public String getNumeroDeTelefoneDoCliente() {
        return numeroDeTelefoneDoCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setEnderecoDoCliente(String enderecoDoCliente) {
        this.enderecoDoCliente = enderecoDoCliente;
    }

    public void setNumeroDeTelefoneDoCliente(String numeroDeTelefoneDoCliente) {
        this.numeroDeTelefoneDoCliente = numeroDeTelefoneDoCliente;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
