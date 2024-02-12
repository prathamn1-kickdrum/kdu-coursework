# Assignment 3

## SQL Script for Database Initialization

```sql
-- Table: devices 
CREATE TABLE devices (
    kickston_id VARCHAR(255) NOT NULL PRIMARY KEY,
    device_password VARCHAR(255),
    device_username VARCHAR(255),
    manufacture_date_time VARCHAR(255),
    manufacture_factory_place VARCHAR(255),
    registered BOOLEAN NOT NULL,
    room_id BIGINT,
    CONSTRAINT fk89ytayyehi53yn26m30x8eg9r FOREIGN KEY (room_id) REFERENCES rooms
);



-- Table: houses 
CREATE TABLE houses (
    house_id BIGSERIAL PRIMARY KEY,
    address VARCHAR(255),
    house_admin VARCHAR(255),
    house_name VARCHAR(255)
);


-- Table: kickston_sequence 
CREATE TABLE kickston_sequence (
    id BIGSERIAL PRIMARY KEY
);

ALTER TABLE kickston_sequence OWNER TO kdu;

-- Table: rooms 
CREATE TABLE rooms (
    room_id BIGSERIAL PRIMARY KEY,
    room_name VARCHAR(255),
    house_id BIGINT,
    CONSTRAINT fk8le2724arhisppb0tutvundby FOREIGN KEY (house_id) REFERENCES houses
);



-- Table: user_house 
CREATE TABLE user_house (
    user_id BIGINT NOT NULL,
    house_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, house_id),
    CONSTRAINT fkb4kfrhce78atg6ehcb933xhm2 FOREIGN KEY (user_id) REFERENCES users,
    CONSTRAINT fksblbin2jikgqihrt01aor36jc FOREIGN KEY (house_id) REFERENCES houses
);


-- Table: users 
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    username VARCHAR(255)
);


-- Sequences 
CREATE SEQUENCE houses_house_id_seq;
ALTER SEQUENCE houses_house_id_seq OWNED BY houses.house_id;

CREATE SEQUENCE kickston_sequence_id_seq;
ALTER SEQUENCE kickston_sequence_id_seq OWNED BY kickston_sequence.id;

CREATE SEQUENCE rooms_room_id_seq;
ALTER SEQUENCE rooms_room_id_seq OWNED BY rooms.room_id;

CREATE SEQUENCE users_user_id_seq;
ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;
```

API contracts are as follows:

# User Controller

## API Endpoint: `POST /api/v1/auth/register`

### Description:

This API endpoint is used to register a new user.

### Request:

- **Method:** `POST`
- **Path:** `/register`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `username` (Type: String) - The username of the user.
        - `password` (Type: String) - The password for the user.
        - `name` (Type: String) - The full name of the user.
        - `firstName` (Type: String) - The first name of the user.
        - `lastName` (Type: String) - The last name of the user.
        - `emailId` (Type: String) - The email ID of the user.

### Response:

- **Success Response:**
    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `token` (Type: String) - The authentication token for the registered user.

# House Controller

## API Endpoint: `POST /api/v1/house`

### Description:

This API endpoint is used to add a new house.

### Request:

- **Method:** `POST`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `address` (Type: String) - The address of the house.
        - `house_name` (Type: String) - The name of the house.
        - `latitude` (Type: Double) - The latitude of the house's location.
        - `longitude` (Type: Double) - The longitude of the house's location.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `house` (Type: House) - Details of the added house.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.

## API Endpoint: `POST /{houseId}/add-user`

### Description
This API endpoint is used to add a user to a house.

### Request:
- **Method:** `POST`
- **Path Parameters:**
    - `houseId` (Type: String) - The identifier of the house to which the user will be added.
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `username` (Type: String) - The username of the user to be added to the house

### Response:
- **Success Response:**
    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `object` (Type: String) - Additional information about the added user.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.

### API Endpoint: `GET /api/v1/house`

#### Description:


This API endpoint retrieves a list of houses.

#### Request:

- **Method:** `GET`
- **Query Parameters:**
    - `pageNumber` (Type: Integer, Default: 0) - The page number for pagination.
    - `pageSize` (Type: Integer, Default: 100) - The number of houses to include per page.

#### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `houses` (Type: String) - JSON representation of the list of houses.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `500 Internal Server Error`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.



### API Endpoint: `PUT /api/v1/house`

#### Description:

This API endpoint is used to update the address of a house.

#### Request:

- **Method:** `PUT`
- **Query Parameter:**
    - `houseId` (Type: String) - The identifier of the house for which the address will be updated.
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `newAddress` (Type: String) - The new address to be set for the house.

#### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `object` (Type: String) - Additional information about the updated address.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.


## API Endpoint: `GET /api/v1/house/{houseId}`

### Description:

This API endpoint retrieves all rooms and devices associated with a specific house.

### Request:

- **Method:** `GET`
- **Path Parameters:**
    - `houseId` (Type: String) - The identifier of the house for which rooms and devices will be retrieved.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `roomsAndDevices` (Type: String) - JSON representation of rooms and devices in the specified house.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.

## API Endpoint: `POST /api/v1/house`

### Description:

This API endpoint is used to add a new house.

### Request:

- **Method:** `POST`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `address` (Type: String) - The address of the house.
        - `house_name` (Type: String) - The name of the house.
        - `latitude` (Type: Double) - The latitude of the house's location.
        - `longitude` (Type: Double) - The longitude of the house's location.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `house` (Type: House) - Details of the added house.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.

# Room Controller

### API Endpoint : `POST/room`

#### Description:

This API endpoint is used to add rooms to a house.

#### Request:

- **Method:** `POST`
- **Query Parameter:**
    - `houseId` (Type: String) - The identifier of the house to which the rooms will be added.
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `room_name` (Type: String) - The name of the room to be added.

#### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `room` (Type: Room) - Details of the added room.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.



# Inventory Controller

## API Endpoint: `GET /api/v1/inventory`

### Description:

This API endpoint retrieves the list of items in the inventory.

### Request:

- **Method:** `GET`

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `inventory` (Type: String) - JSON representation of the list of items in the inventory.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `500 Internal Server Error`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.

## API Endpoint: `POST /api/v1/inventory`

### Description:

This API endpoint is used to add an item to the inventory.
### Request:

- **Method:** `POST`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `kickston_id` (Type: String) - The identifier for the inventory item.
        - `device_username` (Type: String) - The username associated with the device.
        - `device_password` (Type: String) - The password associated with the device.
        - `manufacture_date_time` (Type: String) - The manufacturing date and time of the device (format: "yyyy-MM-dd'T'HH:mm:ss").
        - `manufacture_factory_place` (Type: String) - The place where the device was manufactured.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `object` (Type: String) - Additional information about the added inventory item.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `500 Internal Server Error`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.




# Device Controller

## API Endpoint: `POST /api/v1/device/register`

### Description:

This API endpoint is used to register a new device.

### Request:

- **Method:** `POST`
- **Path:** `/register`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `kickston_id` (Type: String) - The identifier for the device.
        - `device_username` (Type: String) - The username associated with the device.
        - `device_password` (Type: String) - The password associated with the device.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `object` (Type: String) - Additional information about the registered device.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.





## API Endpoint: `POST /api/v1/device/add`

### Description:

This API endpoint is used to add a device to a house.

### Request:

- **Method:** `POST`
- **Path:** `/add`
- **Request Body:**
    - **Content-Type:** `application/json`
    - **Attributes:**
        - `houseId` (Type: String) - The identifier of the house to which the device will be added.
        - `roomId` (Type: String) - The identifier of the room in the house where the device will be placed.
        - `kickstonId` (Type: String) - The identifier of the device to be added.

### Response:

- **Success Response:**

    - **HTTP Status:** `200 OK`
    - **Body:**
        - `message` (Type: String) - A success message.
        - `object` (Type: String) - Additional information about the added device.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.
- **Error Response:**

    - **HTTP Status:** `400 Bad Request`
    - **Body:**
        - `message` (Type: String) - An error message describing the issue.
        - `httpStatus` (Type: HttpStatus) - HTTP status of the response.






