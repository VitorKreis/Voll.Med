create table procedimentos(
    id INT AUTO_INCREMENT,
    medico_id int not null,
    paciente_id int not null,
    data datetime not null,
    motivo_cancelamento varchar(100),

    PRIMARY KEY (id),
    constraint fk_procedimentos_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_procedimentos_paciente_id foreign key(paciente_id) references pacientes(id)
)