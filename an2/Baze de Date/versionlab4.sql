USE RESTAURANT
GO
--A--
CREATE PROCEDURE SetPhoneInt
As
    ALTER TABLE Curieri
        Alter COLUMN telefon_curier INT
GO

CREATE PROCEDURE SetPhoneBackVChar
AS
    ALTER TABLE Curieri
        ALTER COLUMN telefon_curier VARCHAR(15)
GO

EXEC SetPhoneInt
SElECT * FROM Curieri
EXEC SetPhoneBackVChar
GO

--B--
CREATE PROCEDURE AddVehicle
AS
    ALTER TABLE Curieri
        ADD Vehicul VARCHAR(20) CONSTRAINT DF_Vehicle DEFAULT "Masina";
GO

CREATE PROCEDURE DeleteVechicle
AS
    ALTER TABLE Curieri
            DROP CONSTRAINT DF_Vehicle;
        ALTER TABLE Curieri
            DROP COLUMN Vehicul;
GO

EXEC AddVehicle
SELECT * FROM Curieri
EXEC DeleteVechicle
GO

--C--
CREATE PROCEDURE CreateSchedule
AS
    CREATE TABLE Orar
    (
        id INT CONSTRAINT IdPrimaryKey PRIMARY KEY,
        zi VARCHAR(50) NOT NULL,
        ora_deschidere SMALLINT,
        ora_inchidere INT
    )
GO

CREATE PROCEDURE DeleteSchedule
AS
    IF OBJECT_ID('Orar', 'U') IS NOT NULL
        DROP TABLE Orar;
        PRINT 'Tabelul Orar a fost sters';
    ELSE
        PRINT 'Tabelul Orar nu exista';
GO

EXEC CreateSchedule;
select * from Orar
EXEC DeleteSchedule
GO

--D--
CREATE PROCEDURE AddColumnSchedule
AS
    ALTER TABLE Orar
        ADD tematica NVARCHAR(255) NULL;
        PRINT 'Coloana tematica a fost adăugată cu succes la tabelul Orar.';
GO

CREATE PROCEDURE DeleteColumnSchedule
AS
    ALTER TABLE Orar
        DROP COLUMN tematica
        PRINT 'Coloana tematica a fost stearsa cu succes din tabelul Orar.';
GO

EXEC AddColumnSchedule;
select * from Orar
EXEC DeleteColumnSchedule
GO

--E--
CREATE PROCEDURE AddForeignKeyOrarToMese
AS
    ALTER TABLE Orar
        ADD id_masa INT;
    ALTER TABLE Orar
        ADD CONSTRAINT FK_Orar_Mese FOREIGN KEY (id_masa)
        REFERENCES Mese(id_masa);
GO

CREATE PROCEDURE DeleteForeignKeyOrarToMese
AS
    ALTER TABLE Orar
        DROP CONSTRAINT FK_Orar_Mese;
    ALTER TABLE Orar
        DROP COLUMN id_masa;
GO

EXEC AddForeignKeyOrarToMese;
select * from Orar
EXEC DeleteForeignKeyOrarToMese
GO

---- Versions 
--Tabel pentru versiuni
CREATE TABLE VersionHistory (
    version INT
);

--Tabelul cu procedurile de do si undo
CREATE TABLE ProcedureTable(
    UndoProcedure VARCHAR(100),
    RedoProcedure VARCHAR(100),
    Version INT PRIMARY KEY
);

-- Incepem cu versiunea 0
INSERT INTO VersionHistory (VERSION)
VALUES (0);

-- Introducem operatiile de versionare 
INSERT INTO ProcedureTable(RedoProcedure, UndoProcedure, Version)
VALUES
       ('SetPhoneInt', 'SetPhoneBackVChar', 1),
       ('AddVehicle', 'DeleteVechicle', 2),
       ('CreateSchedule', 'DeleteSchedule', 3),
       ('AddColumnSchedule', 'DeleteColumnSchedule', 4),
       ('AddForeignKeyOrarToMese', 'DeleteForeignKeyOrarToMese', 5);

--Procedura GoVersion
GO
CREATE OR ALTER PROCEDURE GoVersion @Version INT
AS 
    IF @Version > 5 OR @Version < 0
    BEGIN 
        RAISERROR('NU EXISTA VERSIUNI DECAT DE LA 0 LA 5', 16, 1);
        RETURN
    END

    DECLARE @currentVersion INT;
    SET @currentVersion = (SELECT TOP 1 VERSION FROM VersionHistory);

    if @Version = @currentVersion
    BEGIN
        PRINT 'Deja esti la versiunea ' + CAST(@currentVersion AS VARCHAR);
        RETURN;
    END

    -- Daca dorim sa revenim la o versiune veche (Undo)
    if @Version < @currentVersion
    BEGIN
        PRINT 'Mergem la versiunea ' + CAST(@Version AS VARCHAR);

        DECLARE @UndoProcedure NVARCHAR(500);
        DECLARE UndoCursor CURSOR FOR 
            SELECT UndoProcedure FROM ProcedureTable WHERE Version <= @currentVersion AND Version > @Version
            ORDER BY Version DESC; 


        OPEN UndoCursor;
        FETCH NEXT FROM UndoCursor INTO @UndoProcedure;

        WHILE @@FETCH_STATUS = 0
        BEGIN
            EXEC sp_executesql @UndoProcedure;  -- Executam procedura de undo
            PRINT 'Procedura de undo: ' + @UndoProcedure;
            FETCH NEXT FROM UndoCursor INTO @UndoProcedure;
        END

        CLOSE UndoCursor;
        DEALLOCATE UndoCursor;

        -- Actualizam versiunea curenta
        UPDATE VersionHistory SET VERSION = @Version;
    END

    ELSE
        BEGIN
        PRINT 'Mergem la versiunea ' + CAST(@Version AS VARCHAR);

        DECLARE @RedoProcedure NVARCHAR(500);
        DECLARE RedoCursor CURSOR FOR 
            SELECT RedoProcedure FROM ProcedureTable WHERE Version > @currentVersion AND Version <= @Version;

        OPEN RedoCursor;
        FETCH NEXT FROM RedoCursor INTO @RedoProcedure;

        WHILE @@FETCH_STATUS = 0
        BEGIN
            EXEC sp_executesql @RedoProcedure;  -- Executam procedura de undo
            PRINT 'Procedura de redo: ' + @RedoProcedure;
            FETCH NEXT FROM RedoCursor INTO @RedoProcedure;
        END

        CLOSE RedoCursor;
        DEALLOCATE RedoCursor;

        -- Actualizam versiunea curenta
        UPDATE VersionHistory SET VERSION = @Version;
    END

GO
EXEC GoVersion 0;
EXEC GoVersion 1;
EXEC GoVersion 2;
EXEC GoVersion 3;
EXEC GoVersion 4;
EXEC GoVersion 5; 