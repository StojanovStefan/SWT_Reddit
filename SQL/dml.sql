INSERT INTO SVT_Baza.User (display_name, username, password, email, avatar, description)
VALUES (
    'Stefan Stojanov',
    'stefan',
    '$2a$10$iWPJsl5.7eHkbLEYSm6Kne9JPhQ7Xw7dyRG7qsaLiv5CFa7cffbwW',
    'stefan@gmail.com',
    'https://example.com/image.png',
    'Android programer'
    );
INSERT INTO SVT_Baza.User (display_name, username, password, email, avatar, description)
VALUES (
    'Sonja Bazov',
    'sonja',
    '$2a$10$ySa9KtdVI4QsAUjN1hL6E.b2dABtClmhOifehS.dZT4JToAFKREaK',
    'sonja@bazov.rs',
    'https://example.com/image.png',
    'Product Developer'
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
INSERT INTO SVT_Baza.Community(name, description)
VALUES (
        'menadzeri',
        'Svi mi koji testiramo'
       );

INSERT INTO SVT_Baza.Moderator(user_id, community_id)
VALUES (
        1,
        1
       );
INSERT INTO SVT_Baza.Moderator(user_id, community_id)
VALUES (
        2,
        2
       );
INSERT INTO SVT_Baza.Post (user_id, community_id, title, text, image_path)
VALUES (
        1,
        1,
        'Prva objava!',
        'Dobrodošli u naš mali kutak',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/800px-Image_created_with_a_mobile_phone.png'
       );
INSERT INTO SVT_Baza.Post (user_id, community_id, title, text, image_path)
VALUES (
        2,
        2,
        'Poziv na Menadzerijadu 2023!',
        'Sutomore 2023, najbolji provod i menadzerovanje!',
        'https://slika.nezavisne.rs/2018/07/750x450/20180706150257_486976.jpg'
       );