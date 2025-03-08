CREATE TABLE product_category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE barcode
(
    id    SERIAL PRIMARY KEY,
    value VARCHAR(255) NOT NULL UNIQUE,
    type  VARCHAR(50)  NOT NULL
);

CREATE TABLE shop
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    description  TEXT,
    standardSize VARCHAR(10)  NOT NULL,
    barcode_id   INTEGER REFERENCES barcode (id),
    category_id  INTEGER      NOT NULL REFERENCES product_category (id),
    deleted_at   TIMESTAMP
);

CREATE TABLE product_price
(
    id            SERIAL PRIMARY KEY,
    product_id    INTEGER        NOT NULL REFERENCES product (id),
    shop_id       INTEGER REFERENCES shop (id),
    amount        DECIMAL(10, 2) NOT NULL,
    currency_code VARCHAR(3)     NOT NULL,
    date_recorded TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category_id   INTEGER        NOT NULL REFERENCES product_category (id),
    deleted_at    TIMESTAMP
);