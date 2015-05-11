DROP TABLE cast_db.WishList;
DROP TABLE cast_db.BiddingPrice;
DROP TABLE cast_db.Items;
DROP TABLE cast_db.Users;


CREATE TABLE cast_db.Users (
	UserName VARCHAR(255) PRIMARY KEY,
	Password VARCHAR(255) NOT NULL,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NOT NULL,
	Address VARCHAR(255),/*Constraint NOT NULL*/
	DOB DATE NOT NULL,
	Verified VARCHAR(1000)
);
	
CREATE TABLE cast_db.Items (
	Item_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	Name VARCHAR(255) NOT NULL,
	Owner VARCHAR(255) REFERENCES cast_db.Users(UserName),
	Description VARCHAR(2000),
	Picture VARCHAR(2000) DEFAULT 'https://abs.twimg.com/sticky/default_profile_images/default_profile_0_400x400.png',
	ReservePrice INT NOT NULL,
	StartingPrice INT DEFAULT 1,
	Duration INT DEFAULT 600,
	Increments INT DEFAULT 1,
	Address VARCHAR(255),
	PRIMARY KEY(Item_ID)
);

CREATE TABLE cast_db.BiddingPrice (
	BidPrice INT NOT NULL,
	UserName VARCHAR(255) REFERENCES cast_db.Users(UserName),
	HighestBidder VARCHAR(255) REFERENCES cast_db.Users(UserName),
	Item_ID INT REFERENCES cast_db.Items(Item_ID),
	PRIMARY KEY (UserName, Item_ID)
);

CREATE TABLE cast_db.WishList (
	UserName VARCHAR(255) REFERENCES cast_db.Users(UserName),
	Item_ID INT REFERENCES cast_db.Items(Item_ID),
	PRIMARY KEY (UserName, Item_ID)
);

INSERT INTO cast_db.Users VALUES ('Hamza', 'password', 'Hamza', 'Shoaib', 'hamza@gmail.com', 'Glenwood', '2015-02-24', 'verified');
INSERT INTO cast_db.Users VALUES ('Godlin', 'password', 'Godlin', 'Raja', 'godyraja@gmail.com', 'Glenwood', '2015-02-24', 'verified');
INSERT INTO cast_db.Users VALUES ('Shariq', 'password', 'Shariq', 'Nabi', 'shazza92@gmail.com', 'Glenwood', '2015-02-24', 'verified');
INSERT INTO cast_db.Users VALUES ('Karn', 'password', 'Karn', 'Agrawal', 'karn@gmail.com', 'Glenwood', '2015-02-24', 'F218FBBF65BC');
INSERT INTO cast_db.Items (Name, Owner, Description, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Iphone 6','Hamza','Black 32 Gb','www',1000,300, 500,'123');
SELECT UserName FROM cast_db.Users WHERE Verified='4D678EA3296AFC870458E591F8929E3C';
