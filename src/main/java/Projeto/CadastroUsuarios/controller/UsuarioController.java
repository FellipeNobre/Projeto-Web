package Projeto.CadastroUsuarios.controller;

import Projeto.CadastroUsuarios.model.Usuario;
import Projeto.CadastroUsuarios.repository.UsuarioRepository;
import Projeto.CadastroUsuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository; // Corrigido: Injetando o repositório corretamente

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@Validated @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Verifica se o usuário existe no banco
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id); // Corrigido

        if (usuarioExistente.isPresent()) {
            // Atualiza os dados do usuário existente
            Usuario atualizado = usuarioExistente.get();
            atualizado.setNome(usuario.getNome());
            atualizado.setEmail(usuario.getEmail());
            atualizado.setSenha(usuario.getSenha());
            atualizado.setPermissao(usuario.getPermissao());

            // Salva as alterações
            usuarioRepository.save(atualizado); // Corrigido
            return ResponseEntity.ok(atualizado);
        }

        // Retorna erro 404 se o usuário não for encontrado
        return ResponseEntity.notFound().build();
    }
}
