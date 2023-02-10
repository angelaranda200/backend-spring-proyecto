INSERT INTO departamento (descripcion, email, indicator, nombre, status) VALUES ('Central', 'mda@footloose.com', 'si', 'Desarrollo TI', 'OK');

INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Baja', 'si', 'BAJA', 'Ok');
INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Media', 'si', 'MEDIA', 'Ok');
INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Alta', 'si', 'ALTA', 'Ok');


INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Falla del POS', 'si', 'POS', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Problema con el Internet', 'si', 'Conexion de Internet', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Problema con la data de Almacen', 'si', 'Almacen', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Cambio de documento', 'si', 'Cambio de Documento', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Eliminar nota de credito', 'si', 'Eliminar NC', 'Ok');

INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('En progreso', 'si', 'EN CURSO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Escalado', 'si', 'ESCALADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Anulado', 'si', 'CANCELADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Abierto', 'si', 'ABIERTO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('En pausa', 'si', 'PAUSADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Terminado', 'si', 'FINALIZADO', 'Ok');

INSERT INTO plan_sla (indicator, nombre, periodo_de_gracia, status) VALUES ('si', 'Soporte', 'Indefinido', 'Ok');
INSERT INTO plan_sla (indicator, nombre, periodo_de_gracia, status) VALUES ('si', 'Oficina', 'Indefinido', 'Ok');

INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Sistema de Ventas', 'si', 'SISTEMA DE VENTAS', 'Ok', '1');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Ecomerce', 'si', 'ECOMERCE', 'Ok', '2');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Hardware', 'si', 'HARDWARE', 'Ok', '2');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Catalogo', 'si', 'CATALOGO', 'Ok', '1');

INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TSJL1', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TSJL2', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TGMR1', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TSBJS1', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TSJL3', 'Ok');


INSERT INTO usuarios (apellidos, celular, email, fecha_creado, indicator, nombres, password, status, telefono, username, area_id_area) VALUES ('aranda vilca', '123465', 'angel@gmail.com', '2022-08-04', 'si', 'angel alex', '1345', 'Ok', '79845', 'AD030465', '1');
INSERT INTO usuarios (apellidos, celular, email, fecha_creado, indicator, nombres, password, status, telefono, username, area_id_area) VALUES ('rojas magallanes', '123465', 'ronny@gmail.com', '2022-08-04', 'si', 'ronny', '1345', 'Ok', '79845', 'US220532', '1');


INSERT INTO role (nombre_role) VALUES ('ADMIN');
INSERT INTO role (nombre_role) VALUES ('USUARIO');

/*
INSERT INTO usuario_rol (usuariorol_id,role_id_role,usuario_id_usuario) VALUES (1,1,1);
INSERT INTO usuario_rol (usuariorol_id,role_id_role,usuario_id_usuario) VALUES (2,2,2);
*/
 
 
INSERT INTO role_usuarios (usuario_id,role_id) VALUES (1,1);
INSERT INTO role_usuarios (usuario_id,role_id) VALUES (2,2);


INSERT INTO ticket (fecha_cierre, fecha_creacion, fecha_vencimiento, numero, area_id_area, estado_ticket_id_estado_ticket, incidencia_id_incidencia, prioridad_id_prioridad, tienda_id_tienda, usuario_id_usuario) VALUES ('2022-08-04', NOW(), '2022-08-04', '1', '1', '4', '2', '2', '1', '1');
