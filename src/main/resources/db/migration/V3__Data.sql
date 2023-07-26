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

INSERT INTO public.friends(user_id,friend_id,status,created_date,updated_date) VALUES

                                                                                   (1,2,'fulfilled','2016-07-26 05:15:58','2016-07-26 05:15:58'),

                                                                                   (3,2,'fulfilled','2016-07-26 05:15:58','2016-07-26 05:15:58'),

                                                                                   (3,1,'pending','2016-07-26 05:15:58','2016-07-26 05:15:58');


INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
    (1, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
    (3, 'comment', 'Это отличный пост!', 1, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
    (2, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00');


