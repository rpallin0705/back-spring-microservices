-- Categorías
INSERT INTO category (id, name) VALUES (1, 'Hamburguesas');
INSERT INTO category (id, name) VALUES (2, 'Bebidas');
INSERT INTO category (id, name) VALUES (3, 'Complementos');

-- Productos
INSERT INTO product (id, name, description, price, available, category_id) VALUES (1, 'Hamburguesa Clásica', 'Hamburguesa con queso, lechuga y tomate', 5.99, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (2, 'Refresco Grande', 'Bebida fría de 500ml', 1.99, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (3, 'Patatas Fritas', 'Ración mediana de papas', 2.49, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (4, 'Hamburguesa BBQ', 'Con salsa barbacoa y cebolla caramelizada', 6.49, true, 1);

-- Menús
INSERT INTO menu (id, name, description, total_price, active) VALUES (1, 'Menú Clásico', 'Incluye hamburguesa, bebida y papas', 10.47, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (2, 'Menú BBQ', 'Hamburguesa BBQ con bebida', 8.48, true);

-- Productos por Menú
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (1, 1, 1, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (2, 1, 2, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (3, 1, 3, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (4, 2, 4, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (5, 2, 2, 1);