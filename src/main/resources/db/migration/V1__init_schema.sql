CREATE TABLE product_category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE barcode
(
    id    BIGSERIAL PRIMARY KEY,
    value VARCHAR(255) NOT NULL,
    type  VARCHAR(8)  NOT NULL,
    CONSTRAINT barcode_value_type_unique UNIQUE (value, type)
);

CREATE TABLE shop
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE product
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   TEXT,
    standard_size VARCHAR(10)  NOT NULL,
    barcode_id    BIGINT REFERENCES barcode (id),
    category_id   INTEGER      NOT NULL REFERENCES product_category (id),
    deleted_at    TIMESTAMP
);

CREATE TABLE product_price
(
    id            BIGSERIAL PRIMARY KEY,
    product_id    BIGINT        NOT NULL REFERENCES product (id),
    shop_id       INTEGER REFERENCES shop (id),
    amount        DECIMAL(10, 2) NOT NULL,
    currency_code VARCHAR(3)     NOT NULL,
    date_recorded TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP
);