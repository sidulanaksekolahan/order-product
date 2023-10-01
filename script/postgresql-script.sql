DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;

-- To install the uuid-ossp module
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
	customer_id VARCHAR DEFAULT uuid_generate_v4(),
	customer_name VARCHAR NOT NULL,
	address VARCHAR NOT NULL,
	phone INT NOT NULL,
	PRIMARY KEY(customer_id)
);

CREATE TABLE product (
	product_id VARCHAR DEFAULT uuid_generate_v4(),
	product_price VARCHAR NOT NULL,
	product_description VARCHAR NOT NULL,
	stock INT,
	PRIMARY KEY (product_id)
);

CREATE TABLE orders (
	order_id VARCHAR DEFAULT uuid_generate_v4(),
	customer_id VARCHAR NOT NULL,
	customer_name VARCHAR NOT NULL,
	amount NUMERIC NOT NULL,
	quantity INT,
	product_id VARCHAR NOT NULL,
	order_date TIMESTAMP NOT NULL,
	PRIMARY KEY (order_id),
	CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
	CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);






