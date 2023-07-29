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
    ('Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer','1999-15-02 00:00:00', 'https://assets.thehansindia.com/h-upload/2020/05/16/969648-k-pop-singer-bts-v-most-han.webp','https://ichef.bbci.co.uk/news/999/cpsprodpb/6D5A/production/_119449972_10.jpg','2023-06-25 10:00:00','2023-06-25 10:00:00', true, 'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
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
