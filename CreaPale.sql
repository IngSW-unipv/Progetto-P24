use PALESTRA;

-- Elimina le iscrizioni ai corsi
DROP TABLE IF EXISTS iscrizioni_corsi;

-- Elimina le richiestePT
DROP TABLE IF EXISTS richiestePT;

-- Elimina le tabelle dei corsi
DROP TABLE IF EXISTS corsi;

-- Elimina le tabelle dei dipendenti
DROP TABLE IF EXISTS dipendenti;

-- Elimina le tabelle dei clienti
DROP TABLE IF EXISTS clienti;

-- Elimina le tabelle delle schede
DROP TABLE IF EXISTS schede;


-- cliente = (nome, cognome, mail) PRIMARY KEY, password, scadenza abbonamento
-- dipendente = (nome, cognome,mail) PRIMARY KEY, password, tipo

CREATE TABLE clienti (
    nome VARCHAR(100),
    cognome VARCHAR(100),
    mail VARCHAR(100),
    eta INT,
    password VARCHAR(100),
    scad_abb DATE,
    PRIMARY KEY (nome, cognome, mail)
);
    
CREATE TABLE dipendenti (
    nome VARCHAR(100),
    cognome VARCHAR(100),
    mail VARCHAR(100),
    eta INT,
    password VARCHAR(100),
    tipo CHAR(30),
    stipendio DOUBLE,
    PRIMARY KEY (nome, cognome, mail)
);

CREATE TABLE corsi (
    nome_corso VARCHAR(100),
    nome_corsista VARCHAR(100),
    cognome_corsista VARCHAR(100),
    mail_corsista VARCHAR(100),
    max_iscritti INT,
    iscritti INT,
    FOREIGN KEY (nome_corsista, cognome_corsista, mail_corsista) REFERENCES dipendenti(nome, cognome, mail),
    PRIMARY KEY (nome_corso, nome_corsista, cognome_corsista, mail_corsista)
);

CREATE TABLE iscrizioni_corsi (
    iscrizione_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_nome VARCHAR(100),
    cliente_cognome VARCHAR(100),
    cliente_mail VARCHAR(100),
    corso_nome VARCHAR(100),
    corso_istruttore_nome VARCHAR(100),
    corso_istruttore_cognome VARCHAR(100),
    corso_istruttore_mail VARCHAR(100),
    FOREIGN KEY (cliente_nome, cliente_cognome, cliente_mail) REFERENCES clienti(nome, cognome, mail),
    FOREIGN KEY (corso_nome, corso_istruttore_nome, corso_istruttore_cognome, corso_istruttore_mail) REFERENCES corsi(nome_corso, nome_corsista, cognome_corsista, mail_corsista)
);

CREATE TABLE Schede (
    id_scheda INT PRIMARY KEY,
    esercizio1 VARCHAR(100),
    esercizio2 VARCHAR(100),
    esercizio3 VARCHAR(100),
    esercizio4 VARCHAR(100),
    esercizio5 VARCHAR(100)
);

CREATE TABLE richiestePT (
    id_richiesta INT AUTO_INCREMENT PRIMARY KEY,
    nome_pt VARCHAR(100),
    cognome_pt VARCHAR(100),
    mail_pt VARCHAR(100),
    nome_cliente VARCHAR(100),
    cognome_cliente VARCHAR(100),
    mail_cliente VARCHAR(100),
    scheda_id INT,
    FOREIGN KEY (scheda_id) REFERENCES Schede(id_scheda),
    FOREIGN KEY (nome_pt, cognome_pt, mail_pt) REFERENCES Dipendenti(nome, cognome, mail),
    FOREIGN KEY (nome_cliente, cognome_cliente, mail_cliente) REFERENCES Clienti(nome, cognome, mail)
);

