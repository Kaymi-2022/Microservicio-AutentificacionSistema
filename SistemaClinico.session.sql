-- 1. Insertar roles
INSERT INTO roles (nombre_rol, fecha_creacion) VALUES
('Administrador', NOW()),
('Médico', NOW()),
('Paciente', NOW()),
('Recepcionista', NOW());

-- 2. Insertar permisos
INSERT INTO permisos (codigo, descripcion) VALUES
('USUARIO_CREAR', 'Permite crear nuevos usuarios'),
('USUARIO_EDITAR', 'Permite editar usuarios existentes'),
('USUARIO_ELIMINAR', 'Permite eliminar usuarios'),
('CITA_CREAR', 'Permite crear nuevas citas'),
('CITA_EDITAR', 'Permite editar citas existentes'),
('CITA_ELIMINAR', 'Permite eliminar citas'),
('HISTORIAL_VER', 'Permite ver historiales médicos'),
('HISTORIAL_EDITAR', 'Permite editar historiales médicos'),
('REPORTE_GENERAR', 'Permite generar reportes');

-- 3. Insertar relaciones roles_permisos
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9);

-- Médico tiene permisos relacionados con citas e historiales
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
(2, 4), (2, 5), (2, 7), (2, 8);

-- Recepcionista tiene permisos relacionados con citas
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
(4, 4), (4, 5);

-- Paciente solo puede ver su historial
INSERT INTO roles_permisos (rol_id, permiso_id) VALUES
(3, 7);

-- 4. Insertar usuarios
INSERT INTO usuarios (username, password, name, lastname, email, telefono, numero_colegiado, especialidad, foto_perfil, activo, fecha_creacion, rol_id) VALUES
-- Administrador
('admin', 'admin123', 'Juan', 'Pérez', 'admin@clinica.com', '5551234567', NULL, NULL, NULL, TRUE, NOW(), 1),
-- Médico
('dr.garcia', 'medico123', 'Carlos', 'García', 'dr.garcia@clinica.com', '5557654321', '12345', 'Cardiología', 'dr_garcia.jpg', TRUE, NOW(), 2),
-- Paciente
('paciente1', 'paciente123', 'María', 'López', 'maria.lopez@email.com', '5559876543', NULL, NULL, 'maria.jpg', TRUE, NOW(), 3),
-- Recepcionista
('recepcion', 'recepcion123', 'Ana', 'Martínez', 'recepcion@clinica.com', '5554567890', NULL, NULL, 'ana.jpg', TRUE, NOW(), 4),
-- Otro médico
('dr.rodriguez', 'rodriguez123', 'Roberto', 'Rodríguez', 'dr.rodriguez@clinica.com', '5556789012', '67890', 'Pediatría', 'dr_rodriguez.jpg', TRUE, NOW(), 2);

-- Actualización de contraseñas para usuarios existentes
UPDATE usuarios SET 
  password = '$2a$10$GmiiDMh4l9UV1IaXaEecJOfKU53q4CSZuicufUomsI4G/ZvdV0eqm'
WHERE usuario_id = 1;

UPDATE roles SET 
  nombre_rol = 'MEDICO'
WHERE rol_id = 2;

UPDATE roles SET 
  nombre_rol = 'PACIENTE'
WHERE rol_id = 3;

UPDATE roles SET 
  nombre_rol = 'RECEPCIONISTA'
WHERE rol_id = 4;



-- 6. Consultar usuarios
SELECT * FROM usuarios;
-- 7. Consultar roles
SELECT * FROM roles;
-- 8. Consultar permisos
SELECT * FROM permisos;
-- 9. Consultar roles_permisos
SELECT * FROM roles_permisos;