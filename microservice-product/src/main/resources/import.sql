-- CATEGORIAS
INSERT INTO category (id, name) VALUES (1,'Sushi');
INSERT INTO category (id, name) VALUES (2,'Ramen');
INSERT INTO category (id, name) VALUES (3,'Tempura');
INSERT INTO category (id, name) VALUES (4,'Sake');
INSERT INTO category (id, name) VALUES (5,'Donburi');
INSERT INTO category (id, name) VALUES (6,'Yakitori');
INSERT INTO category (id, name) VALUES (7,'Udon');
INSERT INTO category (id, name) VALUES (8,'Postres Japoneses');
INSERT INTO category (id, name) VALUES (9, 'Carnes');
INSERT INTO category (id, name) VALUES (10, 'Pescado');
INSERT INTO category (id, name) VALUES (11, 'Mariscos');
INSERT INTO category (id, name) VALUES (12, 'Vinos');
INSERT INTO category (id, name) VALUES (13, 'Refrescos');
INSERT INTO category (id, name) VALUES (14, 'Menús');

-- PRODUCTOS

-- Sushi
INSERT INTO product (id, name, description, price, available, category_id) VALUES (1, 'Nigiri de salmón', 'Arroz prensado con lámina de salmón fresco.', 2.50, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (2, 'Maki de atún', 'Rollo de arroz y alga con atún en su interior.', 2.00, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (3, 'Sashimi de dorada', 'Láminas crudas de dorada servidas con salsa.', 3.50, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (4, 'Uramaki de aguacate', 'Rollo invertido con aguacate y pepino.', 2.20, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (5, 'Nigiri de langostino', 'Langostino cocido sobre arroz prensado.', 2.80, true, 1);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (59, 'Gunkan de huevas de salmón', 'Bocado de arroz envuelto en alga y coronado con huevas.', 3.00, true, 1);

-- Ramen
INSERT INTO product (id, name, description, price, available, category_id) VALUES (6, 'Tonkotsu Ramen', 'Caldo espeso de cerdo, fideos, huevo y chashu.', 9.50, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (7, 'Shoyu Ramen', 'Caldo de soja con alga nori, naruto y huevo.', 9.00, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (8, 'Miso Ramen', 'Ramen de caldo de miso con maíz y verduras.', 9.00, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (9, 'Spicy Ramen', 'Ramen picante con chile, carne y huevo.', 9.50, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (10, 'Vegetarian Ramen', 'Caldo vegetal con fideos y tofu.', 8.50, true, 2);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (60, 'Shio Ramen', 'Ramen con caldo salado ligero y toppings tradicionales.', 8.80, true, 2);

-- Tempura
INSERT INTO product (id, name, description, price, available, category_id) VALUES (11, 'Tempura de langostinos', 'Langostinos rebozados y fritos al estilo japonés.', 6.00, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (12, 'Tempura de verduras', 'Verduras variadas con rebozado ligero.', 5.50, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (13, 'Tempura de calabaza', 'Calabaza dulce frita en tempura.', 4.50, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (14, 'Tempura de setas', 'Setas shitake crujientes y sabrosas.', 5.00, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (15, 'Tempura mixta', 'Langostino, verdura y pescado en rebozado crujiente.', 6.50, true, 3);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (61, 'Tempura de calamares', 'Anillos de calamar en tempura crujiente.', 5.80, true, 3);

-- Sake
INSERT INTO product (id, name, description, price, available, category_id) VALUES (16, 'Sake Junmai', 'Sake puro, sin aditivos, seco y equilibrado.', 4.00, true, 4);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (17, 'Sake Nigori', 'Sake sin filtrar, textura suave y dulce.', 4.50, true, 4);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (18, 'Sake Daiginjo', 'Sake premium muy refinado y aromático.', 6.00, true, 4);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (19, 'Sake caliente', 'Sake clásico servido caliente.', 3.50, true, 4);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (20, 'Sake espumoso', 'Sake con burbujas, fresco y afrutado.', 5.50, true, 4);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (62, 'Sake Umeshu', 'Licor de ciruela japonesa suave y afrutado.', 5.20, true, 4);

-- Donburi
INSERT INTO product (id, name, description, price, available, category_id) VALUES (21, 'Gyudon', 'Arroz cubierto con carne de ternera y cebolla.', 8.50, true, 5);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (22, 'Katsudon', 'Filete de cerdo empanado sobre arroz y huevo.', 8.50, true, 5);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (23, 'Oyakodon', 'Pollo y huevo cocido sobre arroz caliente.', 8.00, true, 5);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (24, 'Unadon', 'Anguila glaseada con arroz y salsa dulce.', 10.00, true, 5);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (25, 'Tendon', 'Tempura variada servida sobre arroz.', 9.00, true, 5);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (63, 'Butadon', 'Donburi de cerdo glaseado en salsa dulce.', 8.80, true, 5);

-- Yakitori
INSERT INTO product (id, name, description, price, available, category_id) VALUES (26, 'Yakitori de muslo', 'Brocheta de pollo jugoso con salsa tare.', 2.20, true, 6);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (27, 'Yakitori de piel', 'Brocheta de piel de pollo dorada y crujiente.', 2.00, true, 6);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (28, 'Yakitori de albóndigas (tsukune)', 'Brochetas de albóndigas de pollo y huevo.', 2.80, true, 6);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (29, 'Yakitori de puerro', 'Brochetas de puerro y pollo alternados.', 2.30, true, 6);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (30, 'Yakitori de hígado', 'Brocheta de hígado de pollo con salsa.', 2.00, true, 6);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (64, 'Yakitori de corazón', 'Brocheta de corazón de pollo a la parrilla.', 2.10, true, 6);

-- Udon
INSERT INTO product (id, name, description, price, available, category_id) VALUES (31, 'Kake Udon', 'Sopa básica de udon con caldo dashi.', 7.50, true, 7);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (32, 'Curry Udon', 'Fideos gruesos en caldo de curry.', 8.00, true, 7);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (33, 'Tempura Udon', 'Udon servido con tempura crujiente.', 8.50, true, 7);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (34, 'Niku Udon', 'Udon con ternera dulce-salada.', 8.50, true, 7);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (35, 'Yaki Udon', 'Udon salteado al wok con verduras.', 8.00, true, 7);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (65, 'Kitsune Udon', 'Udon con tofu frito y caldo suave.', 7.80, true, 7);

-- Postres Japoneses
INSERT INTO product (id, name, description, price, available, category_id) VALUES (36, 'Mochi de fresa', 'Pastelito de arroz relleno de fresa.', 2.50, true, 8);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (37, 'Dorayaki', 'Doble pancake con anko (pasta de judía dulce).', 3.00, true, 8);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (38, 'Taiyaki', 'Pastel en forma de pez relleno de crema.', 3.00, true, 8);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (39, 'Helado de té verde', 'Helado artesanal de matcha.', 2.80, true, 8);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (40, 'Anmitsu', 'Gelatina con frutas, anko y sirope.', 3.20, true, 8);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (66, 'Kuzumochi', 'Postre gelatinoso con sirope kuromitsu y kinako.', 3.00, true, 8);

-- Carnes
INSERT INTO product (id, name, description, price, available, category_id) VALUES (41, 'Ternera teriyaki', 'Filete de ternera glaseado en salsa teriyaki.', 10.00, true, 9);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (42, 'Pollo karaage', 'Pollo frito marinado al estilo japonés.', 7.50, true, 9);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (43, 'Hamburguesa japonesa (Wafu)', 'Hamburguesa de ternera con salsa ponzu y rábano.', 9.00, true, 9);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (44, 'Tonkatsu', 'Lomo de cerdo empanado crujiente.', 8.50, true, 9);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (45, 'Yakiniku', 'Tiras de ternera asadas a la parrilla con salsa.', 9.50, true, 9);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (46, 'Pollo con miso', 'Pechuga de pollo a la plancha con salsa miso.', 7.80, true, 9);

-- Pescado
INSERT INTO product (id, name, description, price, available, category_id) VALUES (47, 'Salmón a la parrilla', 'Salmón asado con salsa cítrica japonesa.', 9.00, true, 10);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (48, 'Caballa al miso', 'Caballa cocida en salsa de miso y mirin.', 8.50, true, 10);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (49, 'Salmón teriyaki', 'Salmón glaseado con salsa teriyaki.', 9.50, true, 10);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (50, 'Tataki de atún', 'Atún marcado al fuego con sésamo y soja.', 10.50, true, 10);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (51, 'Pescado frito nanbanzuke', 'Pescado frito en escabeche japonés.', 8.00, true, 10);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (52, 'Rollito de pez mantequilla', 'Finas lonchas de pez mantequilla con cebollino.', 10.00, true, 10);

-- Marisco
INSERT INTO product (id, name, description, price, available, category_id) VALUES (53, 'Langostinos al vapor', 'Langostinos cocidos al vapor con limón.', 9.00, true, 11);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (54, 'Vieiras a la plancha', 'Vieiras con mantequilla y salsa de soja.', 11.00, true, 11);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (55, 'Tako wasabi', 'Pulpo troceado marinado en wasabi suave.', 5.50, true, 11);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (56, 'Calamar a la plancha', 'Calamar entero asado con salsa tare.', 9.50, true, 11);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (57, 'Langostinos en tempura de coco', 'Langostinos fritos con cobertura de coco.', 10.00, true, 11);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (58, 'Mariscos mixtos al sake', 'Surtido de mariscos salteados al vapor con sake.', 11.50, true, 11);

-- vinos
INSERT INTO product (id, name, description, price, available, category_id) VALUES (67, 'Umeshu Premium', 'Licor de ciruela japonés envejecido.', 6.50, true, 12);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (68, 'Koshu Blanco', 'Vino blanco japonés suave y afrutado.', 7.00, true, 12);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (69, 'Muscat Bailey A', 'Vino tinto japonés afrutado con notas de cereza.', 7.20, true, 12);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (70, 'Rioja Crianza', 'Vino tinto español con notas de roble y frutos rojos.', 6.80, true, 12);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (71, 'Albariño', 'Vino blanco gallego seco y refrescante.', 6.50, true, 12);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (72, 'Ribera del Duero', 'Vino tinto español robusto y elegante.', 7.50, true, 12);

-- Refrescos
INSERT INTO product (id, name, description, price, available, category_id) VALUES (73, 'Ramune Clásico', 'Refresco japonés con gas y sabor a limón.', 2.50, true, 13);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (74, 'Té verde frío', 'Bebida refrescante de té verde sin azúcar.', 2.20, true, 13);
INSERT INTO product (id, name, description, price, available, category_id) VALUES (75, 'Calpis Soda', 'Refresco dulce y espumoso con sabor lácteo.', 2.70, true, 13);


-- MENUS
-- Menús
INSERT INTO menu (id, name, description, total_price, active) VALUES (1, 'Menú Sushi Clásico', 'Una selección variada de sushi tradicional.', 15.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (2, 'Menú Ramen Deluxe', 'Ramen con toppings variados y sabores intensos.', 12.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (3, 'Menú Tempura Mixto', 'Selección de tempuras de vegetales y mariscos.', 11.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (4, 'Menú Donburi Completo', 'Bowl de arroz con combinaciones de carne y pescado.', 13.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (5, 'Menú Yakitori Tradicional', 'Brochetas variadas al estilo japonés.', 9.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (6, 'Menú Mar y Tierra', 'Una combinación de carnes y mariscos premium.', 17.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (7, 'Menú Vegetariano', 'Opciones japonesas sin carne ni pescado.', 10.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (8, 'Menú Dulce Japonés', 'Postres tradicionales japoneses para finalizar.', 6.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (9, 'Menú Udon y Tempura', 'Fideos udon con tempura variada.', 13.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (10, 'Menú Yakitori Deluxe', 'Brochetas yakitori con acompañamientos variados.', 11.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (11, 'Menú Sake y Sushi', 'Selección de sushi con sake tradicional.', 14.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (12, 'Menú Pescado Premium', 'Cortes seleccionados de pescado japonés.', 16.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (13, 'Menú Mariscos Especial', 'Combinación de mariscos frescos al estilo japonés.', 16.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (14, 'Menú Bento Mixto', 'Bento box con sushi, carne y arroz.', 13.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (15, 'Menú Infantil Japonés', 'Plato combinado adaptado para niños.', 8.00, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (16, 'Menú Degustación Tradicional', 'Variedad de platos japoneses para compartir.', 18.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (17, 'Bento con Ramune', 'Bento box completo acompañado con refresco Ramune.', 14.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (18, 'Udon y Té Verde', 'Menú ligero y refrescante con udon frío y té.', 10.50, true);
INSERT INTO menu (id, name, description, total_price, active) VALUES (19, 'Infantil con Calpis', 'Menú para niños con opciones suaves y refresco.', 9.00, true);


-- Productos en menús
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (1, 1, 1, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (2, 1, 2, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (3, 1, 3, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (4, 1, 59, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (5, 1, 5, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (6, 2, 6, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (7, 2, 7, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (8, 2, 60, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (9, 3, 11, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (10, 3, 12, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (11, 3, 15, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (12, 3, 61, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (13, 4, 21, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (14, 4, 22, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (15, 4, 63, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (16, 5, 26, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (17, 5, 27, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (18, 5, 28, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (19, 5, 64, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (20, 6, 41, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (21, 6, 45, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (22, 6, 53, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (23, 6, 58, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (24, 7, 10, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (25, 7, 12, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (26, 7, 35, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (27, 7, 65, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (28, 8, 36, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (29, 8, 38, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (30, 8, 66, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (31, 9, 31, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (32, 9, 61, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (33, 9, 12, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (34, 10, 26, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (35, 10, 28, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (36, 10, 29, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (37, 10, 64, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (38, 11, 1, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (39, 11, 3, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (40, 11, 59, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (41, 11, 16, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (42, 12, 47, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (43, 12, 50, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (44, 12, 52, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (45, 13, 53, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (46, 13, 54, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (47, 13, 58, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (48, 14, 1, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (49, 14, 41, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (50, 14, 21, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (51, 15, 10, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (52, 15, 36, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (53, 15, 65, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (54, 16, 2, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (55, 16, 6, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (56, 16, 15, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (57, 16, 24, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (58, 16, 66, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (59, 17, 22, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (60, 17, 12, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (61, 17, 73, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (62, 18, 31, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (63, 18, 74, 1);

INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (64, 19, 10, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (65, 19, 36, 1);
INSERT INTO menu_producto (id, menu_id, producto_id, cantidad) VALUES (66, 19, 75, 1);
