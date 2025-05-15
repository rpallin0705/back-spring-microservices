-- Pedidos
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (1, 101, 201, '2025-05-14 12:00:00', 'CREATED', 15.99);

INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (2, 102, 202, '2025-05-14 13:00:00', 'IN_PREPARATION', 23.50);

-- Ítems del pedido
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (1, 1, 301, NULL, 2, 5.99);

INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (2, 1, NULL, 401, 1, 3.99);

INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (3, 2, 302, NULL, 1, 8.50);

INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (4, 2, 303, NULL, 1, 15.00);

-- Historial de estados
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (1, 1, 'CREATED', '2025-05-14 12:00:00');

INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (2, 2, 'CREATED', '2025-05-14 13:00:00');

INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (3, 2, 'IN_PREPARATION', '2025-05-14 13:05:00');
