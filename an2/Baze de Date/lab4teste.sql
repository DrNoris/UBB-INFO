
USE RESTAURANT


INSERT into [Tables] (Name) VALUES
('Rezervari'),
('ComenziOnline'),
('Bucatari_Preparate')

SELECT * FROM Tables;
GO

-- pentru un PK - Rezervari
CREATE VIEW View_Rezervari AS
 SELECT *
 FROM Rezervari;
GO

-- pentru un PK si cel putin un FK - ComenziOnline
CREATE VIEW View_ComenziOnline AS
 SELECT ComenziOnline.adresa, Curieri.nume_curier
 from ComenziOnline
 JOIN Curieri
 ON Curieri.id_curier = ComenziOnline.id_curier
GO

-- pentru doua PK - Bucatari_Preparate
CREATE VIEW View_Bucatari_Preparate AS
 SELECT b.nume_bucatar, p.nume_preparat
 FROM Bucatari b
 JOIN Bucatari_Preparate bp
 ON b.id_bucatar = bp.id_bucatar
 JOIN Preparate p
 ON p.id_preparat = bp.id_preparat;
GO


----- Adaug view-urile in tabel -----

INSERT INTO Views VALUES
	('View_Rezervari'),
	('View_ComenziOnline'),
	('View_Bucatari_Preparate');
GO

SELECT * FROM Views;

----- Adaug testele de efectuat in tabela Tests -----

INSERT INTO Tests VALUES
	('DIV_Rezervari_10'),
	('DIV_ComenziOnline_7'),
	('DIV_Bucatari_Preparate_10')
GO

SELECT * FROM Tests;

----- Fac legatura intre teste si tabele -----

INSERT INTO TestTables VALUES
	-- DIV_Rezervari_10
	(1, 1, 10, 1),
	-- DIV_ComenziOnline_7
	(2, 2, 7, 2),
	-- DIV_Bucatari_Preparate_10
	(3, 3, 10, 3);

SELECT * FROM TestTables;
GO

----- Fac legatura intre teste si view-uri -----

INSERT INTO TestViews VALUES
	(1, 1),
	(2, 2),
	(3, 3);

SELECT * FROM TestViews;


---- Creez procedurile de inserare -----

-- pentru Rezervari
GO
CREATE PROCEDURE ins_test_Rezervari
@NoOfRows INT
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @nume_client NVARCHAR(50);
	DECLARE @numar_telefon NVARCHAR(10);
	DECLARE @data_rezervare datetime;
	DECLARE @n INT = 0;
	DECLARE @last_id INT = 
		(SELECT MAX(Rezervari.id_rezervare) FROM Rezervari)

	WHILE @n < @NoOfRows
	BEGIN
		SET @nume_client = 'Rezervare_ TEST ' + CONVERT(VARCHAR(10), @last_id);
		SET @numar_telefon = '0712345678';
        SET @data_rezervare = GETDATE();
		INSERT INTO Rezervari (nume_client, numar_telefon, data_rezervare)
        VALUES (@nume_client, @numar_telefon, @data_rezervare);
		SET @last_id = @last_id + 1
		SET @n = @n + 1;
	END

	PRINT 'S-au inserat ' + CONVERT(VARCHAR(10), @NoOfRows) + ' valori in Rezervare.';
END

-- pentru ComenziOnline
GO
CREATE PROCEDURE ins_test_ComenziOnline
@NoOfRows INT
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @fk INT = (Select TOP 1 id_curier from Curieri);
	DECLARE @adresa nvarchar(100);
	DECLARE @nume_client nvarchar(100);
	DECLARE @n INT = 0;
	DECLARE @last_id INT = 
		(SELECT MAX(ComenziOnline.id_comandaOnline) FROM ComenziOnline)

	WHILE @n < @NoOfRows
	BEGIN
		SET @adresa = 'ComenziOnline TEST ' + CONVERT(VARCHAR(10), @last_id);
		SET @nume_client = 'Nume Client' + CONVERT(VARCHAR(10), @last_id);
		INSERT INTO ComenziOnline(adresa, nume_client, id_curier)
		VALUES (@adresa, @nume_client, @fk);
		SET @last_id = @last_id + 1
		SET @n = @n + 1;
	END

	PRINT 'S-au inserat ' + CONVERT(VARCHAR(10), @NoOfRows) + ' valori in ComenziOnline.';
END

SELECT * FROM ComenziOnline;


