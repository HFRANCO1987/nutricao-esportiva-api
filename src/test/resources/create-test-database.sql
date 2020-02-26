INSERT INTO permissao (id,descricao,permissao) VALUES
(1,'Administrador','ADMIN') ,(2,'Usuário','USER');

INSERT INTO usuario (id,data_cadastro,email,senha,nome,cpf,data_nascimento,sexo) VALUES
(7,'2019-09-10 08:38:42.085','victorhugonegrisoli.ccs@gmail.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6','Victor Hugo Negrisoli','103.324.589-54',current_timestamp,'MASCULINO'),
(59,'2019-12-14 20:05:09.440','joaoalturapeso@gmail.com','$2a$10$ff8F.DQcBbNC2kgHCMVT2.6aaIq6AbEJBVZQa5H6lLPqlufWxFXH2','João Cadastro Altura Peso','909.499.570-03',current_timestamp,'MASCULINO'),
(2,'2019-01-01 00:00:00.000','teste2@teste.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6','User 2','273.080.780-27',current_timestamp,'MASCULINO'),
(3,'2019-09-08 15:53:49.067','teste3@teste.com','$2a$10$uOy6Cs2KXHXR.ZO0KyGh1eupbj1q40vWss2Al2m8tM/R5ZCQZv4p6','User 3','008.566.230-59',current_timestamp,'MASCULINO'),
(15,'2019-09-25 21:49:39.578','rafinha.nonino7@gmail.com','$2a$10$BApX4U6WmvWEKnGsWwhyFO393C4cS36YmFlK3lxOYzSFJYNfqJ.v2','Rafael Nonino Filho','083.720.729-06',current_timestamp,'MASCULINO'),
(61,'2019-12-14 21:08:39.626','emily@gmail.com','$2a$10$BrW7KGcWzAPZN4MwYgJbQuYFKBG6j1Zm9dGvkS.xCJF3Qbz6.zh8.','Emilly Agatha Silvana da Paz','941.708.895-50',current_timestamp,'FEMININO'),
(105,'2019-12-23 12:02:18.731','novousuario@teste.com','$2a$10$MDG/LyK0qhTX26BOJve.Y.2MJBoh8CPS5TGKapbxyzH60NMpUDRqS','nome teste','444.782.770-01',current_timestamp,'FEMININO'),
(108,'2019-12-23 14:53:35.478','novousuario2@teste.com','$2a$10$hmJkUtolFtxjfcxwqBVWf.hYVI1/YHYrSuGKxS7B7T7LsiaqvgihG','nome teste','643.956.590-71',current_timestamp,'FEMININO');

INSERT INTO USUARIO_PERMISSAO (FK_USUARIO, FK_PERMISSAO) VALUES
(7, 1),
(59, 2),
(2, 2),
(3, 1),
(15, 2),
(61, 1),
(105, 1),
(108, 1);

INSERT INTO peso_altura (id,fk_usuario,peso,altura,data_cadastro,peso_altura_atual) VALUES
(106,105,89.70,1.78,'2019-12-23 12:02:18.812','V'),
(109,108,89.70,1.78,'2019-12-23 14:53:35.624','V'),
(75,59,55.00,1.79,'2019-12-02 23:54:19.000','F'),
(76,59,60.00,1.79,'2019-12-15 00:44:11.518','F'),
(80,59,56.70,1.73,'2019-12-18 09:19:51.117','F'),
(82,59,88.40,1.73,'2019-12-18 09:55:41.467','F'),
(83,59,74.60,1.73,'2019-12-18 09:58:47.931','F'),
(84,59,74.60,1.73,'2019-12-18 10:10:48.073','F'),
(85,59,74.60,1.73,'2019-12-18 10:11:41.626','F'),
(96,59,88.90,1.73,'2019-12-21 12:20:33.654','F'),
(97,59,89.00,1.73,'2019-12-21 13:23:54.711','F'),
(98,59,89.00,1.73,'2019-12-21 19:08:45.256','F'),
(99,59,8.40,1.73,'2019-12-21 19:15:00.561','F'),
(100,59,84.00,1.73,'2019-12-21 19:15:51.108','F'),
(101,59,7.20,1.76,'2019-12-23 11:48:41.593','F'),
(133,59,88.00,1.76,'2020-02-18 13:41:49.369','V'),
(135,61,88.00,1.76,'2020-02-18 13:43:09.279','F'),
(136,61,89.42,1.76,'2020-02-18 13:43:46.812','F'),
(137,61,85.00,1.76,'2020-02-18 13:44:27.895','V'),
(104,7,7.20,1.76,'2019-12-23 12:01:39.807','F'),
(107,7,7.20,1.76,'2019-12-23 14:36:40.101','F'),
(132,7,94.00,1.73,'2020-02-15 12:32:30.095','F'),
(138,7,90.00,1.73,'2020-02-20 21:21:41.776','V');