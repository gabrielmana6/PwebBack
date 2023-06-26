package br.edu.ifpb.gugawag.api.repositories;

import br.edu.ifpb.gugawag.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public List<Usuario> findByIdadeAndCpf(Integer idade, String cpf);

    @Query("SELECT u FROM Usuario u where u.idade>=60")
    public List<Usuario> getUsuariosIdosos();
}
