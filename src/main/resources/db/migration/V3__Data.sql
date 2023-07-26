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

INSERT INTO public.users ( full_name,email,password,city,country,gender,work_place,about,birth_date,profile_picture,profile_background_picture,created_date,updated_date,activated,activation_code) VALUES
                                                                                                                                                                                                        ('Alex Smith','alex@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','UIGA','successful investor','1987-07-26 05:15:58','https://qph.cf2.quoracdn.net/main-qimg-ed424b4d548229863a57603462976e3e.webp' ,'https://photographylife.com/wp-content/uploads/2017/01/Best-of-2016-Nasim-Mansurov-20.jpg','1976-07-26 05:15:58','2016-07-26 05:15:58',true,'a42f726b-5483-4082-bf3c-f9fc7f980c00') ,
                                                                                                                                                                                                        ('Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer','1995-07-26 05:15:58', 'https://assets.thehansindia.com/h-upload/2020/05/16/969648-k-pop-singer-bts-v-most-han.webp','https://ichef.bbci.co.uk/news/999/cpsprodpb/6D5A/production/_119449972_10.jpg','1985-07-26 05:15:58','2016-07-26 05:15:58',true,'a42f726b-5483-4082-bf3c-f9fc7f980c00'),
                                                                                                                                                                                                        ('Roger Williams','roger@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','LA','USA','male','FAliy Hospital','surgeon','1990-07-26 05:15:58','https://www.thecoldwire.com/wp-content/uploads/2021/11/Closeup-portrait-of-a-handsome-man-at-gym.jpeg','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7SPMxYYTAmSxcMRvEIwePcBNpJi9eEdEM9A&usqp=CAU','1990-07-26 05:15:58','2016-07-26 05:15:58',true,'a42f726b-5483-4082-bf3c-f9fc7f980c00');











INSERT INTO public.friends(user_id,friend_id,status,created_date,updated_date) VALUES

                                                                                   (1,2,'fulfilled','2016-07-26 05:15:58','2016-07-26 05:15:58'),

                                                                                   (3,2,'fulfilled','2016-07-26 05:15:58','2016-07-26 05:15:58'),

                                                                                   (3,1,'pending','2016-07-26 05:15:58','2016-07-26 05:15:58');


INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
    (1, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
    (3, 'comment', 'Это отличный пост!', 1, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
    (2, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00');


