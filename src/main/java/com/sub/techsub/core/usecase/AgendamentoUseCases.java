package com.sub.techsub.core.usecase;

import com.sub.techsub.core.domain.model.Estabelecimento;
import com.sub.techsub.core.domain.model.Profissional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;


import com.sub.techsub.core.usecase.exception.AgendamentoException;
import com.sub.techsub.core.usecase.exception.ProfissionalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class AgendamentoUseCases {

    public LocalDate formatarDataParaAgendamento(LocalDateTime dataAgendamento) {
        log.info("Formatando Data para Agendamento ...");
        LocalDate localDate = dataAgendamento.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = localDate.format(formatter);
        return LocalDate.parse(dataFormatada);

    }

    public LocalTime formatarHoraParaAgendamento(LocalDateTime dataAgendamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String dataFormatada = dataAgendamento.format(formatter);
        return LocalTime.parse(dataFormatada);
    }

    public void validarDataEHoraEstabelecimento(Optional<Estabelecimento> estabelecimento, LocalDateTime dataAgendamento) {
        if (estabelecimento.isPresent()) {
            log.info("Validando Data e hora do Estabelecimento...");
            formatarEValidaDataHoraAgendamento(estabelecimento.get().getHorarioFuncionamento(), dataAgendamento);
        } else throw new AgendamentoException("Estabelecimento não encontrado");
    }

    public void validarDataEHoraProfissional(Optional<Profissional> profissional, LocalDateTime dataAgendamento) {
        if (profissional.isPresent()) {
            log.info("Validando Data e hora do Profissional...");
            formatarEValidaDataHoraAgendamento(profissional.get().getHorariosDisponiveis(), dataAgendamento);
        } else throw new ProfissionalException("Profissional não encontrado");
    }

    private static final Map<String, DayOfWeek> diasDaSemanaMap = new HashMap<>();

    static {
        diasDaSemanaMap.put("Segunda", DayOfWeek.MONDAY);
        diasDaSemanaMap.put("Terça", DayOfWeek.TUESDAY);
        diasDaSemanaMap.put("Quarta", DayOfWeek.WEDNESDAY);
        diasDaSemanaMap.put("Quinta", DayOfWeek.THURSDAY);
        diasDaSemanaMap.put("Sexta", DayOfWeek.FRIDAY);
        diasDaSemanaMap.put("Sábado", DayOfWeek.SATURDAY);
        diasDaSemanaMap.put("Domingo", DayOfWeek.SUNDAY);
    }

    public static void formatarEValidaDataHoraAgendamento(String dataFuncionamento, LocalDateTime dataAgendamento) {

        String[] diasDaSemana = dataFuncionamento.split(" ");

        if (diasDaSemana.length != 3) {
            throw new IllegalArgumentException("Formato de dia inválido do estabelecimento ou profissional. " +
                    "Use 'Dia Dia Horário' exemplo: Segunda Sexta 9:00-19:00");
        }

        String[] horas = diasDaSemana[2].split("-");

        if (horas.length != 2) {
            throw new IllegalArgumentException("Formato de horário inválido do estabelecimento ou profissional. " +
                    "Use 'HH:MM-HH:MM' as horas de entrada e saida com o -");
        }

        DateTimeFormatter formaterHora = DateTimeFormatter.ofPattern("H:mm");

        LocalTime horaEntrada = LocalTime.parse(horas[0], formaterHora);
        LocalTime horaSaida = LocalTime.parse(horas[1] , formaterHora);

        DayOfWeek diaInicio = diasDaSemanaMap.get(diasDaSemana[0]);
        DayOfWeek diaFim = diasDaSemanaMap.get(diasDaSemana[1]);

        DayOfWeek diaSemanaAgendamento = dataAgendamento.getDayOfWeek();

        boolean validaDiaSemana =
                validarRangeDosDiasDiponiveis(diaInicio.getValue(),diaFim.getValue(),diaSemanaAgendamento.getValue());

        if(validaDiaSemana){
            if(validarRangeHoraDisponivel(dataAgendamento, horaEntrada, horaSaida)){
            } else throw new RuntimeException("O horario do agendamento nao esta disponivel");
        } else throw new RuntimeException("A data do agendamento nao esta dentro dos dias disponiveis da semana");
    }

    public static boolean validarRangeDosDiasDiponiveis(int diaInicio, int diaFim, int diaAgendado) {
        int diaLimiteMinimo = Math.min(diaInicio, diaFim);
        int diaLimiteMaximo = Math.max(diaInicio, diaFim);
        return diaAgendado >= diaLimiteMinimo && diaAgendado <= diaLimiteMaximo;
    }

    public static boolean validarRangeHoraDisponivel(LocalDateTime horaAgendado, LocalTime horaEntrada, LocalTime horaSaida) {
        LocalTime validarHora = horaAgendado.toLocalTime();
        if (horaEntrada.isAfter(horaSaida)) {
            return !validarHora.isBefore(horaEntrada) || !validarHora.isAfter(horaSaida);
        } else {
            return !validarHora.isBefore(horaEntrada) && !validarHora.isAfter(horaSaida);
        }
    }

}
