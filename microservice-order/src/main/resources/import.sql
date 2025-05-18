INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (1, 101, 201, '2025-05-14 12:00:00', 'CREATED', 10.97);
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (2, 102, 202, '2025-05-14 13:00:00', 'IN_PREPARATION', 8.98);

-- Ítems del pedido
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (1, 1, 1, NULL, 1, 5.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (2, 1, 2, NULL, 1, 1.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (3, 1, 3, NULL, 1, 2.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (4, 2, 4, NULL, 1, 6.49);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (5, 2, 2, NULL, 1, 1.99);

-- Historial de estados
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (1, 1, 'CREATED', '2025-05-14 12:00:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (2, 2, 'CREATED', '2025-05-14 13:00:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (3, 2, 'IN_PREPARATION', '2025-05-14 13:05:00');