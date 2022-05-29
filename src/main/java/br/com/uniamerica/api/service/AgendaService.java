package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.StatusAgenda;
import br.com.uniamerica.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    private StatusAgenda statusAgenda;

    public Optional<Agenda> findById(Long id) {return this.agendaRepository.findById(id);}

    public Page<Agenda> listAll(Pageable pageable) {return this.agendaRepository.findAll(pageable);}

    @Transactional
    public void update(Long id, Agenda agenda) {
        if (id == agenda.getId()) {
            this.agendaRepository.save(agenda);
        }
        else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void insert(Agenda agenda) {
        this.validarFormulario(agenda);
        this.agendaRepository.save(agenda);
    }

    public void updateStatus(Long id, Agenda agenda){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dtf.format(LocalDateTime.now());
        if (id == agenda.getId()) {
            this.agendaRepository.updateStatus(agenda.getId(), dtf);
        }
        else {
            throw new RuntimeException();
        }
    }

    public void validarFormulario(Agenda agenda) {
        if (agenda.getData() == null || agenda.getPaciente().getId() == null || agenda.getMedico().getId() == null) {
            throw new RuntimeException("Data ou Paciente ou Medico sao invalidos");
        }
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            LocalDateTime horaAgenda = LocalDateTime.parse(dtf.format(agenda.getData()));
            LocalDateTime horaInicio = LocalDateTime.parse(sdf.format("08:00"));
            LocalDateTime horaFim = LocalDateTime.parse(sdf.format("17:00"));
            LocalDateTime horaEntradaAlmoco = LocalDateTime.parse(sdf.format("12:00"));
            LocalDateTime horaSaidaAlmoco = LocalDateTime.parse(sdf.format("14:00"));

            if (agenda.getSecretaria() == null) {
                agenda.setStatus(statusAgenda.pendente);
                agenda.setEncaixe(false);
            }
            else {
                agenda.setStatus(statusAgenda.aprovado);
            }

            List<Long> agendaExiste = agendamentoExiste(agenda, horaAgenda, horaInicio, horaFim, horaEntradaAlmoco,
                    horaSaidaAlmoco);
            if (agendaExiste.size() > 0) {
                throw new RuntimeException("Paciente ja possui agendamento ou Horario ja esta marcado");
            }
        }
    }

    @Transactional
    public List<Long> agendamentoExiste(Agenda agenda, LocalDateTime horaAgenda, LocalDateTime horaInicio,
                                        LocalDateTime horaFim, LocalDateTime horaEntradaAlmoco,
                                        LocalDateTime horaSaidaAlmoco) {
        Long idMedico = agenda.getMedico().getId();
        Long idPaciente = agenda.getPaciente().getId();

        List<Long> result = this.agendaRepository.agendamentoExiste(agenda, idMedico, idPaciente, horaAgenda,
                horaInicio, horaFim, horaEntradaAlmoco, horaSaidaAlmoco);
        return result;
    }
}
