--Tabel Ingrediente
CREATE OR ALTER FUNCTION ValidateIngredientData (
    @p_nume_ingredient NVARCHAR(255),
    @p_cantitate_ingredient DECIMAL(10,2)
)
RETURNS NVARCHAR(100)
AS
BEGIN
    DECLARE @result NVARCHAR(100);

    IF @p_nume_ingredient IS NULL OR LTRIM(RTRIM(@p_nume_ingredient)) = ''
    BEGIN
        SET @result = 'Eroare: Numele ingredientului nu poate fi gol!';
        RETURN @result;
    END;

    IF @p_cantitate_ingredient <= 0
    BEGIN
        SET @result = 'Eroare: Cantitatea ingredientului trebuie sa fie mai mare decat zero!';
        RETURN @result;
    END;

    RETURN 'Valid';
END;

GO
CREATE OR ALTER PROCEDURE AddIngredient (
    @nume_ingredient NVARCHAR(255),
    @cantitate_ingredient DECIMAL(10,2)
)
AS
BEGIN
    DECLARE @validator NVARCHAR(100);

    SET @validator = dbo.ValidateIngredientData(@nume_ingredient, @cantitate_ingredient);

    IF @validator <> 'Valid'
    BEGIN
        PRINT @validator;
        RETURN;
    END;

    INSERT INTO Ingrediente (nume_ingredient, cantitate_ingredient)
    VALUES (@nume_ingredient, @cantitate_ingredient);

    PRINT 'Ingredientul a fost adăugat cu succes.';
END;


GO
CREATE OR ALTER PROCEDURE GetAllIngrediente
AS
    BEGIN
    SELECT * FROM Ingrediente
END

GO
CREATE OR ALTER PROCEDURE UpdateIngredient (
    @id INT,
    @nume_ingredient NVARCHAR(255),
    @cantitate_ingredient DECIMAL(10,2)
)
AS
BEGIN
    BEGIN TRY
        EXEC ValidateIngredientData @nume_ingredient, @cantitate_ingredient;

        IF NOT EXISTS (SELECT 1 FROM Ingrediente WHERE id_ingredient = @id) 
        BEGIN
            PRINT 'Eroare la modificarea ingredientului: Nu exista';
            RETURN;
        END

        UPDATE Ingrediente
        SET Ingrediente.nume_ingredient = @nume_ingredient, 
        Ingrediente.cantitate_ingredient = @cantitate_ingredient
        WHERE id_ingredient = @id

        PRINT 'Ingredientul a fost modificat cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la modificarea ingredientului:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

GO
CREATE OR ALTER PROCEDURE DeleteIngredient (
    @id INT
)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Ingrediente WHERE id_ingredient = @id) 
    BEGIN
        PRINT 'Eroare la stergere ingredientului: Nu exista';
        RETURN;
    END
        DELETE FROM Ingrediente
        WHERE id_ingredient = @id   

        DELETE from Preparat_Ingrediente
        WHERE id_ingredient = @id

        PRINT 'Ingredientul a fost sters cu succes.';
END;

--Tabel Preparat_Ingrediente
GO
CREATE OR ALTER PROCEDURE AddPreparatIngrediente (
    @id_ingrediente INT,
    @id_preparate INT
)
AS
BEGIN
    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM Ingrediente WHERE id_ingredient = @id_ingrediente)
        BEGIN
            RAISERROR ('Eroare: Ingredientul specificat nu exista!', 16, 1);
            RETURN;
        END;

        IF NOT EXISTS (SELECT 1 FROM Preparate WHERE id_preparat = @id_preparate)
        BEGIN
            RAISERROR ('Eroare: Preparatul specificat nu exista!', 16, 1);
            RETURN;
        END;

        INSERT INTO Preparat_Ingrediente (id_preparat, id_ingredient)
        VALUES (@id_preparate, @id_ingrediente);

        PRINT 'Legatura dintre ingredient si preparat a fost adaugata cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la adaugarea legaturii:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

GO
CREATE OR ALTER PROCEDURE GetAllPreparatIngrediente
AS
BEGIN
    SELECT * FROM Preparat_Ingrediente;
END;

