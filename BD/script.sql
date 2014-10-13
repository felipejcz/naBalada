DROP TABLE Usuario;

CREATE TABLE Usuario(
id serial not null primary key,
usuario varchar(15) not null,
senha varchar(15) not null,
nome varchar(15) not null,
email varchar(30) not null,
nivelAcesso_id int not null references nivelAcesso(id));

SELECT * FROM Usuario;

INSERT INTO Usuario(id,usuario,senha,nome,email,nivelacesso_id)
VALUES(0,'sysdb','sysdb','system','not email',2);
------------------------------------------------------------------------------------

DROP TABLE Evento;

CREATE TABLE Evento(
id serial not null primary key,
titulo varchar(30) not null,
descricao varchar(100) not null,
local varchar(30) not null,
data date not null,
hora time not null,
foto varchar(50),
localizacao varchar(20),
criador int not null references usuario(id),
autorizado boolean not null,
moderador int references usuario(id),
comentario varchar(50)

);

INSERT INTO evento(titulo,descricao,local,data,hora,criador,autorizado)
VALUES('Primeiro Evento','Breve descrição sobre o primeiro evento','Local pertinho','23/10/2014','22:30',1,false);

INSERT INTO evento(titulo,descricao,local,data,hora,criador,autorizado)
VALUES('Segundo Evento','Breve descrição sobre o segundo evento','Local longinho','25/10/2014','2030',1,false);

DELETE Evento where id=1;

SELECT * FROM Evento;

SELECT e.id,titulo,descricao,local,data,hora,foto,localizacao,p.nome AS criador,autorizado,m.nome AS moderador,comentario FROM Usuario p, Usuario m, Evento e where e.criador = p.id AND e.moderador = m.id order by e.id;

SELECT titulo,descricao,local,data,hora,Usuario.nome,autorizado FROM Evento inner join Usuario on (Evento.criador = Usuario.id) order by Evento.id;

SELECT Evento.id,titulo,descricao,local,data,hora,foto,localizacao,Usuario.nome AS criador,autorizado,Usuario.nome AS moderador,comentario FROM Evento inner join Usuario on (Evento.criador = Usuario.id AND Evento.moderador = Usuario.id);

UPDATE Evento set titulo='Primeiro evento',descricao='Breve descrição sobre o evento',local='Local pertinho',data='2014-10-23',hora='22:30:00',foto='',localizacao='',criador=1,autorizado=false,moderador=3,comentario='' WHERE id=1;

UPDATE Evento set moderador=0 where id=8;

//Mostrar dois id na mesma pesquisa
SELECT Evento.titulo, pessoa.nome,moderador.nome FROM usuario as pessoa, usuario as moderador, Evento where Evento.criador = pessoa.id and Evento.moderador = moderador.id; 
SELECT Evento.id,titulo,descricao,local,data,hora,foto,localizacao,pessoa.nome AS usuario,autorizado,moderador.nome AS moderador,comentario FROM Usuario pessoa, Usuario moderador, Evento WHERE Evento.criador = pessoa.id and Evento.moderador = moderador.id;