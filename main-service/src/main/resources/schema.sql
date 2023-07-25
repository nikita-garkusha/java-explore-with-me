DROP TABLE IF EXISTS PUBLIC.USERS CASCADE;
DROP TABLE IF EXISTS PUBLIC.CATEGORIES CASCADE;
DROP TABLE IF EXISTS PUBLIC.COMPILATIONS CASCADE;
DROP TABLE IF EXISTS PUBLIC.LOCATIONS CASCADE;
DROP TABLE IF EXISTS PUBLIC.EVENTS CASCADE;
DROP TABLE IF EXISTS PUBLIC.COMPILATION_EVENTS CASCADE;
DROP TABLE IF EXISTS PUBLIC.REQUESTS CASCADE;

CREATE TABLE IF NOT EXISTS PUBLIC.USERS
(
    ID    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    NAME  VARCHAR(250)                                        NOT NULL,
    EMAIL VARCHAR(254) UNIQUE                                 NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.CATEGORIES
(
    ID   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    NAME VARCHAR(255) UNIQUE                                 NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.COMPILATIONS
(
    ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    PINNED BOOLEAN DEFAULT FALSE                               NOT NULL,
    TITLE  VARCHAR(50)                                         NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.LOCATIONS
(
    ID  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    LAT FLOAT                                               NOT NULL,
    LON FLOAT                                               NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.EVENTS
(
    ID                 BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    ANNOTATION         VARCHAR(2000)                                       NOT NULL,
    CATEGORY_ID        BIGINT                                              NOT NULL,
    CONFIRMED_REQUESTS INTEGER                                             NOT NULL,
    CREATED_ON         TIMESTAMP WITHOUT TIME ZONE                         NOT NULL,
    DESCRIPTION        VARCHAR(7000)                                       NOT NULL,
    EVENT_DATE         TIMESTAMP WITHOUT TIME ZONE                         NOT NULL,
    INITIATOR_ID       BIGINT                                              NOT NULL,
    LOCATION_ID        BIGINT                                              NOT NULL,
    PAID               BOOLEAN                                             NOT NULL,
    PARTICIPANT_LIMIT  INTEGER                                             NOT NULL,
    PUBLISHED_ON       TIMESTAMP WITHOUT TIME ZONE,
    REQUEST_MODERATION BOOLEAN                                             NOT NULL,
    STATE              VARCHAR(10)                                         NOT NULL,
    TITLE              VARCHAR(255)                                        NOT NULL,
    VIEWS              INTEGER DEFAULT 0,
    CONSTRAINT FK_CATEGORIES_EVENTS_ID
        FOREIGN KEY (CATEGORY_ID) REFERENCES PUBLIC.CATEGORIES (ID),
    CONSTRAINT FK_USERS_EVENTS_ID
        FOREIGN KEY (INITIATOR_ID) REFERENCES PUBLIC.USERS (ID),
    CONSTRAINT FK_LOCATIONS_EVENTS_ID
        FOREIGN KEY (LOCATION_ID) REFERENCES PUBLIC.LOCATIONS (ID)
);

CREATE TABLE IF NOT EXISTS PUBLIC.COMPILATION_EVENTS
(
    ID             BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    COMPILATION_ID BIGINT                                              NOT NULL,
    EVENT_ID       BIGINT                                              NOT NULL,
    CONSTRAINT FK_COMPILATION_COMPILATION_EVENTS_ID
        FOREIGN KEY (COMPILATION_ID) REFERENCES PUBLIC.COMPILATIONS (ID),
    CONSTRAINT FK_EVENTS_COMPILATION_EVENTS_ID
        FOREIGN KEY (EVENT_ID) REFERENCES PUBLIC.EVENTS (ID)
);

CREATE TABLE IF NOT EXISTS PUBLIC.REQUESTS
(
    ID           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    EVENT_ID     BIGINT                                              NOT NULL,
    REQUESTER_ID BIGINT                                              NOT NULL,
    STATUS       VARCHAR(10)                                         NOT NULL,
    CREATED      TIMESTAMP                                           NOT NULL,
    UNIQUE (EVENT_ID, REQUESTER_ID),
    CONSTRAINT FK_EVENTS_REQUESTS_ID
        FOREIGN KEY (EVENT_ID) REFERENCES PUBLIC.EVENTS (ID),
    CONSTRAINT FK_USERS_REQUESTS_ID
        FOREIGN KEY (REQUESTER_ID) REFERENCES PUBLIC.USERS (ID)
);

CREATE TABLE IF NOT EXISTS PUBLIC.COMMENTS
(
    ID        BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    TEXT      VARCHAR(255)                NOT NULL,
    AUTHOR_ID BIGINT                      NOT NULL,
    EVENT_ID  BIGINT                      NOT NULL,
    CREATED   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT FK_EVENTS_REQUESTS_ID
        FOREIGN KEY (EVENT_ID) REFERENCES PUBLIC.EVENTS (ID) ON DELETE CASCADE,
    CONSTRAINT FK_USERS_REQUESTS_ID
        FOREIGN KEY (AUTHOR_ID) REFERENCES PUBLIC.USERS (ID) ON DELETE CASCADE
);