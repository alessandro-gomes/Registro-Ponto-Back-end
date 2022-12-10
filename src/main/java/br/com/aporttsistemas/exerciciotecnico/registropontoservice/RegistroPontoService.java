package br.com.aporttsistemas.exerciciotecnico.registropontoservice;

import br.com.aporttsistemas.exerciciotecnico.model.RegistroPonto;
import br.com.aporttsistemas.exerciciotecnico.repository.RegistroPontoRepository;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegistroPontoService {

    @Autowired
    RegistroPontoRepository registroPontoRepository;

    public RegistroPonto save(String latitude, String longitude) {
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setDataEHora(LocalDateTime.now(ZoneId.of("UTC-3")));
        registroPonto.setIpMaquina(getIp());
        registroPonto.setLatitude(latitude);
        registroPonto.setLongitude(longitude);
        registroPonto.setCaminhoImagemSalva("Não informado.");
        return registroPontoRepository.saveAndFlush(registroPonto);
    }

    public Optional<RegistroPonto> getOne(long id) {
        return registroPontoRepository.findById(id);
    }

    public List<RegistroPonto> getAll() {
        return registroPontoRepository.findAll();
    }

    public Optional<RegistroPonto> findById(long id) {
        return registroPontoRepository.findById(id);
    }

    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Problema ao pegar IP.";
        }
    }
    
}
