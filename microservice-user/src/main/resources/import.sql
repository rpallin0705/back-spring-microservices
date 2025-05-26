-- User
INSERT INTO user_table (id, name, email, phone, role, created_at, updated_at) VALUES (1, 'Carlos López', 'carlos@example.com', '654321987', 'CLIENTE', '2025-05-21 10:00:00', '2025-05-21 10:00:00');
INSERT INTO user_table (id, name, email, phone, role, created_at, updated_at) VALUES (2, 'Lucía Fernández', 'lucia@example.com', '611223344', 'COCINERO', '2025-05-21 10:10:00', '2025-05-21 10:10:00');
INSERT INTO user_table (id, name, email, phone, role, created_at, updated_at) VALUES (3, 'Andrés Martín', 'andres@example.com', '600112233', 'REPARTIDOR', '2025-05-21 10:20:00', '2025-05-21 10:20:00');
INSERT INTO user_table (id, name, email, phone, role, created_at, updated_at) VALUES (4, 'Admin Test', 'admin@example.com', '600000001', 'ADMIN', '2025-05-21 10:30:00', '2025-05-21 10:30:00');

-- Address
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (1, 'Calle Gran Vía 1', 'Madrid', '28013', 'España', 1);
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (2, 'Avenida Diagonal 45', 'Barcelona', '08019', 'España', 2);
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (3, 'Calle Larios 7', 'Málaga', '29005', 'España', 3);
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (4, 'Calle Mayor 100', 'Valencia', '46001', 'España', 4);
