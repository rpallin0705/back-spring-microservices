-- Pedidos
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (1, 1, 1, '2025-05-14 12:00:00', 'CREATED', 10.97);
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (2, 2, 2, '2025-05-14 13:00:00', 'IN_PREPARATION', 8.98);
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (3, 3, 3, '2025-05-14 14:00:00', 'CREATED', 10.47); -- pedido solo con menú
INSERT INTO order_table (id, user_id, address_id, created_at, status, total_price) VALUES (4, 4, 4, '2025-05-14 15:00:00', 'CREATED', 16.46); -- pedido mixto

-- Ítems del pedido
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (1, 1, 1, NULL, 1, 5.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (2, 1, 2, NULL, 1, 1.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (3, 1, 3, NULL, 1, 2.99);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (4, 2, 4, NULL, 1, 6.49);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (5, 2, 2, NULL, 1, 1.99);

-- Pedido con solo menú
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (6, 3, NULL, 1, 1, 10.47);

-- Pedido mixto (menu + producto individual)
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (7, 4, NULL, 2, 1, 8.48);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (8, 4, 3, NULL, 1, 2.49);
INSERT INTO order_item (id, order_id, product_id, menu_id, quantity, price) VALUES (9, 4, 2, NULL, 1, 1.49);

-- Historial de estados
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (1, 1, 'CREATED', '2025-05-14 12:00:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (2, 2, 'CREATED', '2025-05-14 13:00:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (3, 2, 'IN_PREPARATION', '2025-05-14 13:05:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (4, 3, 'CREATED', '2025-05-14 14:00:00');
INSERT INTO order_status_history (id, order_id, status, changed_at) VALUES (5, 4, 'CREATED', '2025-05-14 15:00:00');