GO
CREATE OR ALTER PROCEDURE UpdatePreparatIngrediente (
    @id_ingrediente INT,
    @id_preparate INT,
    @new_id_ingrediente INT,
    @new_id_preparate INT
)
AS
BEGIN
    BEGIN TRY
        IF NOT EXISTS (
            SELECT 1 
            FROM Preparat_Ingrediente 
            WHERE id_ingredient = @id_ingrediente AND id_preparat = @id_preparate
        )
        BEGIN
            RAISERROR ('Eroare: Legatura specificata nu exista!', 16, 1);
            RETURN;
        END;

        IF NOT EXISTS (SELECT 1 FROM Ingrediente WHERE id_ingredient = @new_id_ingrediente)
        BEGIN
            RAISERROR ('Eroare: Noul ID al ingredientului nu exista!', 16, 1);
            RETURN;
        END;

        IF NOT EXISTS (SELECT 1 FROM Preparate WHERE id_preparat = @new_id_preparate)
        BEGIN
            RAISERROR ('Eroare: Noul ID al preparatului nu exista!', 16, 1);
            RETURN;
        END;

        UPDATE Preparat_Ingrediente
        SET id_ingredient = @new_id_ingrediente, 
            id_preparat = @new_id_preparate
        WHERE id_ingredient = @id_ingrediente AND id_preparat = @id_preparate;

        PRINT 'Legatura a fost actualizata cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la actualizarea legaturii:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

GO 
CREATE OR ALTER PROCEDURE DeltePreparatIngrediente (
    @id_ingrediente INT,
    @id_preparate INT
)
AS
BEGIN
    BEGIN TRY
        IF NOT EXISTS (
            SELECT 1 
            FROM Preparat_Ingrediente 
            WHERE id_ingredient = @id_ingrediente AND id_preparat = @id_preparate
        )
        BEGIN
            RAISERROR ('Eroare: Legatura specificata nu exista!', 16, 1);
            RETURN;
        END;

        DELETE FROM Preparat_Ingrediente
        WHERE id_ingredient = @id_ingrediente and id_preparat = @id_preparate
        PRINT 'Legatura stearsa cu succes';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la actualizarea legaturii:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

--Tabel Preparate
GO
CREATE OR ALTER PROCEDURE ValidatePreparatData (
    @p_nume NVARCHAR(255),
    @p_ingrediente NVARCHAR(MAX),
    @p_pret DECIMAL(5,2)
)
AS
BEGIN
    IF @p_nume IS NULL OR LTRIM(RTRIM(@p_nume)) = ''
    BEGIN
        RAISERROR ('Eroare: Numele preparatului nu poate fi gol!', 16, 1);
        RETURN;
    END;

    IF @p_ingrediente IS NULL OR LTRIM(RTRIM(@p_ingrediente)) = ''
    BEGIN
        RAISERROR ('Eroare: Lista de ingrediente nu poate fi goala!', 16, 1);
        RETURN;
    END;

    IF @p_pret <= 0
    BEGIN
        RAISERROR ('Eroare: Pretul preparatului trebuie sa fie mai mare decat zero!', 16, 1);
        RETURN;
    END;
END;

GO
CREATE OR ALTER PROCEDURE AddPreparat (
    @nume NVARCHAR(255),
    @ingrediente NVARCHAR(MAX),
    @pret DECIMAL(5,2)
)
AS
BEGIN
    BEGIN TRY
        EXEC ValidatePreparatData @nume, @ingrediente, @pret;

        INSERT INTO Preparate (nume_preparat, ingrediente_preparat, pret_preparat)
        VALUES (@nume, @ingrediente, @pret);

        PRINT 'Preparatul a fost adaugat cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la adaugarea preparatului:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

GO
CREATE OR ALTER PROCEDURE GetAllPreparate
AS
BEGIN
    SELECT * FROM Preparate;
END;

