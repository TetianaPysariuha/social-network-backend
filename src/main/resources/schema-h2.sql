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
