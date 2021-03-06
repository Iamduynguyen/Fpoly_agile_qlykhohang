CREATE DATABASE QUANLYKHO_AGILE
GO

USE QUANLYKHO_AGILE
GO

IF OBJECT_ID('USERROLE') IS NOT NULL
DROP TABLE USERROLE
GO
CREATE TABLE USERROLE(
	ID INT NOT NULL,
	NAMEROLE NVARCHAR(30) NOT NULL

	CONSTRAINT PK_USERROLE PRIMARY KEY (ID)
)

INSERT INTO USERROLE (ID, NAMEROLE) VALUES
(1, N'QUẢN TRỊ VIÊN'),
(2, N'NHÂN VIÊN')

IF OBJECT_ID('USERS') IS NOT NULL
DROP TABLE USERS
GO
CREATE TABLE USERS(
	USERNAME VARCHAR(30) NOT NULL,
	PASSWORD VARCHAR(32) NOT NULL,
	NAME NVARCHAR(50) NOT NULL,
	STATUS BIT NOT NULL,
	IDROLE INT NOT NULL

	CONSTRAINT PK_USERS PRIMARY KEY (USERNAME),
	CONSTRAINT FK_USERS_USERROLE FOREIGN KEY (IDROLE) REFERENCES USERROLE (ID)
)

IF OBJECT_ID('PRODUCT') IS NOT NULL
DROP TABLE PRODUCT
GO
CREATE TABLE PRODUCT(
	IDPRODUCT VARCHAR(15) NOT NULL,
	NAMEPRODUCT NVARCHAR(50) NOT NULL,

	CONSTRAINT PK_PRODUCT PRIMARY KEY (IDPRODUCT)
)

IF OBJECT_ID('UNITPRODUCT') IS NOT NULL
DROP TABLE UNITPRODUCT
GO
CREATE TABLE UNITPRODUCT(
	IDUNIT INT NOT NULL,
	NAMEUNIT NVARCHAR(15) NOT NULL,

	CONSTRAINT PK_UNITPRODUCT PRIMARY KEY (IDUNIT)
)

INSERT INTO UNITPRODUCT (IDUNIT, NAMEUNIT) VALUES
(1, N'CÁI'),
(2, N'KG'),
(3, N'MÉT')

IF OBJECT_ID('LOTIMPORT') IS NOT NULL
DROP TABLE LOTIMPORT
GO
CREATE TABLE LOTIMPORT(
	LOTLO INT IDENTITY (1,1),
	DATEADDED DATETIME NOT NULL

	CONSTRAINT PK_LOTIMPORT PRIMARY KEY (LOTLO)
)

IF OBJECT_ID('PRODUCTIMPORT') IS NOT NULL
DROP TABLE PRODUCTIMPORT
GO
CREATE TABLE PRODUCTIMPORT(
	ID INT IDENTITY (1,1),
	LOTLO INT,
	IDPRODUCT VARCHAR(15) NOT NULL,
	AMOUNT INT NOT NULL CHECK (AMOUNT > 0),
	IDUNIT INT NOT NULL,
	PRICE MONEY NOT NULL,
	EXPIRYDATE DATETIME NOT NULL,
	LOCATION NVARCHAR(100) NOT NULL,
	NOTE NTEXT,
	AMOUNTPRODUCTEXPRORT INT

	CONSTRAINT PK_PRODUCTIMPORT PRIMARY KEY (ID),
	CONSTRAINT FK_PRODUCTIMPORT_LOTIMPORT FOREIGN KEY (LOTLO) REFERENCES LOTIMPORT (LOTLO),
	CONSTRAINT FK_PRODUCTIMPORT_PRODUCT FOREIGN KEY (IDPRODUCT) REFERENCES PRODUCT (IDPRODUCT),
	CONSTRAINT FK_PRODUCTIMPORT_UNITPRODUCT FOREIGN KEY (IDUNIT) REFERENCES UNITPRODUCT (IDUNIT)
)

ALTER TABLE PRODUCTIMPORT ADD CONSTRAINT DF_AMOUNTPRODUCTEXPRORT DEFAULT 0 FOR AMOUNTPRODUCTEXPRORT;

