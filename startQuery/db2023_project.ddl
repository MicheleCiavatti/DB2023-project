-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Mon May 15 22:00:09 2023 
-- * LUN file: C:\Users\miche\OneDrive - Alma Mater Studiorum Università di Bologna\Basi di Dati\Progetto\Progettazione concettuale\Progetto Basi di Dati.lun 
-- * Schema: db2023_dnd/1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database db2023_dnd;
use db2023_dnd;


-- Tables Section
-- _____________ 

create table ABILITA (
     tipoAbilità enum ('attiva', 'passiva') not null,
     descAbilità varchar(255) not null,
     nomeAbilità varchar(50) not null,
     constraint IDABILITA_ID primary key (nomeAbilità));

create table CAMPAGNE (
     NumeroGiocatori tinyint not null,
     nomeCampagna varchar(50) not null,
     constraint IDCAMPAGNE primary key (nomeCampagna));

create table CLASSI (
     nomeClasse varchar(50) not null,
     descClasse varchar(255) not null,
     constraint IDCLASSI_ID primary key (nomeClasse));

create table COMPARSE (
     nomeCampagna varchar(50) not null,
     codProgressivo int not null,
     nomeNPC varchar(50) not null,
     tipoRapporto enum ('positivo', 'negativo', 'neutro') not null,
     constraint IDCOMPARSE primary key (nomeNPC, nomeCampagna, codProgressivo));

create table COMPOSIZIONI (
     nomeProtagonista varchar(50) not null,
     codParty varchar(20) not null,
     constraint IDCOMPOSIZIONI primary key (codParty, nomeProtagonista));

create table MOSTRI (
     nomeMostro varchar(50) not null,
     immagineMostro blob,
     constraint IDMOSTRI primary key (nomeMostro));

create table NPCs (
     tipoInterazione enum ('positivo', 'negativo', 'neutro') not null,
     nomeNPC varchar(50) not null,
     immagineNPC blob,
     descrizionePersonalità varchar(255) not null,
     nomeCampagna varchar(50) not null,
     constraint IDNPCs_ID primary key (nomeNPC));

create table OFF_CLASSI (
     nomeClasse varchar(50) not null,
     nomeAbilità varchar(50) not null,
     constraint IDOFF_CLASSI primary key (nomeAbilità, nomeClasse));

create table OFF_RAZZE (
     nomeAbilità varchar(50) not null,
     nomeRazza varchar(50) not null,
     constraint IDOFF_RAZZE primary key (nomeAbilità, nomeRazza));

create table OFF_SOTTOCLASSI (
     nomeClasse varchar(50) not null,
     numeroSottoclasse tinyint not null,
     nomeAbilità varchar(50) not null,
     constraint IDOFF_SOTTOCLASSI primary key (nomeAbilità, nomeClasse, numeroSottoclasse));

create table OGGETTI (
     tipoOggetto enum ('arma', 'catalizzatore', 'consumabile', 'altro') not null,
     numUtilizzi tinyint,
     nomeOggetto varchar(50) not null,
     descOggetto varchar(255) not null,
     constraint IDOGGETTI primary key (nomeOggetto));

create table PART_MOSTRI (
     numeroTurno tinyint not null,
     nomeCampagna varchar(50) not null,
     codProgressivo int not null,
     nomeMostro varchar(50) not null,
     dannoMostro int not null,
     constraint IDPART_MOSTRI primary key (numeroTurno, nomeCampagna, codProgressivo, nomeMostro));

create table PART_PROTAGONISTI (
     numeroTurno tinyint not null,
     nomeCampagna varchar(50) not null,
     codProgressivo int not null,
     nomeProtagonista varchar(50) not null,
     dannoProtagonista int not null,
     constraint IDPART_PROTAGONISTI primary key (numeroTurno, nomeCampagna, codProgressivo, nomeProtagonista));

create table PARTIES (
     codParty varchar(20) not null,
     constraint IDPARTIES_ID primary key (codParty));

create table PROPRIETA (
     nomeOggetto varchar(50) not null,
     nomeProtagonista varchar(50) not null,
     quantità tinyint not null,
     constraint IDPROPRIETA primary key (nomeOggetto, nomeProtagonista));

create table PROTAGONISTI (
     immagineProtagonista blob,
     nomeProtagonista varchar(50) not null,
     oro int not null,
     livello tinyint not null,
     nomeClasse varchar(50) not null,
     numeroSottoclasse tinyint not null,
     nomeRazza varchar(50) not null,
     constraint IDPROTAGONISTI primary key (nomeProtagonista));

create table RAZZE (
     nomeRazza varchar(50) not null,
     descRazza varchar(255) not null,
     constraint IDRAZZA_ID primary key (nomeRazza));

create table SESSIONI (
     nomeCampagna varchar(50) not null,
     codProgressivo int not null,
     codParty varchar(20) not null,
     constraint IDSESSIONI_ID primary key (nomeCampagna, codProgressivo));

create table SOTTOCLASSI (
     nomeClasse varchar(50) not null,
     numeroSottoclasse tinyint not null,
     descSottoclasse varchar(255) not null,
     constraint IDSOTTOCLASSI_ID primary key (nomeClasse, numeroSottoclasse));

create table TURNI (
     numeroTurno tinyint not null,
     nomeCampagna varchar(50) not null,
     codProgressivo int not null,
     constraint IDTURNI primary key (numeroTurno, nomeCampagna, codProgressivo));


-- Constraints Section
-- ___________________ 

-- Not implemented
-- alter table ABILITA add constraint IDABILITA'_CHK
--     check(exists(select * from OFF_RAZZE
--                  where OFF_RAZZE.nomeAbilità = nomeAbilità)); 

