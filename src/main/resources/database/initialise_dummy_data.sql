-- Inserting security questions
INSERT INTO `security_question` (question)
VALUES
('What was the name of your first pet?'),
('What was the make and model of your first car?'),
('What is your mother’s middle name?'),
('In what city were you born?'),
('What was the name of your elementary school?'),
('What is the name of the street you grew up on?'),
('What was your childhood nickname?'),
('What was the name of your first school?'),
('What is the name of your favorite childhood friend?'),
('What was the name of your first employer?');

-- Inserting users (roles)
INSERT INTO `user` (role_type, `type`)
VALUES
('Goblin', 'Goblin'),
('Platinum Patronus', 'Platinum Patronus'),
('Platinum Patronus', 'Platinum Patronus'),
('Platinum Patronus', 'Platinum Patronus'),
('Golden Galleon', 'Golden Galleon'),
('Golden Galleon', 'Golden Galleon'),
('Golden Galleon', 'Golden Galleon'),
('Silver Snitch', 'Silver Snitch'),
('Silver Snitch', 'Silver Snitch'),
('Silver Snitch', 'Silver Snitch');

-- Inserting addresses
INSERT INTO `address` (country, postcode, state, street_name_1, street_name_2, town)
VALUES
('United Kingdom', 'E1', 'England', 'North Side', 'Diagon Alley', 'London'),
('Malaysia', '52200', 'Federal Territory of Kuala Lumpur', '28, Jalan Saga SD 8/2a', 'Bandar Sri Damansara', 'Kuala Lumpur'),
('Malaysia', '50603', 'Federal Territory of Kuala Lumpur', 'Kolej Kediaman Ke-12, Universiti Malaya', 'Lingkungan Budi', 'Kuala Lumpur'),
('Malaysia', '47400', 'Selangor', 'Jalan SS 20/21', 'Damansara Utama', 'Petaling Jaya'),
('Malaysia', '12900', 'Perlis', 'Jalan Maarof', 'Kuala Layang', 'Arau'),
('Malaysia', '87700', 'Sabah', 'Jalan Abadi 4/13', 'Kampung Rampayan', 'Kota Kinabalu'),
('Malaysia', '34520', 'Kedah', 'Jalan Petani', 'Kampung Padang Teboran', 'Kuala Nerang'),
('Malaysia', '23870', 'Penang', 'Jalan Haji Kassim', 'Kampung Makam', 'George Town'),
('Malaysia', '51000', 'Federal Territory of Kuala Lumpur', 'Jalan Dato Sena 26', 'Sentul', 'Kuala Lumpur'),
('Malaysia', '40300', 'Selangor', 'Jalan Rusa 20/9', 'Seksyen 20', 'Shah Alam');

