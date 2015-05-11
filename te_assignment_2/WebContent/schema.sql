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
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('MacBook Pro 2015','Hamza','Cool notebook','Computers','www',1026,100,864000,'770 Eastern Parkway'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('MacBook Pro 2008','Godlin','Old notebook','Computers','www',613,100,864000,'770 Eastern Parkway'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Hp Pavillion','Shariq','Reliable Laptop','Computers','www',700,100,864000,'63 Farwest Cr'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Asus Transformer','Karn','laptop','Computers','www',200,50,864000,'3 Nookford Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Sony Viao 2012','Godlin','awesome notebook','Computers','www',800,100,864000,'78 Little St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Tobisha laptop','Hamza','Old notebook','Computers','www',400,100,864000,'4 Harington St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Samsung Galaxy S4','Shariq','Reliable Phone','Mobile','www',250,50,864000,'1 Hurley Dr'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('iPhone 3GS','Karn','3GS','Mobile','www',400,50,864000,'24 george St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('iPad','Shariq','ipad','Tablet','www',800,150,864000,'14 george st'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Cool Camera','Karn','Digital CameraLG TV','Camera','www',1200,600,864000,'3 paramatta Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('LG TV','Shariq','LED Television','TV','www',2800,1000,864000,'39 George St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Guitar','Hamza','Them strings be good','Music','www',600,150,864000,'51 George St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Sony Xperia','Godlin','Xperia phone','Mobile','www',500,100,864000,'56 Gordon Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Iphone 6','Shariq','for parts - it is broken','Mobile','www',500,300,864000,'70 Gilba Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('HTC one','Karn','great phone','Mobile','www',600,100,864000,'45 Girraween Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Macbook pro','Shariq','great labtop 2011','Mobile','www',700,300,864000,'21 Yarra St'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Dell Inspiron','Hamza','super fast','Mobile','www',1700,500,864000,'56 Greg Rd'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Nikon','Karn','Great Camera','Camera','www',600,100,864000,'5 Tron Ave'); 
INSERT INTO cast_db.Items (Name, Owner, Description, Category, Picture, ReservePrice, StartingPrice, Duration, Address)
VALUES('Samsung TV','Godlin','Very good TV','Mobile','www',2000,700,864000,'34 Frazers Rd'); 


