REATE TABLE customer
(
	id                    INTEGER NULL,
	name                  VARCHAR(64) NULL,
	address               VARCHAR(128) NULL,
	code                  VARCHAR(64) NULL,
	status                INTEGER NULL,
	id_sub_sub_loc        INTEGER NULL,
	project               VARCHAR(64) NULL,
	start                 TIMESTAMP NULL,
	end                   TIMESTAMP NULL
)
;



ALTER TABLE customer
	ADD  PRIMARY KEY (id)
;



CREATE TABLE dic
(
	id                    INTEGER NULL,
	code                  VARCHAR(64) NULL,
	descr                  VARCHAR(128) NULL,
	id_parent             INTEGER NULL
)
;



ALTER TABLE dic
	ADD  PRIMARY KEY (id)
;



CREATE TABLE dic_dic
(
	id                    INTEGER NULL,
	name                  VARCHAR(20) NULL,
	descr                  VARCHAR(64) NULL
)
;



ALTER TABLE dic_dic
	ADD  PRIMARY KEY (id)
;



CREATE TABLE info
(
	id                    INTEGER NULL,
	code                  VARCHAR(20) NULL,
	id_project_step       INTEGER NULL,
	c1                    VARCHAR(64) NULL,
	c2                    VARCHAR(64) NULL,
	c3                    VARCHAR(64) NULL,
	c4                    VARCHAR(64) NULL,
	c5                    VARCHAR(64) NULL,
	c6                    VARCHAR(64) NULL,
	c7                    VARCHAR(64) NULL,
	c8                    VARCHAR(64) NULL,
	c9                    VARCHAR(64) NULL,
	c10                   VARCHAR(64) NULL,
	c11                   VARCHAR(64) NULL,
	c12                   VARCHAR(64) NULL,
	c13                   VARCHAR(64) NULL,
	c14                   VARCHAR(64) NULL,
	c15                   VARCHAR(64) NULL,
	c16                   VARCHAR(64) NULL,
	c17                   VARCHAR(64) NULL,
	c18                   VARCHAR(64) NULL,
	c19                   VARCHAR(64) NULL,
	c20                   VARCHAR(64) NULL,
	c21                   VARCHAR(64) NULL,
	c22                   VARCHAR(64) NULL,
	c23                   VARCHAR(64) NULL,
	c24                   VARCHAR(64) NULL,
	c25                   VARCHAR(64) NULL,
	c26                   VARCHAR(64) NULL,
	c27                   VARCHAR(64) NULL,
	c28                   VARCHAR(64) NULL,
	c29                   VARCHAR(64) NULL,
	c30                   VARCHAR(64) NULL,
	c31                   VARCHAR(64) NULL,
	c32                   VARCHAR(64) NULL,
	c33                   VARCHAR(64) NULL,
	c34                   VARCHAR(64) NULL,
	c35                   VARCHAR(64) NULL,
	c36                   VARCHAR(64) NULL,
	c37                   VARCHAR(64) NULL,
	c38                   VARCHAR(64) NULL,
	c39                   VARCHAR(64) NULL,
	c40                   VARCHAR(64) NULL,
	c41                   VARCHAR(64) NULL,
	c42                   VARCHAR(64) NULL,
	c43                   VARCHAR(64) NULL,
	c44                   VARCHAR(64) NULL,
	c45                   VARCHAR(64) NULL,
	c46                   VARCHAR(64) NULL,
	c47                   VARCHAR(64) NULL,
	c48                   VARCHAR(64) NULL,
	c49                   VARCHAR(64) NULL,
	c50                   VARCHAR(64) NULL,
	c51                   VARCHAR(64) NULL,
	c52                   VARCHAR(64) NULL,
	c53                   VARCHAR(64) NULL,
	c54                   VARCHAR(64) NULL,
	c55                   VARCHAR(64) NULL,
	c56                   VARCHAR(64) NULL,
	c57                   VARCHAR(64) NULL,
	c58                   VARCHAR(64) NULL,
	c59                   VARCHAR(64) NULL,
	c60                   VARCHAR(64) NULL,
	c61                   VARCHAR(64) NULL,
	c62                   VARCHAR(64) NULL,
	c63                   VARCHAR(64) NULL,
	c64                   VARCHAR(64) NULL,
	d1                    TIMESTAMP NULL,
	d2                    TIMESTAMP NULL,
	d3                    TIMESTAMP NULL,
	d4                    TIMESTAMP NULL,
	d5                    TIMESTAMP NULL,
	d6                    TIMESTAMP NULL,
	d7                    TIMESTAMP NULL,
	d8                    TIMESTAMP NULL,
	d9                    TIMESTAMP NULL,
	d10                   TIMESTAMP NULL,
	d11                   TIMESTAMP NULL,
	s1                    VARCHAR(64) NULL,
	s2                    VARCHAR(64) NULL,
	s3                    VARCHAR(64) NULL,
	s4                    VARCHAR(64) NULL,
	s5                    VARCHAR(64) NULL,
	s6                    VARCHAR(64) NULL,
	s7                    VARCHAR(64) NULL,
	s8                    VARCHAR(64) NULL,
	s9                    VARCHAR(64) NULL,
	s10                   VARCHAR(64) NULL,
	s11                   VARCHAR(64) NULL,
	p1                    VARCHAR(128) NULL,
	p2                    VARCHAR(128) NULL,
	p3                    VARCHAR(128) NULL,
	p4                    VARCHAR(128) NULL,
	p5                    VARCHAR(128) NULL,
	p6                    VARCHAR(128) NULL,
	p7                    VARCHAR(128) NULL,
	p8                    VARCHAR(128) NULL
)
;



