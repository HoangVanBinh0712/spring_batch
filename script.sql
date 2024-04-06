create table batch_job_execution_seq
(
    ID bigint auto_increment
        primary key
);

create table batch_job_instance
(
    JOB_INSTANCE_ID bigint       not null
        primary key,
    VERSION         bigint       null,
    JOB_NAME        varchar(100) not null,
    JOB_KEY         varchar(32)  not null
);

create table batch_job_execution
(
    JOB_EXECUTION_ID bigint        not null
        primary key,
    VERSION          bigint        null,
    JOB_INSTANCE_ID  bigint        not null,
    CREATE_TIME      timestamp     not null,
    START_TIME       timestamp     null,
    END_TIME         timestamp     null,
    STATUS           varchar(10)   null,
    EXIT_CODE        varchar(20)   null,
    EXIT_MESSAGE     varchar(2500) null,
    LAST_UPDATED     timestamp     null,
    constraint JOB_INSTANCE_EXECUTION_FK
        foreign key (JOB_INSTANCE_ID) references batch_job_instance (JOB_INSTANCE_ID)
);

create table batch_job_execution_context
(
    JOB_EXECUTION_ID   bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint JOB_EXEC_CTX_FK
        foreign key (JOB_EXECUTION_ID) references batch_job_execution (JOB_EXECUTION_ID)
);

create table batch_job_execution_params
(
    JOB_EXECUTION_ID bigint        not null,
    PARAMETER_NAME   varchar(100)  not null,
    PARAMETER_TYPE   varchar(100)  not null,
    PARAMETER_VALUE  varchar(2500) null,
    IDENTIFYING      char          not null,
    constraint JOB_EXEC_PARAMS_FK
        foreign key (JOB_EXECUTION_ID) references batch_job_execution (JOB_EXECUTION_ID)
);

create table batch_job_seq
(
    ID bigint auto_increment
        primary key
);

create table batch_step_execution
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    VERSION            bigint        not null,
    STEP_NAME          varchar(100)  not null,
    JOB_EXECUTION_ID   bigint        not null,
    CREATE_TIME        timestamp     not null,
    START_TIME         timestamp     null,
    END_TIME           timestamp     null,
    STATUS             varchar(10)   null,
    COMMIT_COUNT       bigint        null,
    READ_COUNT         bigint        null,
    FILTER_COUNT       bigint        null,
    WRITE_COUNT        bigint        null,
    READ_SKIP_COUNT    bigint        null,
    WRITE_SKIP_COUNT   bigint        null,
    PROCESS_SKIP_COUNT bigint        null,
    ROLLBACK_COUNT     bigint        null,
    EXIT_CODE          varchar(20)   null,
    EXIT_MESSAGE       varchar(2500) null,
    LAST_UPDATED       timestamp     null,
    constraint JOB_EXECUTION_STEP_FK
        foreign key (JOB_EXECUTION_ID) references batch_job_execution (JOB_EXECUTION_ID)
);

create table batch_step_execution_context
(
    STEP_EXECUTION_ID  bigint        not null
        primary key,
    SHORT_CONTEXT      varchar(2500) not null,
    SERIALIZED_CONTEXT text          null,
    constraint STEP_EXEC_CTX_FK
        foreign key (STEP_EXECUTION_ID) references batch_step_execution (STEP_EXECUTION_ID)
);

create table batch_step_execution_seq
(
    ID bigint auto_increment
        primary key
);

create table orders
(
    id            int auto_increment
        primary key,
    customer_id   int         not null,
    item_id       int         not null,
    item_name     varchar(50) null,
    item_price    int         not null,
    purchase_date datetime    null
);

create table user
(
    customer_id     int auto_increment
        primary key,
    customer_name   varchar(255) null,
    age             int          null,
    phone           varchar(20)  null,
    phone_no_hyphen varchar(20)  null
);

