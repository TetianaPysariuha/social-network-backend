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

INSERT INTO public.users (
    full_name,
    email,
    password,
    city,
    country,
    gender,
    work_place,
    about,
    birth_date,
    profile_picture,
    profile_background_picture,
    created_date,
    updated_date,
    activated,
    activation_code) VALUES
                         ('Alex Smith','alex@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','UIGA','successful investor','1985-06-11 00:00:00','https://qph.cf2.quoracdn.net/main-qimg-ed424b4d548229863a57603462976e3e.webp' ,'https://photographylife.com/wp-content/uploads/2017/01/Best-of-2016-Nasim-Mansurov-20.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00') ,
                         ('Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer','1999-02-15 00:00:00', 'https://assets.thehansindia.com/h-upload/2020/05/16/969648-k-pop-singer-bts-v-most-han.webp','https://ichef.bbci.co.uk/news/999/cpsprodpb/6D5A/production/_119449972_10.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Greg White','greg@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','LA','USA','male','FAliy Hospital','surgeon','2016-07-26 00:00:00','https://storage.yandexcloud.net/yandexpro/storage/images/originals/qZEALznNzzFc0pO7igFzH1qNsrhvp2pOpvNjVzcv.jpg','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7SPMxYYTAmSxcMRvEIwePcBNpJi9eEdEM9A&usqp=CAU','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Amely Brown','amely@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Kyiv','Ukraine','female','HollyDay Hotel','receptionist','2007-01-09 00:00:00','https://aif-s3.aif.ru/images/019/507/eeba36a2a2d37754bab8b462f4262d97.jpg','https://moya-planeta.ru/upload/images/l/51/ed/51edab18a4faf2efcf3edf37a51870f7adb69209.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Roger Williams','roger@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Boston','USA','male','MI','student','2008-12-08 00:00:00','https://www.thecoldwire.com/wp-content/uploads/2021/11/Closeup-portrait-of-a-handsome-man-at-gym.jpeg','https://ethnomir.ru/upload/medialibrary/6fb/ozero_baikal.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Jason Yellow','JasonYellow@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Boston','USA','male','Boston University','student','2010-12-25 00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgoz9SaONgwiA7QtescP08tbxF4haEHBs9Yg&usqp=CAU','https://www.ejin.ru/wp-content/uploads/2017/09/1-716.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Igor Gray','IgorGray@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Kyiv','Ukraine','male','NTKI','student','2008-12-08 00:00:00','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQ8MANQDcQRChsMnCj5bfwMOBbkGENZqZ2eA&usqp=CAU','https://klike.net/uploads/posts/2019-09/1568528357_1.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00');


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

