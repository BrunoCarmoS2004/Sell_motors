package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.dtos.puts;

public record LeadPutDto(
        String fullName,

        String email,

        String phone,

        String document
) {

}
