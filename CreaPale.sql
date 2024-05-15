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
    nome VARCHAR(50),
    cognome VARCHAR(50),
    mail VARCHAR(100),
    eta INT,
    password VARCHAR(100),
    scad_abb DATE,
    PRIMARY KEY (nome, cognome, mail)
);
    
CREATE TABLE dipendenti (
	nome VARCHAR(50),
    cognome VARCHAR(50),
    mail VARCHAR(100),
    eta INT,
    password VARCHAR(100),
	tipo CHAR(30),
    stipendio double,
	PRIMARY KEY (nome, cognome, mail)
);

CREATE TABLE corsi (
    nome_corso VARCHAR(255),
    nome_corsista VARCHAR(255),
    cognome_corsista VARCHAR(255),
    max_iscritti INT,
    iscritti INT,
    FOREIGN KEY (nome_corsista, cognome_corsista) REFERENCES dipendenti(nome, cognome),
    PRIMARY KEY (nome_corso, nome_corsista, cognome_corsista)
);

CREATE TABLE iscrizioni_corsi (
    iscrizione_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_nome VARCHAR(50),
    cliente_cognome VARCHAR(50),
    cliente_mail VARCHAR(100),
    corso_nome VARCHAR(255),
    corso_istruttore_nome VARCHAR(50),
    corso_istruttore_cognome VARCHAR(50),
    FOREIGN KEY (cliente_nome, cliente_cognome, cliente_mail) REFERENCES clienti(nome, cognome, mail),
    FOREIGN KEY (corso_nome, corso_istruttore_nome, corso_istruttore_cognome) REFERENCES corsi(nome_corso, nome_corsista, cognome_corsista)
);

CREATE TABLE Schede (
    id_scheda INT PRIMARY KEY,
    esercizio1 VARCHAR(255),
    esercizio2 VARCHAR(255),
    esercizio3 VARCHAR(255),
    esercizio4 VARCHAR(255),
    esercizio5 VARCHAR(255)
);

CREATE TABLE richiestePT (
    id_richiesta INT AUTO_INCREMENT PRIMARY KEY,
    nome_pt VARCHAR(50),
    cognome_pt VARCHAR(50),
    nome_cliente VARCHAR(50),
    cognome_cliente VARCHAR(50),
    scheda_id INT,
    FOREIGN KEY (scheda_id) REFERENCES Schede(id_scheda),
    FOREIGN KEY (nome_pt, cognome_pt) REFERENCES Dipendenti(nome, cognome),
    FOREIGN KEY (nome_cliente, cognome_cliente) REFERENCES Clienti(nome, cognome)
);
