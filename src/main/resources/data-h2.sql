INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
  (NULL, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
  (NULL, 'comment', 'Это отличный пост!', NULL, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
  (NULL, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00');
