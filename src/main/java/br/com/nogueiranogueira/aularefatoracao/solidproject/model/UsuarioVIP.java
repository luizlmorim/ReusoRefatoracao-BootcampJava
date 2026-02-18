package br.com.nogueiranogueira.aularefatoracao.solidproject.model;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("VIP")
public class UsuarioVIP extends Usuario{

    private boolean temCartaoFidelidade;

    public boolean isTemCartaoFidelidade() {
        return temCartaoFidelidade;
    }

    public void setTemCartaoFidelidade(boolean temCartaoFidelidade) {
        this.temCartaoFidelidade = temCartaoFidelidade;
    }

    @Override
    public int getDesconto() {
        if (!temCartaoFidelidade){
            throw new IllegalStateException("Usuário VIP deve ter cartão fidelidade para receber desconto");
        }
        return 10; // desconto de 10% para usuários VIP
    }
}
