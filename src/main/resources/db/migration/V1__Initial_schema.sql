DROP TABLE IF EXISTS public.users  CASCADE ;
CREATE TABLE public.users (
                              id SERIAL PRIMARY KEY,
                              full_name VARCHAR(250) NOT NULL,
                              email  VARCHAR (250) NOT NULL,
                              password VARCHAR (250),
                              city VARCHAR (250) ,
                              country VARCHAR (250) ,
                              gender VARCHAR (250) ,
                              work_place VARCHAR  ,
                              about VARCHAR (250) ,
                              birth_date TIMESTAMP  ,
                              profile_picture VARCHAR (250)  ,
                              profile_background_picture VARCHAR (250)  ,
                              created_date TIMESTAMP NOT NULL ,
                              updated_date TIMESTAMP NOT NULL,
                              activation_code VARCHAR ,
                              activated boolean ,
                              created_by VARCHAR,
                              updated_by VARCHAR
);

DROP TABLE IF EXISTS public.friends CASCADE ;
CREATE TABLE public.friends (
                                id SERIAL PRIMARY KEY,
                                user_id INT REFERENCES users(id) ,
                                friend_id INT REFERENCES users(id),
                                status VARCHAR (250) NOT NULL,

                                created_date TIMESTAMP NOT NULL ,
                                updated_date TIMESTAMP NOT NULL,
                                created_by VARCHAR,
                                updated_by VARCHAR

);
DROP TABLE IF EXISTS public.chats  CASCADE ;
CREATE TABLE public.chats (
                              id SERIAL PRIMARY KEY ,
                              created_date TIMESTAMP NOT NULL,
                              updated_date TIMESTAMP NOT NULL,
                              created_by VARCHAR,
                              updated_by VARCHAR
);

DROP TABLE IF EXISTS public.users_chats CASCADE ;
CREATE TABLE public.users_chats (
                                    id SERIAL PRIMARY KEY,
                                    user_id INT NOT NULL,
                                    chat_id INT NOT NULL ,
                                    foreign key (user_id) references users(id),
                                    foreign key (chat_id) references chats(id)

);

DROP TABLE IF EXISTS public.messages  CASCADE ;
CREATE TABLE public.messages (
                                 id SERIAL PRIMARY KEY ,
                                 content VARCHAR(255),
                                 created_date TIMESTAMP NOT NULL,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR,
                                 chat_id INT,
                                 chat_entity_id INT,
                                 user_id INT,
                                 FOREIGN KEY (chat_id) REFERENCES chats(id) ,
                                 FOREIGN KEY (user_id) REFERENCES users(id)
);
DROP TABLE IF EXISTS public.message_images  CASCADE ;
CREATE TABLE public.message_images (
                                       id SERIAL PRIMARY KEY ,
                                       created_date TIMESTAMP NOT NULL,
                                       updated_date TIMESTAMP NOT NULL,
                                       created_by VARCHAR,
                                       updated_by VARCHAR,
                                       img_url VARCHAR(255),
                                       chat_id INT,
                                       message_id INT,
                                       FOREIGN KEY (chat_id) REFERENCES chats(id),
                                       FOREIGN KEY (message_id) REFERENCES messages(id)
);
DROP TABLE IF EXISTS public.user_images  CASCADE ;
CREATE TABLE public.user_images (
                                    id SERIAL PRIMARY KEY ,
                                    created_date TIMESTAMP NOT NULL,
                                    updated_date TIMESTAMP NOT NULL,
                                    created_by VARCHAR,
                                    updated_by VARCHAR,
                                    img_url VARCHAR(255),
                                    user_id INT,
                                    image_user_id INT,
                                    FOREIGN KEY (image_user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS public.posts CASCADE ;
CREATE TABLE public.posts (
                              id SERIAL PRIMARY KEY,
                              user_id INT REFERENCES users(id),
                              post_type VARCHAR(255) NOT NULL,
                              content VARCHAR(255),
                              parent_id INT REFERENCES posts(id),
                              created_date TIMESTAMP NOT NULL,
                              updated_date TIMESTAMP NOT NULL,
                              created_by VARCHAR,
                              updated_by VARCHAR
);

DROP TABLE IF EXISTS public.users_liked_posts CASCADE;
CREATE TABLE public.users_liked_posts (
                                          id SERIAL PRIMARY KEY,
                                          post_id INTEGER REFERENCES posts(id),
                                          user_id INTEGER REFERENCES users(id)
);

DROP TABLE IF EXISTS public.users_reposted_posts CASCADE;
CREATE TABLE public.users_reposted_posts (
                                             id SERIAL PRIMARY KEY,
                                             post_id INTEGER REFERENCES posts(id),
                                             user_id INTEGER REFERENCES users(id)
);

DROP TABLE IF EXISTS public.post_images CASCADE ;
CREATE TABLE public.post_images (
                                    id SERIAL PRIMARY KEY,
                                    post_id INT REFERENCES posts(id),
                                    img_url VARCHAR(255),
                                    created_date TIMESTAMP NOT NULL,
                                    updated_date TIMESTAMP NOT NULL,
                                    created_by VARCHAR,
                                    updated_by VARCHAR
);