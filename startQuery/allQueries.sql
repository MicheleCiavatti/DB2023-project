insert into campagne
values (6, 'Principi dell\' apocalisse');

insert into campagne
values (6, 'La maledizione di Straad');

insert into classi
values ('Paladino', 'Un guerriero potenziato dai miracoli garantiti dalla sua divinità');

insert into classi
values ('Ranger', 'Un esploratore capace di inseguire ovunque i suoi nemici');

insert into classi
values ('Stregone', 'Un fattucchiere naturalmente portato alla magia');

insert into classi
values ('Bardo', 'Un menestrello che aiuta la sua squadra nei momenti più difficili');

insert into classi
values ('Barbaro', 'Un brutale guerriero preda della sua stessa ira');

insert into oggetti (tipoOggetto, nomeOggetto, descOggetto)
values ('arma', 'Spada lunga', 'Dotazione di base');

insert into oggetti (tipoOggetto, nomeOggetto, descOggetto)
values ('arma', 'Spada a due mani', 'Arma pesante ma efficace');

insert into oggetti (tipoOggetto, nomeOggetto, descOggetto)
values ('arma', 'Martello da guerra', 'Brutale e terrificante');

insert into oggetti (tipoOggetto, nomeOggetto, descOggetto)
values ('catalizzatore', 'Bastone', 'Usato dai maghi per castare magie');

insert into oggetti (tipoOggetto, nomeOggetto, descOggetto)
values ('catalizzatore', 'Sigillo', 'Usato dai fedeli per castare miracoli');

insert into oggetti 
values ('consumabile', 1, 'Pozione di cura', 'Cura gli avventurieri');

insert into oggetti 
values ('consumabile', 1, 'Freccia', 'Per attaccare a distanza');

INSERT INTO parties
VALUES ("845kdg");

insert into parties
values ("72jft");

insert into parties
values ("ouie94740");

insert into mostri(nomeMostro)
values ("Aboleth");

insert into mostri(nomeMostro)
values("Cavaliere di Feathergale");

insert into sottoclassi
values ('Paladino', 1, 'Antichi');

insert into sottoclassi
values ('Paladino', 2, 'Conquista');

insert into sottoclassi
values ('Paladino', 3, 'Corona');

insert into sottoclassi
values ('Stregone', 1, 'Mente aberrante');

insert into sottoclassi
values ('Stregone', 2, 'Eredità draconica');

insert into sottoclassi
values ('Stregone', 3, 'Anima divina9');

update sottoclassi
set descSottoclasse = 'Anima divina'
where nomeClasse = 'Stregone' and numeroSottoclasse = 3;

insert into sottoclassi
values ('Barbaro', 1, 'Guardia ancestrale');

insert into sottoclassi
values ('Barbaro', 2, 'Furia da battaglia');

insert into sottoclassi
values ('Barbaro', 3, 'Berserker');

insert into sottoclassi
values ('Bardo', 1, 'Creazione');

insert into sottoclassi
values ('Bardo', 2, 'Eloquenza');

insert into sottoclassi
values ('Bardo', 3, 'Glamour');

insert into sottoclassi
values ('Ranger', 1, 'Maestro di bestie');

insert into sottoclassi
values ('Ranger', 2, 'Cacciatore');

insert into sottoclassi
values ('Ranger', 3, 'Ammazzamostri');

insert into sottoclassi
values ('Paladino', 1, 'Antichi');

insert into sottoclassi
values ('Paladino', 2, 'Conquista');

insert into sottoclassi
values ('Paladino', 3, 'Corona');

insert into sottoclassi
values ('Stregone', 1, 'Mente aberrante');

insert into sottoclassi
values ('Stregone', 2, 'Eredità draconica');

insert into sottoclassi
values ('Stregone', 3, 'Anima divina9');

update sottoclassi
set descSottoclasse = 'Anima divina'
where nomeClasse = 'Stregone' and numeroSottoclasse = 3;

insert into sottoclassi
values ('Barbaro', 1, 'Guardia ancestrale');

insert into sottoclassi
values ('Barbaro', 2, 'Furia da battaglia');

insert into sottoclassi
values ('Barbaro', 3, 'Berserker');

insert into sottoclassi
values ('Bardo', 1, 'Creazione');

insert into sottoclassi
values ('Bardo', 2, 'Eloquenza');

insert into sottoclassi
values ('Bardo', 3, 'Glamour');

insert into sottoclassi
values ('Ranger', 1, 'Maestro di bestie');

insert into sottoclassi
values ('Ranger', 2, 'Cacciatore');

insert into sottoclassi
values ('Ranger', 3, 'Ammazzamostri');

insert into razze
values ('Umano', 'Bravo in tutto');

insert into razze
values ('Elfo', 'Nobili e antichi');

insert into razze
values ('Nano', 'Bassi e muscolosi');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Blue', 200, 3, 'Stregone', 2, 'Umano');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Turin', 300, 3, 'Paladino', 1, 'Umano');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Hop', 400, 3, 'Bardo', 3, 'Elfo');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Garreth', 100, 3, 'Ranger', 2, 'Nano');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Grukka', 20, 4, 'Barbaro', 3, 'Nano');

insert into protagonisti (nomeProtagonista, oro, livello, nomeClasse, numeroSottoclasse, nomeRazza)
values ('Savra', 500, 3, 'Paladino', 1, 'Umano');

insert into composizioni
values ('Blue', '72jft');

insert into composizioni
values ('Garreth', '72jft');

insert into composizioni
values ('Turin', '72jft');

insert into composizioni
values ('Blue', '845kdg');

insert into npcs (tipoInterazione, nomeNPC, descrizionePersonalità, nomeCampagna)
VALUES ('positivo', 'Vallivoe', 'Un venditore di cianfrusaglie residente a Red Larch', 'Principi dell\' apocalisse');

insert into turni
values (1, "La maledizione di Straad", 1);

insert into turni
values (2, "La maledizione di Straad", 1);

insert into turni
values (3, "La maledizione di Straad", 1);


insert into part_protagonisti
values (1, "La maledizione di Straad", 1, "Garreth", 10);

insert into part_protagonisti
values (2, "La maledizione di Straad", 1, "Garreth", 20);

insert into part_protagonisti
values (3, "La maledizione di Straad", 1, "Garreth", 30);
