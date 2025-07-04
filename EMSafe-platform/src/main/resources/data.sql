-- =====================================================
-- DATA.SQL - INSERCIONES SOLO SI LAS TABLAS ESTÁN VACÍAS
-- =====================================================

-- Verificar usuario admin
SET @user_count = (SELECT COUNT(*) FROM user WHERE username = 'admin');
INSERT INTO user (username, password, email, first_name, last_name, role)
SELECT 'admin', '$2a$10$qj2t1Y/OF9kkbIep5e2Be.0ALuus3Xvii3Ao7VmYpSSltFMfOn4gu', 'admin@emsafe.com', 'Administrador', 'Sistema', 'ADMIN'
WHERE @user_count = 0;

-- Verificar tabla status
SET @status_count = (SELECT COUNT(*) FROM status);
INSERT INTO status (status)
SELECT * FROM (
                  SELECT 'active' UNION ALL
                  SELECT 'maintenance' UNION ALL
                  SELECT 'inactive' UNION ALL
                  SELECT 'alert' UNION ALL
                  SELECT 'offline'
              ) AS tmp
WHERE @status_count = 0;

-- Verificar device_type
SET @type_count = (SELECT COUNT(*) FROM device_type);
INSERT INTO device_type (type)
SELECT * FROM (
                  SELECT 'Detector Gamma' UNION ALL
                  SELECT 'Detector Beta' UNION ALL
                  SELECT 'Detector Epsilon' UNION ALL
                  SELECT 'Monitor Ambiental' UNION ALL
                  SELECT 'Dosímetro Personal'
              ) AS tmp
WHERE @type_count = 0;

-- Verificar measurement_frequency
SET @freq_count = (SELECT COUNT(*) FROM measurement_frequency);
INSERT INTO measurement_frequency (frequency)
SELECT * FROM (
                  SELECT 'Tiempo Real' UNION ALL
                  SELECT 'Cada 5 minutos' UNION ALL
                  SELECT 'Cada 15 minutos' UNION ALL
                  SELECT 'Cada hora' UNION ALL
                  SELECT 'Cada 6 horas' UNION ALL
                  SELECT 'Diario'
              ) AS tmp
WHERE @freq_count = 0;

-- Verificar device
SET @device_count = (SELECT COUNT(*) FROM device);
INSERT INTO device (name, location, current_reading, last_read_date, status_id, type_id, frequency_id)
SELECT * FROM (
                  SELECT 'Detector Epsilon-5', 'Sala Principal', 0.60, '2024-05-09', 1, 3, 2 UNION ALL
                  SELECT 'Detector Delta-4', 'Cuarto 1', 12.3, '2024-07-11', 4, 2, 1 UNION ALL
                  SELECT 'Detector Gamma-3', 'Cocina', 25.5, '2025-05-07', 2, 1, 3 UNION ALL
                  SELECT 'Monitor Ambiental-1', 'Laboratorio Principal', 0.35, '2025-06-25', 1, 4, 1 UNION ALL
                  SELECT 'Detector Epsilon-6', 'Área de Almacén', 85.4, '2025-06-25', 4, 3, 2 UNION ALL
                  SELECT 'Monitor Ambiental-2', 'Sala de Control', 18.2, '2025-06-25', 2, 4, 3 UNION ALL
                  SELECT 'Detector Gamma-4', 'Zona de Seguridad', 0.52, '2025-06-25', 1, 1, 1 UNION ALL
                  SELECT 'Monitor Presión-1', 'Sistema de Ventilación', 210.0, '2025-06-25', 4, 4, 2 UNION ALL
                  SELECT 'Dosímetro Personal-1', 'Técnico Principal', 15.0, '2025-06-25', 2, 5, 4 UNION ALL
                  SELECT 'Detector Beta-2', 'Entrada Principal', 10.0, '2025-07-03', 4, 2, 1 UNION ALL
                  SELECT 'Monitor Especial-1', 'Zona ASDA', 111.0, '2025-07-03', 2, 4, 3 UNION ALL
                  SELECT 'Detector Epsilon-7', 'Área Crítica', 11.0, '2025-07-03', 1, 3, 2
              ) AS tmp
WHERE @device_count = 0;