-- pentru Bucatari_Preparate
GO
CREATE OR ALTER PROCEDURE ins_test_Bucatari_Preparate
@NoOfRows INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @n INT = 0;
    DECLARE @id_bucatar INT;
    DECLARE @id_preparat INT;
    DECLARE @max_id_preparat INT;

    INSERT INTO Bucatari(nume_bucatar) values ('TEST');
    SELECT @id_bucatar = MAX(id_bucatar) FROM Bucatari WHERE nume_bucatar = 'TEST';


    SELECT @max_id_preparat = MAX(id_preparat) FROM Preparate;

    SET @id_preparat = 1;

    WHILE @n < @NoOfRows
    BEGIN
        INSERT INTO Bucatari_Preparate (id_bucatar, id_preparat)
        VALUES (@id_bucatar, @id_preparat);

        SET @id_preparat = @id_preparat + 1;
        
        if @id_preparat > @max_id_preparat
        BEGIN
            SET @id_preparat = 1;
            SET @id_bucatar = @id_bucatar + 1;
            INSERT INTO Bucatari(nume_bucatar) values ('TEST');
            SELECT @id_bucatar = MAX(id_bucatar) FROM Bucatari WHERE nume_bucatar = 'TEST';
        END

        SET @n = @n + 1;
    END

    PRINT 'S-au inserat ' + CONVERT(VARCHAR(10), @n) + ' valori in Bucatari_Preparate.';
END
GO

SELECT * FROM Bucatari_Preparate;

----- Creez procedurile de stergere -----
-- pentru Rezervari
GO
CREATE PROCEDURE del_test_Rezervari
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM Rezervari
	WHERE Rezervari.nume_client LIKE 'Rezervare_ TEST %';
	PRINT 'S-au sters ' + CONVERT(VARCHAR(10), @@ROWCOUNT) + ' valori din Rezervari.';
END


-- pentru ComenziOnline
GO
CREATE PROCEDURE del_test_ComenziOnline
AS
BEGIN
	SET NOCOUNT ON;
	DELETE FROM ComenziOnline
	WHERE ComenziOnline.adresa LIKE 'ComenziOnline TEST %';
	PRINT 'S-au sters ' + CONVERT(VARCHAR(10), @@ROWCOUNT) + ' valori din ComenziOnline.';
END

-- pentru Bucatari_Preparate
GO
CREATE OR ALTER PROCEDURE del_test_Bucatari_Preparate
AS
BEGIN
	SET NOCOUNT ON;

    DELETE Bucatari_Preparate
    FROM Bucatari_Preparate
    JOIN Bucatari ON Bucatari.id_bucatar = Bucatari_Preparate.id_bucatar
    WHERE Bucatari.nume_bucatar = 'TEST';
    PRINT 'S-au sters ' + CONVERT(VARCHAR(10), @@ROWCOUNT) + ' valori din Bucatari_Preparate.';

    DELETE Bucatari
    WHERE Bucatari.nume_bucatar = 'TEST';

    PRINT 'S-au sters ' + CONVERT(VARCHAR(10), @@ROWCOUNT) + ' valori din Bucatari.';
END


----- Creez procedura generala de inserare -----

GO
CREATE PROCEDURE inserare_testgen
@idTest INT
AS
BEGIN
	DECLARE @numeTest NVARCHAR(50) = (SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);
	DECLARE @numeTabela NVARCHAR(50);
	DECLARE @NoOfRows INT;
	DECLARE @procedura NVARCHAR(50);

	DECLARE cursorTab CURSOR FORWARD_ONLY FOR
		SELECT Tab.Name, Test.NoOfRows FROM TestTables Test
		JOIN Tables Tab ON Test.TableID = Tab.TableID
		WHERE Test.TestID = @idTest
		ORDER BY Test.Position;
	OPEN cursorTab;

	FETCH NEXT FROM cursorTab INTO @numeTabela, @NoOfRows;
	WHILE (@numeTest NOT LIKE N'DIV_' + @numeTabela + N'_' + CONVERT(NVARCHAR(10), @NoOfRows)) AND (@@FETCH_STATUS = 0)
	BEGIN
		SET @procedura = N'ins_test_' + @numeTabela;
		EXECUTE @procedura @NoOfRows;
		FETCH NEXT FROM cursorTab INTO @numeTabela, @NoOfRows;
	END

	SET @procedura = N'ins_test_' + @numeTabela;
	EXECUTE @procedura @NoOfRows;

	CLOSE cursorTab;
	DEALLOCATE cursorTab;
END

EXECUTE inserare_testgen 1;


