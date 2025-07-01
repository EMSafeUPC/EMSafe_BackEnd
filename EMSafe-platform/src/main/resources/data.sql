-- Datos iniciales de zonas de radiación en Lima, Perú (10 puntos estratégicos)

INSERT INTO radiation_point (latitude, longitude, level, color, description, radiation_value, unit, device_id) VALUES
-- Zonas Críticas (2 puntos)
(-12.0653, -77.1501, 'Crítico', '#f44336', 'Callao - Zona Portuaria', 1.35, 'µSv/h', 1),
(-12.0580, -77.1420, 'Crítico', '#f44336', 'Callao - Zona Industrial', 1.28, 'µSv/h', 2),

-- Zonas Altas (3 puntos)
(-12.0732, -77.0937, 'Alto', '#ff9800', 'Jesús María - Av. Brasil', 0.89, 'µSv/h', 3),
(-12.1100, -77.0600, 'Alto', '#ff9800', 'Surquillo - Mercado Central', 0.78, 'µSv/h', 4),
(-12.0400, -77.0300, 'Alto', '#ff9800', 'Rímac - Centro Histórico', 0.85, 'µSv/h', 5),

-- Zonas Elevadas (3 puntos)
(-12.1228, -77.0300, 'Elevado', '#ffeb3b', 'Miraflores - Malecón', 0.52, 'µSv/h', 6),
(-12.0975, -77.0428, 'Elevado', '#ffeb3b', 'San Isidro - Centro Financiero', 0.48, 'µSv/h', 7),
(-12.1291, -77.0042, 'Elevado', '#ffeb3b', 'Barranco - Plaza Municipal', 0.55, 'µSv/h', 8),

-- Zonas Normales (2 puntos)
(-12.0464, -77.0428, 'Normal', '#4caf50', 'Lima Centro - Plaza Mayor', 0.28, 'µSv/h', 9),
(-12.1350, -77.0200, 'Normal', '#4caf50', 'Miraflores - Parque Kennedy', 0.19, 'µSv/h', 10);