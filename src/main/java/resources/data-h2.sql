INSERT INTO chats (created_date, updated_date, created_by, updated_by)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David');

INSERT INTO messages (content, created_date, updated_date, created_by, updated_by, chat_id, user_id)
VALUES
    ('Hello', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 1, 1),
    ('Hi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 1, 2),
    ('How are you?', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 2, 3),
    ('I am fine, thanks!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 2, 1),
    ('Good morning', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 3, 2);

INSERT INTO message_images (created_date, updated_date, created_by, updated_by, img_url, message_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 'image1.jpg', 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 'image2.jpg', 2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 'image3.jpg', 3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 'image4.jpg', 4),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 'image5.jpg', 5);
