create table administrateur(
    idAdmin varchar(10) primary key,
    email varchar(150),
    mdp varchar(300)
);


create table utilisateur(
    idUtilisateur varchar(10) primary key,
    email varchar(150),
    mdp varchar(300)
);
alter table utilisateur add region varchar(255)
alter table utilisateur add foreign key (region) references region(id_region)

create table token(
    idToken varchar(150),
    idAdmin varchar(10),
    dateValidite dateTime,
    token varchar(255),
    foreign key (idAdmin) references administrateur(idAdmin) on delete cascade
);

create table categorie(
    idCategorie varchar(10) primary key,
    label varchar(150)
);

create table sousCategorie(
    idSousCategorie varchar(10) primary key,
    label varchar(150),
    idCategorie varchar(10),
    foreign key (idCategorie) references categorie(idCategorie)
);

create table signalement(
    idSignalement varchar(10) primary key,
    dateSignalement dateTime,
    description varchar(150),
    idGroupement varchar(255),
    idUtilisateur varchar(10),
    region varchar(150),
    longitude float,
    latitude float,
    idSousCategorie varchar(10),

    nomImage varchar(150),
    foreign key (idUtilisateur) references utilisateur(idUtilisateur) on delete cascade ,
    foreign key (idSousCategorie) references sousCategorie(idSousCategorie) on delete cascade
);

create table groupement (
    idGroupement varchar(10) primary key,
    description varchar(150),
    region varchar(150),
    longitude float,
    latitude float,
    etat varchar(25),
    nomImage varchar(10)
);

create table detailGroupement(
    id_groupement varchar(255),
    id_signalement varchar(255),
    foreign key (id_groupement) references groupement(id_groupement) on delete cascade,
    foreign key (id_signalement) references signalement(id_signalement) on delete cascade
);

create table region(
    idRegion varchar(255) primary key,
    nom varchar(255)
);


insert into region values (1,'Diana');
insert into region values (2,'Sava');
insert into region values (3,'Itasy');
insert into region values (4,'Analamanga');
insert into region values (5,'Vakinankaratra');
insert into region values (6,'Bongolava');
insert into region values (7,'Sofia');
insert into region values (8,'Boeny');
insert into region values (9,'Betsiboka');
insert into region values (10,'Melaky');
insert into region values (11,'Alaotra-Mangoro');
insert into region values (12,'Atsinanana');
insert into region values (13,'Analanjirofo');
insert into region values (14,'Amoron i Mania');
insert into region values (15,'Haute Matsiatra');
insert into region values (16,'Vatovavy-Fitovinany');
insert into region values (17,'Atsimo-Atsinanana');
insert into region values (18,'Ihorombe');
insert into region values (19,'Menabe');
insert into region values (20,'Atsimo-Andrefana');
insert into region values (21,'Androy');
insert into region values (22,'Anosy');

ALTER TABLE token ADD FOREIGN KEY (id_admin) references administrateur(id_admin) 
ALTER TABLE sous_categorie ADD FOREIGN KEY (id_categorie) references categorie(id_categorie) 
ALTER TABLE signalement ADD FOREIGN KEY (id_utilisateur) references utilisateur(id_utilisateur) 
ALTER TABLE signalement ADD FOREIGN KEY (id_sous_categorie) references sous_categorie(id_sous_categorie) 
ALTER TABLE signalement ADD FOREIGN KEY (region) references region(idRegion) 
ALTER TABLE signalement ADD FOREIGN KEY (id_groupement) references groupement(id_groupement)


drop table detailGroupement;
drop table groupement;
drop table signalement;

create view detailsSignalement as (
select s.id_signalement as idSignalement,
s.date_signalement as dateSignalement,
s.description,
s.latitude,
s.longitude,
s.nom_image as nomImage,
r.nom as nomRegion,
sc.label as nomSousCat,
c.label as nomCat,
u.email 
from signalement s join sous_categorie sc on s.id_sous_categorie=sc.id_sous_categorie
join categorie c on c.id_categorie=sc.id_categorie
join utilisateur u on u.id_utilisateur=s.id_utilisateur
outer join region r on r.id_region = s.region
);

insert into utilisateur values ('U1','u1@gmail.com','123');

insert into signalement values (1,GETDATE(),'lalana simba',1,'SC1','U1',1800,1450,null,4);
insert into signalement values (2,GETDATE(),'trano may',2,'SC2','U1',1800,1450,null,4);
insert into signalement values (3,GETDATE(),'tondra-drano',3,'SC3','U1',1800,1450,null,4);
insert into signalement values (4,GETDATE(),'faty olona',4,'SC4','U1',1800,1450,null,4);

