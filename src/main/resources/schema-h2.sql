DROP TABLE IF EXISTS public.users  CASCADE ;
CREATE TABLE public.users (
                              entity_id INT AUTO_INCREMENT PRIMARY KEY,
                              full_name VARCHAR(250) NOT NULL,
                              email  VARCHAR (250) NOT NULL,
                              password VARCHAR (250) NOT NULL,
                              city VARCHAR (250) NOT NULL,
                              country VARCHAR (250) NOT NULL,
                              gender VARCHAR (250) NOT NULL,
                              work_place VARCHAR NOT NULL ,
                              about VARCHAR (250) NOT NULL,
                              birth_date TIMESTAMP NOT NULL ,
                              profile_picture VARCHAR (250) NOT NULL ,
                              profile_background_picture VARCHAR (250) NOT NULL ,
    /*  auth_id INT NOT NULL ,
    FOREIGN KEY (auth_id) RERENCES sys_users(user_id),*/
                              created_date TIMESTAMP NOT NULL ,
                             updated_date TIMESTAMP NOT NULL,
                              created_by VARCHAR,
                              updated_by VARCHAR
);
DROP TABLE IF EXISTS public.posts CASCADE ;
CREATE TABLE public.posts(
                             entity_id INT AUTO_INCREMENT PRIMARY KEY ,
                             user_id INTEGER references users(entity_id),
                             content  VARCHAR(280) NOT NULL,
                              post_type VARCHAR NOT NULL ,
                              parent_id INT ,
                             created_date TIMESTAMP NOT NULL ,
                            updated_date TIMESTAMP NOT NULL,
                             created_by VARCHAR,
                             updated_by VARCHAR

);
DROP TABLE IF EXISTS public.chats CASCADE ;
CREATE TABLE public.chats(
                             entity_id INT AUTO_INCREMENT PRIMARY KEY ,
                             created_date TIMESTAMP NOT NULL ,
                            updated_date TIMESTAMP NOT NULL,
                             created_by VARCHAR,
                             updated_by VARCHAR

);
DROP TABLE IF EXISTS public.messages CASCADE ;
CREATE TABLE public.messages (
                                 entity_id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INTEGER references users(entity_id) ,
                                 chat_id INTEGER references chats(entity_id),
                                 content VARCHAR (250) NOT NULL,
                                 created_date TIMESTAMP NOT NULL ,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR

);

DROP TABLE IF EXISTS public.friends CASCADE ;
CREATE TABLE public.friends (
                                 entity_id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT REFERENCES users(entity_id) ,
                                 friend_id INT REFERENCES users(entity_id),
                                 status VARCHAR (250) NOT NULL,

                                 created_date TIMESTAMP NOT NULL ,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR

);

DROP TABLE IF EXISTS public.users_friends CASCADE ;
CREATE TABLE public.users_friends (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT NOT NULL,
                                 user_friend_id INT NOT NULL ,
                                 foreign key (user_id) references users(entity_id),
                                 foreign key (user_friend_id) references friends (entity_id)


);

DROP TABLE IF EXISTS public.likes CASCADE ;
CREATE TABLE public.likes (
                                entity_id INT AUTO_INCREMENT PRIMARY KEY,
                                post_id  INTEGER REFERENCES posts (entity_id),
                                user_id  INTEGER REFERENCES users (entity_id),
                                created_date TIMESTAMP NOT NULL ,
                                updated_date TIMESTAMP NOT NULL,
                                    created_by VARCHAR,
                                     updated_by VARCHAR


);
DROP TABLE IF EXISTS public.users_chats CASCADE ;
CREATE TABLE public.users_chats (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT NOT NULL,
                                      chat_id INT NOT NULL ,
                                      foreign key (user_id) references users(entity_id),
                                      foreign key (chat_id) references chats(entity_id)

);
DROP TABLE IF EXISTS public.chat_images CASCADE ;
CREATE TABLE public.chat_images(
                             entity_id INT AUTO_INCREMENT PRIMARY KEY ,
                             image_url VARCHAR NOT NULL ,
                             chat_id  INTEGER REFERENCES chats (entity_id),
                             created_date TIMESTAMP NOT NULL ,
                             updated_date TIMESTAMP NOT NULL,
                             created_by VARCHAR,
                             updated_by VARCHAR

);
DROP TABLE IF EXISTS public.post_images CASCADE ;
CREATE TABLE public.post_images(
                                   entity_id INT AUTO_INCREMENT PRIMARY KEY ,
                                   image_url VARCHAR NOT NULL ,
                                   post_id  INTEGER REFERENCES posts (entity_id),
                                       created_date TIMESTAMP NOT NULL ,
                                   updated_date TIMESTAMP NOT NULL,
                                   created_by VARCHAR,
                                   updated_by VARCHAR

);
DROP TABLE IF EXISTS public.reposts CASCADE ;
CREATE TABLE public.reposts(
                                   entity_id INT AUTO_INCREMENT PRIMARY KEY ,
                                   user_id  INTEGER REFERENCES users (entity_id),
                                   post_id  INTEGER REFERENCES posts (entity_id),
                                       created_date TIMESTAMP NOT NULL ,
                                   updated_date TIMESTAMP NOT NULL,
                                   created_by VARCHAR,
                                   updated_by VARCHAR

);