package Projeto.CadastroUsuarios.service;

import Projeto.CadastroUsuarios.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto.CadastroUsuarios.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

   public Usuario atualizarUsuario(Long id, Usuario usuario) {
    Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

    if (usuarioExistente == null) {
        return null; // Usuário não encontrado
    }

    usuarioExistente.setNome(usuario.getNome());
    usuarioExistente.setEmail(usuario.getEmail());
    usuarioExistente.setSenha(usuario.getSenha());
    usuarioExistente.setPermissao(usuario.getPermissao());

    return usuarioRepository.save(usuarioExistente);
}

}