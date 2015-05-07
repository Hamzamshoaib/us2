DROP TABLE cast_db.BiddingPrice;
DROP TABLE cast_db.Items;
DROP TABLE cast_db.Users;


CREATE TABLE cast_db.Users (
	UserName VARCHAR(255) PRIMARY KEY,
	FirstName VARCHAR(255) NOT NULL,
	LastName VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NOT NULL,
	Address VARCHAR(255),/*Constraint NOT NULL*/
	DOB DATE NOT NULL
);
	
CREATE TABLE cast_db.Items (
	Item_ID INT PRIMARY KEY,
	Name VARCHAR(255) NOT NULL,
	Owner VARCHAR(255) REFERENCES cast_db.Users(UserName),
	Description VARCHAR(2000),
	ReservePrice INT NOT NULL
);

CREATE TABLE cast_db.BiddingPrice (
	BidPrice INT NOT NULL,
	UserName VARCHAR(255) REFERENCES cast_db.Users(UserName),
	HighestBidder VARCHAR(255) REFERENCES cast_db.Users(UserName),
	Item_ID INT REFERENCES cast_db.Items(Item_ID),
	PRIMARY KEY (Username, Item_ID)
);
	

INSERT INTO cast_db.Users VALUES ('Hamza', 'Hamza', 'Shoaib', 'hamza@gmail.com', 'Glenwood', '2015-02-24');
