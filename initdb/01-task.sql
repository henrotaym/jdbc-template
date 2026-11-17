SET NAMES utf8mb4;

CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    done BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO task (name, done) VALUES
    ('Installer le dev container', TRUE),
    ('Lire le déroulé JDBC', FALSE),
    ('Écrire la première requête', FALSE),
    ('Comprendre les curseurs', FALSE),
    ('Rendre le CRUD', FALSE);