GO
CREATE OR ALTER PROCEDURE UpdatePreparat (
    @id INT,
    @nume NVARCHAR(255),
    @ingrediente NVARCHAR(MAX),
    @pret DECIMAL(5,2)
)
AS
BEGIN
    BEGIN TRY
        EXEC ValidatePreparatData @nume, @ingrediente, @pret;

        IF NOT EXISTS (SELECT 1 FROM Preparate WHERE id_preparat = @id)
        BEGIN
            RAISERROR ('Eroare: Preparatul cu acest ID nu exista!', 16, 1);
            RETURN;
        END;

        UPDATE Preparate
        SET nume_preparat = @nume,
            ingrediente_preparat = @ingrediente,
            pret_preparat = @pret
        WHERE id_preparat = @id;

        PRINT 'Preparatul a fost modificat cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la modificarea preparatului:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;


GO
CREATE OR ALTER PROCEDURE DeletePreparat (
    @id INT
)
AS
BEGIN
    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM Preparate WHERE id_preparat = @id)
        BEGIN
            RAISERROR ('Eroare: Preparatul cu acest ID nu exista!', 16, 1);
            RETURN;
        END;

        DELETE FROM Preparate WHERE id_preparat = @id;
        DELETE FROM Preparat_Ingrediente WHERE id_preparat = @id;


        PRINT 'Preparatul a fost sters cu succes.';
    END TRY
    BEGIN CATCH
        PRINT 'Eroare la stergerea preparatului:';
        PRINT ERROR_MESSAGE();
    END CATCH;
END;

--Igredient
EXEC AddIngredient @nume_ingredient = 'test', @cantitate_ingredient = 100;
EXEC GetAllIngrediente;
EXEC UpdateIngredient @id = 41, @nume_ingredient = 'TEST', @cantitate_ingredient = 2.00;
EXEC DeleteIngredient @id = 41;

--Preparat_Ingrediente
EXEC AddPreparatIngrediente @id_ingrediente = 40, @id_preparate = 10;
EXEC GetAllPreparatIngrediente;
EXEC UpdatePreparatIngrediente @id_ingrediente = 40, @id_preparate = 10, @new_id_ingrediente = 40, @new_id_preparate = 1;
EXEC DeltePreparatIngrediente @id_ingrediente = 40, @id_preparate = 1;

--Preparat
EXEC AddPreparat @nume = 'test', @ingrediente = 'asd, dasad', @pret = 200.00;
EXEC GetAllPreparate;
EXEC UpdatePreparat @id = 16, @nume = 'TEST', @ingrediente = 'a, b, c, d, e', @pret = 22.00;
EXEC DeletePreparat @id = 16;


--VIEWS
GO
CREATE OR ALTER VIEW ViewPreparatIngrediente AS
SELECT 
    p.id_preparat AS PreparatID,
    p.nume_preparat AS PreparatNume,
    p.pret_preparat AS PreparatPret,
    i.nume_ingredient AS IngredientNume
FROM 
    Preparate p
JOIN 
    Preparat_Ingrediente ip ON p.id_preparat = ip.id_preparat
JOIN 
    Ingrediente i ON i.id_ingredient = ip.id_ingredient;

GO
CREATE OR ALTER VIEW ViewPreparatCantitatePosibila AS
SELECT 
    p.id_preparat AS PreparatID,
    p.nume_preparat AS PreparatNume,
    MIN(i.cantitate_ingredient) AS CantitatePosibila,
    MIN(i.cantitate_ingredient) * pret_preparat AS VenitPosibil
FROM 
    Preparate p
JOIN 
    Preparat_Ingrediente ip ON p.id_preparat = ip.id_preparat
JOIN 
    Ingrediente i ON i.id_ingredient = ip.id_ingredient
GROUP BY 
    p.id_preparat, p.nume_preparat, p.pret_preparat;



go
SELECT * FROM ViewPreparatIngrediente;
go
SELECT * FROM ViewPreparatCantitatePosibila;

--index
CREATE NONCLUSTERED INDEX IX_Preparate_Nume_Pret
ON Preparate (nume_preparat)
INCLUDE (pret_preparat);

SELECT nume_preparat, pret_preparat
FROM Preparate
ORDER BY nume_preparat;


CREATE NONCLUSTERED INDEX IX_Ingrediente_Nume_Cantitate
ON Ingrediente (nume_ingredient)
INCLUDE (cantitate_ingredient);

SELECT nume_ingredient, cantitate_ingredient
FROM Ingrediente
ORDER BY nume_ingredient;