ALTER TABLE info
	ADD  PRIMARY KEY (id)
;



CREATE TABLE loan
(
	id                    INTEGER NULL,
	code                  VARCHAR(64) NULL,
	id_customer           INTEGER NULL,
	bank                  VARCHAR(64) NULL,
	duration              INTEGER NULL,
	capacity              INTEGER NULL,
	radio                 INTEGER NULL,
	amount                INTEGER NULL,
	amount_permonth       FLOAT NULL,
	payment_time          VARCHAR(20) NULL,
	unit_price            FLOAT NULL,
	subsidy_price         FLOAT NULL,
	estimate_capacity     FLOAT NULL,
	estimate_income_permonth  FLOAT NULL,
	estimate_income_peryear  FLOAT NULL,
	act_capacity          FLOAT NULL,
	act_income_permonth   FLOAT NULL,
	act_income_peryear    FLOAT NULL,
	recharge              FLOAT NULL,
	balance               FLOAT NULL,
	risk                  FLOAT NULL,
	id_project_step       INTEGER NULL
)
;



ALTER TABLE loan
	ADD  PRIMARY KEY (id)
;



CREATE TABLE location
(
	id                    INTEGER NULL,
	name                  VARCHAR(64) NULL,
	id_sub_loc            INTEGER NULL
)
;



ALTER TABLE location
	ADD  PRIMARY KEY (id)
;



CREATE TABLE logs
(
	id                    INTEGER NULL,
	user                  INTEGER NULL,
	time                  TIMESTAMP NULL,
	url                   VARCHAR(20) NULL,
	ip                    VARCHAR(64) NULL
)
;



ALTER TABLE logs
	ADD  PRIMARY KEY (id)
;



CREATE TABLE noti
(
	id                    INTEGER NULL,
	id_step               INTEGER NULL,
	start                 VARCHAR(64) NULL,
	end                   VARCHAR(64) NULL,
	delay                 VARCHAR(64) NULL,
	delay_more            VARCHAR(64) NULL
)
;



ALTER TABLE noti
	ADD  PRIMARY KEY (id)
;



