INSERT INTO departamento (descripcion, email, indicator, nombre, status) VALUES ('Central', 'areadesarrollo@hakan.com', 'si', 'Area de Desarrollo', 'OK');
INSERT INTO departamento (descripcion, email, indicator, nombre, status) VALUES ('Central', 'areasoprte@hakan.com', 'si', 'Area de Soporte', 'OK');
INSERT INTO departamento (descripcion, email, indicator, nombre, status) VALUES ('Central', 'areaelectronica@hakan.com', 'si', 'Area de Electronica', 'OK');
INSERT INTO departamento (descripcion, email, indicator, nombre, status) VALUES ('Central', 'areainfraestructura@hakan.com', 'si', 'Area de Infraestructura', 'OK');

INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Baja', 'si', 'BAJA', 'Ok');
INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Media', 'si', 'MEDIA', 'Ok');
INSERT INTO prioridad (descripcion, indicator, nombre, status) VALUES ('Alta', 'si', 'ALTA', 'Ok');


INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Falla del POS', 'si', 'POS', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Problema con el Internet', 'si', 'Conexion de Internet', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Fallas con el Servidor', 'si', 'Almacen', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Cambio en el diseño de la pagina web', 'si', 'Cambio de diseño', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Servidor caido', 'si', 'Servidor caido', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Falta de tinta', 'si', 'Falta de tinta', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Falla de impresora', 'si', 'Falla de impresora', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Problemas con el sistema de camaras', 'si', 'Falla en las camaras', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Actualizacion del sistema Windows', 'si', 'Actualizacion del Windows', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Formateo', 'si', 'Formateo', 'Ok');
INSERT INTO servicio (descripcion, indicator, nombre, status) VALUES ('Aumento de almacenamiento', 'si', 'Aumento de almacenamiento', 'Ok');


INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('En progreso', 'si', 'EN CURSO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Escalado', 'si', 'ESCALADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Anulado', 'si', 'CANCELADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Abierto', 'si', 'ABIERTO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('En pausa', 'si', 'PAUSADO', 'Ok');
INSERT INTO estado_ticket (descripcion, indicator, nombre, status) VALUES ('Terminado', 'si', 'FINALIZADO', 'Ok');

INSERT INTO plan_sla (indicator, nombre, periodo_de_gracia, status) VALUES ('si', 'Soporte', 'Indefinido', 'Ok');
INSERT INTO plan_sla (indicator, nombre, periodo_de_gracia, status) VALUES ('si', 'Oficina ', 'Indefinido', 'Ok');
INSERT INTO plan_sla (indicator, nombre, periodo_de_gracia, status) VALUES ('si', 'Oficina Central ', 'Indefinido', 'Ok');

INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Sistema Web', 'si', 'SISTEMA DE VENTAS', 'Ok', '1');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Servidores', 'si', 'ECOMERCE', 'Ok', '2');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Hardware', 'si', 'HARDWARE', 'Ok', '2');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Software', 'si', 'CATALOGO', 'Ok', '1');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Seguridad', 'si', 'CATALOGO', 'Ok', '2');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Sistemas', 'si', 'CATALOGO', 'Ok', '3');
INSERT INTO incidencia (descripcion, indicator, nombre, status, plan_sla_id_plan_sla) VALUES ('Soporte', 'si', 'CATALOGO', 'Ok', '1');

INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'TLimpio', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Cinepolis', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Cineplanet Los Olivos', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Cineplanet Puruchuco', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Cineplanet Santa Clara', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Clinica arbrays laser', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', 'Clinica buena vista', 'Ok');
INSERT INTO tienda (indicator, nombre_tienda, status) VALUES ('si', '2wtrade', 'Ok');


INSERT INTO usuarios (apellidos, celular, email, fecha_creado, indicator, nombres, password, status, telefono, username, area_id_area) VALUES ('Canamero Gutierrez', '123465', 'carloscg@gmail.com', '2022-08-04', 'si', 'Carlos Jesus', '1345', 'Ok', '79845', 'AD030465', '1');
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
