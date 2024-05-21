-- drop table test_small_data;

-- CREATE TABLE test_small_data (
--     time TIMESTAMP,
--     latitude DOUBLE PRECISION,
--     longitude DOUBLE PRECISION,
--     sea_surface_temperature DOUBLE PRECISION,
--     sea_bottom_temperature DOUBLE PRECISION,
--     sea_surface_salinity DOUBLE PRECISION,
--     sea_bottom_salinity DOUBLE PRECISION,
--     sea_surface_x_velocity DOUBLE PRECISION,
--     sea_surface_y_velocity DOUBLE PRECISION,
--     mixed_layer_depth DOUBLE PRECISION,
--     significant_wave_height DOUBLE PRECISION,
--     mean_wave_direction DOUBLE PRECISION,
--     mean_wave_period DOUBLE PRECISION
-- );

-- select sea_surface_temperature from test_data

-- INSERT INTO test_small_data (time, latitude, longitude, sea_surface_temperature, sea_bottom_temperature, sea_surface_salinity, sea_bottom_salinity, sea_surface_x_velocity, sea_surface_y_velocity, mixed_layer_depth, significant_wave_height, mean_wave_direction, mean_wave_period)
-- SELECT time, latitude, longitude, sea_surface_temperature, sea_bottom_temperature, sea_surface_salinity, sea_bottom_salinity, sea_surface_x_velocity, sea_surface_y_velocity, mixed_layer_depth, significant_wave_height, mean_wave_direction, mean_wave_period
-- FROM full_data
-- WHERE time IS DISTINCT FROM NULL
--   OR latitude IS DISTINCT FROM NULL
--   OR longitude IS DISTINCT FROM NULL
--   OR sea_surface_temperature IS DISTINCT FROM NULL
--   OR sea_bottom_temperature IS DISTINCT FROM NULL
--   OR sea_surface_salinity IS DISTINCT FROM NULL
--   OR sea_bottom_salinity IS DISTINCT FROM NULL
--   OR sea_surface_x_velocity IS DISTINCT FROM NULL
--   OR sea_surface_y_velocity IS DISTINCT FROM NULL
--   OR mixed_layer_depth IS DISTINCT FROM NULL
--   OR significant_wave_height IS DISTINCT FROM NULL
--   OR mean_wave_direction IS DISTINCT FROM NULL
--   OR mean_wave_period IS DISTINCT FROM NULL
-- LIMIT 2000;



