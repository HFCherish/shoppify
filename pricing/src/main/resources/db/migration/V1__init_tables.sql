CREATE TABLE pricings (
  id VARCHAR(32) PRIMARY KEY,
  product_id VARCHAR(32) NOT NULL,
  value DOUBLE NOT NULL,
  create_at BIGINT NOT NULL
);