USE [master]
drop database LTDD
GO
CREATE DATABASE LTDD
GO
USE LTDD

CREATE TABLE Customer (
    customer_id VARCHAR(20) PRIMARY KEY,
	full_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(20),
	image_link VARCHAR(50) NULL,
	cus_address varchar(50),
	gender bit,
	birth_day date
);

CREATE TABLE Staff (
    staff_id VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(20),
	gender bit,
	birth_day date,
	cic VARCHAR(50) UNIQUE, -- cccd
	staff_address varchar(50),
	image_link VARCHAR(50)
);

CREATE TABLE Account (
    account_id VARCHAR(20) PRIMARY KEY,
    account_type VARCHAR(10),
    customer_id VARCHAR(20) NULL,
    staff_id VARCHAR(20) NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id),
    CHECK ((customer_id IS NOT NULL AND staff_id IS NULL) OR (customer_id IS NULL AND staff_id IS NOT NULL)),
    CHECK ((account_type = 'Customer' AND customer_id IS NOT NULL) OR (account_type = 'Staff' AND staff_id IS NOT NULL))
);

CREATE TABLE Hotel (
    hotel_id VARCHAR(20) PRIMARY KEY,
    hotel_name VARCHAR(50),
    hotel_address VARCHAR(100),
    province VARCHAR(50),
    phone_number VARCHAR(20),
    email VARCHAR(50),
    website VARCHAR(100) NULL,
	image_link VARCHAR(100)
);

CREATE TABLE Vehicle (
    vehicle_id VARCHAR(20) PRIMARY KEY,
	rental_company VARCHAR(50),
    model VARCHAR(50),
    color VARCHAR(50),
    license_plate VARCHAR(20),
    rental_rate DECIMAL(8, 2),
    available bit
);
--=======


CREATE TABLE TourGuider (
  tour_guider_id VARCHAR(20) PRIMARY KEY ,
  full_name NVARCHAR(50) NOT NULL,
  birth_day DATE NOT NULL,
  gender BIT NOT NULL,
  address NVARCHAR(100),
  tel CHAR(10),
  email NVARCHAR(100),
  image_link VARCHAR(50),
  cic VARCHAR(50) UNIQUE,
  salary MONEY NOT NULL
);

CREATE TABLE Place (
  place_id VARCHAR(20) PRIMARY KEY,
  place_name NVARCHAR(50) NOT NULL,
  dec NVARCHAR(255),
  contents NVARCHAR(MAX),
  image_link VARCHAR(50)
);


CREATE TABLE Tour (
  tour_id VARCHAR(20) PRIMARY KEY,
  tour_guider_id VARCHAR(20) NOT NULL,
  place_id VARCHAR(20) NOT NULL,
  tour_name NVARCHAR(50) NOT NULL,
  place_go NVARCHAR(50) NOT NULL,
  date_go DATE NOT NULL,
  date_back DATE NOT NULL,
  num_person INT NOT NULL,
  price MONEY NOT NULL,
  image_link VARCHAR(50),
  state BIT NOT NULL,

	FOREIGN KEY (tour_guider_id) REFERENCES TourGuider(tour_guider_id),
	FOREIGN KEY (place_id) REFERENCES Place(place_id)
);

--=======

CREATE TABLE Booking (
    booking_id VARCHAR(20) PRIMARY KEY,
    customer_id VARCHAR(20),
    staff_id VARCHAR(20),
    vehicle_id VARCHAR(20),
    tour_id VARCHAR(20),
	hotel_id VARCHAR(20),
    booking_date DATE,
    booking_type VARCHAR(10),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
	FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id),
    FOREIGN KEY (staff_id) REFERENCES Staff(staff_id),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id),
    FOREIGN KEY (tour_id) REFERENCES Tour(tour_id),
    CHECK (booking_type IN ('Vehicle', 'Hotel', 'Tour')),
    CHECK ((booking_type = 'Hotel' AND hotel_id IS NOT NULL AND vehicle_id IS NULL AND tour_id IS NULL) OR 
          (booking_type = 'Vehicle' AND hotel_id IS NULL AND vehicle_id IS NOT NULL AND tour_id IS NULL) OR
          (booking_type = 'Tour' AND hotel_id IS NULL AND vehicle_id IS NULL AND tour_id IS NOT NULL))
);