-- Inserting accounts
INSERT INTO `account` (closing_date, creation_date, date_of_birth, email_address, full_name, gender, last_login_date, `password`, security_pin, telephone_number, username, address_id, security_question_id, user_id)
VALUES
(NULL, '2024-06-01', '1564-09-24', 'egringottsbank@gmail.com', 'E-Gringotts Bank', 'Male', '2024-06-05', '$2a$10$BOzyNsatqfg0SrijvJUeLOPigmlw4mAXlO.sz/hgQP0qx8xL3WGLS', '$2a$10$m1PGJ2OEgrFBTEw2tHzRQOZCbLdmXierlgFYQE3xdAcOyOSrk4vDm', '+44123456789', 'egringotts', 1, 1, 1),
(NULL, '2024-06-01', '2004-07-05', 'hazhalim0507@gmail.com', 'Ammar Haziq Abdul Halim', 'Male', '2024-06-05', '$2a$10$8AiWdeyCpS1nBxUpUnYwI.VmkctlGoZLhTGFyOHw8mZoSDtwdkCs.', '$2a$10$pLyHmZjnJ0pRnrbQucH9eOryj.PhRaIwtpxpCnow1PBMjFqr6Yaye', '+60127273192', 'hazhalim', 2, 2, 2),
(NULL, '2024-06-01', '2004-01-01', 'mnfitrifzn2004@gmail.com', 'Muhammad Nur Fitri Faizan', 'Male', '2024-06-05', '$2a$10$2z21Oq0NUt3CrfS5TfLar.01XIlgoutqFDuv7zLLXS0OvfiKIhsIu', '$2a$10$77CARs8fmJy3jL95g8ob2eygd31v9xYdpgoYiqGi6b2KbZPW.9WA.', '+60176802569', 'mnfitrifzn', 3, 3, 3),
(NULL, '2024-06-01', '2004-01-01', 'raufhazim@gmail.com', 'Abdul Rauf Hazim Harisi', 'Male', '2024-06-05', '$2a$10$/EGxjecoYD/tWuKU.7HoK.telZgunn2UjWC/aGyrGVihcKDObEAbK', '$2a$10$0ExfS8CmXTaGOA1S.5zKKeg7JH.UMhJRVAs7nB7wKcNmC3VNSsmWa', '+601164323349', 'raufhazim', 4, 4, 4),
(NULL, '2024-06-01', '2004-01-01', 'zharifanas12@gmail.com', 'Muhammad Zharif Anas Ab Rahman', 'Male', '2024-06-05', '$2a$10$lvhfsL9QeYhooFEO4JKOAuVXbdE8JAXw4lM9tWLine6W7XNTOSuBO', '$2a$10$YJhCMy4UbpSIsMazzruYvuN29J1f5Cm17RmPmONFlT3xXh0DovJDe', '+60133411223', 'zharifanas', 5, 5, 5),
(NULL, '2024-06-01', '2004-01-01', 'iskandar@gmail.com', 'Wan Muhammad Iskandar Wan Mohd Razali', 'Male', '2024-06-05', '$2a$10$M/jfXXMz/IlQL1T0QzVBTenCP/ajxHvb7PmUH9AaG1UznFh3bXowW', '$2a$10$vH1pZYrD3VxC6lTwTNnnr.Fie/0gI6sdRL3rzXFK4VlwANN6SBNqa', '+601111578554', 'iskandar', 6, 6, 6),
(NULL, '2024-06-01', '1990-01-01', 'testing1@gmail.com', 'John Doe', 'Male', '2024-06-05', '$2a$10$6wOuj7jogrD8y95zVxaSXOdlkd7Cr2DzolnW13VzBPl/UF.2kzlYm', '$2a$10$0sYw5Rc2NbanTljn8kcUiegKfml9o1zQityfkvcjZJzXraDvvtd9i', '+60123456789', 'johndoe', 7, 7, 7),
(NULL, '2024-06-01', '1990-01-01', 'testing2@gmail.com', 'Jane Doe', 'Female', '2024-06-05', '$2a$10$Y1l9mehNvxz4uqLBAx2.DOSCqJmkl9hAblDCBskyueeWW3faoZk6K', '$2a$10$zPvAwykfkgpZjLlm8/gU..scTq49rSytG0yGsYZ0xT.jWnqv0GzAK', '+60134567890', 'janedoe', 8, 8, 8),
(NULL, '2024-06-01', '1990-01-01', 'testing3@gmail.com', 'Ali Abu', 'Male', '2024-06-05', '$2a$10$C4Es8vbuMErrHEOkG6XUa.gX/NlZqwGJIMmsUkIbHg0sGYYq814nG', '$2a$10$qPC.qOxD/cWU.hs8mLpS5e4mhmM4cFG2KWHHxXF8c6R2imMIRtYUW', '+60145678901', 'egringotts', 9, 9, 9),
(NULL, '2024-06-01', '1990-01-01', 'testing4@gmail.com', 'Alia Abu', 'Female', '2024-06-05', '$2a$10$rTAh2dB8VJpNPjPZ2MxJ1uz9A87LxIxT0MU0NLYWUOD.M.IwSwqNC', '$2a$10$7RWkt7LwQd0.FQILnp160..ywgwbHOZ1Uo5UrT68pRkA0dsufuYLe', '+60156789012', 'aliaabu', 10, 10, 10);

