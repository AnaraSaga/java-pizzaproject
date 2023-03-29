
insert into cafe (id, name, address, phone_number)
    values ("e6563d59-078c-46a4-ac82-14a9decae0cd", "Cafe001", "Berlinerstr.40", "013787889456");

insert into pizza (id, name, price, description, image, cafe_id)
    values ("c1a457877-3d2b-4a92-bfa3-cdfed038775e", "Margherita", 12.00, "Tomato sause, mozzarella", "margherita.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");
insert into pizza (id, name, price, description, image, cafe_id)
    values ("c72018aa-f69e-4c0f-b408-bcfd01802a49", "Salami", 15.00, "Salami, tomato sause, mozzarella", "salami.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");
insert into pizza (id, name, price, description, image, cafe_id)
    values ("ba2ea3e5-38c1-4dfe-8b8c-9d9c4d9a492b", "Veggytarian", 15.00, "Tomato sause, mozzarella, grilled aubergine, zucchini, ricotta cheese", "veggie1.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");
insert into pizza (id, name, price, description, image, cafe_id)
    values ("8bf458ea-d89b-4487-a61a-a67654545d6c", "Mushroom", 15.00, "Mushroom, onion, tomato sause, mozzarella", "mushroom.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");
insert into pizza (id, name, price, description, image, cafe_id)
    values ("20e46cf2-e5d4-4809-99cd-88d1be6a8771", "Bolognese", 18.00, "Bolognese sause, mozzarella", "bolognese.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");
insert into pizza (id, name, price, description, image, cafe_id)
    values ("91860ee1-1e0d-4808-b319-197e3eb58116", "4Cheeses", 15.00, "Tomato sause, mozzarella, blue cheese, ricotta cheese, parmigiano", "4cheese.jpg", "e6563d59-078c-46a4-ac82-14a9decae0cd");

insert into roles (id, name) values ('eb8f4b2c-df05-4f05-857d-46f4ff75eb62','ROLE_ADMIN');
insert into roles (id, name) values ('ff87dbfd-9f0e-48f6-9001-9e5091dc9a9b','ROLE_USER');

insert into users (id, login, name, password) values ('ab092ee-9b1a-4913-9de8-d712145474b', 'admin', 'Henry', '$2a$10$9zELWTjgWlR4mvCL0M10.eVSXulcwtQ3kfQ/7Rrfh7KnRAyYx/iES');
insert into users (id, login, name, password) values ('c15edd0e-0eb7-4982-ad4b-f045787ef98cd', 'user', 'Carin', '$2a$10$X/p2DcfMjwiXE/Ae64cuheVyrZUJg3Yekvb5kBH1PQxYlkSvvRMH.');

insert into users_roles (user_id, role_id) values ('ab092ee-9b1a-4913-9de8-d712145474b', 'eb8f4b2c-df05-4f05-857d-46f4ff75eb62');
insert into users_roles (user_id, role_id) values ('c15edd0e-0eb7-4982-ad4b-f045787ef98cd', 'ff87dbfd-9f0e-48f6-9001-9e5091dc9a9b');