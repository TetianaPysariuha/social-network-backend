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
                         ('Alex Smith','alex@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','UIGA','successful investor','1985-06-11 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693162292/main-qimg-ed424b4d548229863a57603462976e3e_f5x9ns.webp' ,'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165786/Best-of-2016-Nasim-Mansurov-20_shlj17.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00') ,
                         ('Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer','1999-02-15 00:00:00', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165821/969648-k-pop-singer-bts-v-most-han_fee4nw.webp','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165863/_119449972_10_o8qrsh.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Greg White','greg@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','LA','USA','male','FAliy Hospital','surgeon','2016-07-26 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165901/qZEALznNzzFc0pO7igFzH1qNsrhvp2pOpvNjVzcv_hapb1f.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165923/images_yd5bux.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Amely Brown','amely@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Kyiv','Ukraine','female','HollyDay Hotel','receptionist','2007-01-09 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165949/eeba36a2a2d37754bab8b462f4262d97_gifcs4.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693165977/51edab18a4faf2efcf3edf37a51870f7adb69209_mffp73.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Roger Williams','roger@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Boston','USA','male','MI','student','2008-12-08 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166005/Closeup-portrait-of-a-handsome-man-at-gym_bx9cgv.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166025/ozero_baikal_xmrakm.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Jason Yellow','JasonYellow@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Boston','USA','male','Boston University','student','2010-12-25 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166046/images_z2y2f6.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166065/1-716_wbuydq.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Igor Gray','IgorGray@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Kyiv','Ukraine','male','NTKI','student','2008-12-08 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166086/images_eoa2km.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166104/1568528357_1_vtcytw.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Sarah Johnson','sarah@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Los Angeles','USA','female','ABC Company','marketing specialist','1990-03-22 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166216/pexels-photo-214574_vi38dr.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166301/pexels-photo-3055014_e1jjdr.jpg','2023-06-26 10:00:00','2023-06-26 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('John Doe','john@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','XYZ Corporation','software engineer','1988-05-15 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166348/pexels-photo-1484806_p7jlcx.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166375/pexels-photo-3617500_cejuqj.jpg','2023-06-27 10:00:00','2023-06-27 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Alice Brown','alice@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Chicago','USA','female','DEF Corporation','accountant','1995-09-18 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166428/pexels-photo-1181424_ldufsf.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166455/pexels-photo-2754200_krofyw.jpg','2023-06-28 10:00:00','2023-06-28 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Michael Smith','michael@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','San Francisco','USA','male','GHI Inc.','data scientist','1993-11-30 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166495/pexels-photo-4259140_jqcqbx.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166530/pexels-photo-3571551_gz8g2k.jpg','2023-06-29 10:00:00','2023-06-29 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Emily Wilson','emily@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Seattle','USA','female','JKL Ltd.','teacher','1987-07-12 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166562/pexels-photo-2853592_rbgcmj.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166589/pexels-photo-3181458_ipchpr.jpg','2023-06-30 10:00:00','2023-06-30 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('William Jones','william@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Houston','USA','male','MNO Co.','engineer','1991-04-02 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166624/pexels-photo-3628700_zhaq6t.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166648/pexels-photo-2440024_ylau2c.jpg','2023-07-01 10:00:00','2023-07-01 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Olivia Davis','olivia@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Miami','USA','female','PQR Solutions','developer','1994-08-25 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166682/pexels-photo-1181579_v5qzcl.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166709/pexels-photo-3331094_kcirar.jpg','2023-07-02 10:00:00','2023-07-02 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Daniel Wilson','daniel@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Phoenix','USA','male','STU Tech','analyst','1996-12-18 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166748/pexels-photo-12118422_csoxe5.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166773/pexels-photo-2387866_zcbza2.jpg','2023-07-03 10:00:00','2023-07-03 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Sophia Brown','sophia@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Dallas','USA','female','UVW Corporation','doctor','1992-10-05 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166799/pexels-photo-4006576_o8x1t1.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166830/pexels-photo-1144687_j1xyrs.jpg','2023-07-04 10:00:00','2023-07-04 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Matthew Davis','matthew@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Atlanta','USA','male','XYZ Corp.','researcher','1989-06-09 00:00:00','https://images.pexels.com/photos/7533347/pexels-photo-7533347.jpeg?auto=compress&cs=tinysrgb&w=800','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166890/pexels-photo-3512848_j2st4x.jpg','2023-07-05 10:00:00','2023-07-05 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                         ('Emma Johnson','emma@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','Philadelphia','USA','female','ABC Ltd.','nurse','1997-01-14 00:00:00','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166915/pexels-photo-7656336_tnp4kj.jpg','https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693166937/pexels-photo-1670187_f3kmou.jpg','2023-07-06 10:00:00','2023-07-06 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00');



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
                                                                                   (2,5,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (7,8,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (7,9,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (7,10,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (7,11,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (12,13,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (12,14,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (12,14,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (12,15,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (16,17,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (16,1,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (17,2,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (17,3,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (16,4,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (12,1,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (13,1,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (14,1,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   (15,1,'accepted','2016-07-26 05:15:58','2016-07-26 05:15:58');
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
    (3, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693167348/river-5524569_1280_hvld1j.jpg', '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (4, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693222379/pexels-photo-785293_erfzmo.jpg', '2023-06-25 10:15:00', '2023-06-25 10:15:00'),
    (5, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693222407/1_epu10r.jpg', '2023-06-25 10:20:00', '2023-06-25 10:20:00'),
    (6, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693222865/pexels-photo-210186_khcevg.jpg', '2023-06-25 10:25:00', '2023-06-25 10:25:00'),
    (7, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693222931/D1_80_D0_BE_D1_81_D1_82_c1pttq.jpg', '2023-06-25 10:30:00', '2023-06-25 10:30:00'),
    (8, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693222950/D1_81_D1_84_D0_B5_D1_80_D0_B0-_D0_B8_D0_B7_D0_BE_D0_B1_D1_80_D0_B0_D0_B6_D0_B5_D0_BD_D0_B8_D0_B9-44140012_lrusif.jpg', '2023-06-25 10:35:00', '2023-06-25 10:35:00'),
    (9, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225039/flower-3140492_1280_h3lemn.jpg', '2023-06-25 10:40:00', '2023-06-25 10:40:00'),
    (10, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225058/images_v1xobs.jpg', '2023-06-25 10:45:00', '2023-06-25 10:45:00'),
    (11, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225074/images_dgiq4f.jpg', '2023-06-25 10:50:00', '2023-06-25 10:50:00'),
    (12, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225087/images_cnmzed.jpg', '2023-06-25 10:55:00', '2023-06-25 10:55:00'),
    (3, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225101/week7_1100_tpdu87.jpg', '2023-06-25 10:10:00', '2023-06-25 10:10:00'),
    (4, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225114/9c8dbd93078c4276a741b47c3fe1502b_nq5uwf.jpg', '2023-06-25 10:15:00', '2023-06-25 10:15:00'),
    (4, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225132/05d_2a99744_dyka_pryroda_bilka_ta_tulpan_a948ap.jpg', '2023-06-25 10:20:00', '2023-06-25 10:20:00'),
    (5, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225184/pexels-photo-460775_oca6pi.jpg', '2023-06-25 10:25:00', '2023-06-25 10:25:00'),
    (5, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225205/1618815701_51_axckc0.jpg', '2023-06-25 10:30:00', '2023-06-25 10:30:00'),
    (5, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225268/25693-19-nerealno-veselyx-foto-iz-zhizni-dikix-zhivotnyx-oni---luchshie-komedianty_n96z35.jpg', '2023-06-25 10:35:00', '2023-06-25 10:35:00'),
    (6, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225330/comedy3_g1zcyx.jpg', '2023-06-25 10:40:00', '2023-06-25 10:40:00'),
    (6, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225346/1649784557_1-kartinkof-club-p-rzhachnie-kartinki-zhivotnikh-1_lf5p5p.jpg', '2023-06-25 10:45:00', '2023-06-25 10:45:00'),
    (6, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225360/1600x1066_0xac120003_13834569621568370571_xjmt7p.jpg', '2023-06-25 10:50:00', '2023-06-25 10:50:00'),
    (6, 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225374/390cb340f17810c0ffc4e041d39dd874_povlh7.jpg', '2023-06-25 10:55:00', '2023-06-25 10:55:00');

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Roger Williams', 'Roger Williams'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith');

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
    ('How much do you need? :D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Igor Gray', 'Igor Gray', 3, 7),
    ('How are you? :D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 4, 1),
    ('Fine!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White', 4, 3),
    ('go to the gym?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Greg White', 'Greg White', 4, 3),
    ('Missed me?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 5, 1),
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 6, 1),
    ('Hello guys!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 7, 1),
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Igor Gray', 'Igor Gray', 7, 7),
    ('Yo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Sarah Johnson', 'Sarah Johnson', 7, 8),
    ('Howdy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John Doe', 'John Doe', 7, 9),
    ('Would you like to meet in person?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice Brown', 'John Doe', 8, 10),
    ('At what time and where', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 8, 1);

INSERT INTO message_images (created_date, updated_date, created_by, updated_by, img_url, message_id,chat_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alex Smith', 'Alex Smith', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225838/kitty-cat-kitten-pet-45201_jw32by.jpg', 3,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225861/pexels-photo-1643457_madwrx.jpg', 14,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225878/pexels-photo-2071873_iwivie.jpg', 14,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Jason Yellow', 'Jason Yellow', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225903/pexels-photo-1183434_ncnmjc.jpg', 14,3);


INSERT INTO public.users_chats (user_id,chat_id) VALUES

                                                     (1,1),
                                                     (2,1),
                                                     (3,2),
                                                     (4,2),
                                                     (5,3),
                                                     (6,3),
                                                     (7,3),
                                                     (1,4),
                                                     (3,4),
                                                     (1,5),
                                                     (4,5),
                                                     (1,6),
                                                     (5,6),
                                                     (1,7),
                                                     (7,7),
                                                     (8,7),
                                                     (9,7),
                                                     (1,8),
                                                     (10,8);



DROP TABLE IF EXISTS public.message_status  CASCADE ;
CREATE TABLE message_status (
                                message_id INT,
                                user_id INT,
                                PRIMARY KEY (message_id, user_id),
                                FOREIGN KEY (message_id) REFERENCES messages(id),
                                FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS public.users_reposted_posts CASCADE;
CREATE TABLE public.users_reposted_posts (
                                             id SERIAL PRIMARY KEY,
                                             post_id INTEGER REFERENCES posts(id),
                                             user_id INTEGER REFERENCES users(id)
);
DROP TABLE IF EXISTS public.notifications CASCADE ;
CREATE TABLE public.notifications (
                                      id SERIAL PRIMARY KEY,
                                      type VARCHAR NOT NULL ,
                                      status VARCHAR NOT NULL ,
                                      sender_id INT NOT NULL ,
                                      updated_entity_id INT ,
                                      content VARCHAR NOT NULL ,
                                      foreign key (sender_id) references users(id),
                                      created_date TIMESTAMP NOT NULL ,
                                      updated_date TIMESTAMP NOT NULL,
                                      created_by VARCHAR,
                                      updated_by VARCHAR



);
INSERT INTO public.notifications(type,status,sender_id,content,updated_entity_id,created_date,updated_date) VALUES
                                                                                                                ('newLike','pending',3,'New like from Greg White',1,'2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                ('newRepost','pending',4,'Amely Brown shared your post',2,'2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                ('newMessage','pending',2,'New message from Cris Tomson',3,'2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                ('friendsBirthday','pending',1,'Today is Alex Smith Birthday',4,'2016-07-26 05:15:58','2016-07-26 05:15:58');
DROP TABLE IF EXISTS public.users_notifications CASCADE ;
CREATE TABLE public.users_notifications (
                                            id SERIAL PRIMARY KEY,
                                            user_id INT NOT NULL,
                                            notification_id INT NOT NULL ,
                                            foreign key (user_id) references users(id),
                                            foreign key (notification_id) references notifications(id)
);
INSERT INTO public.users_notifications (user_id,notification_id) VALUES

                                                                     (1,1),
                                                                     (2,1),
                                                                     (3,2),
                                                                     (4,2),
                                                                     (5,3),
                                                                     (2,4);
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
INSERT INTO user_images (created_date, updated_date, created_by, updated_by, img_url, user_id,image_user_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225330/comedy3_g1zcyx.jpg', 1,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225184/pexels-photo-460775_oca6pi.jpg', 2,2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225087/images_cnmzed.jpg', 3,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225058/images_v1xobs.jpg', 1,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225184/pexels-photo-460775_oca6pi.jpg', 2,2);


DROP TABLE IF EXISTS public.comments CASCADE ;
CREATE TABLE public.comments (
                                 id SERIAL PRIMARY KEY,
                                 author_id INT NOT NULL,
                                 image_id INT NOT NULL ,
                                 content VARCHAR NOT NULL ,
                                 foreign key (author_id) references users(id),
                                 foreign key (image_id) references user_images(id),
                                 created_date TIMESTAMP NOT NULL ,
                                 updated_date TIMESTAMP NOT NULL,
                                 created_by VARCHAR,
                                 updated_by VARCHAR



);
INSERT INTO public.comments(author_id,image_id,content,created_date,updated_date) VALUES
                                                                                      (1,1,'Beautiful picture','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                      (2,2,'I like that','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                      (4,3,'How beautiful','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                      (3,4,'Very interesting','2016-07-26 05:15:58','2016-07-26 05:15:58');
