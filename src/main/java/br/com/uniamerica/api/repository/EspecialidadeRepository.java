package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

/**
 * @author Eduardo Mendes
 *
 * @since 1.0.0, 05/04/2022
 * @version 1.0.0
 */
@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    @Modifying
    @Query("UPDATE Especialidade especialidade SET especialidade.ativo = false WHERE especialidade.id = :idEspecialidade")
    public void desativar(@Param("idEspecialidade") Long idEspecialidade);
}
