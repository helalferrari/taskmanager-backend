-- Tasks para o Usuário 1 (5 tasks)
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Comprar mantimentos', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Ler livro sobre Spring Boot', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Lavar o carro', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Exercitar-se por 30 minutos', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Marcar reunião com equipe', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Tasks para o Usuário 2 (3 tasks)
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'b1fccda0-0d1c-4f99-bc7e-7cc0ce491b22', 'Estudar React Native', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'b1fccda0-0d1c-4f99-bc7e-7cc0ce491b22', 'Consertar bug no frontend', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, user_id, title, completed, deleted, create_date, modify_date) VALUES (RANDOM_UUID(), 'b1fccda0-0d1c-4f99-bc7e-7cc0ce491b22', 'Ir ao supermercado', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
