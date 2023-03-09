-- blog user add

INSERT INTO bloguser(name, email, password, is_admin, is_active, updated_at) VALUES('test01', 'test01@gmail.com', '1234', true, true, CURRENT_TIMESTAMP)
INSERT INTO bloguser(name, email, password, is_admin, is_active, updated_at) VALUES('test02', 'test02@gmail.com', '1234', true, true, CURRENT_TIMESTAMP)
INSERT INTO bloguser(name, email, password, is_admin, is_active, updated_at) VALUES('test03', 'test03@gmail.com', '1234', true, true, CURRENT_TIMESTAMP)
INSERT INTO bloguser(name, email, password, is_admin, is_active, updated_at) VALUES('test04', 'test04@gmail.com', '1234', true, true, CURRENT_TIMESTAMP)
INSERT INTO bloguser(name, email, password, is_admin, is_active, updated_at) VALUES('test05', 'test05@gmail.com', '1234', true, true, CURRENT_TIMESTAMP)


-- category add
INSERT INTO category(name) VALUES('graphics')
INSERT INTO category(name) VALUES('infra')
INSERT INTO category(name) VALUES('web')

-- article add

INSERT INTO article(title, content, author_id, category_id) VALUES('title-1', 'content-1', 1, 1)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-2', 'content-2', 1, 1)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-3', 'content-3', 1, 2)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-4', 'content-4', 1, 2)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-5', 'content-5', 1, 3)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-6', 'content-6', 1, 3)
INSERT INTO article(title, content, author_id, category_id) VALUES('title-7', 'content-7', 1, 3)

