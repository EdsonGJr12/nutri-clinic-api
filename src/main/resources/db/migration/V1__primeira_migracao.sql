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
    profissao varchar(100)
);

create table paciente_historico(
	id bigint auto_increment primary key,
	id_paciente bigint,
    id_usuario bigint,
    data_ocorrencia datetime,
    ocorrencia varchar(50),
    foreign key(id_paciente) references paciente(id),
    foreign key(id_usuario) references usuario(id)
);

create table circunferencia(
	id bigint auto_increment primary key,
    braco_esquerdo_relaxado numeric,
    braco_direito_relaxado numeric,
    braco_esquerdo_contraido numeric,
    braco_direito_contraido numeric,
    antebraco_direito numeric,
    antebraco_esquerdo numeric,
    punho_esquerdo numeric,
    punho_direito numeric
);

create table composicao_corporal(
	id bigint auto_increment primary key,
    tipo varchar(50),
    protocolo varchar(50),
    biceps numeric,
    abdominal numeric,
    triceps numeric,
    suprailiaca numeric,
    axilar_media numeric,
    subscapular numeric,
    torax numeric,
    coxa numeric,
    panturrilha_medial numeric
);

create table avaliacao_fisica(
	id bigint auto_increment primary key,
    id_paciente bigint,
    altura numeric,
    peso numeric,
    id_circunferencia bigint,
    id_composicao_corporal bigint,
    foreign key(id_paciente) references paciente(id),
    foreign key(id_circunferencia) references circunferencia(id),
    foreign key(id_composicao_corporal) references composicao_corporal(id)
);

create table plano_alimentar(
	id bigint auto_increment primary key,
    id_paciente bigint,
    descricao varchar(200),
    segunda boolean,
    terca boolean,
    quarta boolean,
    quinta boolean,
    sexta boolean,
    sabado boolean,
    domingo boolean,
    foreign key(id_paciente) references paciente(id)
);

create table atendimento(
	id bigint auto_increment primary key,
    id_nutricionista bigint,
    id_paciente bigint,
    id_avaliacao_fisica bigint,
    id_plano_alimentar bigint,
    data_atendimento datetime,
    anamnese varchar(200),
    foreign key(id_nutricionista) references nutricionista(id_usuario),
    foreign key(id_paciente) references paciente(id),
    foreign key(id_avaliacao_fisica) references avaliacao_fisica(id),
    foreign key(id_plano_alimentar) references plano_alimentar(id)
);

create table refeicao(
	id bigint auto_increment primary key,
	id_plano_alimentar bigint,
    horario time,
    descricao varchar(50),
    observacao varchar(100),
    foreign key(id_plano_alimentar) references plano_alimentar(id)
);

create table alimento(
	id bigint auto_increment primary key,
	descricao varchar(50)
);

create table medida(
	id bigint auto_increment primary key,
	descricao varchar(50),
	descricao_apresentacao varchar(50)
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


create table imc(
	id bigint auto_increment primary key,
	descricao varchar(50),
	faixa_inicio numeric(10,1),
	faixa_fim numeric(10,1),
	classificacao varchar(20)
);

insert into medida(descricao, descricao_apresentacao) values('Grama', 'grama(s)');
insert into medida(descricao, descricao_apresentacao) values('Colher', 'colher(res)');
insert into medida(descricao, descricao_apresentacao) values('Xícara', 'xírcara(s)');
insert into medida(descricao, descricao_apresentacao) values('Copo', 'copo(s)');
insert into medida(descricao, descricao_apresentacao) values('Unidade', 'unidade(s)');

insert into alimento(descricao) values ('Café');
insert into alimento(descricao) values ('Cuzcuz');
insert into alimento(descricao) values ('Ovo');

insert into alimento(descricao) values ('Filé de frango');
insert into alimento(descricao) values ('Arroz integral');
insert into alimento(descricao) values ('Batata doce cozida');

insert into alimento(descricao) values ('Pão speciale - Multigrãos');
insert into alimento(descricao) values ('Requeijão - Sabor e vida');
insert into alimento(descricao) values ('Atum em conserva');

insert into alimento(descricao) values ('Leite longa vida desnatado');
insert into alimento(descricao) values ('Morango');

insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('Menor que 18,5', 0, 18.4, 'Magreza');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('18,5 a 24,9', 18.5, 24.9, 'Normal');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('25 a 29,9', 25, 29.9, 'Sobrepeso');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('30 a 34,6', 30, 34.9, 'Obesidade grau I');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('35 a 39,9', 35, 39.9, 'Obesidade grau II');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('Maior que 40', 40, 99999999, 'Obesidade grau III');

insert into usuario(nome, login, senha, perfil) values('Edson', '05982191370', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');
insert into usuario(nome, login, senha, perfil) values('Robério', '00000000000', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTROLOGO');
insert into usuario(nome, login, senha, perfil) values('Igor', '11111111111', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');
insert into usuario(nome, login, senha, perfil) values('Vanessa', '22222222222', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'ENFERMEIRA');
insert into usuario(nome, login, senha, perfil) values('Rafael', '33333333333', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');

insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Francisco Edson', '1995-04-10', '05982191370', 1);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('José Robério', '1995-04-10', '00000000000', 2);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Igor da Silva', '1995-04-10', '11111111111', 3);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Vanessa Camargo', '1995-04-10', '22222222222', 4);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Rafael Lisboa', '1995-04-10', '33333333333', 5);

insert into paciente(nome, data_nascimento, sexo, profissao) values('Marcos Gomes da Silva', '1995-04-11', 'MASCULINO', 'Professor');
insert into paciente(nome, data_nascimento, sexo, profissao) values('Maria Josefa de Alencar', '1986-12-07', 'FEMININO', 'Engenheira Química');
insert into paciente(nome, data_nascimento, sexo, profissao) values('Mônica Barreto Aguiar', '1979-04-06', 'FEMININO', 'Médica');
insert into paciente(nome, data_nascimento, sexo, profissao) values('Cássio Fernandes Sousa', '2001-04-10', 'MASCULINO', 'Analista de Sistemas');

insert into atendimento(id_paciente, id_nutricionista, data_atendimento) values(1, 1, '2023-04-15');
insert into atendimento(id_paciente, id_nutricionista, data_atendimento) values(2, 1, '2023-04-15');
insert into atendimento(id_paciente, id_nutricionista, data_atendimento) values(3, 1, '2023-04-15');
insert into atendimento(id_paciente, id_nutricionista, data_atendimento) values(4, 1, '2023-04-15');

insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(1, 1, '2023-04-15', 'ATENDIMENTO_NUTRICIONISTA');
insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(1, 2, '2022-11-04', 'ATENDIMENTO_NUTROLOGO');
insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(1, 1, '2022-11-04', 'REGISTRO_ACOMPANHAMENTO');
insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(1, 4, '2020-09-01', 'ATENDIMENTO_ENFERMAGEM');
insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(1, 5, '2023-04-15', 'ATENDIMENTO_NUTRICIONISTA');