-- Inserting currencies
INSERT INTO `currency` (abbreviation, `name`)
VALUES
('K', 'Knut'),
('S', 'Sickle'),
('G', 'Galleon');

-- Inserting balances
INSERT INTO `balance` (balance, account_id, currency_id)
VALUES
(1000000000, 1, 1),
(1000000000, 1, 2),
(1000000000, 1, 3),
(10000, 2, 1),
(10000, 2, 2),
(10000, 2, 3),
(10000, 3, 1),
(10000, 3, 2),
(10000, 3, 3),
(10000, 4, 1),
(10000, 4, 2),
(10000, 4, 3),
(5000, 5, 1),
(5000, 5, 2),
(5000, 5, 3),
(5000, 6, 1),
(5000, 6, 2),
(5000, 6, 3),
(5000, 7, 1),
(5000, 7, 2),
(5000, 7, 3),
(2500, 8, 1),
(2500, 8, 2),
(2500, 8, 3),
(2500, 9, 1),
(2500, 9, 2),
(2500, 9, 3),
(2500, 10, 1),
(2500, 10, 2),
(2500, 10, 3);

-- Inserting cards
INSERT INTO `card` (card_number, cvv, expiry_date, `type`, account_id)
VALUES
('1234123412341234', '123', '2029-09-01', 'Debit Card', 1),
('4321432143214321', '321', '2029-09-01', 'Credit Card', 1),

('4298994011149106', '123', '2029-09-01', 'Debit Card', 2),
('4273463579365873', '321', '2029-09-01', 'Credit Card', 2),

('4433331809335547', '123', '2029-09-01', 'Debit Card', 3),
('6786114004976660', '321', '2029-09-01', 'Credit Card', 3),

('3982154300763237', '123', '2029-09-01', 'Debit Card', 4),
('6195209134347590', '321', '2029-09-01', 'Credit Card', 4),

('8755565670149303', '123', '2029-09-01', 'Debit Card', 5),
('6891896722221217', '321', '2029-09-01', 'Credit Card', 5),

('9212096203992492', '123', '2029-09-01', 'Debit Card', 6),
('5085952111377668', '321', '2029-09-01', 'Credit Card', 6),

('3701400231559994', '123', '2029-09-01', 'Debit Card', 7),
('3306258660348808', '321', '2029-09-01', 'Credit Card', 7),

('8558422643602907', '123', '2029-09-01', 'Debit Card', 8),
('2866621044178266', '321', '2029-09-01', 'Credit Card', 8),

('2005477976468661', '123', '2029-09-01', 'Debit Card', 9),
('7064815299047156', '321', '2029-09-01', 'Credit Card', 9),

('9700747550050376', '123', '2029-09-01', 'Debit Card', 10),
('7779080610194245', '321', '2029-09-01', 'Credit Card', 10);

-- Insert currency exchanges
INSERT INTO `currency_exchange` (processing_fee, rate, from_currency_id, to_currency_id)
VALUES
(0.0, 1, 1, 1),
(0.05, 29, 1, 2),
(0.01, 493, 1, 3),

(0.0, 1, 2, 2),
(10.0, 0.03448, 2, 1),
(0.2, 17, 2, 3),

(0.0, 1, 3, 3),
(25.0, 0.002028, 3, 1),
(10.0, 0.05882, 3, 2);

-- Insert security answers
INSERT INTO `security_answer` (answer, account_id, security_question_id)
VALUES
("Goblin", 1, 1),
("Perodua Myvi", 2, 2),
("Mother", 3, 3),
("Shah Alam", 4, 4),
("SK Bandar Sri Damansara 1", 5, 5),
("Jalan Abadi", 6, 6),
("John", 7, 7),
("SK Bandar Sri Damansara 1", 8, 8),
("Friend", 9, 9),
("Employer", 10, 10);