package projeto.service;

import java.math.BigDecimal;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.model.Conta;
import projeto.model.Usuario;
import projeto.repositories.ContaRepository;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> getContas() {
        return this.contaRepository.findAll();
    }

    public Conta getContaPorId(Long idConta) {
        return this.contaRepository.findById(idConta).orElse(null);
    }

    public Conta findByCpf(String cpf){
        return this.contaRepository.findByCpf(cpf);
    }

    @Transactional
    public Conta inserir(Conta conta) {
        List<String> ListaCpf = this.contaRepository.getAllCpf();

        for (String cpf : ListaCpf ) {
            if(conta.getCpf().equals(cpf)) {
                throw new RuntimeException("CPF Existente");
            }
        }
        Conta contaInserida = this.contaRepository.save(conta);
        return contaInserida;
    }

    @Transactional
    public Conta atualizar(Conta conta) {
        System.out.println("conta-------------------------------------------------------------------------------------------------");
        List<String> ListaCpf = this.contaRepository.getAllCpf();
        boolean contemCPF = ListaCpf.contains(conta.getCpf());

        if(contemCPF){
            System.out.println("dentro do IFconta -------------------------------------------------------------------------------------------------");
            final Long contaId = this.contaRepository.findIdByCpf(conta.getCpf());
            if(!conta.getId().equals(contaId)) {
                throw new RuntimeException("CPF Existente conta");
            }
        }
        Conta contaInserida = this.contaRepository.save(conta);
        return contaInserida;
    }

    @Transactional
    public Conta depositar(Conta conta){

        if(conta.getSaldo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposite uma quantia maior que zero");
        }
        Conta contaInserida = this.contaRepository.save(conta);
        return contaInserida;
    }

    public void apagar(Long id) {
        this.contaRepository.deleteById(id);
    }
}
