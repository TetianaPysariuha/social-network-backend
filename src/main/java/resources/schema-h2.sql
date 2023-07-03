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
  user_id INT,
  FOREIGN KEY (chat_id) REFERENCES chats(id) ,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
DROP TABLE IF EXISTS public.message_images  CASCADE ;
CREATE TABLE public.message_images (
  id INT PRIMARY KEY AUTO_INCREMENT,
    created_date TIMESTAMP NOT NULL,
                                     updated_date TIMESTAMP NOT NULL,
                                     created_by VARCHAR,
                                     updated_by VARCHAR,
  img_url VARCHAR(255),
  message_id INT,
  FOREIGN KEY (message_id) REFERENCES messages(id)
);
