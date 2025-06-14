-- USERS
INSERT INTO user_table (id, name, auth_email, phone, created_at, updated_at) VALUES (1, 'admin1', 'admin1@gmail.com', '+34111111111', NOW(), NOW());
INSERT INTO user_table (id, name, auth_email, phone, created_at, updated_at) VALUES (2, 'cook1', 'cook1@gmail.com', '+34222222222', NOW(), NOW());
INSERT INTO user_table (id, name, auth_email, phone, created_at, updated_at) VALUES (3, 'cliente1', 'cliente1@gmail.com', '+34333333333', NOW(), NOW());

-- ADDRESSES
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (1, 'Calle Sakura 12', 'Madrid', '28001', 'España', 3);
INSERT INTO address (id, street, city, postal_code, country, user_id) VALUES (2, 'Av. del Sol 45', 'Barcelona', '08021', 'España', 3);
