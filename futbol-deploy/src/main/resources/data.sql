-- ASOCIACIONES
INSERT IGNORE INTO asociaciones (nombre, pais, presidente) VALUES
('Federación Colombiana de Fútbol', 'Colombia', 'Ramon Jesurun'),
('FIFA', 'Suiza', 'Gianni Infantino'),
('CONMEBOL', 'Paraguay', 'Alejandro Dominguez');

-- ENTRENADORES
INSERT IGNORE INTO entrenadores (nombre, apellido, edad, nacionalidad) VALUES
('Alberto', 'Gamero',   62, 'Colombiano'),
('Hernán',  'Crespo',   48, 'Argentino'),
('Jorge',   'Almirón',  55, 'Argentino'),
('Paulo',   'Autuori',  66, 'Brasileño'),
('Nestor',  'Lorenzo',  57, 'Argentino');

-- CLUBES  (asociacion_id, entrenador_id)
INSERT IGNORE INTO clubes (nombre, ciudad, fundacion, estadio, asociacion_id, entrenador_id) VALUES
('Millonarios FC',        'Bogotá',    1946, 'El Campín',          1, 1),
('Atlético Nacional',     'Medellín',  1947, 'Atanasio Girardot',  1, 2),
('Independiente Santa Fe','Bogotá',    1941, 'El Campín',          1, 3),
('Deportivo Cali',        'Cali',      1912, 'Pascual Guerrero',   1, 4);

-- JUGADORES (club_id)
INSERT IGNORE INTO jugadores (nombre, apellido, numero, posicion, club_id) VALUES
('David',    'Mackalister Silva', 10, 'Mediocampista', 1),
('Leonardo', 'Castro',            9,  'Delantero',     1),
('Álvaro',   'Montero',           1,  'Portero',       1),
('Dorlan',   'Pabón',             11, 'Extremo',       2),
('Jefferson','Duque',             9,  'Delantero',     2),
('Andrés',   'Andrade',           8,  'Mediocampista', 2),
('Hugo',     'Rodallega',         9,  'Delantero',     3),
('Kelvin',   'Osorio',            7,  'Extremo',       3),
('Hárold',   'Preciado',          9,  'Delantero',     4),
('Jhon',     'Mosquera',          2,  'Defensa',       4);

-- COMPETICIONES
INSERT IGNORE INTO competiciones (nombre, monto_premio, fecha_inicio, fecha_fin) VALUES
('Liga BetPlay Dimayor',       2000000, '2024-01-20', '2024-06-15'),
('Copa BetPlay Dimayor',        500000, '2024-02-10', '2024-05-30'),
('Copa Libertadores',          7000000, '2024-02-06', '2024-11-30'),
('Copa Sudamericana',          3000000, '2024-02-06', '2024-11-23');

-- TABLA INTERMEDIA clubes_competiciones (ManyToMany)
INSERT IGNORE INTO clubes_competiciones (club_id, competicion_id) VALUES
(1, 1),(1, 2),(1, 3),
(2, 1),(2, 2),(2, 3),(2, 4),
(3, 1),(3, 2),(3, 4),
(4, 1),(4, 2);