CREATE TABLE Room (
    RoomID INT PRIMARY KEY,
	hotel_id VARCHAR(20),
    RoomNumber VARCHAR(20),
    RoomType VARCHAR(50),
    RoomCapacity INT,
    RoomStatus VARCHAR(20),
	FOREIGN KEY (hotel_id) REFERENCES Hotel(hotel_id)
)

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




INSERT INTO Customer (customer_id, full_name, email, phone_number, image_link, cus_address, gender, birth_day)
VALUES 
(1, 'John Doe', 'johndoe@example.com', '123-456-7890', 'https://example.com/image1.jpg', '123 Main St, Anytown, USA', 1, '1990-01-01'),
(2, 'Jane Smith', 'janesmith@example.com', '987-654-3210', 'https://example.com/image2.jpg', '456 Oak Rd, Anytown, USA', 0, '1995-05-10'),
(3, 'Bob Johnson', 'bobjohnson@example.com', '555-555-1212', NULL, '789 Maple Ave, Anytown, USA', 1, '1985-12-31');

INSERT INTO Staff (staff_id, full_name, email, phone_number, gender, birth_day, cic, staff_address, image_link)
VALUES 
(1, 'Alice Jones', 'alicejones@example.com', '111-222-3333', 0, '1980-06-01', '1234567890', '123 Main St, Anytown, USA', 'https://example.com/image1.jpg'),
(2, 'Bob Smith', 'bobsmith@example.com', '444-555-6666', 1, '1995-05-10', '0987654321', '456 Oak Rd, Anytown, USA', 'https://example.com/image2.jpg'),
(3, 'Charlie Brown', 'charliebrown@example.com', '777-888-9999', 1, '1985-12-31', '1357924680', '789 Maple Ave, Anytown, USA', NULL);

INSERT INTO Account (account_id, account_type,staff_id , customer_id)
VALUES 
(1, 'Customer', NULL, 1),
(2, 'Customer', NULL, 2),
(3, 'Staff', 2, NULL),
(4, 'Staff', 3, NULL);

INSERT INTO Hotel (hotel_id, hotel_name, hotel_address, province, phone_number, email, website, image_link)
VALUES 
(1, 'Grand Hotel', '123 Main St', 'Ontario', '555-1234', 'info@grandhotel.com', 'www.grandhotel.com', 'https://example.com/grandhotel.jpg'),
(2, 'Seaside Inn', '456 Ocean Ave', 'Florida', '555-5678', 'info@seasideinn.com', 'www.seasideinn.com', 'https://example.com/seasideinn.jpg'),
(3, 'Mountain Lodge', '789 Forest Rd', 'Colorado', '555-9012', 'info@mountainlodge.com', 'www.mountainlodge.com', 'https://example.com/mountainlodge.jpg');

INSERT INTO Vehicle (vehicle_id, rental_company, model, color, license_plate, rental_rate, available)
VALUES 
(1, 'Hertz', 'Toyota Corolla', 'Silver', 'ABC123', 50.00, 1),
(2, 'Avis', 'Honda Civic', 'Black', 'DEF456', 45.00, 1),
(3, 'Enterprise', 'Ford Escape', 'White', 'GHI789', 65.00, 0),
(4, 'Budget', 'Tesla Model S', 'Red', 'JKL012', 150.00, 1),
(5, 'Alamo', 'Chevrolet Suburban', 'Black', 'MNO345', 100.00, 0);


