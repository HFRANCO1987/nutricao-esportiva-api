INSERT INTO esporte (id,atividade,kcal_hora_min,kcal_hora_max,kcal_min_min,kcal_min_max) VALUES
(1,'Andando de bicicleta',180.00,300.00,3.00,5.00),
(2,'Balé',480.00,0.00,8.00,0.00),
(3,'Basquete',600.00,0.00,10.00,0.00),
(4,'Beijando',480.00,0.00,8.00,0.00),
(5,'Beijar e fazer carícias',60.00,0.00,1.00,0.00),
(6,'Boxe',660.00,0.00,11.00,0.00),
(7,'Caminhada',330.00,0.00,5.50,0.00),
(8,'Caminhando rápido',520.00,0.00,8.67,0.00),
(9,'Caminhar devagar',240.00,0.00,4.00,0.00),
(10,'Carregando bebê',141.00,0.00,2.35,0.00),
(11,'Capoeira',720.00,0.00,12.00,0.00),
(12,'Ciclismo',360.00,0.00,6.00,0.00),
(13,'Compra no Supermercado',270.00,0.00,4.50,0.00),
(14,'Corrida',600.00,0.00,10.00,0.00),
(15,'Corrida',500.00,900.00,8.33,15.00),
(16,'Cozinhar',168.00,0.00,2.80,0.00),
(17,'Dança de Salão',210.00,0.00,3.50,0.00),
(18,'Dançando rápido',605.00,0.00,10.08,0.00),
(19,'Deitado',77.00,0.00,1.28,0.00),
(20,'Digitando',95.00,0.00,1.58,0.00),
(21,'Dormindo',60.00,0.00,1.00,0.00),
(22,'Escrever',10.00,20.00,0.17,0.33),
(23,'Esqui aquático',660.00,0.00,11.00,0.00),
(24,'Esqui na neve',450.00,0.00,7.50,0.00),
(25,'Estudar',120.00,0.00,2.00,0.00),
(26,'Exercício leve',310.00,0.00,5.17,0.00),
(27,'Falando ao telefone',85.00,0.00,1.42,0.00),
(28,'Fazer amor',190.00,0.00,3.17,0.00),
(29,'Ficar de pé',130.00,0.00,2.17,0.00),
(30,'Futebol',540.00,0.00,9.00,0.00),
(31,'Ginástica aeróbica',360.00,0.00,6.00,0.00),
(32,'Ginástica olímpica',360.00,0.00,6.00,0.00),
(33,'Golfe',180.00,0.00,3.00,0.00),
(34,'Handebol',600.00,0.00,10.00,0.00),
(35,'Hidroginástica',360.00,0.00,6.00,0.00),
(36,'Jiu-jitsu',720.00,0.00,12.00,0.00),
(37,'Jogando vídeo game',108.00,0.00,1.80,0.00),
(38,'Jogar futebol',580.00,0.00,9.67,0.00),
(39,'Judô',720.00,0.00,12.00,0.00),
(40,'Lavar louça',60.00,0.00,1.00,0.00),
(41,'Limpeza de casa',300.00,0.00,5.00,0.00),
(42,'Mountain bike',720.00,0.00,12.00,0.00),
(43,'Musculação',300.00,0.00,5.00,0.00),
(44,'Natação',540.00,0.00,9.00,0.00),
(45,'Natação',500.00,0.00,8.33,0.00),
(46,'Pintar casa',160.00,0.00,2.67,0.00),
(47,'Remo',660.00,0.00,11.00,0.00),
(48,'Squash',780.00,0.00,13.00,0.00),
(49,'Subir escada',1000.00,0.00,16.67,0.00),
(50,'Surfe',480.00,0.00,8.00,0.00),
(51,'Tênis',480.00,0.00,8.00,0.00),
(52,'Trabalhar leve em pé',150.00,0.00,2.50,0.00),
(53,'Trabalho mental casa',60.00,0.00,1.00,0.00),
(54,'Vôlei',360.00,0.00,6.00,0.00),
(55,'Windsurf',420.00,0.00,7.00,0.00);

INSERT INTO usuario_esportes(id, fk_usuario, fk_esporte) values
(1, 7, 1),
(2, 7, 2),
(3, 7, 3),
(4, 7, 4),
(5, 3, 2),
(6, 3, 4),
(7, 3, 30);