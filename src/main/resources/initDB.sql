DROP TABLE IF EXISTS menu_dishes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY NOT NULL,
                       name varchar(255) NOT NULL,
                       email varchar(255) NOT NULL UNIQUE,
                       password varchar(255) NOT NULL,
                       registration_date DATE NOT NULL DEFAULT CURRENT_DATE
);


CREATE TABLE roles (
                       "id" SERIAL NOT NULL PRIMARY KEY,
                       "name" varchar(255) NOT NULL
);


CREATE TABLE user_roles (
                            "id" SERIAL PRIMARY KEY NOT NULL,
                            "user_id" bigint NOT NULL,
                            "roles_id" int NOT NULL,
                            CONSTRAINT user_roles_idx UNIQUE (user_id, roles_id),
                            FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                            FOREIGN KEY (roles_id) REFERENCES roles (id) ON DELETE CASCADE
);



CREATE TABLE restaurants (
                             "id" SERIAL PRIMARY KEY NOT NULL,
                             "name" varchar(255) NOT NULL,
                             "address" varchar(255) NOT NULL
);



CREATE TABLE menus (
                       "id" SERIAL PRIMARY KEY NOT NULL,
                       "date" DATE NOT NULL UNIQUE,
                       "restaurant_id" int NOT NULL,
                       FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);



CREATE TABLE dishes (
                        "id" serial PRIMARY KEY NOT NULL,
                        "name" varchar(255) NOT NULL,
                        "price" numeric NOT NULL
);



CREATE TABLE menu_dishes (
                             "id" SERIAL PRIMARY KEY NOT NULL,
                             "menu_id" bigint NOT NULL,
                             "dish_id" bigint NOT NULL,
                             FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE,
                             FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE,
                             CONSTRAINT menu_dishes_idx UNIQUE (menu_id, dish_id)
);



CREATE TABLE votes (
                       "id" SERIAL PRIMARY KEY NOT NULL,
                       "user_id" bigint NOT NULL,
                       "menu_id" bigint NOT NULL,
                       "date" DATE NOT NULL,
                       "time" TIME NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                       FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE,
                       CONSTRAINT votes_idx UNIQUE (user_id, date)
);