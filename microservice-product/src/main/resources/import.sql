-- Categorías
INSERT INTO category (id, name) VALUES (1, 'Hamburguesas');
INSERT INTO category (id, name) VALUES (2, 'Bebidas');
INSERT INTO category (id, name) VALUES (3, 'Complementos');

-- Productos
INSERT INTO product (id, name, description, price, available, category_id) VALUES (1, 'Hamburguesa Clásica', 'Hamburguesa con queso, lechuga y tomate', 5.99, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (2, 'Refresco Grande', 'Bebida fría de 500ml', 1.99, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (3, 'Patatas Fritas', 'Ración mediana de papas', 2.49, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (4, 'Hamburguesa BBQ', 'Con salsa barbacoa y cebolla caramelizada', 6.49, true, 1);
