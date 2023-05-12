package com.br.WhatsCodeClientMicroservice.service;

import com.br.WhatsCodeClientMicroservice.models.AuditingLog;
import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.repository.AuditingLogRepository;
import com.br.WhatsCodeClientMicroservice.repository.ClientRepository;
import com.br.WhatsCodeClientMicroservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private AuditingLogRepository auditingRepository;

    public Client getByCpf(String cpf){
        List<Client> clients = repository.findAll();
        Client target = null;
        for(Client client : clients){
            if(client.getCpf().equals(cpf)){
                target = client;
                break;
            }
        }
        return target;
    }

    public Boolean checkExistingCpf(Client client){
        if(getByCpf(client.getCpf()) == null){
            return true;
        }else{
            return false;
        }
    }

    public void deleteById(Long id, String employeeName){
        Optional<Client> clientOptional = repository.findById(id);

        Client client = clientOptional.get();

        AuditingLog auditing = new AuditingLog();

        auditing.setDeletedBy(employeeName);
        auditing.setDeletedAt(new Date());
        auditing.setEntityType("Client");
        auditing.setEntityId(id);

        auditingRepository.save(auditing);

        repository.deleteById(id);
    }

}
