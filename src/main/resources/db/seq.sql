set foreign_key_checks =0;
ALTER TABLE customer modify id int(11) not null auto_increment;


ALTER TABLE info modify id int(11)  not null auto_increment;

ALTER TABLE loan modify id int(11)  not null auto_increment;

ALTER TABLE location modify int(11)  id not null auto_increment;

ALTER TABLE logs modify id int(11) not null auto_increment;

ALTER TABLE noti modify id int(11)  not null auto_increment;


ALTER TABLE project_steps int(11)  modify id not null auto_increment;

ALTER TABLE so modify id int(11)  not null auto_increment;

ALTER TABLE steps modify id int(11) not null auto_increment;

ALTER TABLE sub_location modify id int(11) not null auto_increment;

ALTER TABLE sub_sub_location modify int(11) id not null auto_increment;

ALTER TABLE transfer_sheet modify id int(11) not null auto_increment;

ALTER TABLE users modify id int(11) not null auto_increment;
set foreign_key_checks =1;
