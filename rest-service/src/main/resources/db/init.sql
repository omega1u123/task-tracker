
create table t_task (
    id serial primary key,
    c_title varchar not null,
    c_description varchar,
    c_is_competed boolean not null,
    c_modified timestamp without time zone not null,
    c_user_id integer not null,
    foreign key (c_user_id) references t_user(id)
);

create table t_user (
    id serial primary key,
    c_email varchar not null unique,
    c_password varchar not null
);
