DROP TABLE IF EXISTS public.notifications CASCADE ;
CREATE TABLE public.notifications (
                                      id SERIAL PRIMARY KEY,
                                      type VARCHAR NOT NULL ,
                                      status VARCHAR NOT NULL ,
                                      sender_full_name VARCHAR NOT NULL ,
                                      content VARCHAR NOT NULL ,
                                      created_date TIMESTAMP NOT NULL ,
                                      updated_date TIMESTAMP NOT NULL,
                                      created_by VARCHAR,
                                      updated_by VARCHAR



);
INSERT INTO public.notifications(type,status,sender_full_name,content,created_date,updated_date) VALUES
                                                                                                                 ('newLike','pending','Greg White','New like from Greg White','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                 ('newRepost','pending','Amely Brown','Amely Brown shared your post','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                 ('newMessage','pending','Cris Thomson','New message from Cris Tomson','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                 ('friendsBirthday','pending','Alex Smith','Today is Alex Smith Birthday','2016-07-26 05:15:58','2016-07-26 05:15:58');
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