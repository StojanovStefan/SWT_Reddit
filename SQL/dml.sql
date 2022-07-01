INSERT INTO SVT_Baza.User (display_name, username, password, email, avatar, description)
VALUES (
    'Stefan Stojanov',
    'stefan',
    '$2a$10$iWPJsl5.7eHkbLEYSm6Kne9JPhQ7Xw7dyRG7qsaLiv5CFa7cffbwW',
    'stefan@gmail.com',
    'stefanov avatar',
    'Android programer'
    );
INSERT INTO SVT_Baza.User (display_name, username, password, email, avatar, description)
VALUES (
    'Iva Josfov',
    'ivajos',
    'iva2022',
    'iva.jos@gmail.com',
    'https://example.com/image.png',
    'iOS programer'
    );

INSERT INTO SVT_Baza.Administrator
VALUES (
        1
       );

INSERT INTO SVT_Baza.Community(name, description)
VALUES (
        'programeri',
        'Svi mi koji volimo da programiramo'
       );

INSERT INTO SVT_Baza.Moderator(user_id, community_id)
VALUES (
        1,
        1
       );
INSERT INTO SVT_Baza.Post (user_id, community_id, title, text)
VALUES (
        1,
        1,
        'Prva objava!',
        'Dobrodošli u naš mali kutak'
       );

