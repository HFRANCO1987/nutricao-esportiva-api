INSERT INTO dieta (id,fk_usuario,descricao,data_cadastro) VALUES
(120,7,'Atualizar dieta 2','2019-12-24 11:58:32.214'),
(116,7,'Atualizar dieta 2','2019-12-24 11:58:07.658'),
(169,7,'Nova Dieta Completa Response','2020-02-25 18:21:38.644');

INSERT INTO periodo_alimento_dieta (id,fk_dieta,fk_periodo,fk_alimento,quantidade) VALUES
(121,116,10,24,150.65),
(122,116,10,27,150.65),
(123,116,11,32,150.65),
(124,116,12,55,150.65),
(160,120,10,1,150.65),
(164,120,13,165,150.65),
(165,120,12,16,150.65),
(168,120,12,116,150.65),
(170,116,5,24,150.65),
(171,169,5,24,150.65),
(172,169,5,135,179.30);