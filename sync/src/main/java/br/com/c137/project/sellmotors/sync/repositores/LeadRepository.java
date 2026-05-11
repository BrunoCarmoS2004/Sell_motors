package br.com.c137.project.sellmotors.sync.repositores;

import br.com.c137.project.sellmotors.sync.dtos.LeadDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LeadRepository extends MongoRepository<LeadDto, UUID> {
}
