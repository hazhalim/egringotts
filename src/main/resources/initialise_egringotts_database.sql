# Create the egringotts database
CREATE SCHEMA egringotts;
# ^ Comment this with `#` if the schema already exists

USE egringotts;

# Create the User table
CREATE TABLE `User`(

	userID BIGINT AUTO_INCREMENT PRIMARY KEY,
    userType VARCHAR(50)

);

# Create the UserImage table
CREATE TABLE `UserImage`(
	
    userImageID BIGINT AUTO_INCREMENT PRIMARY KEY,
    imagePath VARCHAR(1024)

);

# Create the Address table
CREATE TABLE `Address`(

	addressID BIGINT AUTO_INCREMENT PRIMARY KEY,
    `type` VARCHAR(50),
    streetName1 VARCHAR(255),
    streetName2 VARCHAR(255),
    town VARCHAR(255),
    state VARCHAR(255),
    postcode VARCHAR(10),
    country VARCHAR(255)
    
);

# Create the SecurityQuestion table
CREATE TABLE `SecurityQuestion`(

	securityQuestionID BIGINT AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255)
	
);



# Create the Currency table
CREATE TABLE `Currency`(

	currencyID BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    toOneKnut FLOAT,
    toOneSickle FLOAT,
    toOneGalleon FLOAT
    
);

# Create the Account table
CREATE TABLE `Account`(

	accountID BIGINT AUTO_INCREMENT PRIMARY KEY,
    userID BIGINT,
    creationDate TIMESTAMP,
    closingDate TIMESTAMP,
    lastLoginDate TIMESTAMP,
    lastActivityDate TIMESTAMP,
    userImageID BIGINT,
    
    givenName VARCHAR(255),
    lastName VARCHAR(255),
    gender VARCHAR(25),
    dateOfBirth DATE,
    
    mailingAddressID BIGINT,
    billingAddressID BIGINT,
    
    emailAddress VARCHAR(255),
    username VARCHAR(255),
    `password` VARCHAR(255),
    
    homeTelephoneNumber VARCHAR(255),
    mobileTelephoneNumber VARCHAR(255),
    workTelephoneNumber VARCHAR(255),
    
    securityQuestionID BIGINT,
    securityPIN INT,
    
	FOREIGN KEY (userID) REFERENCES `User`(userID),
    FOREIGN KEY (userImageID) REFERENCES `UserImage`(userImageID),
	FOREIGN KEY (mailingAddressID) REFERENCES `Address`(addressID),
    FOREIGN KEY (billingAddressID) REFERENCES `Address`(addressID),
    FOREIGN KEY (securityQuestionID) REFERENCES `SecurityQuestion`(securityQuestionID)

);

# Create the SecurityAnswer table
CREATE TABLE `SecurityAnswer`(

	securityAnswerID BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountID BIGINT,
    securityQuestionID BIGINT,
    answer VARCHAR(255),
    
    FOREIGN KEY (accountID) REFERENCES `Account`(accountID),
    FOREIGN KEY (securityQuestionID) REFERENCES `SecurityQuestion`(securityQuestionID)

);

# Create the Card table
CREATE TABLE `Card`(

	cardID BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountID BIGINT,
    cardNumber VARCHAR(50),
    cvv VARCHAR(10),
    expiryDate DATE,
    
    FOREIGN KEY (accountID) REFERENCES `Account`(accountID)

);

# Create the Transaction table
CREATE TABLE `Transaction`(
	
    transactionID BIGINT AUTO_INCREMENT PRIMARY KEY,
    fromAccountID BIGINT,
    toAccountID BIGINT,
    amount FLOAT,
    currencyID BIGINT,
    `date` TIMESTAMP,
    category VARCHAR(50),
    
    FOREIGN KEY (fromAccountID) REFERENCES `Account`(accountID),
    FOREIGN KEY (toAccountID) REFERENCES `Account`(accountID),
    FOREIGN KEY (currencyID) REFERENCES `Currency`(currencyID)
    
);

# Create the Balance table
CREATE TABLE `Balance`(

	balanceID BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountID BIGINT,
    currencyID BIGINT,
    balance FLOAT,
    
    FOREIGN KEY (accountID) REFERENCES `Account`(accountID),
    FOREIGN KEY (currencyID) REFERENCES `Currency`(currencyID)

);