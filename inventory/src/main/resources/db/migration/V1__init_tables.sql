CREATE TABLE inventories (
  id VARCHAR(32) PRIMARY KEY,
  product_id VARCHAR(32) NOT NULL,
  amount INT NOT NULL,
  create_at BIGINT NOT NULL
);