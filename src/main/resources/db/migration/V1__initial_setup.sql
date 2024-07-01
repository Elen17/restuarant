CREATE TABLE order_status
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE customer
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    name       VARCHAR(511) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    country    VARCHAR(255),
    city       VARCHAR(255),
    street     VARCHAR(255),
    state      VARCHAR(255),
    zip        VARCHAR(255),
    phone      VARCHAR(255)
);

CREATE TABLE item
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE `order`
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_date TIMESTAMP NOT NULL,
    updated_date TIMESTAMP NOT NULL,
    customer_id  BIGINT NOT NULL,
    total_amount DOUBLE NOT NULL,
    status_id    INT NOT NULL,
    CONSTRAINT fk_order_status_id FOREIGN KEY (status_id)
        REFERENCES order_status (id),
    CONSTRAINT fk_order_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
        ON DELETE CASCADE
);

CREATE TABLE order_item
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    item_id  INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id) REFERENCES `order` (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_order_item_item_id FOREIGN KEY (item_id) REFERENCES item (id)
        ON DELETE CASCADE
);
