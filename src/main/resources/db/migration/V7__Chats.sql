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

INSERT INTO chats (created_date, updated_date, created_by, updated_by)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Roger Williams', 'Roger Williams');

INSERT INTO messages (content, created_date, updated_date, created_by, updated_by, chat_id, user_id)
VALUES
    ('Hello', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 1, 1),
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cris Thomson', 'Cris Thomson', 1, 2),
    ('How are you?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cris Thomson"', 'Cris Thomson', 1, 2),
    ('Fine, thanks. And you?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 1, 1),
    ('What about your project?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White', 2, 3),
    ('I wll finish it on Monday?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Amely Brown', 'Amely Brown', 2, 4),
    ('What do yo do today?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Amely Brown', 'Amely Brown', 2, 4),
    ('I dont have any plans for tonight. Do you have some ideas?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White', 2, 3),
    ('Some coffee?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Amely Brown', 'Amely Brown', 2, 4),
    ('Ofc! Meet in our coffee house at 20:00?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White', 2, 3),
    ('OK! See you!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Amely Brown', 'Amely Brown', 2, 4),
    ('Hello guys!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Roger Williams', 'Roger Williams', 3, 5),
    ('Can you drop here some cute images?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Roger Williams', 'Roger Williams', 3, 5),
    ('Ofc! Take it.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 3, 6),
    ('How much do you need? :D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Igor Gray', 'Igor Gray', 3, 7);

INSERT INTO message_images (created_date, updated_date, created_by, updated_by, img_url, message_id,chat_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 'https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2', 3,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://images.pexels.com/photos/1643457/pexels-photo-1643457.jpeg?auto=compress&cs=tinysrgb&w=1200', 14,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://images.pexels.com/photos/2071873/pexels-photo-2071873.jpeg?auto=compress&cs=tinysrgb&w=1200', 14,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://images.pexels.com/photos/1183434/pexels-photo-1183434.jpeg?auto=compress&cs=tinysrgb&w=1200', 14,3);


INSERT INTO public.users_chats (user_id,chat_id) VALUES

                                                     (1,1),
                                                     (2,1),
                                                     (3,2),
                                                     (4,2),
                                                     (5,3),
                                                     (6,3),
                                                     (7,3);
