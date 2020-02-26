INSERT INTO permissao (id,descricao,permissao) VALUES
(1,'Administrador','ADMIN') ,(2,'Usuário','USER');

INSERT INTO usuario (id,data_cadastro,email,senha,ultimo_acesso,nome,cpf,data_nascimento,sexo) VALUES
(7,'2019-09-10 08:38:42.085','victorhugonegrisoli.ccs@gmail.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6',current_timestamp,'Victor Hugo Negrisoli','103.324.589-54',current_timestamp,'MASCULINO'),
(59,'2019-12-14 20:05:09.440','joaoalturapeso@gmail.com','$2a$10$ff8F.DQcBbNC2kgHCMVT2.6aaIq6AbEJBVZQa5H6lLPqlufWxFXH2',current_timestamp,'João Cadastro Altura Peso','909.499.570-03',current_timestamp,'MASCULINO'),
(2,'2019-01-01 00:00:00.000','teste2@teste.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6',current_timestamp,'User 2','273.080.780-27',current_timestamp,'MASCULINO'),
(3,'2019-09-08 15:53:49.067','teste3@teste.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6',current_timestamp,'User 3','008.566.230-59',current_timestamp,'MASCULINO'),
(15,'2019-09-25 21:49:39.578','rafinha.nonino7@gmail.com','$2a$10$BApX4U6WmvWEKnGsWwhyFO393C4cS36YmFlK3lxOYzSFJYNfqJ.v2',current_timestamp,'Rafael Nonino Filho','083.720.729-06',current_timestamp,'MASCULINO'),
(61,'2019-12-14 21:08:39.626','emily@gmail.com','$2a$10$BrW7KGcWzAPZN4MwYgJbQuYFKBG6j1Zm9dGvkS.xCJF3Qbz6.zh8.',current_timestamp,'Emilly Agatha Silvana da Paz','941.708.895-50',current_timestamp,'FEMININO'),
(105,'2019-12-23 12:02:18.731','novousuario@teste.com','$2a$10$MDG/LyK0qhTX26BOJve.Y.2MJBoh8CPS5TGKapbxyzH60NMpUDRqS',current_timestamp,'nome teste','444.782.770-01',current_timestamp,'FEMININO'),
(108,'2019-12-23 14:53:35.478','novousuario2@teste.com','$2a$10$hmJkUtolFtxjfcxwqBVWf.hYVI1/YHYrSuGKxS7B7T7LsiaqvgihG',current_timestamp,'nome teste','643.956.590-71',current_timestamp,'FEMININO');

INSERT INTO USUARIO_PERMISSAO (FK_USUARIO, FK_PERMISSAO) VALUES
(7, 1),
(59, 1),
(2, 1),
(3, 1),
(15, 1),
(61, 1),
(105, 1),
(108, 1);