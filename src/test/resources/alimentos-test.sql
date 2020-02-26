INSERT INTO categoria (id, descricao) VALUES
(1, 'Cereais e derivados'),
(2, 'Verduras, hortaliças e derivados'),
(3, 'Frutas e derivados');

INSERT INTO alimento (id,fk_categoria,descricao,umidade,energia_kcal,energia_kj,protei_g,lipideos_g,colesterol_mg,carboidrato_g,fibra_alimentar_g,cinzas_g,calcio_mg,magnesio_mg,numero_alimento,manganes_mg,fosforo_mg,ferro_mg,sodio_mg,potassio_mg,cobre_mg,zinco_mg,retinol_mcg,re_mcg,era_mcg,tiami_mg,riboflavi_mg,piridoxi_mg,niaci_mg,vitami_c_mg,imagem) VALUES
(16,1,'Bolo, pronto, chocolate',19.276333330,410.113666700,1715.497181000,6.222916667,18.472333330,76.797666670,54.717750000,1.430000000,1.310666667,74.584666670,27.686666670,16,0.379333333,196.976666700,2.132333333,283.300000000,211.763333300,0.146333333,0.713666667,0.000000000,NULL,NULL,0.133333333,0.193333333,'0.15',0.000000000,0.000000000,NULL),
(1,1,'Arroz, integral, cozido',70.138666670,123.534892500,516.869990200,2.588250000,1.100333333,NULL,25.809750000,2.749333333,0.463000000,5.204000000,58.702000000,1,0.627333333,105.853000000,0.262000000,1.244666667,75.151666670,0.120333333,0.682666667,NULL,NULL,NULL,0.180000000,0.000000000,'0.18',0.000000000,NULL,'arroz'),
(27,1,'Creme de arroz, pó',7.333000000,386.101190300,1615.128980000,7.126949775,1.226000000,NULL,83.869383560,1.171333333,0.544666667,7.185333333,50.503333330,27,1.237333333,153.120000000,0.632333333,1.130333333,114.680333300,0.138000000,1.863666667,NULL,NULL,NULL,0.216666667,0.000000000,'0.153333333',0.000000000,0.000000000,'arroz'),
(32,1,'Farinha, de centeio, integral',10.776666670,335.777662800,1404.893741000,12.515066500,1.753333333,NULL,73.298266830,15.480000000,1.656666667,33.916666670,120.233333300,32,3.856666667,339.986666700,4.730000000,41.376666670,333.620000000,0.563333333,2.663333333,NULL,NULL,NULL,0.286666667,0.130000000,'0.18',0.000000000,0.000000000,NULL),
(116,2,'Couve, manteiga, refogada',81.527333330,90.344815430,378.102707700,1.666666667,6.594000000,NULL,8.707666667,5.736333333,1.504333333,177.332333300,26.204666670,116,0.123333333,33.406666670,0.495333333,11.446666670,314.889000000,0.120333333,0.193666667,NULL,384.000000000,192.000000000,0.000000000,0.150000000,'0.17',0.000000000,76.943333330,NULL),
(135,2,'Mostarda, folha, crua',93.430000000,18.107389050,75.761315790,2.110416667,0.167666667,NULL,3.236583333,1.891000000,1.155333333,68.178333330,15.618333330,135,0.138666667,58.398333330,1.197000000,2.879333333,363.566333300,0.148333333,0.284000000,NULL,NULL,NULL,0.146666667,0.140000000,'0.15',0.000000000,38.553333330,NULL),
(24,1,'Cereais, mistura para vitami, trigo, cevada e aveia',4.366666667,381.133333300,1594.661867000,8.895833333,2.120000000,NULL,81.617500000,4.983333333,3.000000000,584.251333300,72.268000000,24,2.284000000,515.136000000,12.641333330,1163.257000000,244.361000000,0.206333333,2.122333333,0.000000000,NULL,NULL,0.760000000,0.876666667,'1.486666667',7.456666667,13.106666670,NULL),
(55,1,'Pastel, de carne, cru',34.398333330,288.702071500,1207.929467000,10.740699640,8.793000000,18.200666670,42.116633690,1.140000000,4.151333333,16.691666670,17.705000000,55,0.343333333,117.376666700,1.991000000,1309.265000000,165.610333300,0.110000000,1.659000000,0.000000000,NULL,NULL,0.160000000,0.130000000,'0.0',1.580000000,0.000000000,'carne'),
(165,3,'Abacaxi, polpa, congelada',91.294666670,30.591799190,127.996087800,0.466666667,0.113333333,NULL,7.798666667,0.326666667,0.326666667,13.538000000,10.171333330,165,1.120666667,7.626000000,0.357333333,1.236333333,106.684333300,0.139000000,0.160333333,NULL,2.255555556,1.127777778,0.146666667,0.000000000,'0.0',0.000000000,1.246666667,NULL);
