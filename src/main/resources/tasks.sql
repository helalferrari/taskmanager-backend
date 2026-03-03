-- Tasks para o Usuário Admin
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), (SELECT id FROM users WHERE email = 'admin@example.com'), 'Configurar ambiente Spring Boot', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), (SELECT id FROM users WHERE email = 'admin@example.com'), 'Implementar JWT Authentication', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), (SELECT id FROM users WHERE email = 'admin@example.com'), 'Testar endpoints no Swagger', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
