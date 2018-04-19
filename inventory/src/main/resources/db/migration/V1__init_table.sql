create table inventories(
  id VARCHAR(32) PRIMARY KEY,
  product_id VARCHAR(32) NOT NULL,
  amount INT NOT NULL,
  create_at BIGINT NOT NULL
);

create table outbound_orders(
  id VARCHAR(32) PRIMARY KEY,
  previous_inventory_id VARCHAR(32),
  orderitem_id VARCHAR(32),
  product_id VARCHAR(32) NOT NULL,
  quantity INT NOT NULL,
  create_at BIGINT NOT NULL,

  foreign key (previous_inventory_id) references inventories(id)
);