INSERT INTO public.friends(user_id,friend_id,status,created_date,updated_date) VALUES
                                                                                   (1,2,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (2,3,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (4,5,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (3,1,'pending','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (2,4,'pending','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (1,5,'pending','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (2,5,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58');
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

INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
    (1, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
    (3, 'comment', 'Это отличный пост!', 1, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
    (2, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (1, 'post', 'Здесь я провел восхитительный отпуск!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
    (3, 'comment', 'Прекрасное фото! ', 1, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
    (2, 'post', 'Новый рецепт моей любимой пасты ', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (1, 'post', 'Наш новый член семьи ', NULL, '2023-06-25 10:15:00', '2023-06-25 10:15:00'),
    (3, 'comment', 'Какой милый котенок!', 4, '2023-06-25 10:20:00', '2023-06-25 10:20:00'),
    (2, 'post', 'Поделюсь своими мыслями о путешествии...', NULL, '2023-06-25 10:25:00', '2023-06-25 10:25:00'),
    (1, 'comment', 'У вас просто потрясающие фото!', 6, '2023-06-25 10:30:00', '2023-06-25 10:30:00'),
    (3, 'post', 'Сегодня замечательный день! ', NULL, '2023-06-25 10:35:00', '2023-06-25 10:35:00'),
    (2, 'comment', 'Отличный настрой на фото ', 8, '2023-06-25 10:40:00', '2023-06-25 10:40:00'),
    (1, 'post', 'Поздравляю с днем рождения, друг! ', NULL, '2023-06-25 10:45:00', '2023-06-25 10:45:00'),
    (3, 'comment', 'Спасибо за поздравления!', 10, '2023-06-25 10:50:00', '2023-06-25 10:50:00'),
    (2, 'post', 'Моя новая татуировка! ', NULL, '2023-06-25 10:55:00', '2023-06-25 10:55:00'),
    (1, 'comment', 'Очень стильно!', 10, '2023-06-25 11:00:00', '2023-06-25 11:00:00'),
    (3, 'post', 'С друзьями на пляже ', NULL, '2023-06-25 11:05:00', '2023-06-25 11:05:00'),
    (2, 'comment', 'Какой классный день провели!', 14, '2023-06-25 11:10:00', '2023-06-25 11:10:00'),
    (1, 'post', 'Спасибо за отличное вечеринку! ', NULL, '2023-06-25 11:15:00', '2023-06-25 11:15:00');

DROP TABLE IF EXISTS public.users_liked_posts CASCADE;
CREATE TABLE public.users_liked_posts (
                                          id SERIAL PRIMARY KEY,
                                          post_id INTEGER REFERENCES posts(id),
                                          user_id INTEGER REFERENCES users(id)
);

INSERT INTO public.users_liked_posts (post_id, user_id)
VALUES
    (1, 2),(1, 3),(2, 3),(2, 1),(3, 2),(3, 1),(4, 2),(4, 3),(6, 3),(5, 1),(5, 2),(8, 1),(7, 2),(7, 3),(9, 3),(11, 1),(10, 2),(10, 1),(11, 2),(7, 3),(13, 3),(12, 1),(12, 2),(14, 1),(15, 1),(15, 2),(16, 2),(16, 3),(17, 3),(17, 1),(18, 1),(18, 2);

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
INSERT INTO public.post_images (post_id, img_url, created_date, updated_date)
VALUES
    (3, 'https://cdn.pixabay.com/photo/2020/08/28/13/15/river-5524569_1280.jpg', '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (4, 'https://images.pexels.com/photos/785293/pexels-photo-785293.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', '2023-06-25 10:15:00', '2023-06-25 10:15:00'),
    (5, 'https://linchakin.com/files/word/1000/212/1.jpg', '2023-06-25 10:20:00', '2023-06-25 10:20:00'),
    (6, 'https://p4.wallpaperbetter.com/wallpaper/30/770/536/full-hd-download-nature-images-1920x1200-wallpaper-preview.jpg', '2023-06-25 10:25:00', '2023-06-25 10:25:00'),
    (7, 'https://media.istockphoto.com/id/546200366/ru/%D1%84%D0%BE%D1%82%D0%BE/%D1%80%D0%BE%D1%81%D1%82.jpg?s=612x612&w=0&k=20&c=F07W1xuXGypn38uUQgFQQoC0QC4lUIudgRyAI24kINc=', '2023-06-25 10:30:00', '2023-06-25 10:30:00'),
    (8, 'https://thumbs.dreamstime.com/b/%D1%81%D1%84%D0%B5%D1%80%D0%B0-%D0%B8%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9-44140012.jpg', '2023-06-25 10:35:00', '2023-06-25 10:35:00'),
    (9, 'https://cdn.pixabay.com/photo/2018/02/08/22/27/flower-3140492_1280.jpg', '2023-06-25 10:40:00', '2023-06-25 10:40:00'),
    (10, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8lyxdgQzYPjUH1mMXnkOQ3ZHP1KfYa8DJQA&usqp=CAU', '2023-06-25 10:45:00', '2023-06-25 10:45:00'),
    (11, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPR6-gNbPwQDSa4J7aw2wt54kn3kyp6_w90w&usqp=CAU', '2023-06-25 10:50:00', '2023-06-25 10:50:00'),
    (12, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkGnLh1zk5zJL1hatwtKg40YI9BSCfckFPYQ&usqp=CAU', '2023-06-25 10:55:00', '2023-06-25 10:55:00'),
    (3, 'https://www.interfax.ru/ftproot/photos/photostory/2022/04/29/week/week7_1100.jpg', '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (4, 'https://img02.rl0.ru/afisha/e780x-i/daily.afisha.ru/uploads/images/9/c8/9c8dbd93078c4276a741b47c3fe1502b.jpg', '2023-06-25 10:15:00', '2023-06-25 10:15:00'),
    (4, 'https://s0.tchkcdn.com/g-49bK-ihki_h_sf4gLEKMHQ/17/261234/660x480/f/0/05d_2a99744_dyka_pryroda_bilka_ta_tulpan.jpg', '2023-06-25 10:20:00', '2023-06-25 10:20:00'),
    (5, 'https://p4.wallpaperbetter.com/wallpaper/30/770/536/full-hd-download-nature-images-1920x1200-wallpaper-preview.jpg', '2023-06-25 10:25:00', '2023-06-25 10:25:00'),
    (5, 'https://klike.net/uploads/posts/2021-04/1618815701_51.jpg', '2023-06-25 10:30:00', '2023-06-25 10:30:00'),
    (5, 'https://cdn.mapme.club/images/2569/25693-19-nerealno-veselyx-foto-iz-zhizni-dikix-zhivotnyx-oni---luchshie-komedianty.jpg', '2023-06-25 10:35:00', '2023-06-25 10:35:00'),
    (6, 'https://greenbelarus.info/files/field/image/comedy3.jpg', '2023-06-25 10:40:00', '2023-06-25 10:40:00'),
    (6, 'https://kartinkof.club/uploads/posts/2022-04/1649784557_1-kartinkof-club-p-rzhachnie-kartinki-zhivotnikh-1.jpg', '2023-06-25 10:45:00', '2023-06-25 10:45:00'),
    (6, 'https://n1s1.hsmedia.ru/30/e0/a9/30e0a97ecb4b1967ee4339998f8613c6/1600x1066_0xac120003_13834569621568370571.jpg', '2023-06-25 10:50:00', '2023-06-25 10:50:00'),
    (6, 'https://static7.tgstat.ru/channels/_0/39/390cb340f17810c0ffc4e041d39dd874.jpg', '2023-06-25 10:55:00', '2023-06-25 10:55:00');

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
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cris Thomson"', 'Cris Thomson', 1, 2),
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



DROP TABLE IF EXISTS public.message_status  CASCADE ;
CREATE TABLE message_status (
                                message_id INT,
                                user_id INT,
                                PRIMARY KEY (message_id, user_id),
                                FOREIGN KEY (message_id) REFERENCES messages(id),
                                FOREIGN KEY (user_id) REFERENCES users(id)
);

