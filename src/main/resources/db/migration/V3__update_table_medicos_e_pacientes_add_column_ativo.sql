
alter table medicos add ativo BOOLEAN;
update medicos set ativo = true;

alter table pacientes add ativo BOOLEAN;
update pacientes set ativo = true;