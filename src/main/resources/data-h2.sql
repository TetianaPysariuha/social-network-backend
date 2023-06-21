
INSERT INTO public.users ( entity_id,full_name,email,password,city,country,gender,work_place,about,birth_date,profile_picture,profile_background_picture,created_date,updated_date) VALUES
                                                                                                                                                                                               (100,'Alex Smith','alex@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','New York','USA','male','UIGA','successful investor',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),'https://qph.cf2.quoracdn.net/main-qimg-ed424b4d548229863a57603462976e3e.webp' ,'https://photographylife.com/wp-content/uploads/2017/01/Best-of-2016-Nasim-Mansurov-20.jpg',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')) ,
                                                                                                                                                                                               (101,'Cris Thomson','cris@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','London','Great Britain','male','JklO','web designer',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'), 'https://assets.thehansindia.com/h-upload/2020/05/16/969648-k-pop-singer-bts-v-most-han.webp','https://ichef.bbci.co.uk/news/999/cpsprodpb/6D5A/production/_119449972_10.jpg',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                                                                                                               (102,'Roger Williams','roger@gmail.com','$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq','LA','USA','male','FAliy Hospital','surgeon',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),'https://www.thecoldwire.com/wp-content/uploads/2021/11/Closeup-portrait-of-a-handsome-man-at-gym.jpeg','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7SPMxYYTAmSxcMRvEIwePcBNpJi9eEdEM9A&usqp=CAU',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));






INSERT INTO public.posts (entity_id,user_id,content,post_type,parent_id,created_date,updated_date) VALUES
                                                                                                              (100,102,'Hy Today is a good day Im going to visit very interesting place','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (101,100,'My Scala course is finally released on the Manning learning platform. I''ve invested quite a lot of time and it was challenging due to the constantly challenging conditions in Ukraine (power outages, missile attacks)','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (102,101, 'Understood - yours was a point well made. I’m only exploring the discussion. Some of my procedural coding has had way too many indents, I fully admit. FP can be both beautiful and functional!','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (103,100,'However, in real codebases, you are dealing with longer nested code blocks which are not that readable anymore, even for proficient imperative programmers. Additionally, as Tomasz Lipinski noted, they can have some hidden unexpected parts.My objective here was to make a point .','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (104,102,'When I started my programming journey I dreamed of a widescreen display  The indentation in my code was killing me.Later in my career, I learned that a code needs to read like a book  No unnecessary words, snappy, and to the point!Thats what list comprehensions are for.','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (105,101,'I am immensely grateful to the organizers for recognizing my potential and inviting me to the renowned PyCon 2023 conference, which took place in the picturesque city of Florence, Italy.  The conference served as an invaluable platform for industry experts, thought leaders.','post',null,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                              (106,100,'ddffgkrksmxme','comment',100,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));




INSERT INTO public.chats (entity_id,created_date,updated_date) VALUES

                                                                          (207,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (208,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (209,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (210,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (211,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (212,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                          (213,PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));



INSERT INTO public.messages (entity_id,user_id,chat_id,content,created_date,updated_date) VALUES

                                                                                                     (200,102,207,'Hy',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (201,101,207,'Hello1',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (202,101,208,'Hy',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (203,102,208,'Hello2',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (204,100,209,'Hy',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (205,101,209,'Hy',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                     (206,101,207,'Hello',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));
INSERT INTO public.friends(entity_id,user_id,friend_id,status,created_date,updated_date) VALUES

                                                                                                    (214,100,101,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (215,101,100,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (216,102,101,'fulfilled',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (217,100,102,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (218,101,102,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en')),
                                                                                                    (219,102,100,'pending',PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'),PARSEDATETIME('26 Jul 2016, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'));





INSERT INTO public.users_friends (id,user_id,user_friend_id) VALUES

                                                                 (81,100,215),
                                                                 (82,100,216),
                                                                 (83,101,214),
                                                                 (84,101,216),
                                                                 (85,102,214),
                                                                 (86,102,215);