--======================
INSERT INTO Place (place_id, place_name, dec, contents, image_link) VALUES 
(1, 'Ha Long Bay', 'Ha Long Bay description', 'Ha Long Bay contents', 'https://example.com/images/ha-long-bay.jpg'),
(2, 'Phong Nha Cave', 'Phong Nha Cave description', 'Phong Nha Cave contents', 'https://example.com/images/phong-nha-cave.jpg'),
(3, 'Sapa', 'Sapa description', 'Sapa contents', 'https://example.com/images/sapa.jpg'),
(4, 'Hoi An Ancient Town', 'Hoi An Ancient Town description', 'Hoi An Ancient Town contents', 'https://example.com/images/hoi-an-ancient-town.jpg'),
(5, 'Da Lat', 'Da Lat description', 'Da Lat contents', 'https://example.com/images/da-lat.jpg');

INSERT INTO TourGuider (tour_guider_id, full_name, birth_day, gender, address, tel, email, image_link, cic, salary) VALUES
(1, 'Nguyen Van A', '1990-01-01', 1, '123 Nguyen Trai, Hanoi', '0987654321', 'nguyenvana@gmail.com', NULL, 'ID123456', 10000000),
(2, 'Tran Thi B', '1995-05-10', 0, '456 Le Loi, Danang', '0123456789', 'tranthib@gmail.com', NULL,'ID789012', 8000000),
(3, 'Pham Van C', '1988-11-30', 1, '789 Nguyen Hue, HCMC', '0912345678', 'phamvanc@gmail.com', NULL, 'ID345678', 12000000),
(4, 'Le Thi D', '1992-07-15', 0, '456 Tran Phu, Nha Trang', '0367890123', 'lethid@gmail.com', NULL,'ID901234', 9000000),
(5, 'Hoang Van E', '1993-04-20', 1, '321 Cao Thang, Da Lat', '0543210987', 'hoangvane@gmail.com', NULL, 'ID567890', 11000000);

INSERT INTO Tour (tour_id, tour_guider_id, place_id, tour_name, place_go, date_go, date_back, num_person, price, image_link, state) VALUES
(1, 1, 1, 'Tour Ha Long Bay', 'Hanoi - Quang Ninh', '2023-06-01', '2023-06-04', 10, 5000000, NULL, 1),
(2, 2, 2, 'Tour Sapa', 'Hanoi - Lao Cai', '2023-07-01', '2023-07-04', 15, 7000000, NULL, 0),
(3, 3, 3, 'Tour Hoi An - Da Nang', 'HCMC - Da Nang', '2023-08-01', '2023-08-04', 20, 6000000, NULL, 1),
(4, 4, 4, 'Tour Nha Trang', 'HCMC - Khanh Hoa', '2023-09-01', '2023-09-04', 12, 8000000, NULL, 1),
(5, 5, 5, 'Tour Phu Quoc', 'HCMC - Kien Giang', '2023-10-01', '2023-10-04', 8, 9000000, NULL, 0);

INSERT INTO Booking (booking_id, customer_id, staff_id, vehicle_id, tour_id, hotel_id, booking_date, booking_type) VALUES 
(1, 1, 1, 1, NULL, NULL, '2023-05-07', 'Vehicle'),
(2, 2, 1, 2, NULL, NULL, '2023-05-07', 'Vehicle'),
(3, 3, 2, NULL, 1, NULL, '2023-05-07', 'Tour'),
(4, 3, 3, NULL, 2, NULL, '2023-05-07', 'Tour'),
(5, 2, 2, NULL, NULL, 1, '2023-05-07', 'Hotel'),
(6, 2, 3, NULL, NULL, 2, '2023-05-07', 'Hotel'),
(7, 1, 1, NULL, NULL, 3, '2023-05-07', 'Hotel')
;

INSERT INTO Room (RoomID, hotel_id, RoomNumber, RoomType, RoomCapacity, RoomStatus)
VALUES
    (1, 1, '101', 'Single', 1, 'Available'),
    (2, 1, '102', 'Double', 2, 'Occupied'),
    (3, 2, '201', 'Single', 1, 'Available'),
    (4, 2, '202', 'Double', 2, 'Under Maintenance'),
    (5, 3, '301', 'Suite', 4, 'Available');