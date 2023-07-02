package projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.model.Conta;
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

    public Conta inserirOuAtualizar(Conta conta) {
        Conta contaInserida = this.contaRepository.save(conta);
        return contaInserida;
    }

    public void apagar(Long id) {
        this.contaRepository.deleteById(id);
    }
}
