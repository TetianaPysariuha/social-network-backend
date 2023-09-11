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