-- Not implemented
-- alter table ABILITA add constraint IDABILITA'_CHK
--     check(exists(select * from OFF_CLASSI
--                  where OFF_CLASSI.nomeAbilità = nomeAbilità)); 

-- Not implemented
-- alter table ABILITA add constraint IDABILITA'_CHK
--     check(exists(select * from OFF_SOTTOCLASSI
--                  where OFF_SOTTOCLASSI.nomeAbilità = nomeAbilità)); 

-- Not implemented
-- alter table CLASSI add constraint IDCLASSE_CHK
--     check(exists(select * from OFF_CLASSI
--                  where OFF_CLASSI.nomeClasse = nomeClasse)); 

alter table COMPARSE add constraint FKCOM_NPC
     foreign key (nomeNPC)
     references NPCs (nomeNPC);

alter table COMPARSE add constraint FKCOM_SES
     foreign key (nomeCampagna, codProgressivo)
     references SESSIONI (nomeCampagna, codProgressivo);

alter table COMPOSIZIONI add constraint FKCOM_PAR
     foreign key (codParty)
     references PARTIES (codParty);

alter table COMPOSIZIONI add constraint FKCOM_PRO
     foreign key (nomeProtagonista)
     references PROTAGONISTI (nomeProtagonista);

-- Not implemented
-- alter table NPCs add constraint IDNPC_CHK
--     check(exists(select * from COMPARSE
--                  where COMPARSE.nomeNPC = nomeNPC)); 

alter table NPCs add constraint FKPRESENZA
     foreign key (nomeCampagna)
     references CAMPAGNE (nomeCampagna);

alter table OFF_CLASSI add constraint FKOFF_ABI
     foreign key (nomeAbilità)
     references ABILITA (nomeAbilità);

alter table OFF_CLASSI add constraint FKOFF_CLA
     foreign key (nomeClasse)
     references CLASSI (nomeClasse);

alter table OFF_RAZZE add constraint FKOFF_RAZ
     foreign key (nomeRazza)
     references RAZZE (nomeRazza);

alter table OFF_RAZZE add constraint FKOFF_AB
     foreign key (nomeAbilità)
     references ABILITA (nomeAbilità);

alter table OFF_SOTTOCLASSI add constraint FKOFF_AI
     foreign key (nomeAbilità)
     references ABILITA (nomeAbilità);

alter table OFF_SOTTOCLASSI add constraint FKOFF_SOT
     foreign key (nomeClasse, numeroSottoclasse)
     references SOTTOCLASSI (nomeClasse, numeroSottoclasse);

alter table PART_MOSTRI add constraint FKR
     foreign key (numeroTurno, nomeCampagna, codProgressivo)
     references TURNI (numeroTurno, nomeCampagna, codProgressivo);

alter table PART_MOSTRI add constraint FKR_1
     foreign key (nomeMostro)
     references MOSTRI (nomeMostro);

alter table PART_PROTAGONISTI add constraint FKR_2
     foreign key (numeroTurno, nomeCampagna, codProgressivo)
     references TURNI (numeroTurno, nomeCampagna, codProgressivo);

alter table PART_PROTAGONISTI add constraint FKR__3
     foreign key (nomeProtagonista)
     references PROTAGONISTI (nomeProtagonista);

-- Not implemented
-- alter table PARTIES add constraint IDPARTY_CHK
--     check(exists(select * from COMPOSIZIONI
--                  where COMPOSIZIONI.codParty = codParty)); 

alter table PROPRIETA add constraint FKPRO_PRO
     foreign key (nomeProtagonista)
     references PROTAGONISTI (nomeProtagonista);

alter table PROPRIETA add constraint FKPRO_OGG
     foreign key (nomeOggetto)
     references OGGETTI (nomeOggetto);

alter table PROTAGONISTI add constraint FKRUOLO
     foreign key (nomeClasse, numeroSottoclasse)
     references SOTTOCLASSI (nomeClasse, numeroSottoclasse);

alter table PROTAGONISTI add constraint FKGENEALOGIA
     foreign key (nomeRazza)
     references RAZZE (nomeRazza);

-- Not implemented
-- alter table RAZZE add constraint IDRAZZA_CHK
--     check(exists(select * from OFF_RAZZE
--                  where OFF_RAZZE.nomeRazza = nomeRazza)); 

-- Not implemented
-- alter table SESSIONI add constraint IDSESSIONE_CHK
--     check(exists(select * from TURNI
--                  where TURNI.nomeCampagna = nomeCampagna and TURNI.codProgressivo = codProgressivo)); 

alter table SESSIONI add constraint FKSUDDIVISIONE
     foreign key (nomeCampagna)
     references CAMPAGNE (nomeCampagna);

alter table SESSIONI add constraint FKPARTITA
     foreign key (codParty)
     references PARTIES (codParty);

-- Not implemented
-- alter table SOTTOCLASSI add constraint IDSOTTOCLASSE_CHK
--     check(exists(select * from OFF_SOTTOCLASSI
--                  where OFF_SOTTOCLASSI.nomeClasse = nomeClasse and OFF_SOTTOCLASSI.numeroSottoclasse = numeroSottoclasse)); 

alter table SOTTOCLASSI add constraint FKSUBALTERNO
     foreign key (nomeClasse)
     references CLASSI (nomeClasse);

alter table TURNI add constraint FKINCONTRO
     foreign key (nomeCampagna, codProgressivo)
     references SESSIONI (nomeCampagna, codProgressivo);


-- Index Section
-- _____________ 

