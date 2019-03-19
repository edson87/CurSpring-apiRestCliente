/*Copulando datos*/
INSERT INTO regiones (id, nombre) VALUES(1, 'Sudamerica');
INSERT INTO regiones (id, nombre) VALUES(2, 'Centroamerica');
INSERT INTO regiones (id, nombre) VALUES(3, 'Norteamerica');
INSERT INTO regiones (id, nombre) VALUES(4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES(5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES(6, 'Oceania');

INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'Andres', 'Guzman', 'profesor@gmail.com', '2018-01-01');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(2, 'Jhon', 'Doe', 'jhon@gmail.com', '2018-01-02');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Andrea', 'Peralta', 'andrea@gmail.com', '2018-01-02');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(4, 'Juan', 'Antezana', 'juan@gmail.com', '2018-01-05');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(1, 'Pablo', 'Balco', 'pablo@gmail.com', '2018-01-06');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Liz', 'Moya', 'liz@gmail.com', '2018-02-06');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(3, 'Maria', 'Crespo', 'maria@gmail.com', '2018-02-10');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(5, 'Pedro', 'Rivera', 'pedro@gmail.com', '2018-03-01');
INSERT INTO clientes (region_id, nombre, apellido, email, create_at) VALUES(6, 'Ruben', 'Morales', 'ruben@gmail.com', '2018-03-11');

/*Creamos usuarios y roles */
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$0zCDk4/.DeiUA08CcSwgmusmGwkbp0TOvAwMx0UKAP4ikvD9eIdCq',1, 'Andres','Guzman','profesor@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$NdDjJP7BED1DQt2YiKeCluQ0W/iDC38ymLveABrzAIV.rpCYVzFke',1, 'Jhon','Doe', 'jhon@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);

