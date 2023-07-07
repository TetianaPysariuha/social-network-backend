
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


DROP TABLE IF EXISTS public.users_chats CASCADE ;
CREATE TABLE public.users_chats (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT NOT NULL,
                                      chat_id INT NOT NULL ,
                                      foreign key (user_id) references users(id),
                                      foreign key (chat_id) references chats(id)

);
DROP TABLE IF EXISTS public.chats  CASCADE ;
CREATE TABLE public.chats (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              created_date TIMESTAMP NOT NULL,
                              updated_date TIMESTAMP NOT NULL,
                              created_by VARCHAR,
                              updated_by VARCHAR
);
DROP TABLE IF EXISTS public.messages  CASCADE ;
CREATE TABLE public.messages (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 content VARCHAR(255),
                                 created_date TIMESTAMP NOT NULL,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR,
                                 chat_id INT,
                                 user_id INT ,
                                 FOREIGN KEY (chat_id) REFERENCES chats(id),
                                 FOREIGN KEY (user_id) REFERENCES users(id)

);
DROP TABLE IF EXISTS public.message_images  CASCADE ;
CREATE TABLE public.message_images (
                                       id INT PRIMARY KEY AUTO_INCREMENT,
                                       created_date TIMESTAMP NOT NULL,
                                       updated_date TIMESTAMP NOT NULL,
                                       created_by VARCHAR,
                                       updated_by VARCHAR,
                                       img_url VARCHAR,
                                       message_id INT,
                                       chat_id INT,
                                       FOREIGN KEY (chat_id) REFERENCES chats(id),
                                       FOREIGN KEY (message_id) REFERENCES messages(id)
);

DROP TABLE IF EXISTS public.posts CASCADE ;
CREATE TABLE public.posts (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT REFERENCES users(id),

                               content VARCHAR(355),
                               post_type VARCHAR(255) NOT NULL,
                               parent_id INT,
                               created_date TIMESTAMP NOT NULL,
                               updated_date TIMESTAMP NOT NULL,
                               created_by VARCHAR,
                               updated_by VARCHAR
);


DROP TABLE IF EXISTS public.post_images CASCADE ;
CREATE TABLE public.post_images (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                               post_id INT REFERENCES posts(id),
                               img_url VARCHAR(255),
                               created_date TIMESTAMP NOT NULL,
                               updated_date TIMESTAMP NOT NULL,
                               created_by VARCHAR,
                               updated_by VARCHAR
);

DROP TABLE IF EXISTS public.likes CASCADE;
CREATE TABLE public.likes (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          user_id INT references users (id),
                                          post_id INT references posts(id),
                                          created_date TIMESTAMP NOT NULL,
                                          updated_date TIMESTAMP NOT NULL,
                                          created_by VARCHAR,
                                          updated_by VARCHAR

);
DROP TABLE IF EXISTS public.reposts CASCADE;
CREATE TABLE public.reposts (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              user_id INT references users (id),
                              post_id INT references posts(id),
                              created_date TIMESTAMP NOT NULL,
                              updated_date TIMESTAMP NOT NULL,
                              created_by VARCHAR,
                              updated_by VARCHAR

);