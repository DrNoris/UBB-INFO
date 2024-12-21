CREATE TABLE Bucatari(
    id_bucatar INT PRIMARY KEY IDENTITY(1, 1),
    nume_bucatar VARCHAR(50) NOT NULL,
    specialitate_bucatar VARCHAR(50),
    salariu_bucatar DECIMAL(5, 2) DEFAULT 18.00,
);

CREATE TABLE Preparate(
    id_preparat INT PRIMARY KEY IDENTITY(1,1),
    nume_preparat VARCHAR(50) NOT NULL,
    ingrediente_preparat VARCHAR(300),
    pret_preparat DECIMAL(5, 2),
);

CREATE TABLE Bucatari_Preparate(
    id_bucatar INT,
    id_preparat INT,
    CONSTRAINT pk_Bucatari_Preparate PRIMARY KEY (id_bucatar, id_preparat),
    CONSTRAINT fk_bucatar FOREIGN KEY (id_bucatar) REFERENCES Bucatari(id_bucatar),
    CONSTRAINT fk_preparat FOREIGN KEY (id_preparat) REFERENCES Preparate(id_preparat),
);

CREATE TABLE Ospatari(
    id_ospatar INT PRIMARY KEY IDENTITY(1,1),
    nume_ospatar VARCHAR(50) NOT NULL, 
    salariu_ospatar DECIMAL(5,2) DEFAULT 15.00,
    
);

CREATE TABLE Mese(
    id_masa INT PRIMARY KEY IDENTITY(1,1),
    capacitate_masa INT NOT NULL,
    id_ospatar INT,
    CONSTRAINT fk_ospatar FOREIGN KEY (id_ospatar) REFERENCES Ospatari(id_ospatar)
);

CREATE TABLE Serviri(
    id_servire INT PRIMARY KEY IDENTITY(1,1),
    id_ospatar INT,
    id_masa INT, 
    id_preparat INT, 
    CONSTRAINT fk_ospatar_servire FOREIGN KEY (id_ospatar) REFERENCES Ospatari (id_ospatar),
    CONSTRAINT fk_masa_servire FOREIGN KEY (id_masa) REFERENCES Mese (id_masa),
    CONSTRAINT fk_preparat_servire FOREIGN KEY (id_preparat) REFERENCES Preparate (id_preparat),
    data_servire DATETIME DEFAULT GETDATE()
);

CREATE TABLE Curieri (
    id_curier INT PRIMARY KEY IDENTITY(1,1),
    nume_curier VARCHAR(50) NOT NULL,
    telefon_curier VARCHAR(15)
);

CREATE TABLE ComenziOnline(
    id_comandaOnline INT PRIMARY KEY IDENTITY(1,1),
    adresa VARCHAR(100) NOT NULL,
    nume_client VARCHAR(100),
    id_curier INT,
    CONSTRAINT fk_curier FOREIGN KEY (id_curier) REFERENCES Curieri(id_curier)
);

CREATE TABLE Comenzi_Preparate (
    id_comandaOnline INT,
    id_preparat INT,
    CONSTRAINT pk_Comenzi_Preparate PRIMARY KEY (id_comandaOnline, id_preparat),
    CONSTRAINT fk_comandaOnline FOREIGN KEY (id_comandaOnline) REFERENCES ComenziOnline(id_comandaOnline),
    CONSTRAINT fk_preparatComandaOnline FOREIGN KEY (id_preparat) REFERENCES Preparate(id_preparat)
);

CREATE TABLE Rezervari(
    id_rezervare INT PRIMARY KEY IDENTITY(1,1),
    nume_client VARCHAR(50) NOT NULL,
    numar_telefon VARCHAR(10),
    data_rezervare DATETIME,
);

CREATE TABLE Rezervari_Mese (
    id_rezervare INT,
    id_masa INT,
    CONSTRAINT pk_Rezervari_Mese PRIMARY KEY (id_rezervare, id_masa),
    CONSTRAINT fk_rezervare FOREIGN KEY (id_rezervare) REFERENCES Rezervari(id_rezervare),
    CONSTRAINT fk_masa FOREIGN KEY (id_masa) REFERENCES Mese(id_masa),
    pret_rezervare DECIMAL(5,2)
);

CREATE TABLE Ingrediente(
    id_ingredient INT PRIMARY KEY IDENTITY(1,1),
    nume_ingredient VARCHAR(50),
    cantitate_ingredient INT
);

CREATE TABLE Preparat_Ingrediente(
    id_preparat INT,
    id_ingredient INT,
    CONSTRAINT fk_preparatIngrediente FOREIGN KEY (id_preparat) REFERENCES Preparate(id_preparat),
    CONSTRAINT fk_ingredient FOREIGN KEY (id_ingredient) REFERENCES Ingrediente (id_ingredient),
);

CREATE TABLE Plati (
    id_plata INT PRIMARY KEY IDENTITY(1,1),
    id_comandaOnline INT,
    suma DECIMAL(10, 2) NOT NULL,
    data_plata DATETIME DEFAULT GETDATE(),
    metoda_plata VARCHAR(50),
    CONSTRAINT fk_comanda_plata FOREIGN KEY (id_comandaOnline) REFERENCES ComenziOnline(id_comandaOnline)
);

ALTER TABLE Plati
ADD CONSTRAINT uq_id_comandaOnline UNIQUE (id_comandaOnline);