----- Creez procedura generala de stergere -----

GO
CREATE PROCEDURE stergere_testgen
@idTest INT
AS
BEGIN
	DECLARE @numeTest NVARCHAR(50) = (SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);
	DECLARE @numeTabela NVARCHAR(50);
	DECLARE @NoOfRows INT;
	DECLARE @procedura NVARCHAR(50);

	DECLARE cursorTab CURSOR FORWARD_ONLY FOR
		SELECT Tab.Name, Test.NoOfRows FROM TestTables Test
		INNER JOIN Tables Tab ON Test.TableID = Tab.TableID
		WHERE Test.TestID = @idTest
		ORDER BY Test.Position DESC;
	OPEN cursorTab;

	FETCH NEXT FROM cursorTab INTO @numeTabela, @NoOfRows;
	WHILE (@numeTest NOT LIKE N'DIV_' + @numeTabela + N'_' + CONVERT(NVARCHAR(10), @NoOfRows)) AND (@@FETCH_STATUS = 0)
	BEGIN
		SET @procedura = N'del_test_' + @numeTabela;
		EXECUTE @procedura;
		FETCH NEXT FROM cursorTab INTO @numeTabela, @NoOfRows;
	END

	SET @procedura = N'del_test_' + @numeTabela;
	EXECUTE @procedura;

	CLOSE cursorTab;
	DEALLOCATE cursorTab;
END

EXECUTE stergere_testgen 1;
SELECT * FROM Rezervari;


----- Creez procedura generala pentru view-uri -----

GO
CREATE PROCEDURE view_testgen
@idTest INT
AS
BEGIN
	DECLARE @viewName NVARCHAR(50) = 
		(SELECT V.Name FROM Views V
		JOIN TestViews TV ON TV.ViewID = V.ViewID
		WHERE TV.TestID = @idTest);

	DECLARE @comanda NVARCHAR(55) = 
		N'SELECT * FROM ' + @viewName;
	
	EXECUTE (@comanda);
END

EXECUTE view_testgen 1;

----- Creez procedura de rulare a unui test -----

GO
CREATE PROCEDURE run_test
@idTest INT
AS
BEGIN
	DECLARE @startTime DATETIME;
	DECLARE @interTime DATETIME;
	DECLARE @endTime DATETIME;

	SET @startTime = GETDATE();

	EXECUTE inserare_testgen @idTest;
	EXECUTE stergere_testgen @idTest;
	
	SET @interTime = GETDATE();
	
	EXECUTE view_testgen @idTest;

	SET @endTime = GETDATE();

	-- var pt insert
	DECLARE @testName NVARCHAR(50) =
		(SELECT T.Name FROM Tests T WHERE T.TestID = @idTest);
	INSERT INTO TestRuns VALUES (@testName, @startTime, @endTime);

	DECLARE @viewID INT =
		(SELECT V.ViewID FROM Views V
		INNER JOIN TestViews TV ON TV.ViewID = V.ViewID
		WHERE TV.TestID = @idTest);
	DECLARE @tableID INT =
		(SELECT TB.TableID FROM Tests T
		INNER JOIN TestTables TT ON T.TestID = TT.TestID
		INNER JOIN Tables TB ON TB.TableID = TT.TableID
		WHERE T.TestID = @idTest AND 
		T.Name LIKE N'DIV_' + TB.Name + N'_' + CONVERT(NVARCHAR(10), TT.NoOfRows));
	DECLARE @testRunID INT = 
		(SELECT TOP 1 T.TestRunID FROM TestRuns T
		WHERE T.Description = @testName
		ORDER BY T.TestRunID DESC);
	
	INSERT INTO TestRunTables VALUES (@testRunID, @tableID, @startTime, @interTime);
	INSERT INTO TestRunViews VALUES (@testRunID, @viewID, @interTime, @endTime);

	PRINT CHAR(10) + '---> TEST COMPLETAT CU SUCCES IN ' + 
		 CONVERT(VARCHAR(10), DATEDIFF(millisecond, @startTime, @endTime)) +
		 ' milisecunde. <---'
END


EXECUTE run_test 3;
GO

SELECT * FROM Rezervari;
SELECT * FROM ComenziOnline;
SELECT * FROM Bucatari_Preparate;

SELECT * FROM Tables;
SELECT * FROM TestRuns;
SELECT * FROM TestRunTables;
SELECT * FROM TestRunViews;
SELECT * FROM Tests;
SELECT * FROM TestTables;
SELECT * FROM TestViews;
SELECT * FROM Views;