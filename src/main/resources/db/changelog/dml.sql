INSERT INTO employee (id, name, position, email, manager_id)
VALUES
('1','Tri','MNGR0','tri@mail.com', null),
('2','Rahman','MNGR1','rahman@mail.com', '1'),
('3','Ilham','MNGR1','ilham@mail.com', '1'),
('4','Arif','MNGR2','arif@mail.com', '2'),
('5','Hasan','MNGR2','hasan@mail.com', '2'),
('6','Sulaiman','STAFF','sulaiman@mail.com', '4'),
('7','Citra','STAFF','citra@mail.com', '4'),
('8','Taufik','STAFF','taufik@mail.com', '5'),
('9','Yuda','STAFF','yuda@mail.com', '5'),
('10','Bagus','STAFF','bagus@mail.com', '5');
