

DROP TABLE IF EXISTS public.notifications CASCADE ;
CREATE TABLE public.notifications (
                                    id SERIAL PRIMARY KEY,
                                    type VARCHAR NOT NULL ,
                                    status VARCHAR NOT NULL ,
                                    receiver_id INT NOT NULL,
                                    sender_full_name VARCHAR NOT NULL ,
                                    content VARCHAR NOT NULL ,
                                    foreign key (receiver_id) references users(id),
                                    created_date TIMESTAMP NOT NULL ,
                                    updated_date TIMESTAMP NOT NULL,
                                    created_by VARCHAR,
                                    updated_by VARCHAR



);
INSERT INTO public.notifications(type,status,receiver_id,sender_full_name,content,created_date,updated_date) VALUES
                                                                                   ('newMessage','pending',1,'Cris Thomson','New message from Cris Tomson','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   ('newLike','pending',2,'Greg White','New like from Greg White','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   ('newRepost','pending',4,'Jason Yellow','Jason Yellow shared your post','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                   ('friendsBirthday','pending',3,'Alex Smith','Today is Alex Smith Birthday','2016-07-26 05:15:58','2016-07-26 05:15:58');