insert into signalement values (5,GETDATE(),'poto tapaka',5,'SC1','U1',1800,1450,null,4);
insert into signalement values (6,GETDATE(),'lalana simba be',6,'SC1','U1',1800,1450,null,4);
insert into signalement values (7,GETDATE(),'trano rodana',7,'SC2','U1',1800,1450,null,2);
insert into signalement values (8,GETDATE(),'olona nisy namono',8,'SC4','U1',1800,1450,null,6);
insert into signalement values (9,GETDATE(),'agression à domicile',9,'SC4','U1',1800,1450,null,6);
insert into signalement values (10,GETDATE(),'suicide',10,'SC4','U1',1800,1450,null,5);
insert into signalement values (11,GETDATE(),'accident de voiture',11,'SC5','U1',1800,1450,null,4);
insert into signalement values (12,GETDATE(),'camion latsaka anaty hantsana',12,'SC5','U1',1800,1450,null,12);
insert into signalement values (13,GETDATE(),'doro tanety',13,'SC3','U1',1800,1450,null,5);
insert into signalement values (14,GETDATE(),'doro tanety',14,'SC3','U1',1800,1450,null,7);
insert into signalement values (15,GETDATE(),'famonoana biby an''ala',15,'SC3','U1',1800,1450,null,6);


insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'lalana simba','1',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'trano may','1',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'tondra-drano','0',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'famonoana olona','0',1800,1450,null,4);

insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'poto tapaka','1',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'lalana simba be','0',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'trano rodana','1',1800,1450,null,2);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'olona nisy namono','0',1800,1450,null,6);
insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'agression à domicile','1',1800,1450,null,6);
insert into Groupement values (NEXT VALUE FOR seq_groupement,GETDATE(),'suicide','1',1800,1450,null,5);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'accident de voiture','0',1800,1450,null,4);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'camion latsaka anaty hantsana','0',1800,1450,null,12);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'doro tanety','0',1800,1450,null,5);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'doro tanety','0',1800,1450,null,7);
insert into Groupement values (NEXT VALUE FOR seq_groupement,null,'famonoana biby an''ala','0',1800,1450,null,6);

insert into categorie values ('C1','infrastructure');
insert into categorie values ('C2','evenement');

insert into sous_categorie values ('SC1', 'C1', 'route');
insert into sous_categorie values ('SC2', 'C1', 'batiment');
insert into sous_categorie values ('SC3', 'C2', 'pollution');
insert into sous_categorie values ('SC4', 'C2', 'violence');
insert into sous_categorie values ('SC5', 'C2', 'accident');

select region,count(*) as nb from signalement where region is not null group by region order by nb DESC;

--top 4
 
 create view top4RegionSignalement as (
    select region,nb from
    (select TOP 4  r.nom as region,count(s.region) as nb from groupement s
    join region r on r.id_region=s.region
    where s.region is not null group by s.region,r.nom order by nb DESC) first
    union all
    select 'autre' as region, sum(nb) as taux  from
    (select region,count(*) as nb,row_number() over (Order by count(*) desc) as rn from groupement
    where region is not null group by region)
    as last where rn>4
 );

create view statSousCategorie as (
    select sc.label, count(s.id_sous_categorie) nb from signalement s
    join sous_categorie sc on sc.id_sous_categorie=s.id_sous_categorie
    group by s.id_sous_categorie, sc.label
);

create view statSignalementParRegion as (
    select s.region as idRegion,r.nom,count(s.region) as nb from signalement s
    join region r on r.id_region=s.region
    where s.region is not null group by s.region,r.nom 
);

-- CREATE MASTER KEY ENCRYPTION BY PASSWORD = 'motdepasse'


CREATE CERTIFICATE SignalementCertificate69   
   ENCRYPTION BY PASSWORD = 'motdepasse'  
   WITH SUBJECT = 'Signalement Records',   
   EXPIRY_DATE = '20221231';  

CREATE SYMMETRIC KEY Signal69   
WITH ALGORITHM = AES_256  
ENCRYPTION BY CERTIFICATE SignalementCertificate69; 


OPEN SYMMETRIC KEY Signal69
   DECRYPTION BY CERTIFICATE SignalementCertificate69 WITH PASSWORD = 'motdepasse';

-- ALTER CERTIFICATE SignalementCertificate69
--     WITH PRIVATE KEY (DECRYPTION BY PASSWORD = 'motdepasse');

create view viewUtil as (select u.id_utilisateur 
as idUtilisateur,u.email,u.mdp,r.id_region as 
idRegion,r.nom as nomRegion 
from utilisateur u join region r on r.id_region=u.region)