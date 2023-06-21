--create tables
BEGIN; 



INSERT INTO public.customers (entity_id,name,email,password,tel_num,age,creation_date,last_modified_date) VALUES
                                                                                              (100,'Alex Smith', 'gtrgtg@gmail.com','123','0675432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (101,'Cris Thomson', 'feers2@gmail.com','123','0675432178', 34,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                              (102,'Roger Williams','dtrgrvrg@gmail.com','123','0997653421',43,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                              (103,'Thomas Spencer','dtrgravrg@gmail.com','123','0876542345',35,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                              (104,'Alexa Tomson', 'alexa@gmail.com','123','0505432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (105,'Kate Spade', 'kate@gmail.com','123','0675432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (106,'Michael Colins', 'michael@gmail.com','123','0505432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (107,'John Richardson', 'john@gmail.com','123','0675432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (108,'Sara Parker ', 'sara@gmail.com','123','0505432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (109,'Tim Johnson', 'tim@gmail.com','123','0675432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (110,'Mary Olsen', 'mary@gmail.com','123','0505432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (111,'Linda Hendricks', 'linda@gmail.com','123','0675432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ,
                                                                                              (112,'Marta Spencer', 'marta@gmail.com','123','0505432178', 23,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')) ;



INSERT INTO public.accounts (entity_id,number,currency,balance,customer_id,creation_date,last_modified_date) VALUES
                                                                                                                 (100,'1a3e8fd2-ab6c-486b-a0c6-4eab6a7dc857','UAH',0,100,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (101,'d4f441c8-c73a-4de0-a20a-12a58dd24589','EUR', 0,100,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (102,'78905f8b-e9d7-4e88-a782-d083207bcc20','USD', 0,101,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (103,'cfcbbf3f-9128-44f8-b51a-ad8813d7093d','UAH',0,101,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (104,'5dc9a4e3-7323-4cfd-9cb2-5123a03caf2e','EUR', 0,102,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (105,'e61c4a69-9408-4ad5-9d0c-5996ef52fac5','USD', 0,102,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (106,'e277a12f-132a-4382-a330-5674657f3e1a','UAH',0,103,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (107,'50d9f1e8-b4f6-4729-bad1-1e499314d59c','EUR', 0,103,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (108,'78f39904-721f-4553-bbcd-ddf1a111349b','UAH', 0,104,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (109,'9d2a514d-c047-4370-9efc-62dfcab4a7d2','GBP', 0,104,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (110,'11de0b35-fcb3-4998-956a-869345b3ee44','EUR', 0,105,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (111,'9e59b3ce-3795-421e-bd25-059cb93af1c5','UAH', 0,105,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (112,'dac3112b-b1cb-4f6a-ac34-da42f6992a54','EUR', 0,106,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (113,'bdbdb728-8ae6-46ea-8585-d7130648782a','USD', 0,106,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (114,'a198f222-2d8b-491d-8ad2-494475947d91','EUR', 0,107,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (115,'9603a21f-9cd9-4295-b0c6-4fc98bd5c033','UAH', 0,107,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (116,'a89913a1-91f6-4d29-b2c9-0f086dad4534','EUR', 0,108,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (117,'e66db750-5635-4085-80fe-74f6a6b32aad','GBP', 0,108,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (118,'5d240cd5-7ac8-43e7-9d5f-b01b5bd3b838','GBP', 0,109,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (119,'eb80dbaa-94ac-4ef0-a349-5c786af3d291','UAH', 0,109,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (120,'03ee4945-bb04-4462-8c60-d043c4eed23d','GBP', 0,110,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (121,'1aa2a180-05b6-4402-baf5-c556f69990d5','USD', 0,110,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (122,'f7ef1fda-48c5-4897-86c8-787692c28bd9','GBP', 0,111,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (123,'9f88f3b9-25e1-4c66-a0aa-73f3205a14da','UAH', 0,111,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (124,'8276b4c2-ea86-42a5-a45a-b3c5cefe17f6','GBP', 0,112,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                                 (125,'7c8801ca-7078-4e0a-b7f2-d9db77662370','EUR', 0,112,TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy'));


INSERT INTO employee (entity_id,  employer_name, location,creation_date,last_modified_date) VALUES

                                                                                                (200,'Samsung','LA',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (201,'Siemens','NewYork',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (202,'Google','Chikago',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (203,'Twitter','San Francisco',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (204,'Zara','San Francisco',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (205,'Michaels','New York',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (206,'Trader Joe','Ostin',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (207,'McDonalds','San Francisco',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (208,'Mikrosoft','Seatle',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (209,'Facebook','San Francisco',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (210,'Coca Cola','Boston',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (211,'Macys','San Francisco',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy')),
                                                                                                (212,'Victorias Secret','LA',TO_DATE('25-JUN-1997', 'dd-MON-yyyy'),TO_DATE('25-JUN-1997', 'dd-MON-yyyy'));

INSERT INTO employee_customer (id,customer_id,employee_id) VALUES

                                                                      (81,100,201),
                                                                      (82,101,200),
                                                                      (83,102,201),
                                                                      (84,103,202),
                                                                      (85,101,203),
                                                                      (86,103,200),
                                                                      (87,103,200),
                                                                      (88,103,201),
                                                                      (89,103,202),
                                                                      (90,104,200),
                                                                      (91,105,200),
                                                                      (92,106,204),
                                                                      (93,107,205),
                                                                      (94,107,206),
                                                                      (95,108,207),
                                                                      (96,108,208);

INSERT INTO users(user_id, enabled, encrypted_password, user_name) VALUES
                                                                       (101, true, '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', 'a'),
                                                                       (102, true, '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', 'admin'),
                                                                       (103, true, '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', 'test');;
INSERT INTO roles(role_id, role_name, user_id) VALUES
                                                   (101, 'USER', 101),
                                                   (102, 'ADMIN', 102),
                                                   (103,'USER',103 );


COMMIT;

