create table usuario(
	id bigint auto_increment primary key,
    nome varchar(150) not null,
    login varchar(50) not null,
    senha varchar(100) not null,
    perfil varchar(50) not null,
    avatar varchar(50),
    avatar_content_type varchar(10),
    push_token varchar(200)
);

create table nutricionista(
	id_usuario bigint primary key,
    nome varchar(150) not null,
    data_nascimento datetime not null,
    cpf varchar(11) not null, 
    foreign key(id_usuario) references usuario(id)
);

create table paciente(
	id_usuario bigint auto_increment primary key,
    nome varchar(150),
    data_nascimento datetime,
    sexo varchar(10),
    url_avatar varchar(50),
    profissao varchar(100),
    cpf varchar(11) not null
);

create table paciente_historico(
	id bigint auto_increment primary key,
	id_paciente bigint,
    id_usuario bigint,
    data_ocorrencia datetime,
    ocorrencia varchar(50),
    foreign key(id_paciente) references paciente(id_usuario),
    foreign key(id_usuario) references usuario(id)
);

create table circunferencia(
	id bigint auto_increment primary key,
    braco_esquerdo_relaxado numeric(6,2),
    braco_direito_relaxado numeric(6,2),
    braco_esquerdo_contraido numeric(6,2),
    braco_direito_contraido numeric(6,2),
    antebraco_direito numeric(6,2),
    antebraco_esquerdo numeric(6,2),
    punho_esquerdo numeric(6,2),
    punho_direito numeric(6,2)
);

create table composicao_corporal(
	id bigint auto_increment primary key,
    tipo varchar(50),
    protocolo varchar(50),
    biceps numeric(6,2),
    abdominal numeric(6,2),
    triceps numeric(6,2),
    suprailiaca numeric(6,2),
    axilar_media numeric(6,2),
    subscapular numeric(6,2),
    torax numeric(6,2),
    coxa numeric(6,2),
    panturrilha_medial numeric(6,2)
);

create table avaliacao_fisica(
	id bigint auto_increment primary key,
    id_paciente bigint,
    altura numeric(6,2),
    peso numeric(6,2),
    id_circunferencia bigint,
    id_composicao_corporal bigint,
    foreign key(id_paciente) references paciente(id_usuario),
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
    data_hora_inclusao timestamp,
    foreign key(id_paciente) references paciente(id_usuario)
);

create table plano_alimentar_dia_semana(
	id bigint auto_increment primary key,
    dia_semana smallint,
    id_plano_alimentar bigint, 
    foreign key(id_plano_alimentar) references plano_alimentar(id)
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
    foreign key(id_paciente) references paciente(id_usuario),
    foreign key(id_avaliacao_fisica) references avaliacao_fisica(id),
    foreign key(id_plano_alimentar) references plano_alimentar(id)
);

create table refeicao(
	id bigint auto_increment primary key,
	id_plano_alimentar_dia_semana bigint,
    horario time,
    descricao varchar(50),
    observacao varchar(100),
    foreign key(id_plano_alimentar_dia_semana) references plano_alimentar_dia_semana(id)
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
    quantidade numeric(6,2),
    foreign key(id_refeicao) references refeicao(id),
    foreign key(id_alimento) references alimento(id),
    foreign key(id_medida) references medida(id)
);

create table imc(
	id bigint auto_increment primary key,
	descricao varchar(50),
	faixa_inicio numeric(10,1),
	faixa_fim numeric(10,1),
	classificacao varchar(30)
);

create table faulkner_4_pregas(
	id bigint auto_increment primary key, 
	idade_inicio smallint,
	idade_fim smallint,
    faixa_inicio numeric(4,2),
	faixa_fim numeric(6,2),
	classificacao varchar(20),
    sexo varchar(10)
);

create table alerta_hidratacao(
	id bigint auto_increment primary key, 
	id_usuario bigint,
	horario time,
	foreign key(id_usuario) references usuario(id)
);

create table historico_notificacoes(
	id bigint auto_increment primary key, 
	id_usuario bigint,
	titulo varchar(50),
	corpo varchar(100),
	foreign key(id_usuario) references usuario(id)
);


INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (1,18,25,0.00,3.99,'Muito baixo','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (2,18,25,4.00,6.99,'Excelente','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (3,18,25,7.00,10.99,'Muito bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (12,18,25,11.00,13.99,'Bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (13,18,25,14.00,16.99,'Adequado','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (14,18,25,17.00,20.99,'Moderadamente alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (15,18,25,21.00,24.99,'Alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (16,18,25,25.00,9999.00,'Muito alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (17,26,35,0.00,7.99,'Muito baixo','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (18,26,35,8.00,11.99,'Excelente','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (19,26,35,12.00,15.99,'Muito bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (20,26,35,16.00,18.99,'Bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (21,26,35,19.00,20.99,'Adequado','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (22,26,35,21.00,24.99,'Moderadamento alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (23,26,35,25.00,27.99,'Alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (24,26,35,27.00,9999.00,'Muito alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (25,36,45,0.00,9.99,'Muito baixo','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (26,36,45,10.00,14.99,'Excelente','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (27,36,45,15.00,18.99,'Muito bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (28,36,45,19.00,21.99,'Bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (29,36,45,22.00,23.99,'Adequado','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (30,36,45,24.00,25.99,'Moderadamente alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (31,36,45,26.00,29.99,'Alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (32,36,45,30.00,9999.00,'Muito alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (33,46,55,0.00,11.99,'Muito baixo','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (34,46,55,12.00,16.99,'Excelente','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (35,46,55,17.00,20.99,'Muito bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (36,46,55,21.00,23.99,'Bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (37,46,55,24.00,25.99,'Adequado','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (38,46,55,26.00,27.99,'Moderadamento alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (39,46,55,28.00,30.99,'Alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (40,46,55,31.00,9999.00,'Muito alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (41,56,65,0.00,12.99,'Muito baixo','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (42,56,65,13.00,18.99,'Excelente','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (43,56,65,19.00,21.99,'Muito bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (44,56,65,22.00,23.99,'Bom','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (45,56,65,24.00,25.99,'Adequado','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (46,56,65,26.00,27.99,'Moderadamente alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (47,56,65,28.00,30.99,'Alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (48,56,65,31.00,9999.00,'Muito alto','MASCULINO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (49,18,25,0.00,12.99,'Muito baixo','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (50,18,25,13.00,16.99,'Excelente','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (51,18,25,17.00,19.99,'Muito bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (52,18,25,20.00,22.99,'Bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (53,18,25,23.00,25.99,'Adequado','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (54,18,25,26.00,28.99,'Moderadamente alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (55,18,25,29.00,31.99,'Alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (56,18,25,32.00,9999.00,'Muito alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (57,26,35,0.00,13.99,'Muito baixo','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (58,26,35,14.00,16.99,'Excelente','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (59,26,35,17.00,20.99,'Muito bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (60,26,35,21.00,23.99,'Bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (61,26,35,24.00,25.99,'Adequado','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (62,26,35,26.00,29.99,'Moderadamente alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (63,26,35,30.00,33.99,'Alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (64,26,35,34.00,9999.00,'Muito alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (65,36,45,0.00,15.99,'Muito baixo','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (66,35,45,16.00,19.99,'Excelente','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (67,35,45,20.00,23.99,'Muito bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (68,35,45,24.00,26.99,'Bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (69,35,45,27.00,29.99,'Adequado','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (70,35,45,30.00,32.99,'Moderadamente alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (71,35,45,33.00,36.99,'Alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (72,35,45,37.00,9999.00,'Muito alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (73,46,55,0.00,16.99,'Muito baixo','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (74,46,55,17.00,21.99,'Excelente','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (75,46,55,22.00,25.99,'Muito bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (76,46,55,26.00,28.99,'Bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (77,46,55,29.00,31.99,'Adequado','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (78,46,55,32.00,34.99,'Moderadamente alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (79,46,55,35.00,38.99,'Alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (80,46,55,39.00,9999.00,'Muito alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (81,56,65,0.00,17.99,'Muito baixo','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (82,56,65,18.00,22.99,'Excelente','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (83,56,65,23.00,26.99,'Muito bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (84,56,65,27.00,29.99,'Bom','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (85,56,65,30.00,32.99,'Adequado','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (86,56,65,33.00,35.99,'Moderadamente alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (87,56,65,36.00,38.99,'Alto','FEMININO');
INSERT INTO faulkner_4_pregas (`id`,`idade_inicio`,`idade_fim`,`faixa_inicio`,`faixa_fim`,`classificacao`,`sexo`) VALUES (88,56,65,39.00,9999.00,'Muito alto','FEMININO');

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

insert into alimento(descricao) values ('Suco de manga'); 

insert into alimento(descricao) values ('Leite longa vida desnatado');
insert into alimento(descricao) values ('Morango');

insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('Menor que 18,5', 0, 18.4, 'Abaixo do peso normal');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('18,5 a 24,9', 18.5, 24.9, 'Normal');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('25 a 29,9', 25, 29.9, 'Sobrepeso');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('30 a 34,6', 30, 34.9, 'Obesidade grau I');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('35 a 39,9', 35, 39.9, 'Obesidade grau II');
insert into imc(descricao, faixa_inicio, faixa_fim, classificacao) values('Maior que 40', 40, 99999999, 'Obesidade grau III');

insert into usuario(nome, login, senha, perfil) values('Edson', '05982191370', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');
insert into usuario(nome, login, senha, perfil) values('Igor', '11111111111', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');
insert into usuario(nome, login, senha, perfil) values('Rafael', '33333333333', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'NUTRICIONISTA');

insert into usuario(nome, login, senha, perfil) values('Robério', '00000000000', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'PACIENTE');
insert into usuario(nome, login, senha, perfil) values('Vanessa', '22222222222', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'PACIENTE');
insert into usuario(nome, login, senha, perfil) values('Francisco', '43014089320', '$2a$10$.FvcPiWS5zRSibS5URBCJen8cILgsZYss3rEbbNCC2amfPbQN/VsG', 'PACIENTE');

insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Francisco Edson', '1995-04-10', '05982191370', 1);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('José Robério', '1995-04-10', '00000000000', 2);
insert into nutricionista(nome, data_nascimento, cpf, id_usuario) values('Igor da Silva', '1995-04-10', '11111111111', 3);

insert into paciente(id_usuario, nome, cpf, data_nascimento, sexo, profissao) values(4, 'Robério da Silva Xavier', '00000000000', '1995-04-11', 'MASCULINO', 'Professor');
insert into paciente(id_usuario, nome, cpf, data_nascimento, sexo, profissao) values(5, 'Vanessa da Rocha Pinho', '22222222222', '1986-12-07', 'FEMININO', 'Engenheira Química');
insert into paciente(id_usuario, nome, cpf, data_nascimento, sexo, profissao) values(6, 'Francisco Moneteiro Junior', '43014089320', '1979-04-06', 'MASCULINO', 'MédicO');

insert into circunferencia(braco_esquerdo_relaxado, braco_direito_relaxado, braco_esquerdo_contraido, braco_direito_contraido, antebraco_direito, antebraco_esquerdo, punho_direito, punho_esquerdo) values(1, 2, 3, 4, 5, 6, 7, 8);

insert into composicao_corporal(tipo, protocolo, biceps, abdominal, triceps, suprailiaca, axilar_media, subscapular, torax, coxa, panturrilha_medial) values('PREGAS_CUTANEAS', 'QUATRO_PREGAS_FAULKNER', 1, 2, 3, 4, 5, 6, 7, 8, 9);

insert into avaliacao_fisica(id_paciente, altura, peso, id_circunferencia, id_composicao_corporal) values(6, 1.73, 79.5, 1, 1);

insert into plano_alimentar(id_paciente, descricao, data_hora_inclusao, segunda, terca, quarta, quinta, sexta, sabado, domingo) values(6, 'Plano alimentar do Francisco', current_timestamp(), true, true, true, true, true, true, true);

insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(1, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(2, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(3, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(4, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(5, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(6, 1);
insert into plano_alimentar_dia_semana(dia_semana, id_plano_alimentar) values(7, 1);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(1, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(1, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(1, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(1, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(1, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(2, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(2, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(2, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(2, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(2, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(3, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(3, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(3, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(3, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(3, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(4, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(4, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(4, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(4, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(4, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(5, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(5, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(5, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(5, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(5, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(6, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(6, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(6, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(6, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(6, '22:00', 'Ceia', null);

insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(7, '07:00', 'Café da manhã', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(7, '12:00', 'Almoço', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(7, '15:30', 'Lanche da tarde', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(7, '19:00', 'Jantar', null);
insert into refeicao(id_plano_alimentar_dia_semana, horario, descricao, observacao) values(7, '22:00', 'Ceia', null);

insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(1, 1, 10, 3);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(1, 2, 20, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(1, 3, 30, 5);

insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(2, 4, 40, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(2, 5, 50, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(2, 6, 60, 1);

insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(3, 7, 70, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(3, 8, 80, 2);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(3, 9, 90, 1);

insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(4, 4, 200, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(4, 5, 100, 1);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(4, 10, 1, 4);

insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(5, 11, 100, 4);
insert into refeicao_alimento(id_refeicao, id_alimento, quantidade, id_medida) values(5, 12, 110, 1);

insert into atendimento(id_paciente, id_nutricionista, id_plano_alimentar, id_avaliacao_fisica, data_atendimento) values(6, 1, 1, 1, '2023-04-15');

insert into paciente_historico(id_paciente, id_usuario, data_ocorrencia, ocorrencia) values(6, 1, '2023-04-15', 'ATENDIMENTO_NUTRICIONISTA');