-- Verificar alarm
SET @alarm_count = (SELECT COUNT(*) FROM alarm);
INSERT INTO alarm (device_id, level, type, reading, threshold, unit, notes, acknowledged, acknowledged_by, acknowledged_at, resolved, resolved_at, timestamp)
SELECT * FROM (
                  SELECT
                      5 AS device_id, 'critical' AS level, 'temperature_threshold' AS type, 85.4 AS reading, 75.0 AS threshold, '°C' AS unit, 'Sobrepasó límite seguro' AS notes,
                      false AS acknowledged, 'técnico2' AS acknowledged_by, '2024-09-08 13:14:44' AS acknowledged_at, false AS resolved, NULL AS resolved_at, '2025-06-25 10:30:00' AS timestamp
                  UNION ALL
                  SELECT
                      6, 'warning', 'humidity_threshold', 18.2, 20.0, '%', 'Se ajustó ventilación',
                      true, 'técnico2', '2025-06-25 11:20:00', true, '2025-06-25 13:10:00', '2025-06-25 11:15:45'
                  UNION ALL
                  SELECT
                      7, 'warning', 'radiation_threshold', 0.52, 0.5, 'μSv/h', 'Se ajustó ventilación',
                      false, 'técnico2', '2024-08-05 17:53:44', false, NULL, '2025-06-25 12:00:00'
                  UNION ALL
                  SELECT
                      8, 'critical', 'pressure_threshold', 210.0, 180.0, 'kPa', 'Válvula reemplazada',
                      true, 'operador3', '2025-06-25 12:50:00', true, '2025-06-25 13:10:00', '2025-06-25 12:45:30'
                  UNION ALL
                  SELECT
                      9, 'warning', 'battery_low', 15.0, 20.0, '%', 'Programar recarga pronto',
                      false, 'técnico1', '2024-08-05 17:53:44', false, NULL, '2025-06-25 13:30:15'
                  UNION ALL
                  SELECT
                      10, 'critical', 'radiation_threshold', 10.0, 10.0, 'μSv/h', 'Programar recarga pronto',
                      true, 'operador1', '2025-06-25 12:50:00', true, '2025-06-25 13:10:00', '2025-07-03 20:37:01'
                  UNION ALL
                  SELECT
                      11, 'warning', 'asda', 111.0, 100.0, 'μSv/h', 'Programar recarga pronto',
                      true, 'operador2', '2025-06-25 12:50:00', false, NULL, '2025-07-03 20:57:19'
                  UNION ALL
                  SELECT
                      12, 'warning', 'radiation_threshold', 11.0, 10.0, 'μSv/h', 'Programar recarga pronto',
                      false, 'técnico2', '2025-06-25 12:50:00', false, NULL, '2025-07-03 21:26:11'
              ) AS tmp
WHERE @alarm_count = 0;


-- Verificar puntos de radiación
SET @rp_count = (SELECT COUNT(*) FROM radiation_point);
INSERT INTO radiation_point (latitude, longitude, level, color, description, radiation_value, unit, device_id)
SELECT * FROM (
                  SELECT -12.0653, -77.1501, 'Crítico', '#f44336', 'Callao - Zona Portuaria', 1.35, 'μSv/h', 1 UNION ALL
                  SELECT -12.0580, -77.1420, 'Crítico', '#f44336', 'Callao - Zona Industrial', 1.28, 'μSv/h', 2 UNION ALL
                  SELECT -12.0732, -77.0937, 'Alto', '#ff9800', 'Jesús María - Av. Brasil', 0.89, 'μSv/h', 3 UNION ALL
                  SELECT -12.1100, -77.0600, 'Alto', '#ff9800', 'Surquillo - Mercado Central', 0.78, 'μSv/h', 4 UNION ALL
                  SELECT -12.0400, -77.0300, 'Alto', '#ff9800', 'Rímac - Centro Histórico', 0.85, 'μSv/h', 5 UNION ALL
                  SELECT -12.1228, -77.0300, 'Elevado', '#ffeb3b', 'Miraflores - Malecón', 0.52, 'μSv/h', 6 UNION ALL
                  SELECT -12.0464, -77.0428, 'Normal', '#4caf50', 'Lima Centro - Plaza Mayor', 0.28, 'μSv/h', 9 UNION ALL
                  SELECT -12.1350, -77.0200, 'Normal', '#4caf50', 'Miraflores - Parque Kennedy', 0.19, 'μSv/h', 10
              ) AS tmp
WHERE @rp_count = 0;
