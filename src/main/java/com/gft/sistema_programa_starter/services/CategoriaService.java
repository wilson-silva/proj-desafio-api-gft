package com.gft.sistema_programa_starter.services;


import com.gft.sistema_programa_starter.entities.Categoria;
import com.gft.sistema_programa_starter.config.exception.EntityNotFoundException;
import com.gft.sistema_programa_starter.repositories.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    //--------------------------------------------------------------------
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //--------------------------------------------------------------------
    public Page<Categoria> listarTodosAsCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    //--------------------------------------------------------------------
    public Categoria buscarCategoria(Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    //--------------------------------------------------------------------
    public Categoria atualizarCategoria(Categoria categoria, Long id) {
        Categoria categoriaOriginal = this.buscarCategoria(id);
        categoria.setId(categoriaOriginal.getId());
        return categoriaRepository.save(categoria);
    }

    //--------------------------------------------------------------------
    public void excluirCategoria(Long id) {
        Categoria produtoOriginal = this.buscarCategoria(id);
        categoriaRepository.delete(produtoOriginal);
    }
}
