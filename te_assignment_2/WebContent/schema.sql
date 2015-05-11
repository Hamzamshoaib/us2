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
	Category VARCHAR(255),
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


INSERT INTO cast_db.Users VALUES ('Hamza', 'password', 'Hamza', 'Shoaib', 'hamza@gmail.com', 'Glenwood', '1992-04-24', 'verified');
INSERT INTO cast_db.Users VALUES ('Godlin', 'password', 'Godlin', 'Rajendran', 'godyraja@gmail.com', 'Girraween', '1989-08-17', 'verified');
INSERT INTO cast_db.Users VALUES ('Shariq', 'password', 'Shariq', 'Nabi', 'shazza92@gmail.com', 'Bankstown', '1992-05-21', 'verified');
INSERT INTO cast_db.Users VALUES ('Karn', 'password', 'Karn', 'Agrawal', 'karn@gmail.com', 'Epping', '1991-10-07', 'verified');
INSERT INTO cast_db.Users VALUES ('Sam', 'password', 'Sam', 'Jackson', 'godlin17@gmail.com', 'Parramatta', '1994-09-05', 'F218FYHF65BC');
INSERT INTO cast_db.Users VALUES ('Denzel', 'password', 'Denzel', 'Washington', 'godlin17@gmail.com', 'Parramatta', '1994-10-05', 'A218FBBF65BC');
INSERT INTO cast_db.Users VALUES ('Leo', 'password', 'Leo', 'Decapri', 'godlin17@gmail.com', 'Parramatta', '1994-02-05', 'H618FBBF65BC');
INSERT INTO cast_db.Users VALUES ('Keith', 'password', 'Keith', 'Bar', 'godlin17@gmail.com', 'Parramatta', '1991-12-07', 'verified');
INSERT INTO cast_db.Users VALUES ('Roger', 'password', 'Roger', 'David', 'godlin17@gmail.com', 'Parramatta', '1990-09-05', 'UJ18FBBF65BC');
INSERT INTO cast_db.Users VALUES ('Jerry', 'password', 'Jerry', 'Spring', 'godlin17@gmail.com', 'Parramatta', '1986-11-13', 'F21J5BBF65BC');
INSERT INTO cast_db.Users VALUES ('Nick', 'password', 'Nick', 'Cage', 'godlin17@gmail.com', 'Parramatta', '1994-09-14', 'verified');
INSERT INTO cast_db.Users VALUES ('Scarlet', 'password', 'Scarlet', 'Jo', 'godlin17@gmail.com', 'Parramatta', '1989-09-24', 'verified');
INSERT INTO cast_db.Users VALUES ('Sarah', 'password', 'Sarah', 'Stock', 'godlin17@gmail.com', 'Parramatta', '1981-09-10', 'verified');
INSERT INTO cast_db.Users VALUES ('Gary', 'password', 'Gary', 'Smith', 'godlin17@gmail.com', 'Parramatta', '1985-09-11', 'verified');
INSERT INTO cast_db.Users VALUES ('Will', 'password', 'Will', 'Smith', 'godlin17@gmail.com', 'Westmead', '1993-12-05', 'verified');
INSERT INTO cast_db.Users VALUES ('Henry', 'password', 'Henry', 'Peters', 'godlin17@gmail.com', 'Westmead', '1994-11-23', 'F218FBBF65BC');
INSERT INTO cast_db.Users VALUES ('Peter', 'password', 'Peter', 'Jackson', 'godlin17@gmail.com', 'Westmead', '1993-01-25', 'verified');
INSERT INTO cast_db.Users VALUES ('Michael', 'password', 'Michael', 'Jordan', 'godlin17@gmail.com', 'Westmead', '1989-09-12', 'verified');
INSERT INTO cast_db.Users VALUES ('Greg', 'password', 'Greg', 'Hurley', 'godlin17@gmail.com', 'Westmead', '1990-07-14', 'verified');
INSERT INTO cast_db.Users VALUES ('Dean', 'password', 'Dean', 'Downer', 'godlin17@gmail.com', 'Westmead', '1994-08-17', 'verified');
INSERT INTO cast_db.Users VALUES ('France', 'password', 'France', 'Cheel', 'godlin17@gmail.com', 'Westmead', '1987-04-03', 'U618FBBF65BC');
INSERT INTO cast_db.Users VALUES ('Jesse', 'password', 'Jesse', 'Jack', 'godlin17@gmail.com', 'Westmead', '1990-03-06', 'verified');
INSERT INTO cast_db.Users VALUES ('Matt', 'password', 'Matt', 'Burners', 'godlin17@gmail.com', 'Westmead', '1994-09-05', 'verified');
INSERT INTO cast_db.Users VALUES ('Homer', 'password', 'Homer', 'Simpson', 'godlin17@gmail.com', 'Westmead', '1990-05-04', 'verified');

INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Iphone 6','Hamza','Black 32 Gb','Mobile','www',1000,300, 500,'123');
/*SELECT UserName FROM cast_db.Users WHERE Verified='4D678EA3296AFC870458E591F8929E3C';*/
