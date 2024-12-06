package Projeto.CadastroUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Projeto.CadastroUsuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
