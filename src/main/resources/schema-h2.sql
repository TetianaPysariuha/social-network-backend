DROP TABLE IF EXISTS public.users  CASCADE ;
CREATE TABLE public.users (
                             id INT AUTO_INCREMENT PRIMARY KEY,
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
                             id INT AUTO_INCREMENT PRIMARY KEY ,
                             user_id INTEGER references users(id),
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
                             id INT AUTO_INCREMENT PRIMARY KEY ,
                             created_date TIMESTAMP NOT NULL ,
                            updated_date TIMESTAMP NOT NULL,
                             created_by VARCHAR,
                             updated_by VARCHAR

);
DROP TABLE IF EXISTS public.messages CASCADE ;
CREATE TABLE public.messages (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INTEGER references users(id) ,
                                 chat_id INTEGER references chats(id),
                                 content VARCHAR (250) NOT NULL,
                                 created_date TIMESTAMP NOT NULL ,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR

);

DROP TABLE IF EXISTS public.friends CASCADE ;
CREATE TABLE public.friends (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 user_id INT REFERENCES users(id) ,
                                 friend_id INT REFERENCES users(id),
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
                                 foreign key (user_id) references users(id),
                                 foreign key (user_friend_id) references friends (id)


);

DROP TABLE IF EXISTS public.likes CASCADE ;
CREATE TABLE public.likes (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                post_id  INTEGER REFERENCES posts (id),
                                user_id  INTEGER REFERENCES users (id),
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
                                      foreign key (user_id) references users(id),
                                      foreign key (chat_id) references chats(id)

);
DROP TABLE IF EXISTS public.chat_images CASCADE ;
CREATE TABLE public.chat_images(
                             id INT AUTO_INCREMENT PRIMARY KEY ,
                             image_url VARCHAR NOT NULL ,
                             chat_id  INTEGER REFERENCES chats (id),
                             created_date TIMESTAMP NOT NULL ,
                             updated_date TIMESTAMP NOT NULL,
                             created_by VARCHAR,
                             updated_by VARCHAR

);
DROP TABLE IF EXISTS public.post_images CASCADE ;
CREATE TABLE public.post_images(
                                   id INT AUTO_INCREMENT PRIMARY KEY ,
                                   image_url VARCHAR NOT NULL ,
                                   post_id  INTEGER REFERENCES posts (id),
                                       created_date TIMESTAMP NOT NULL ,
                                   updated_date TIMESTAMP NOT NULL,
                                   created_by VARCHAR,
                                   updated_by VARCHAR

);
DROP TABLE IF EXISTS public.reposts CASCADE ;
CREATE TABLE public.reposts(
                                   id INT AUTO_INCREMENT PRIMARY KEY ,
                                   user_id  INTEGER REFERENCES users (id),
                                   post_id  INTEGER REFERENCES posts (id),
                                       created_date TIMESTAMP NOT NULL ,
                                   updated_date TIMESTAMP NOT NULL,
                                   created_by VARCHAR,
                                   updated_by VARCHAR

);