CREATE TABLE pages
(
	id                    INTEGER NULL,
	name                  VARCHAR(64) NULL,
	descr                  VARCHAR(64) NULL,
	menu_name             VARCHAR(64) NULL
)
;



ALTER TABLE pages
	ADD  PRIMARY KEY (id)
;



CREATE TABLE project_steps
(
	id                    INTEGER NULL,
	id_step               INTEGER NULL,
	name                  VARCHAR(64) NULL,
	forcast_days          INTEGER NULL,
	lasted_days           INTEGER NULL,
	start                 TIMESTAMP NULL,
	end                   TIMESTAMP NULL,
	act_days              INTEGER NULL,
	remark                VARCHAR(128) NULL,
	department            INTEGER NULL,
	progress              INTEGER NULL,
	status                INTEGER NULL,
	user                  INTEGER NULL,
	id_customer           INTEGER NULL
)
;



ALTER TABLE project_steps
	ADD  PRIMARY KEY (id)
;



CREATE TABLE role_locations
(
	id_role               INTEGER NOT NULL,
	id_sub_sub_location   INTEGER NOT NULL
)
;



ALTER TABLE role_locations
	ADD  PRIMARY KEY (id_role,id_sub_sub_location)
;



CREATE TABLE role_pages
(
	id_role               INTEGER NOT NULL,
	id_page               INTEGER NOT NULL,
	seq                   INTEGER NULL
)
;



ALTER TABLE role_pages
	ADD  PRIMARY KEY (id_role,id_page)
;



CREATE TABLE so
(
	id                    INTEGER NULL,
	id_customer           INTEGER NULL,
	creation_date         TIMESTAMP NULL,
	c1                    VARCHAR(64) NULL,
	c2                    VARCHAR(64) NULL,
	c3                    VARCHAR(64) NULL,
	c4                    VARCHAR(64) NULL,
	c5                    VARCHAR(64) NULL,
	c6                    VARCHAR(64) NULL,
	c7                    VARCHAR(64) NULL,
	c8                    VARCHAR(64) NULL,
	c9                    VARCHAR(64) NULL,
	c10                   VARCHAR(64) NULL,
	c11                   VARCHAR(64) NULL,
	c12                   VARCHAR(64) NULL,
	c13                   VARCHAR(64) NULL
)
;



ALTER TABLE so
	ADD  PRIMARY KEY (id)
;



CREATE TABLE steps
(
	id                    INTEGER NOT NULL,
	name                  VARCHAR(20) NULL,
	seq                   INTEGER NULL,
	forcast_days          INTEGER NULL,
	lasted_days           INTEGER NULL,
	form                  VARCHAR(64) NULL,
	department            INTEGER NULL
)
;



ALTER TABLE steps
	ADD  PRIMARY KEY (id)
;



CREATE TABLE sub_location
(
	id                    INTEGER NULL,
	name                  VARCHAR(64) NULL
)
;



ALTER TABLE sub_location
	ADD  PRIMARY KEY (id)
;



CREATE TABLE sub_sub_location
(
	id                    INTEGER NULL,
	name                  VARCHAR(64) NULL,
	id_sub_loc            INTEGER NULL
)
;



ALTER TABLE sub_sub_location
	ADD  PRIMARY KEY (id)
;



