create table Usuario(
id serial not null primary key,
usuario varchar(20) not null,
senha varchar(15) not null,
nome varchar(30) not null,
email varchar(30) not null,
nivelAcesso_id int not null references NivelAcesso(id));

insert into Usuario(usuario,senha,nome,email,nivelAcesso_id) values('admin','admin','Felipe','felipe@admin.com',4);
insert into Usuario(usuario,senha,nome,email,nivelAcesso_id) values('mod','mod','Joao','joao@moderador.com',3);
insert into Usuario(usuario,senha,nome,email,nivelAcesso_id) values('usufim','usufim','Mariazinha','mariazinha@usuariofinal.com',2);

delete from Usuario where usuario='usufim';

create table Evento(
id serial not null primary key,
titulo varchar(30) not null,
descricao varchar(100) not null,
local varchar(30) not null,
data varchar(10) not null,
hora varchar(5) not null);

insert into Evento(titulo,descricao,local,data,hora) values('Primeiro Evento','descricao do primeiro evento','algum lugar','30/09/2014','13:00');
insert into Evento(titulo,descricao,local,data,hora) values('Segundo Evento','descricao do segundo evento','algum lugar','01/10/2014','17:00');

create table NivelAcesso(
id int not null primary key,
tipo varchar(13) not null,
AdicionaEvento boolean not null,
visualizaEvento boolean not null,
editaEvento boolean not null,
excluiEvento boolean not null,
AdicionaUsuario boolean not null,
visualizaUsuario boolean not null,
editaUsuario boolean not null,
excluiUsuario boolean not null,
AdicionaModerador boolean not null,
visualizaModerador boolean not null,
editaModerador boolean not null,
excluiModerador boolean not null,
AdicionaAdministrador boolean not null,
visualizaAdministrador boolean not null,
editaAdministrador boolean not null,
excluiAdministrador boolean not null);

insert into NivelAcesso values(1,'UsuarioNovo',true,true,true,true);
insert into NivelAcesso values(2,'UsuarioFinal',true,true,true,true);
insert into NivelAcesso values(3,'Moderador',true,true,true,true);
insert into NivelAcesso values(4,'Administrador',true,true,true,true);

select * from nivelAcesso;