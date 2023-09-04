create sequence itm_id_seq start with 1 increment by 50;
create table items (
id bigint default nextval('itm_id_seq') not null,
created_at timestamp(6) with time zone,
league varchar(255) not null,
type varchar(255) not null,
item_name varchar(255) not null,
primary key (id)
);