IF OBJECT_ID('PRODUCTEXPORT') IS NOT NULL
DROP TABLE PRODUCTEXPORT
GO
CREATE TABLE PRODUCTEXPORT(
	LOTLO INT IDENTITY (1,1),
	IDIMPORT INT,
	AMOUNT INT CHECK (AMOUNT > 0),
	DATEEXPORT DATETIME NOT NULL,
	NOTE NTEXT

	CONSTRAINT PK_PRODUCTEXPORT PRIMARY KEY (LOTLO),
	CONSTRAINT FK_PRODUCTEXPORT_PRODUCTIMPORT FOREIGN KEY (IDIMPORT) REFERENCES PRODUCTIMPORT (ID)
)


IF OBJECT_ID('PROC_GETIDT') IS NOT NULL
DROP PROC PROC_GETID
GO
CREATE PROC PROC_GETID
	@TABLE_NAME VARCHAR(30)
AS
	IF(@TABLE_NAME IS NULL)
		PRINT N'DỮ LIỆU NHẬP VÀO KHÔNG HỢP LỆ'
	ELSE
		DECLARE @LASSTID INT
		SET @LASSTID = IDENT_CURRENT(@TABLE_NAME)
		SELECT @LASSTID

EXEC PROC_GETID 'LOTIMPORT'

IF OBJECT_ID('PROC_IMPORTPRODUCT') IS NOT NULL
DROP PROC PROC_IMPORTPRODUCT
GO
CREATE PROC PROC_IMPORTPRODUCT
	@IDPRODUCT VARCHAR(15),
	@NAMEPRODUCT NVARCHAR(50),
	@DATEADDED DATETIME,
	@LOTLO INT,
	@AMOUNT INT,
	@IDUNIT INT,
	@PRICE MONEY,
	@EXPIRYDATE DATETIME,
	@LOCATION NVARCHAR(100),
	@NOTE NTEXT
AS
	IF(@DATEADDED IS NULL OR @IDPRODUCT IS NULL OR @NAMEPRODUCT IS NULL 
		OR @AMOUNT IS NULL OR @PRICE IS NULL OR @EXPIRYDATE IS NULL OR @LOCATION IS NULL)
			PRINT N'DỮ LIỆU NHẬP KHÔNG HỢP LỆ'
	ELSE
		IF(@AMOUNT < 0 OR @PRICE < 0)
			PRINT N'DỮ LIỆU NHẬP SAI'
		ELSE
			IF(@IDPRODUCT NOT IN (SELECT IDPRODUCT FROM PRODUCT))
				INSERT INTO PRODUCT VALUES (@IDPRODUCT, @NAMEPRODUCT)
			IF (@LOTLO NOT IN (SELECT LOTLO FROM LOTIMPORT))
				INSERT INTO LOTIMPORT (DATEADDED) VALUES (@DATEADDED)
			IF (@IDPRODUCT IN (SELECT IDPRODUCT FROM PRODUCT)
				AND @LOTLO IN (SELECT LOTLO FROM LOTIMPORT))
				INSERT INTO PRODUCTIMPORT (LOTLO, IDPRODUCT, AMOUNT, IDUNIT, PRICE, EXPIRYDATE, LOCATION, NOTE)
					VALUES (@LOTLO, @IDPRODUCT, @AMOUNT, @IDUNIT, @PRICE, @EXPIRYDATE, @LOCATION, @NOTE)

IF OBJECT_ID('PROC_EDITPRODUCT') IS NOT NULL
DROP PROC PROC_EDITPRODUCT
GO
CREATE PROC PROC_EDITPRODUCT
	@ID INT,
	@IDPRODUCT VARCHAR(15),
	@NAMEPRODUCT NVARCHAR(50),
	@AMOUNT INT,
	@IDUNIT INT,
	@PRICE MONEY,
	@EXPIRYDATE DATETIME,
	@LOCATION NVARCHAR(100),
	@NOTE NTEXT,
	@AMOUNTEXPORT INT