CREATE TABLE transfer_sheet
(
	id                    INTEGER NULL,
	code                  VARCHAR(20) NULL,
	id_project_step       INTEGER NULL,
	c1                    VARCHAR(64) NULL,
	c2                    VARCHAR(64) NULL,
	c3                    VARCHAR(64) NULL,
	c4                    VARCHAR(64) NULL,
	c5                    VARCHAR(64) NULL,
	c6                    VARCHAR(64) NULL,
	c7                    VARCHAR(64) NULL,
	c8                    VARCHAR(64) NULL,
	c9                    VARCHAR(64) NULL,
	c10                   VARCHAR(64) NULL,
	c11                   VARCHAR(64) NULL,
	c12                   VARCHAR(64) NULL,
	c13                   VARCHAR(64) NULL,
	c14                   VARCHAR(64) NULL,
	c15                   VARCHAR(64) NULL,
	c16                   VARCHAR(64) NULL,
	c17                   VARCHAR(64) NULL,
	c18                   VARCHAR(64) NULL,
	c19                   VARCHAR(64) NULL,
	c20                   VARCHAR(64) NULL,
	c21                   VARCHAR(64) NULL,
	c22                   VARCHAR(64) NULL,
	c23                   VARCHAR(64) NULL,
	c24                   VARCHAR(64) NULL,
	c25                   VARCHAR(64) NULL,
	c26                   VARCHAR(64) NULL,
	c27                   VARCHAR(64) NULL,
	c28                   VARCHAR(64) NULL,
	c29                   VARCHAR(64) NULL,
	c30                   VARCHAR(64) NULL,
	c31                   VARCHAR(64) NULL,
	c32                   VARCHAR(64) NULL,
	c33                   VARCHAR(64) NULL,
	c34                   VARCHAR(64) NULL,
	c35                   VARCHAR(64) NULL,
	c36                   VARCHAR(64) NULL,
	c37                   VARCHAR(64) NULL,
	c38                   VARCHAR(64) NULL,
	c39                   VARCHAR(64) NULL,
	c40                   VARCHAR(64) NULL,
	c41                   VARCHAR(64) NULL,
	c42                   VARCHAR(64) NULL,
	c43                   VARCHAR(64) NULL,
	c44                   VARCHAR(64) NULL,
	c45                   VARCHAR(64) NULL,
	c46                   VARCHAR(64) NULL,
	c47                   VARCHAR(64) NULL,
	c48                   VARCHAR(64) NULL,
	c49                   VARCHAR(64) NULL,
	c50                   VARCHAR(64) NULL,
	c51                   VARCHAR(64) NULL,
	c52                   VARCHAR(64) NULL,
	c53                   VARCHAR(64) NULL,
	c54                   VARCHAR(64) NULL,
	c55                   VARCHAR(64) NULL,
	c56                   VARCHAR(64) NULL,
	c57                   VARCHAR(64) NULL,
	c58                   VARCHAR(64) NULL,
	c59                   VARCHAR(64) NULL,
	c60                   VARCHAR(64) NULL,
	c61                   VARCHAR(64) NULL,
	c62                   VARCHAR(64) NULL,
	c63                   VARCHAR(64) NULL,
	c64                   VARCHAR(64) NULL,
	d1                    TIMESTAMP NULL,
	d2                    TIMESTAMP NULL,
	d3                    TIMESTAMP NULL,
	d4                    TIMESTAMP NULL,
	d5                    TIMESTAMP NULL,
	d6                    TIMESTAMP NULL,
	d7                    TIMESTAMP NULL,
	d8                    TIMESTAMP NULL,
	d9                    TIMESTAMP NULL,
	d10                   TIMESTAMP NULL,
	d11                   TIMESTAMP NULL,
	s1                    VARCHAR(64) NULL,
	s2                    VARCHAR(64) NULL,
	s3                    VARCHAR(64) NULL,
	s4                    VARCHAR(64) NULL,
	s5                    VARCHAR(64) NULL,
	s6                    VARCHAR(64) NULL,
	s7                    VARCHAR(64) NULL,
	s8                    VARCHAR(64) NULL,
	s9                    VARCHAR(64) NULL,
	s10                   VARCHAR(64) NULL,
	s11                   VARCHAR(64) NULL,
	p1                    VARCHAR(128) NULL,
	p2                    VARCHAR(128) NULL,
	p3                    VARCHAR(128) NULL,
	p4                    VARCHAR(128) NULL,
	p5                    VARCHAR(128) NULL,
	p6                    VARCHAR(128) NULL,
	p7                    VARCHAR(128) NULL,
	p8                    VARCHAR(128) NULL
)
;



