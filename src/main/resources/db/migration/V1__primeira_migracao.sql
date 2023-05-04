create table usuario(
	id bigint auto_increment primary key,
    nome varchar(150) not null,
    login varchar(50) not null,
    senha varchar(100) not null,
    perfil varchar(50) not null
);

create table nutricionista(
	id_usuario bigint primary key,
    nome varchar(150) not null,
    data_nascimento datetime not null,
    cpf varchar(11) not null, 
    foreign key(id_usuario) references usuario(id)
);

create table paciente(
	id bigint auto_increment primary key,
    nome varchar(150),
    data_nascimento datetime,
    sexo varchar(10),
    url_avatar varchar(50),
    profissao varchar(100),
    id_nutricionista bigint not null,
    foreign key(id_nutricionista) references nutricionista(id_usuario)
);

create table atendimento(
	id bigint auto_increment primary key,
    id_nutricionista bigint,
    data_atendimento datetime,
    anamnese varchar(200),
    foreign key(id_nutricionista) references nutricionista(id_usuario)
);

create table plano_alimentar(
	id_atendimento bigint auto_increment primary key,
    id_paciente bigint,
    descricao varchar(200),
    segunda boolean,
    terca boolean,
    quarta boolean,
    quita boolean,
    sexta boolean,
    sabado boolean,
    domingo boolean,
    foreign key(id_atendimento) references atendimento(id),
    foreign key(id_paciente) references paciente(id)
);

create table refeicao(
	id bigint auto_increment primary key,
	id_plano_alimentar bigint,
    horario time,
    descricao varchar(50),
    foreign key(id_plano_alimentar) references plano_alimentar(id_atendimento)
);

create table alimento(
	id bigint auto_increment primary key,
	descricao varchar(50)
);

create table medida(
	id bigint auto_increment primary key,
	descricao varchar(50)
);

create table refeicao_alimento(
	id bigint auto_increment primary key,
	id_refeicao bigint,
	id_alimento bigint,
	id_medida bigint,
    quantidade numeric,
    foreign key(id_refeicao) references refeicao(id),
    foreign key(id_alimento) references alimento(id),
    foreign key(id_medida) references medida(id)
);


insert into usuario(nome, login, senha, perfil) values('Edson', '05982191370', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');

insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Francisco Edson Gonçalves Junior', '1995-04-10', '05982191370', 1);

insert into paciente(nome, data_nascimento, sexo, profissao, id_nutricionista) values('Marcos Gomes da Silva', '1995-04-11', 'MASCULINO', 'Professor', 1);
insert into paciente(nome, data_nascimento, sexo, profissao, id_nutricionista) values('Maria Josefa de Alencar', '1986-12-07', 'FEMININO', 'Engenheira Química', 1);
insert into paciente(nome, data_nascimento, sexo, profissao, id_nutricionista) values('Mônica Barreto Aguiar', '1979-04-06', 'FEMININO', 'Médica', 1);
insert into paciente(nome, data_nascimento, sexo, profissao, id_nutricionista) values('Cássio Fernandes Sousa', '2001-04-10', 'MASCULINO', 'Analista de Sistemas', 1);