AS
	IF(@IDPRODUCT IS NULL OR @NAMEPRODUCT IS NULL OR @AMOUNT IS NULL 
		OR @PRICE IS NULL OR @EXPIRYDATE IS NULL OR @LOCATION IS NULL)
			PRINT N'DỮ LIỆU NHẬP KHÔNG HỢP LỆ'
	ELSE
		IF(@AMOUNT < 0 OR @PRICE < 0 OR @AMOUNT < @AMOUNTEXPORT)
			PRINT N'DỮ LIỆU NHẬP SAI'
		ELSE
			IF(@ID NOT IN (SELECT ID FROM PRODUCTIMPORT))
				PRINT N'ID KHÔNG TỒN TẠI'
			ELSE
				BEGIN
					UPDATE PRODUCT SET NAMEPRODUCT = @NAMEPRODUCT WHERE IDPRODUCT = @IDPRODUCT
					UPDATE PRODUCTIMPORT SET AMOUNT = @AMOUNT, PRICE = @PRICE, EXPIRYDATE = @EXPIRYDATE, 
						LOCATION = @LOCATION, NOTE = @NOTE, IDUNIT = @IDUNIT WHERE ID = @ID
				END

				/*
SELECT LIM.LOTLO, PRI.PRICE, PRI.EXPIRYDATE, LIM.DATEADDED, PRI.LOCATION,
PRI.NOTE, PRI.ID, P.IDPRODUCT, P.NAMEPRODUCT, PRI.AMOUNT, UNI.IDUNIT, PRI.AMOUNTPRODUCTEXPRORT
FROM LOTIMPORT AS LIM JOIN PRODUCTIMPORT AS PRI
ON LIM.LOTLO = PRI.LOTLO JOIN PRODUCT AS P
ON P.IDPRODUCT = PRI.IDPRODUCT JOIN UNITPRODUCT AS UNI
ON PRI.IDUNIT = UNI.IDUNIT

-- lấy tên của unit
--SELECT NAMEUNIT FROM UNITPRODUCT WHERE IDUNIT = ?

-- xuất bảng xuất hàng
SELECT PRE.LOTLO, PRI.LOTLO, PRE.DATEEXPORT, PRI.LOCATION, PRE.NOTE,
PRI.ID, P.IDPRODUCT, P.NAMEPRODUCT, PRE.AMOUNT, PRI.IDUNIT
FROM PRODUCTEXPORT AS PRE JOIN PRODUCTIMPORT AS PRI
ON PRE.IDIMPORT = PRI.ID JOIN PRODUCT AS P
ON PRI.IDPRODUCT = P.IDPRODUCT

SELECT USERNAME, PASSWORD, NAME, STATUS, IDROLE FROM USERS

SELECT NAMEPRODUCT FROM PRODUCT WHERE IDPRODUCT

SELECT NAMEUNIT FROM UNITPRODUCT

INSERT PRODUCTEXPORT (IDIMPORT, AMOUNT, DATEEXPORT, NOTE)

UPDATE PRODUCTIMPORT SET AMOUNTPRODUCTEXPRORT = ? WHERE ID = ?

SELECT AMOUNTPRODUCTEXPRORT FROM PRODUCTIMPORT WHERE ID = 6

SELECT USERNAME, PASSWORD, NAME, STATUS, IDROLE FROM USERS

SELECT NAMEROLE FROM USERROLE WHERE ID = ?

SELECT DISTINCT P.IDPRODUCT FROM PRODUCT AS P JOIN PRODUCTIMPORT AS PRI
ON P.IDPRODUCT = PRI.IDPRODUCT
WHERE PRI.AMOUNT - PRI.AMOUNTPRODUCTEXPRORT

-- int lotImport, double price, String expiryDate, String addDate, String location, 
-- String note, String idProduct, String nameProduct, int amount, String unit
*/

USE QUANLYKHO_AGILE

IF OBJECT_ID('PROC_CHECKTRUNG') IS NOT NULL
DROP PROC PROC_CHECKTRUNG
GO
CREATE PROC PROC_CHECKTRUNG
	@SDT VARCHAR(15)
AS
	DECLARE @KQ BIT
	IF(@SDT IN (SELECT NAMEUNIT FROM UNITPRODUCT))
	BEGIN
		SET @KQ = 1
		SELECT @KQ
	END
	ELSE
	BEGIN
		SET @KQ = 0
		SELECT @KQ
	END

		SELECT * FROM UNITPRODUCT

		EXEC PROC_CHECKTRUNG N'KG'
	
