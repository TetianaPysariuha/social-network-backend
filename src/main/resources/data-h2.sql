

INSERT INTO public.users ( id,full_name,email,password,city,country,gender,work_place,about,birth_date,profile_picture,profile_background_picture,created_date,updated_date) VALUES
                                                                                                                                                                                               (100,'Alex Smith','alex@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','UIGA','successful investor',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),'https://qph.cf2.quoracdn.net/main-qimg-ed424b4d548229863a57603462976e3e.webp' ,'https://photographylife.com/wp-content/uploads/2017/01/Best-of-2016-Nasim-Mansurov-20.jpg',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')) ,
                                                                                                                                                                                               (101,'Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'), 'https://assets.thehansindia.com/h-upload/2020/05/16/969648-k-pop-singer-bts-v-most-han.webp','https://ichef.bbci.co.uk/news/999/cpsprodpb/6D5A/production/_119449972_10.jpg',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                                                                                                               (102,'Roger Williams','roger@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','LA','USA','male','FAliy Hospital','surgeon',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),'https://www.thecoldwire.com/wp-content/uploads/2021/11/Closeup-portrait-of-a-handsome-man-at-gym.jpeg','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7SPMxYYTAmSxcMRvEIwePcBNpJi9eEdEM9A&usqp=CAU',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));










INSERT INTO public.friends(id,user_id,friend_id,status,created_date,updated_date) VALUES

                                                                                                    (214,100,101,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (215,101,100,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (216,102,101,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (217,100,102,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (218,101,102,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (219,102,100,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));

INSERT INTO chats (created_date, updated_date, created_by, updated_by)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David');

INSERT INTO messages (content, created_date, updated_date, created_by, updated_by, chat_id, user_id)
VALUES
    ('Hello', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 1, 101),
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 1, 102),
    ('How are you?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 2, 100),
    ('I am fine, thanks!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 2, 101),
    ('Good morning', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 3, 100);

INSERT INTO message_images (created_date, updated_date, created_by, updated_by, img_url, message_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 'image1.jpg', 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 'image2.jpg', 2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 'image3.jpg', 3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 'image4.jpg', 4),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 'image5.jpg', 5);







INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
  (100, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
  (101, 'comment', 'Это отличный пост!', NULL, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
  (102, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00');

INSERT INTO public.users_liked_posts (post_id, user_id)
VALUES
  (1, 100),
  (1, 101),
  (2, 102),
  (3, 100);

INSERT INTO public.users_reposted_posts (post_id, user_id)
VALUES
  (2, 101),
  (3, 100);

INSERT INTO public.post_images (post_id, img_url, created_date, updated_date)
VALUES
  (1, 'https://example.com/image1.jpg', '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
  (2, 'https://example.com/image2.jpg', '2023-06-25 10:05:00', '2023-06-25 10:05:00');

INSERT INTO public.users_chats (id,user_id,chat_id) VALUES

                                                        (91,100,1),
                                                        (92,100,2),
                                                        (93,101,3),
                                                        (94,101,4),
                                                        (95,102,1),
                                                        (96,102,2);

