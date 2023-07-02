package projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {


}
