use palestra;

INSERT INTO clienti (nome, cognome, mail, eta, password, scad_abb) VALUES
('Mario', 'Rossi', 'mario.rossi@example.com', 18, 'password123', '2024-06-30'),
('Luca', 'Bianchi', 'luca.bianchi@example.com', 25, 'securepass', '2024-08-15'),
('Giovanna', 'Verdi', 'giovanna.verdi@example.com', 28, 'password456', '2024-07-20'),
('Giuseppe', 'Neri', 'giuseppe.neri@example.com', 35, 'mysecret', '2024-09-10'),
('Anna', 'Gialli', 'anna.gialli@example.com', 21, 'p@ssw0rd', '2024-08-25'),
('Alessia', 'Marroni', 'alessia.marroni@example.com', 31, 'topsecret', '2024-07-05'),
('Paolo', 'Arancioni', 'paolo.arancioni@example.com', 27, 'pa$$w0rd', '2024-08-30'),
('Eleonora', 'Rosa', 'eleonora.rosa@example.com', 32, 'securepwd', '2024-09-05'),
('Marco', 'Blu', 'marco.blu@example.com', 23, 'password789', '2024-07-15'),
('Federica', 'Gialli', 'federica.gialli@example.com', 26, 'password!23', '2024-06-20');

INSERT INTO Dipendenti (nome, cognome, mail, eta, password, tipo, stipendio) VALUES
('Luigi', 'Verdi', 'luigi.verdi@example.com', 32, 'password123', 'personaltrainer', 2500.00),
('Maria', 'Rossi', 'maria.rossi@example.com', 23, 'securepass', 'corsista', 1000.00),
('Giovanni', 'Bianchi', 'giovanni.bianchi@example.com', 35, 'password456', 'dietista', 3000.00),
('Roberta', 'Neri', 'roberta.neri@example.com', 30, 'mysecret', 'istruttoredisala', 2200.00),
('Laura', 'Gialli', 'laura.gialli@example.com', 27, 'p@ssw0rd', 'dietista', 3200.00),
('Antonio', 'Marroni', 'antonio.marroni@example.com', 31, 'topsecret', 'personaltrainer', 2600.00),
('Anna', 'Verdi', 'anna.verdi@example.com', 28, 'password123', 'corsista', 1100.00),
('Marco', 'Bianchi', 'marco.bianchi@example.com', 26, 'securepass', 'corsista', 1050.00),
('Chiara', 'Neri', 'chiara.neri@example.com', 30, 'password456', 'corsista', 1200.00);


INSERT INTO corsi (nome_corso, nome_corsista, cognome_corsista, mail_corsista, max_iscritti, iscritti) VALUES 
('Zumba', 'Anna', 'Verdi', 'anna.verdi@example.com', 15, 0),
('Pilates', 'Marco', 'Bianchi', 'marco.bianchi@example.com', 20, 0),
('Fitness', 'Chiara', 'Neri', 'chiara.neri@example.com', 18, 0),
('Yoga', 'Maria', 'Rossi', 'maria.rossi@example.com', 15, 0);


INSERT INTO iscrizioni_corsi (cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome, corso_istruttore_mail)
VALUES ('Mario', 'Rossi', 'mario.rossi@example.com', 'Zumba', 'Anna', 'Verdi', 'anna.verdi@example.com');

-- Incrementa di uno il numero di iscritti al corso di Zumba
UPDATE corsi SET iscritti = iscritti + 1 WHERE nome_corso = 'Zumba';


INSERT INTO iscrizioni_corsi (cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome, corso_istruttore_mail)
VALUES ('Luca', 'Bianchi', 'luca.bianchi@example.com', 'Pilates', 'Marco', 'Bianchi', 'marco.bianchi@example.com');

-- Incrementa di uno il numero di iscritti al corso di Pilates
UPDATE corsi SET iscritti = iscritti + 1 WHERE nome_corso = 'Pilates';

-- Iscrizione di Alessia al corso di Fitness
INSERT INTO iscrizioni_corsi (cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome, corso_istruttore_mail)
VALUES ('Alessia', 'Marroni', 'alessia.marroni@example.com', 'Fitness', 'Chiara', 'Neri', 'chiara.neri@example.com');

-- Incrementa di uno il numero di iscritti al corso di Fitness
UPDATE corsi SET iscritti = iscritti + 1 WHERE nome_corso = 'Fitness';

-- Iscrizione di Alessia al corso di Zumba
INSERT INTO iscrizioni_corsi (cliente_nome, cliente_cognome, cliente_mail, corso_nome, corso_istruttore_nome, corso_istruttore_cognome, corso_istruttore_mail)
VALUES ('Alessia', 'Marroni', 'alessia.marroni@example.com', 'Zumba', 'Anna', 'Verdi', 'anna.verdi@example.com');

-- Incrementa di uno il numero di iscritti al corso di Zumba
UPDATE corsi SET iscritti = iscritti + 1 WHERE nome_corso = 'Zumba';

INSERT INTO Schede (id_scheda, esercizio1, esercizio2, esercizio3, esercizio4, esercizio5) VALUES 
(1, 'Flessioni', 'Addominali', 'Squat', 'Affondi', 'Curl con manubri'),
(2, 'Panca piana', 'Plank', 'Rematore', 'Stacchi da terra', 'Affondi statici'),
(3, 'Push-up', 'Mountain climbers', 'Curl con bilanciere', 'Stacchi rumeni', 'Crunches');

INSERT INTO richiestePT (nome_pt, cognome_pt, mail_pt, nome_cliente, cognome_cliente, mail_cliente, scheda_id) VALUES 
('Luigi', 'Verdi', 'luigi.verdi@example.com', 'Mario', 'Rossi', 'mario.rossi@example.com', null), 
('Luigi', 'Verdi', 'luigi.verdi@example.com', 'Luca', 'Bianchi', 'luca.bianchi@example.com', 1), 
('Antonio', 'Marroni', 'antonio.marroni@example.com', 'Alessia', 'Marroni', 'alessia.marroni@example.com', 2);