ALTER TABLE transfer_sheet
	ADD  PRIMARY KEY (id)
;



CREATE TABLE users
(
	id                    INTEGER NULL,
	username              VARCHAR(64) NULL,
	password              VARCHAR(128) NULL,
	token                 VARCHAR(128) NULL,
	mobile                VARCHAR(20) NULL,
	email                 VARCHAR(64) NULL,
	role                  INTEGER NULL,
	pos                   INTEGER NULL,
	department            INTEGER NOT NULL,
	emp_status            INTEGER NULL,
	name                  VARCHAR(64) NULL
)
;



ALTER TABLE users
	ADD  PRIMARY KEY (id)
;



ALTER TABLE customer
	ADD FOREIGN KEY R_18 (status) REFERENCES dic(id)
;


ALTER TABLE customer
	ADD FOREIGN KEY R_19 (id_sub_sub_loc) REFERENCES sub_sub_location(id)
;



ALTER TABLE dic
	ADD FOREIGN KEY R_7 (id_parent) REFERENCES dic_dic(id)
;



ALTER TABLE info
	ADD FOREIGN KEY R_30 (id_project_step) REFERENCES project_steps(id)
;



ALTER TABLE loan
	ADD FOREIGN KEY R_28 (id_customer) REFERENCES customer(id)
;


ALTER TABLE loan
	ADD FOREIGN KEY R_29 (id_project_step) REFERENCES project_steps(id)
;



ALTER TABLE location
	ADD FOREIGN KEY R_17 (id_sub_loc) REFERENCES sub_location(id)
;



ALTER TABLE logs
	ADD FOREIGN KEY R_6 (user) REFERENCES users(id)
;



ALTER TABLE noti
	ADD FOREIGN KEY R_27 (id_step) REFERENCES steps(id)
;



ALTER TABLE project_steps
	ADD FOREIGN KEY R_20 (id_step) REFERENCES steps(id)
;


ALTER TABLE project_steps
	ADD FOREIGN KEY R_21 (department) REFERENCES dic(id)
;


ALTER TABLE project_steps
	ADD FOREIGN KEY R_22 (progress) REFERENCES dic(id)
;


ALTER TABLE project_steps
	ADD FOREIGN KEY R_23 (status) REFERENCES dic(id)
;


ALTER TABLE project_steps
	ADD FOREIGN KEY R_24 (user) REFERENCES users(id)
;


ALTER TABLE project_steps
	ADD FOREIGN KEY R_32 (id_customer) REFERENCES customer(id)
;



ALTER TABLE role_locations
	ADD FOREIGN KEY R_15 (id_role) REFERENCES dic(id)
;


ALTER TABLE role_locations
	ADD FOREIGN KEY R_16 (id_sub_sub_location) REFERENCES sub_sub_location(id)
;



ALTER TABLE role_pages
	ADD FOREIGN KEY R_13 (id_role) REFERENCES dic(id)
;


ALTER TABLE role_pages
	ADD FOREIGN KEY R_14 (id_page) REFERENCES pages(id)
;



ALTER TABLE so
	ADD FOREIGN KEY R_26 (id_customer) REFERENCES customer(id)
;



ALTER TABLE steps
	ADD FOREIGN KEY R_25 (department) REFERENCES dic(id)
;



ALTER TABLE sub_sub_location
	ADD FOREIGN KEY R_12 (id_sub_loc) REFERENCES sub_location(id)
;



ALTER TABLE users
	ADD FOREIGN KEY R_8 (role) REFERENCES dic(id)
;


ALTER TABLE users
	ADD FOREIGN KEY R_9 (department) REFERENCES dic(id)
;


ALTER TABLE users
	ADD FOREIGN KEY R_10 (pos) REFERENCES dic(id)
;


ALTER TABLE users
	ADD FOREIGN KEY R_11 (emp_status) REFERENCES dic(id)
;



