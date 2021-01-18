
-- Storing aisles (4 aisles)

insert into aisle(name,packing_station_id) values('a1',1);
insert into aisle(name,packing_station_id) values('a2',2);
insert into aisle(name,packing_station_id) values('a3',3);
insert into aisle(name,packing_station_id) values('a4',4);


-- Storing sections regards to Aisle

insert into section(section_Id,aisle_id) values('a2.0',2);
insert into section(section_Id,aisle_id) values('a2.1',2);
insert into section(section_Id,aisle_id) values('a2.2',2);
insert into section(section_Id,aisle_id) values('a2.3',2);
insert into section(section_Id,aisle_id) values('a2.4',2);


insert into section(section_Id,aisle_id) values('a1.0',1);
insert into section(section_Id,aisle_id) values('a1.1',1);
insert into section(section_Id,aisle_id) values('a1.2',1);
insert into section(section_Id,aisle_id) values('a1.3',1);
insert into section(section_Id,aisle_id) values('a1.4',1);


insert into section(section_Id,aisle_id) values('a3.0',3);
insert into section(section_Id,aisle_id) values('a3.1',3);
insert into section(section_Id,aisle_id) values('a3.2',3);
insert into section(section_Id,aisle_id) values('a3.3',3);
insert into section(section_Id,aisle_id) values('a3.4',3);

insert into section(section_Id,aisle_id) values('a4.0',4);
insert into section(section_Id,aisle_id) values('a4.1',4);
insert into section(section_Id,aisle_id) values('a4.2',4);
insert into section(section_Id,aisle_id) values('a4.3',4);
insert into section(section_Id,aisle_id) values('a4.4',4);

-- Storing packing stations

insert into packing_station(name) values('PA1');
insert into packing_station(name) values('PA2');
insert into packing_station(name) values('PA3');
insert into packing_station(name) values('PA4');

-- Queries for populate shelves data using sections which shelves are organized

insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 1),1);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 1),1);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 2),2);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 2),2);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 3),3);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 3),3);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 4),4);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 4),4);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 5),5);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 5),5);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 6),6);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 6),6);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 7),7);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 7),7);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 8),8);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 8),8);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 9),9);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 9),9);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 10),10);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 10),10);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 11),11);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 11),11);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 12),12);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 12),12);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 13),13);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 13),13);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 14),14);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 14),14);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 15),15);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 15),15);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 16),16);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 16),16);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 17),17);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 17),17);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 18),18);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 18),18);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 19),19);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 19),19);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/A') from section where id = 20),20);
insert into shelf (label,section_id) values((select CONCAT(section_id,'/B') from section where id = 20),20);

-- Product Catalogue

-- Real Products - packed as per the popularity and based on higher sales
insert into item(product_id,name,weight,supplier,shelf_id) values('kitkat','Kit Kat','2','Nestle',1);
insert into item(product_id,name,weight,supplier,shelf_id) values('chocola','Choco La','1','Ritzbury',2);
insert into item(product_id,name,weight,supplier,shelf_id) values('munchiscc','Munchi Super Cream Cracker','3','Munchi',3);
insert into item(product_id,name,weight,supplier,shelf_id) values('promises','Promises','2','Kandos',4);
insert into item(product_id,name,weight,supplier,shelf_id) values('sbread','Slide Bread','4','Keels',5);
insert into item(product_id,name,weight,supplier,shelf_id) values('sfbread','Flavoured Slide Bread','2','keels',6);
insert into item(product_id,name,weight,supplier,shelf_id) values('abutter','Astra Butter','1','Unilever',7);
insert into item(product_id,name,weight,supplier,shelf_id) values('mbutter','Medoli','1','Medoli',8);
insert into item(product_id,name,weight,supplier,shelf_id) values('coke','Coca Cola','4','CocaCola',9);
insert into item(product_id,name,weight,supplier,shelf_id) values('sprite','Sprite','4','Sprite',10);
insert into item(product_id,name,weight,supplier,shelf_id) values('feggs','Farm Eggs Pack','2','Central Farm',11);
insert into item(product_id,name,weight,supplier,shelf_id) values('fchickan','Farm Chickan','5','Crisbo',12);
insert into item(product_id,name,weight,supplier,shelf_id) values('wsuger','White Suger','3','CeylonSuger',13);
insert into item(product_id,name,weight,supplier,shelf_id) values('bsuger','Brown Suger','3','CeylonSuger',14);
insert into item(product_id,name,weight,supplier,shelf_id) values('cteapowder','Ceylonta Tea','2','Ceylonta',15);
insert into item(product_id,name,weight,supplier,shelf_id) values('kteapowder','Kotagala Tea','2','Kotagala',16);
insert into item(product_id,name,weight,supplier,shelf_id) values('redrice','Red Rice','10','Araliya',17);
insert into item(product_id,name,weight,supplier,shelf_id) values('whiterice','White Rice','10','Nipuna',18);
insert into item(product_id,name,weight,supplier,shelf_id) values('basmathi','Basmathi','15','Araliya',19);
insert into item(product_id,name,weight,supplier,shelf_id) values('dhal','Dhal Budget Pack','4','Mayisoor',20);

--  Dummy Products
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode21','Product21','21','supplier21',21);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode22','Product22','22','supplier22',22);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode23','Product23','23','supplier23',23);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode24','Product24','24','supplier24',24);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode25','Product25','25','supplier25',25);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode26','Product26','26','supplier26',26);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode27','Product27','27','supplier27',27);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode28','Product28','28','supplier28',28);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode29','Product29','29','supplier29',29);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode30','Product30','30','supplier30',30);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode31','Product31','31','supplier31',31);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode32','Product32','32','supplier32',32);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode33','Product33','33','supplier33',33);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode34','Product34','34','supplier34',34);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode35','Product35','35','supplier35',35);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode36','Product36','36','supplier36',36);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode37','Product37','37','supplier37',37);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode38','Product38','38','supplier38',38);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode39','Product39','39','supplier39',39);
insert into item(product_id,name,weight,supplier,shelf_id) values('prcode40','Product40','40','supplier40',40);


-- Storing worker data
insert into worker(name,type,age,capacity,is_available) values('bob','PI',25,20,1);
insert into worker(name,type,age,capacity,is_available) values('rem','PI',45,10,1);
insert into worker(name,type,age,capacity,is_available) values('steve','PA',25,20,1);
insert into worker(name,type,age,capacity,is_available) values('john','PI',35,25,0);

insert into worker(name,type,age,capacity,is_available) values('jenifer','PI',15,20,0);
insert into worker(name,type,age,capacity,is_available) values('Singer','PA',45,10,0);
insert into worker(name,type,age,capacity,is_available) values('Ajay','PA',25,20,1);
insert into worker(name,type,age,capacity,is_available) values('Kingston','PI',35,25,1);
insert into worker(name,type,age,capacity,is_available) values('Max','PI',15,20,0);
insert into worker(name,type,age,capacity,is_available) values('Dexter','PI',15,10,1);
insert into worker(name,type,age,capacity,is_available) values('Jonny','PI',5,20,1);
insert into worker(name,type,age,capacity,is_available) values('Morgan','PI',45,25,1);