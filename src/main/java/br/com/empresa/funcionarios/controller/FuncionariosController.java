package br.com.empresa.funcionarios.controller;

import br.com.empresa.funcionarios.controller.dto.FuncionarioDto;
import br.com.empresa.funcionarios.controller.request.FuncionarioRequest;
import br.com.empresa.funcionarios.model.Cargo;
import br.com.empresa.funcionarios.model.Funcionario;
import br.com.empresa.funcionarios.repository.CargoRepository;
import br.com.empresa.funcionarios.repository.DepartamentoRepository;
import br.com.empresa.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@Transactional
public class FuncionariosController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<FuncionarioDto> listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return FuncionarioDto.toDto(funcionarios);
    }

    @GetMapping("/departamentos/{idDepartamento}")
    public List<FuncionarioDto> listarPorDepartamento(@PathVariable Long idDepartamento) {
        List<Funcionario> funcionarios = funcionarioRepository.findByDepartamentoId(idDepartamento);
        return FuncionarioDto.toDto(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDto> detalhar(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isPresent()) {
            return ResponseEntity.ok(new FuncionarioDto(funcionario.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<FuncionarioDto> cadastrar(@RequestBody @Valid FuncionarioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Funcionario funcionario;
        try {
            funcionario = request.toEntity(cargoRepository, departamentoRepository);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        funcionarioRepository.save(funcionario);
        URI uri = uriComponentsBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioDto(funcionario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioRequest request) {
        Optional<Funcionario> optional = funcionarioRepository.findById(id);
        if (optional.isPresent()) {
            Funcionario funcionario;
            try {
                funcionario = request.atualizar(id, funcionarioRepository, cargoRepository, departamentoRepository);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(new FuncionarioDto(funcionario));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/cargo/chefe-departamento")
    public ResponseEntity<FuncionarioDto> atualizarChefeDeDepartamento(@PathVariable Long id) {
        Cargo cargo = cargoRepository.findByNome("Chefe de Departamento");
        Funcionario funcionario = funcionarioRepository.getOne(id);

        if(cargo == null || funcionario == null ) {
            return ResponseEntity.notFound().build();
        }

        funcionario.setCargo(cargo);
        return ResponseEntity.ok(new FuncionarioDto(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(funcionario.isPresent()) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
