INSERT INTO book_store.role (role_name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT role_name FROM book_store.role WHERE role_name = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO book_store.role (role_name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT role_name FROM book_store.role WHERE role_name = 'ROLE_USER'
) LIMIT 1;

